package team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import attributes.SkaterAttributes;
import game.event.refactored.Evnt;
import game.refactored.Perd;
import line.Line;
import player.Player;
import player.Position;
import stats.PlayerStats;
import stats.SkaterStats;
import stats.TeamStats;
import utils.MySql.DataBase;

public class Team {
	private int id;
	private int leagueId;
	private String city;
	private String name;
	private ArrayList<TeamStats> stats;
	private HashMap<Integer, Player> players;
	private HashMap<Integer, Line> forwardLines;
	private HashMap<Integer, Line> defenseLines;
	
	/**
	 * @param id
	 * @param leagueId
	 * @param city
	 * @param name
	 */
	public Team(final int id, final int leagueId, final String city, 
			final String name) {
		super();
		this.id = id;
		this.leagueId = leagueId;
		this.city = city;
		this.name = name;
		this.stats = new ArrayList<TeamStats>(0);
		this.players = new HashMap<Integer, Player>();
		this.forwardLines = new HashMap<Integer, Line>();
		this.defenseLines = new HashMap<Integer, Line>();
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public int getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(final int leagueId) {
		this.leagueId = leagueId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public ArrayList<TeamStats> getStats() {
		ArrayList<TeamStats> ret = new ArrayList<TeamStats>();
		this.stats.forEach(stat -> {
			ret.add(stat);
		});
		return ret;
	}

	public void setStats(final ArrayList<TeamStats> stats) {
		stats.forEach(stat -> {
			this.addStats(stat);
		});
	}
	
	public void addStats(
			final TeamStats stats) {
		if (stats.getTeamId() == this.id) {
			for (TeamStats season : this.stats) {
				if (season.getSeason() == stats.getSeason()) {
					season.addTeamStats(stats);
					return;
				}
			}
			this.stats.add(stats);
		}
	}
	
	/**
	 * @return HashMap of cloned players
	 */
	public HashMap<Integer, Player> getPlayers() {
		HashMap<Integer, Player> ret = new HashMap<Integer, Player>();
		this.players.forEach((id, player) -> {
			ret.put(id, player.clone());
		});
		return ret;
	}

	/**
	 * @param playerId
	 * @return clone of player
	 */
	public Player getPlayerById(final int playerId) {
		if (this.players.get(playerId) != null) {
			return this.players.get(playerId).clone();
		}
		return null;
	}
	
	/**
	 * @param playerId
	 * @return clone of player without stats
	 */
	public Player getPlayerByIdWOStats(final int playerId) {
		return this.players.get(playerId).cloneWOStats();
	}
	
	/**
	 * @return HashMap of cloned players without stats
	 */
	public HashMap<Integer, Player> getPlayersWOStats() {
		HashMap<Integer, Player> ret = new HashMap<Integer, Player>();
		this.players.forEach((id, player) -> {
			ret.put(id, player.cloneWOStats());
		});
		return ret;
	}
	
	/**
	 * clones HashMap of players to team
	 * @param players
	 */
	public void setPlayers(HashMap<Integer, Player> players) {
		players.forEach((id, player) -> {
			this.players.put(id, player.clone());
		});
	}
	
	/**
	 * clones HashMap of player to team without stats
	 * @param players
	 */
	public void setPlayersWOStats(HashMap<Integer, Player> players) {
		players.forEach((id, player) -> {
			this.players.put(id, player.cloneWOStats());
		});
	}
	
	/**
	 * Add stats to team's players from ArrayList of player
	 * @param players
	 */
	public void addPlayerStats(ArrayList<Player> players) {
		players.forEach(player -> {
			player.getStats().forEach(stat -> {
				if (this.players.get(player.getId()) != null) {
					this.players.get(player.getId()).addStats(stat);
				}
			});
		});
	}
	
	/**
	 * Add stats to team's players from HashMap of player
	 * @param players
	 */
	public void addPlayerStats(HashMap<Integer, Player> players) {
		players.forEach((id, player) -> {
			player.getStats().forEach(stat -> {
				if (this.players.get(id) != null) {
					//XXX Here is sout!
					//System.out.println("team.addPlayerStats " + this.players.get(id).getId() + " stats.playerId: " + stat.getPlayerId());
					this.players.get(id).addStats(stat);
				} else {
					System.out.println("addPlayerStats failed on id: " + id);
				}
			});
		});
	}
	
	public void addSinglePlayerStats(PlayerStats stats) {
		if (this.players.get(stats.getPlayerId()) != null) {
			//XXX Here is sout!
			//System.out.println("team.addSinglePlayerStats, id: " + stats.getPlayerId());
			//System.out.println(stats.getClass());
			this.players.get(stats.getPlayerId()).addStats(stats);
		}
	}
	
	public void recordEvent(Evnt event) {
		TeamStats ts = new TeamStats(this.id, 1);
		ts.recordEventForTeam(event);
		this.addStats(ts);
		this.recordEventForPlayers(event);
	}
	
	public void recordEventForPlayers(Evnt event) {
		for(int id : event.getPlayerIds()) {
			if (this.players.get(id) != null) {
				this.players.get(id).recEvent(event);
			}
		}
	}
	
	/**
	 * Resets stats for team and players and adds new stats-objects.
	 * Intended to be used only from Game or Period.
	 */
	public void createNewStatsForGame() {
		this.stats = new ArrayList<TeamStats>();
		stats.add(new TeamStats(this.id, 1));
		this.players.forEach((id, player)->{
			player.createNewStatsForGame();
		});
	}
	
	public void incSkatersGamesPlayed(ArrayList<Integer> playerIds) {
		playerIds.forEach((id) -> {
			if (this.players.get(id) != null && this.players.get(id).getPosition() != Position.Goalie) {
				SkaterStats stats = new SkaterStats(id, 1);
				stats.incPlayedGames();
				this.players.get(id).addStats(stats);
			}
		});
	}
	
	public void incPlayersIceTime() {
		getFwdLineOnIce().getPlayers().forEach(plrId -> {
			SkaterStats stats = new SkaterStats(plrId, 1);
			stats.incIceTime();
			this.players.get(plrId).addStats(stats);
		});
		getDefLineOnIce().getPlayers().forEach(plrId -> {
			SkaterStats stats = new SkaterStats(plrId, 1);
			stats.incIceTime();
			this.players.get(plrId).addStats(stats);
		});
	}
	
	/**
	 * @return clone of team's forward lines
	 */
	public HashMap<Integer, Line> getForwardLines() {
		HashMap<Integer, Line> lines = new HashMap<Integer, Line>();
		this.forwardLines.forEach((lineNmbr, line) -> {
			lines.put(lineNmbr, line.clone());
		});
		return lines;
	}
	
	/**
	 * @return current forward line on ice
	 */
	public Line getFwdLineOnIce() {
		for (int i = 1; i < 5; i++) {
			//System.out.println("getFwdLineOnIce " + forwardLines.get(i).getOnIce() + " teamid " + id);
			if (forwardLines.get(i) != null && forwardLines.get(i).getOnIce()) {
				return forwardLines.get(i);
			}
		}
		System.out.println("ei toimi");
		return null;
	}
	
	/**
	 * @return current def pairing on ice
	 */
	public Line getDefLineOnIce() {
		for (int i = 1; i < 5; i++) {
			if (defenseLines.get(i) != null && defenseLines.get(i).getOnIce()) {
				return defenseLines.get(i);
			}
		}
		return null;
	}
	
	/**
	 * changes lines on ice if it is time to change
	 */
	public void changeLines(int seconds) {
		changeFwdLine(seconds);
		changeDefLine(seconds);
	}

	private void changeFwdLine(int seconds) {
		if (seconds == 0) {
			forwardLines.get(1).changeOnIceStatus();
			forwardLines.get(1).setTimetoLeave(seconds);
			return;
		}
		for (int i = 1; i < 5; i++) {
			Line currentLine = forwardLines.get(i);
			if (currentLine != null && currentLine.getOnIce() &&
					currentLine.getTimeToLeave() == seconds) {
				currentLine.changeOnIceStatus();
				if (i == 4) {
					if (currentLine != null) {
						forwardLines.get(1).changeOnIceStatus();
						forwardLines.get(1).setTimetoLeave(seconds);
						return;
					}
				} else if (forwardLines.get(i+1) != null) {
					forwardLines.get(i+1).changeOnIceStatus();
					forwardLines.get(i+1).setTimetoLeave(seconds);
					return;
				}
			}
		}
	}
	
	private void changeDefLine(int seconds) {
		if (seconds == 0) {
			defenseLines.get(1).changeOnIceStatus();
			defenseLines.get(1).setTimetoLeave(seconds);
			return;
		}
		for (int i = 1; i < 4; i++) {
			Line currentLine = defenseLines.get(i);
			if (currentLine != null && currentLine.getOnIce() &&
					currentLine.getTimeToLeave() == seconds) {
				currentLine.changeOnIceStatus();
				if (i == 3) {
					if (currentLine != null) {
						defenseLines.get(1).changeOnIceStatus();
						defenseLines.get(1).setTimetoLeave(seconds);
						return;
					}
				} else if (defenseLines.get(i+1) != null) {
					defenseLines.get(i+1).changeOnIceStatus();
					defenseLines.get(i+1).setTimetoLeave(seconds);
					return;
				}
			}
		}
	}
	

	/**
	 * adds forward lines to team
	 * @param forwardLines
	 */
	public void setForwardLines(
			HashMap<Integer, Line> forwardLines) {
		forwardLines.forEach((number, line) -> {
			if (this.forwardLines.size() >= 4) {return;}
			this.forwardLines.put(number, line.clone());
		});
	}
	
	/**
	 * @return clone of team's defense lines
	 */
	public HashMap<Integer, Line> getDefenseLines() {
		HashMap<Integer, Line> lines = new HashMap<Integer, Line>();
		this.defenseLines.forEach((lineNmbr, line) -> {
			lines.put(lineNmbr, line.clone());
		});
		return lines;
	}
	
	/**
	 * adds defense lines to team
	 * @param forwardLines
	 */
	public void setDefenseLines(
			HashMap<Integer, Line> defenseLines) {
		defenseLines.forEach((number, line) -> {
			if (this.defenseLines.size() >= 3) {return;}
			this.defenseLines.put(number, line.clone());
		});
	}
	
	public SkaterAttributes getSkaterAttributesById(final int playerId) {
		return (SkaterAttributes) this.players.get(playerId).getAttributes();
	}
	
	public void recordPeriod(Perd period) {
		if (period.getHomeTeamId() == this.id) {
			this.addStats(period.getHomeTeamStats());
			this.addPlayerStats(period.getHomeTeamPlayers());
		} else {
			this.addStats(period.getVisitorTeamStats());
			this.addPlayerStats(period.getVisitorTeamPlayers());
		}
	}
	
	/**
	 * returns clone of team
	 */
	public Team clone() {
		Team ret = new Team(this.id, this.leagueId, this.city, this.name);
		this.stats.forEach(stat -> {
			ret.addStats(stat);
		});
		ret.setPlayers(this.players);
		ret.setForwardLines(this.forwardLines);
		ret.setDefenseLines(this.defenseLines);
		return ret;
	}
	
	/**
	 * @return clone of team without stats
	 */
	public Team cloneWOStats() {
		Team ret = new Team(this.id, this.leagueId, this.city, this.name);
		ret.setPlayersWOStats(this.players);
		ret.setForwardLines(this.forwardLines);
		ret.setDefenseLines(this.defenseLines);
		return ret;
	}
	
	/**
	 * @param Team
	 * @return id from db
	 */
	public static long insertTeam(final Team t) {
		DataBase db = new DataBase();
		String query = "insert into teams (city, name)";
		query += "values (?,?)";
		long id = 0;
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = db.connect();
			stmt = con.prepareStatement(query, 
			Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, t.getCity());
			stmt.setString(2, t.getName());
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				id = rs.getLong(1);
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
		return id;
	}
	
	/**
	 * @param id
	 * @return team by id
	 */
	public static Team fetchTeamById(final int id) {
		DataBase db = new DataBase();
		String query = "SELECT * FROM teams WHERE id = " + id;
		Connection con = null;
		Statement stmt = null;
		
		Team t = null;
		
		try {
			con = db.connect();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				final int _id = rs.getInt(1);
				final int leagueId = rs.getInt(2);
				final String city = rs.getString(3);
				final String name = rs.getString(4);
				t = new Team(_id, leagueId, city, name);
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
		//t.players = Player.fetchPlayersByTeamId(t.id);
		t.forwardLines = Line.fetchFwdLinesByTeamId(t.id);
		t.defenseLines = Line.fetchDefLinesByTeamId(t.id);

		return t;
	}
}
