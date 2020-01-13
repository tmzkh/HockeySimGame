package player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import attributes.GoalieAttributes;
import attributes.PlayerAttributes;
import attributes.SkaterAttributes;
import console.testing.ConsoleUtilsForPlayers;
import game.event.refactored.Evnt;
import stats.GoalieStats;
import stats.PlayerStats;
import stats.SkaterStats;
import utils.MySql.DataBase;

public class Player implements Cloneable {
	private int id;
	private int teamId;
	private int playerNumber;
	private String firstName;
	private String lastName;
	private ArrayList<PlayerStats> stats;
	private Position position;
	private PlayerAttributes attributes;
	
	/**
	 * @param id
	 * @param teamId
	 * @param playerNumber
	 * @param firstName
	 * @param lastName
	 * @param stats
	 */
	public Player(final int id, final int teamId, final int playerNumber, 
			final String firstName, final String lastName, final Position position) {
		//super();
		this.id = id;
		this.teamId = teamId;
		this.playerNumber = playerNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.stats = new ArrayList<PlayerStats>(0);
		this.position = position;
	}

	public int getId() {
		return id;
	}
	public void setId(final int id) {
		this.id = id;
	}

	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(final int teamId) {
		this.teamId = teamId;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}
	public void setPlayerNumber(final int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public ArrayList<PlayerStats> getStats() {
		return stats;
	}

	public void setStats(final ArrayList<PlayerStats> stats) {
		this.stats = stats;
	}

	/**
	 * @param season
	 * @return
	 */
	public PlayerStats getStatsBySeason(final int season) {
		for (PlayerStats ps : this.stats) {
			if (ps.getSeason() == season) {
				return ps;
			}
		}
		return null;
	}
	
	public void addStats(final PlayerStats stats) {
		if (stats.getPlayerId() != this.id) {
			System.out.println("player.addStats ei oikea id, player.id: " + this.id + ", stats.getPlayerId: " + stats.getPlayerId());
			return;
		}
		
		for (PlayerStats ps : this.stats) {
			if (ps.getSeason() == stats.getSeason()) {
				if (stats instanceof SkaterStats) {
					if (this.position == Position.Goalie) {
						System.out.println("wtf");
					}
					SkaterStats ss1 = (SkaterStats) ps;
					SkaterStats ss2 = (SkaterStats)stats;
					ss1.addStats(ss2);
					return;
				} else if (stats instanceof GoalieStats) {
					if (this.position != Position.Goalie) {
						System.out.println("wtf");
					}
					//XXX Here is sout!
					//System.out.println(ps.getClass() + " " + ps.getPlayerId());
					//System.out.println(stats.getClass() + " " + stats.getPlayerId());
					GoalieStats gs1 = (GoalieStats) ps;
					GoalieStats gs2 = (GoalieStats)stats;
					gs1.addStats(gs2);
					return;
				}
			}
		}
		
		this.stats.add(stats);
		
//		if (this.id == 19 || this.id == 20) {
//			//XXX here is sout
//			System.out.println("stats.playerId " + stats.getPlayerId() + " stats.class " + stats.getClass()
//			+ " player.id " + this.id + " player.stats.get(0) " + this.stats.get(0).getClass() + " stats.getPlayerId "
//			+ this.stats.get(0).getPlayerId());
//		}
		
		
	}
	
	/**
	 * Resets stats for player and adds new stats-object.
	 * Intended to be used only from Game or Period.
	 */
	public void createNewStatsForGame() {
		this.stats = new ArrayList<PlayerStats>();
		if (this.position == Position.Goalie) {
			this.stats.add(new GoalieStats(this.id, 1));
		} else {
			this.stats.add(new SkaterStats(this.id, 1));
		}
	}
	
	public void recEvent(Evnt event) {
		if (this.position != Position.Goalie) {
			SkaterStats ss = new SkaterStats(this.id, 1);
			ss.recordEvent(event);
			addStats(ss);
		} else {
			GoalieStats gs = new GoalieStats(this.id, 1);
			gs.recordEvent(event);
			addStats(gs);
		}
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(final Position position) {
		this.position = position;
	}

	public PlayerAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(
			final PlayerAttributes attributes) {
		this.attributes = attributes;
	}

	
	/**
	 * @param Skater id
	 * @return Skater by id
	 */
	public static Player fetchPlayerById(final int id) {
		DataBase db = new DataBase();
		String query = "SELECT * FROM players WHERE id = " + id;
		Connection con = null;
		Statement stmt = null;
		
		Player p = null;
		
		try {
			con = db.connect();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				final int _id = rs.getInt(1);
				final int teamId = rs.getInt(2);
				final int playerNumber = rs.getInt(3);
				final String firstName = rs.getString(4);
				final String lastName = rs.getString(5);
				final Position position = Position.valueOf(rs.getString(6));
				p = new Player(_id, teamId, playerNumber, firstName, lastName, position);
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
		return p;
	}
	
	/**
	 * @param _teamId
	 * @return
	 */
	public static ArrayList<Player> fetchPlayerByTeamId(final int _teamId) {
		DataBase db = new DataBase();
		String query = "SELECT * FROM players WHERE teamId = " + _teamId;
		Connection con = null;
		Statement stmt = null;
		
		ArrayList<Player> pl = new ArrayList<Player>(0);
		
		try {
			con = db.connect();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				final int _id = rs.getInt(1);
				final int teamId = rs.getInt(2);
				final int playerNumber = rs.getInt(3);
				final String firstName = rs.getString(4);
				final String lastName = rs.getString(5);
				final Position position = Position.valueOf(rs.getString(6));
				Player p = new Player(_id, teamId, playerNumber, firstName, lastName, position);
				pl.add(p);
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
		return pl;
	}
	
	public static HashMap<Integer, Player> fetchPlayersByTeamId (final int _teamId) {
		DataBase db = new DataBase();
		String query = "SELECT * FROM players WHERE teamId = " + _teamId;
		Connection con = null;
		Statement stmt = null;
		
		HashMap<Integer, Player> pl = new HashMap<Integer, Player>();
		
		try {
			con = db.connect();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				final int _id = rs.getInt(1);
				final int teamId = rs.getInt(2);
				final int playerNumber = rs.getInt(3);
				final String firstName = rs.getString(4);
				final String lastName = rs.getString(5);
				final Position position = Position.valueOf(rs.getString(6));
				Player p = new Player(_id, teamId, playerNumber, firstName, lastName, position);
				pl.put(_id, p);
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
		return pl;
	}
	
	@Override
 	public Player clone() {
		Player ret = new Player(this.id, this.teamId, this.playerNumber,
				this.firstName, this.lastName, this.position); 
		if (this.position == Position.Goalie) {
			this.stats.forEach(s -> {
				GoalieStats gs = (GoalieStats)s;
				ret.stats.add(gs);
			});
			if (this.attributes != null) {
				GoalieAttributes ga = (GoalieAttributes)this.attributes;
				ret.attributes = ga.clone();
			}
			
		} else {
			this.stats.forEach(s -> {
				SkaterStats ss = (SkaterStats)s;
				ret.stats.add(ss);
			});
			SkaterAttributes sa = (SkaterAttributes)this.attributes;
			ret.attributes = sa.clone();
		}
		return ret;
	}

	public Player cloneWOStats() {
		Player ret = new Player(this.id, this.teamId, this.playerNumber,
				this.firstName, this.lastName, this.position); 
		if (this.position == Position.Goalie) {
			if (this.attributes != null) {
				GoalieAttributes ga = (GoalieAttributes)this.attributes;
				ret.attributes = ga.clone();
			}
		} else if (this.position != Position.Goalie) {
			SkaterAttributes sa = (SkaterAttributes)this.attributes;
//			System.out.println("---------------------------------------");
//			System.out.println(sa.toCSVString());
			ret.attributes = sa.clone();
		} else {
			System.out.println("cloneWOStats failed on id: " + this.id);
		}
		return ret;
	}
	
}
