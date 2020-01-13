package attributes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import utils.MySql.DataBase;

public class GoalieAttributes extends PlayerAttributes 
implements Cloneable {
	private int glove;
	private int stick;
	private int legs;
	
	private static int min = 1;
	private static int max = 10;
	
	/**
	 * @param playerId
	 * @param speed
	 * @param glove
	 * @param stick
	 * @param legs
	 */
	public GoalieAttributes(final int playerId, final int speed, 
			final int glove, final int stick, final int legs) {
		super(playerId, speed);
		if (glove <= max && glove >= min) {
			this.glove = glove;
		}
		if (stick <= max && stick >= min) {
			this.stick = stick;
		}
		if (legs <= max && legs >= min) {
			this.legs = legs;
		}
	}

	public int getGlove() {
		return glove;
	}
	public void setGlove(final int glove) {
		if (glove <= max && glove >= min) {
			this.glove = glove;
		}
	}
	public void incGlove() {
		if (this.glove < max) {
			this.glove++;
		}
	}
	public void decGlove() {
		if (this.glove > min) {
			this.glove--;
		}
	}

	public int getStick() {
		return stick;
	}
	public void setStick(final int stick) {
		if (stick <= max && stick >= min) {
			this.stick = stick;
		}
	}
	public void incStick() {
		if (this.stick < max) {
			this.stick++;
		}
	}
	public void decStick() {
		if (this.stick > min) {
			this.stick--;
		}
	}

	public int getLegs() {
		return legs;
	}
	public void setLegs(final int legs) {
		if (legs <= max && legs >= min) {
			this.legs = legs;
		}
	}
	public void incLegs() {
		if (this.legs < max) {
			this.legs++;
		}
	}
	public void decLegs() {
		if (this.legs > min) {
			this.legs--;
		}
	}
	
	@Override
	public GoalieAttributes clone() {
		GoalieAttributes ret = new GoalieAttributes(
				this.getPlayerId(), this.getSpeed(),
				this.glove, this.stick, this.legs);
		return ret;
	}
	
	/**
	 * @param id
	 * @return
	 */
	public static GoalieAttributes fetchAttributesByPlayerId(final int id) {
		DataBase db = new DataBase();
		String query = "SELECT * FROM goalieAttributes WHERE playerId = " + id;
		Connection con = null;
		Statement stmt = null;
		
		GoalieAttributes pa = null;
		
		try {
			con = db.connect();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				final int speed = rs.getInt(3);
				final int glove = rs.getInt(4);
				final int stick = rs.getInt(5);
				final int legs = rs.getInt(6);
				pa = new GoalieAttributes(id, speed, glove, stick, legs);
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
		return pa;
	}
	
	/**
	 * @param _teamId
	 * @return
	 */
	public static HashMap<Integer, GoalieAttributes> fetchPlayerAttributesByTeamId(
			final int _teamId) {
		DataBase db = new DataBase();
		String query = "SELECT players.teamId, goalieAttributes.* " + 
				"FROM goalieAttributes LEFT JOIN players " + 
				"ON players.id = goalieAttributes.playerId WHERE teamId = " + 
				_teamId;
		Connection con = null;
		Statement stmt = null;
		
		HashMap<Integer, GoalieAttributes> pal = new HashMap<Integer, GoalieAttributes>();
		
		try {
			con = db.connect();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				final int id = rs.getInt(3);
				final int speed = rs.getInt(4);
				final int glove = rs.getInt(5);
				final int stick = rs.getInt(6);
				final int legs = rs.getInt(7);
				final GoalieAttributes ga = new GoalieAttributes(id, speed, glove, stick, legs);
				pal.put(id, ga);
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
		return pal;
	}

}
