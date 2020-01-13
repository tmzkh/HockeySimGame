package line;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import utils.MySql.DataBase;

public class Line {
	private int id;
	private int lineNumber;
	private LineType type;
	private ArrayList<Integer> playerIds;
	private Boolean onIce;
	private int iceTime;
	private int timeToLeave;
	
	/**
	 * @param id
	 * @param lineNumber
	 * @param type
	 * @param playerIds
	 * @param onIce
	 * @param timeToLeave
	 */
	public Line(int id, int lineNumber, LineType type) {
		super();
		this.id = id;
		this.lineNumber = lineNumber;
		this.type = type;
		this.playerIds = new ArrayList<Integer>();
		this.onIce = false;
		this.timeToLeave = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * @param type
	 * @param players
	 */
	public Line(final LineType type, final ArrayList<Integer> players) {
		super();
		this.type = type;
		this.playerIds = players;
		this.onIce = false;
		this.timeToLeave = 0;
	}
	
	public Line() {
		
	}
	
	public LineType getType() {
		return type;
	}
	public void setType(LineType type) {
		this.type = type;
	}
	
	public ArrayList<Integer> getPlayers() {
		ArrayList<Integer> players = new ArrayList<Integer>(
				this.playerIds.size());
		for (int playerId : this.playerIds) {
			players.add(playerId);
		}
		return players;
	}
	public void setPlayers(ArrayList<Integer> players) {
		for (int playerId : players) {
			this.playerIds.add(playerId);
		}
	}
	
	public Boolean getOnIce() {
		return onIce;
	}
	public void changeOnIceStatus() {
		//System.out.println("changeOnIceStatus " + this.onIce + " player 1 id: " + playerIds.get(0));
		this.onIce = !this.onIce;
		//System.out.println("changeOnIceStatus " + this.onIce + " player 1 id: " + playerIds.get(0));
	}
	
	public int getIceTime() {
		return iceTime;
	}

	public void setIceTime(int iceTime) {
		this.iceTime = iceTime;
	}

	public int getTimeToLeave() {
		return timeToLeave;
	}
	public void setTimetoLeave(int seconds) {
		this.timeToLeave = seconds + this.iceTime;
	}
	
	public Line clone() {
		Line ret = new Line();
		ret.id = this.id;
		ret.lineNumber = this.lineNumber;
		ret.type = this.type;
		ret.playerIds = new ArrayList<Integer>();
		this.playerIds.forEach(plrId -> {
			ret.playerIds.add(plrId);
		});
		ret.onIce = false;
		ret.iceTime = this.iceTime;
		ret.timeToLeave = 0;
		return ret;
	}
	
	public static HashMap<Integer, Line> 
		fetchFwdLinesByTeamId(final int teamId) {
		
		DataBase db = new DataBase();
		String query = "SELECT * FROM forwardlines WHERE teamId = " + teamId;
		Connection con = null;
		Statement stmt = null;
		
		HashMap<Integer, Line> ret = new HashMap<Integer, Line>();
		
		try {
			con = db.connect();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			int lineNumber = 1;
			
			while (rs.next()) {
				ArrayList<Integer> players = new ArrayList<Integer>();
				final int id = rs.getInt(1);
				final int leftWingId = rs.getInt(3);
				final int centerId = rs.getInt(4);
				final int rightWingId = rs.getInt(5);
				players.add(leftWingId);
				players.add(centerId);
				players.add(rightWingId);
				Line line = new Line(id, lineNumber, LineType.ForwardLine);
				line.setPlayers(players);
				
				switch(lineNumber) {
					case 1:
						line.setIceTime(40);
						break;
					case 2:
						line.setIceTime(35);
						break;
					case 3:
						line.setIceTime(30);
						break;
					case 4:
						line.setIceTime(25);
						break;
				}
				
				ret.put(lineNumber++, line);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return ret;
		
	}
	
	public static HashMap<Integer, Line> 
		fetchDefLinesByTeamId(final int teamId) {
		
		DataBase db = new DataBase();
		String query = "SELECT * FROM defenselines WHERE teamId = " + teamId;
		Connection con = null;
		Statement stmt = null;
		
		HashMap<Integer, Line> ret = new HashMap<Integer, Line>();
		
		try {
			con = db.connect();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			int lineNumber = 1;
			
			while (rs.next()) {
				ArrayList<Integer> players = new ArrayList<Integer>(2);
				final int id = rs.getInt(1);
				final int leftDefensemanId = rs.getInt(3);
				final int rightDefensemanId = rs.getInt(4);
				players.add(leftDefensemanId);
				players.add(rightDefensemanId);
				Line line = new Line(id, lineNumber, LineType.DefensivePairing);
				line.setPlayers(players);
				switch(lineNumber) {
				case 1:
					line.setIceTime(45);
					break;
				case 2:
					line.setIceTime(40);
					break;
				case 3:
					line.setIceTime(35);
					break;
			}
				ret.put(lineNumber++, line);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return ret;
		
	}
	
	public static HashMap<Integer, ArrayList<Integer>> 
		fetchForwardLinesByTeamId(final int teamId) {
		
		DataBase db = new DataBase();
		String query = "SELECT * FROM forwardlines WHERE teamId = " + teamId;
		Connection con = null;
		Statement stmt = null;
		
		HashMap<Integer, ArrayList<Integer>> ret = new HashMap<Integer, ArrayList<Integer>>();
		
		try {
			con = db.connect();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			int lineNumber = 1;
			
			while (rs.next()) {
				ArrayList<Integer> line = new ArrayList<Integer>();
				final int leftWingId = rs.getInt(3);
				final int centerId = rs.getInt(4);
				final int rightWingId = rs.getInt(5);
				line.add(leftWingId);
				line.add(centerId);
				line.add(rightWingId);
				ret.put(lineNumber++, line);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return ret;
		
	}
	
	public static HashMap<Integer, ArrayList<Integer>> 
	fetchDefensePairingsByTeamId(final int teamId) {
	
	DataBase db = new DataBase();
	String query = "SELECT * FROM defenselines WHERE teamId = " + teamId;
	Connection con = null;
	Statement stmt = null;
	
	HashMap<Integer, ArrayList<Integer>> ret = new HashMap<Integer, ArrayList<Integer>>();
	
	try {
		con = db.connect();
		stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		int lineNumber = 1;
		
		while (rs.next()) {
			ArrayList<Integer> line = new ArrayList<Integer>();
			final int leftDefensemanId = rs.getInt(3);
			final int rightDefensemanId = rs.getInt(4);
			line.add(leftDefensemanId);
			line.add(rightDefensemanId);
			ret.put(lineNumber++, line);
		}			
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (stmt != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}		
	return ret;
	
}

}
