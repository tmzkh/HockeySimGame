package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import attributes.SkaterAttributes;
import console.testing.ConsoleUtilsForTeams;
import line.Line;
import player.Player;
import player.Position;
import player.SortByPlayerId;
import stats.GoalieStats;
import stats.SkaterStats;
import stats.TeamStats;

public class Game {
	private int id;
	private int homeTeamId;
	private int visitorTeamId;
	private int homeTeamGoals;
	private int visitorTeamGoals;
	ArrayList<Player> homeTeamPlayers;
	ArrayList<Player> visitorTeamPlayers;
	private ArrayList<Period> periods;
	private TeamStats homeTeamStats;
	private TeamStats visitorTeamStats;
	private int winnerId;

	
	/**
	 * @param homeTeamId
	 * @param visitorTeamId
	 * @param homeTeamPlayers
	 * @param visitorTeamPlayers
	 */

	public Game(final int homeTeamId, final int visitorTeamId,
			final HashMap<Integer, Player> homeTeamPlayers,
			final HashMap<Integer, Player> visitorTeamPlayers) {
		this.id = 0;
		this.homeTeamId = homeTeamId;
		this.visitorTeamId = visitorTeamId;
		this.homeTeamGoals = 0;
		this.visitorTeamGoals = 0;
		this.periods = new ArrayList<Period>(3);
		this.homeTeamStats = new TeamStats(homeTeamId, 1);
		this.visitorTeamStats = new TeamStats(visitorTeamId, 1);
		
		setHomeTeamPlayers(homeTeamPlayers);
		setVisitorTeamPlayers(visitorTeamPlayers);

		Collections.sort(this.homeTeamPlayers, new SortByPlayerId());
		Collections.sort(this.visitorTeamPlayers, new SortByPlayerId());
	}
	
	private void setHomeTeamPlayers(
			final HashMap<Integer, Player> homeTeamPlayers) {
		this.homeTeamPlayers = new ArrayList<Player>();
		homeTeamPlayers.forEach((id, player) -> {
			Player p = player.cloneWOStats();
			if (player.getPosition() == Position.Goalie) {
				GoalieStats gs = new GoalieStats(id, 1);
				p.addStats(gs);
			} else {
				SkaterStats ss = new SkaterStats(id, 1);
				p.addStats(ss);
			}
			this.homeTeamPlayers.add(p);
		});
	}
	
	private void setVisitorTeamPlayers(
			final HashMap<Integer, Player> visitorTeamPlayers) {
		this.visitorTeamPlayers = new ArrayList<Player>();
		visitorTeamPlayers.forEach((id, player) -> {
			Player p = player.cloneWOStats();
			if (player.getPosition() == Position.Goalie) {
				GoalieStats gs = new GoalieStats(p.getId(), 1);
				p.addStats(gs);
			} else {
				SkaterStats ss = new SkaterStats(p.getId(), 1);
				p.addStats(ss);
			}
			this.visitorTeamPlayers.add(p);
		});
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(final int id) {
		this.id = id;
	}

	public int getHomeTeamId() {
		return homeTeamId;
	}
	public void setHomeTeamId(final int homeTeamId) {
		this.homeTeamId = homeTeamId;
	}

	public int getVisitorTeamId() {
		return visitorTeamId;
	}

	public void setVisitorTeamId(final int visitorTeamId) {
		this.visitorTeamId = visitorTeamId;
	}

	public int getHomeTeamGoals() {
		return homeTeamGoals;
	}
	public void setHomeTeamGoals(final int homeTeamGoals) {
		this.homeTeamGoals = homeTeamGoals;
	}
	public void addHomeTeamGoals(final int homeTeamGoals) {
		this.homeTeamGoals += homeTeamGoals;
	}
	public void incHomeTeamGoals() {
		this.homeTeamGoals += 1;
	}
	public void decHomeTeamGoals() {
		this.homeTeamGoals -= 1;
	}

	public int getVisitorTeamGoals() {
		return visitorTeamGoals;
	}
	public void setVisitorTeamGoals(final int visitorTeamGoals) {
		this.visitorTeamGoals = visitorTeamGoals;
	}
	public void addVisitorTeamGoals(final int visitorTeamGoals) {
		this.visitorTeamGoals += visitorTeamGoals;
	}
	public void incVisitorTeamGoals() {
		this.visitorTeamGoals += 1;
	}
	public void decVisitorTeamGoals() {
		this.visitorTeamGoals -= 1;
	}

	public ArrayList<Period> getPeriods() {
		return periods;
	}
	public void setPeriods(final ArrayList<Period> periods) {
		this.periods = periods;
	}
	public void addPeriods(final Period period) {
		this.periods.add(period);
	}

	public TeamStats getHomeTeamStats() {
		return homeTeamStats;
	}
	public void setHomeTeamStats(final TeamStats homeTeamStats) {
		this.homeTeamStats = homeTeamStats;
	}

	public TeamStats getVisitorTeamStats() {
		return visitorTeamStats;
	}
	public void setVisitorTeamStats(final TeamStats visitorTeamStats) {
		this.visitorTeamStats = visitorTeamStats;
	}

	public int getWinnerId() {
		return winnerId;
	}
	public void setWinnerId(int winnerId) {
		this.winnerId = winnerId;
	}	
	


	public ArrayList<Player> getHomeTeamPlayers() {
		return homeTeamPlayers;
	}
	public void setHomeTeamPlayers(
			final ArrayList<Player> homeTeamPlayers) {
		this.homeTeamPlayers = homeTeamPlayers;
	}
	public Player getHomeTeamPlayerById(final int playerId, 
			final int begin, final int end) {
		if (begin < end) {
			final int middle = begin + (end - begin) / 2;
			if (playerId < this.homeTeamPlayers.get(middle).getId()) {
				return getHomeTeamPlayerById(playerId, begin, middle);
			} else if (playerId > this.homeTeamPlayers.get(middle).getId()) {
				return getHomeTeamPlayerById(playerId, middle + 1, end);
			} else {
				return this.homeTeamPlayers.get(middle);
			}
		}
		return null;
	}


	public ArrayList<Player> getVisitorTeamPlayers() {
		return visitorTeamPlayers;
	}
	public void setVisitorTeamPlayers(
			final ArrayList<Player> visitorTeamPlayers) {
		this.visitorTeamPlayers = visitorTeamPlayers;
	}
	public Player getVisitorTeamPlayerById(final int playerId, 
			final int begin, final int end) {
		if (begin < end) {
			final int middle = begin + (end - begin) / 2;
			if (playerId < this.visitorTeamPlayers.get(middle).getId()) {
				return getVisitorTeamPlayerById(playerId, begin, middle);
			} else if (playerId > this.visitorTeamPlayers.get(middle).getId()) {
				return getVisitorTeamPlayerById(playerId, middle + 1, end);
			} else {
				return this.visitorTeamPlayers.get(middle);
			}
		}
		return null;
	}

	public String toCSVString() {
		String ret = "";
		
		ret += "homeTeamStats:\r\n";
		ret += "\r\n";
		ret += "ID;Name;City;Games;Wins;Losses;OTW;OTL;PTS;SAF;SF;MS;BSF;SAA;SA;BS;GF;S%;GA;TAs;GAs";
		ret += "\r\n";
		ret += ";;;";
		ret += ConsoleUtilsForTeams.teamStatsToCSVString(homeTeamStats);
		ret += "\r\n";
		ret += "\r\n";
		ret += "\r\n";
		ret += "ID;Team;FirstName;LastName;#;Position;Games;G;A;PTS;SA;S;MS;BSF;BS;TAs;GAs;S%;";
		ret += "\r\n";
		
		String[] value = new String[40];
		
		//AtomicInteger i = new AtomicInteger(0);
				
		for (String s : value) {
			if (s != null) {
				ret += s;
				ret += "\r\n";
			}
		}
		
		ret += "\r\n";
		ret += "\r\n";
		ret += "visitorTeamStats:\r\n";
		ret += "\r\n";
		ret += "ID;Name;City;Games;Wins;Losses;OTW;OTL;PTS;SAF;SF;MS;BSF;SAA;SA;BS;GF;S%;GA;TAs;GAs";
		ret += "\r\n";
		ret += ";;;";
		ret += ConsoleUtilsForTeams.teamStatsToCSVString(visitorTeamStats);
		ret += "\r\n";
		ret += "\r\n";
		ret += "\r\n";
		ret += "ID;Team;FirstName;LastName;#;Position;Games;G;A;PTS;SA;S;MS;BSF;BS;TAs;GAs;S%;";
		ret += "\r\n";
		
		String[] values = new String[40];
		
		//AtomicInteger j = new AtomicInteger(0);
		
//		visitorPlayerStats.forEach((id, ps) -> {
//			if (ps instanceof SkaterStats) {
//				values[j.getAndAdd(1)] = "" + id + ";;;;;" +
//						ConsoleUtilsForPlayers.skaterStatsToCSVString((SkaterStats)ps);
//			}
//			
//		});
		
		for (String s : values) {
			if (s != null) {
				ret += s;
				ret += "\r\n";
			}
		}
		
		ret += "\r\n";
		ret += "\r\n";
		ret += "\r\n";
		ret += "-----------------------------------------------------------------------------------------------";
		ret += "\r\n";
		ret += "\r\n";
		ret += "\r\n";
		
		return ret;
		
	}

	public void simGame(final int homeId, 
			final int visitorId) {
		
		this.homeTeamId = homeId;
		this.visitorTeamId = visitorId;

		// init period variables
		PeriodVariables periodVariables = new PeriodVariables(
				0, homeId, visitorId);
		
		// fetch skater attributes and set them to periodVariables
		periodVariables.setHomeTeamSkaterAttributes(
				SkaterAttributes.fetchSkaterAttributesByTeamId(homeId));
		periodVariables.setVisitorTeamSkaterAttributes(
				SkaterAttributes.fetchSkaterAttributesByTeamId(visitorId));
		
		// draw goalies and set them to periodVariables
		periodVariables.setHomeGoalieId(
				GameUtils.getRandomGoalieId(
						this.homeTeamPlayers));
		periodVariables.setVisitorGoalieId(
				GameUtils.getRandomGoalieId(
						this.visitorTeamPlayers));

		// set team players to periodVariables
		periodVariables.setHomeTeamPlayers(this.homeTeamPlayers);
		periodVariables.setVisitorTeamPlayers(this.visitorTeamPlayers);
		
		periodVariables.setHomeFwdLines(
				Line.fetchForwardLinesByTeamId(homeId));
		periodVariables.setHomeDefLines(
				Line.fetchDefensePairingsByTeamId(homeId));
		periodVariables.setVisitorFwdLines(
				Line.fetchForwardLinesByTeamId(visitorId));
		periodVariables.setVisitorDefLines(
				Line.fetchDefensePairingsByTeamId(visitorId));

		int periodNumber = 0;
		
		do {
			periodNumber++;
			
			periodVariables.setPeriodNumber(periodNumber);
			
			// simulate period
			Period period = new Period(periodNumber, 
					((periodNumber <= 3) ? PeriodType.Regular : 
						PeriodType.Overtime));
			period.simulatePeriod(periodVariables);
			
			this.periods.add(period);
			
			// add goals
			this.addHomeTeamGoals(period.getHomeGoals());
			this.addVisitorTeamGoals(period.getVisitorGoals());
			
			// add team stats
			this.getHomeTeamStats().addTeamStats(
					period.getHomeTeamStats());
			this.getVisitorTeamStats().addTeamStats(
					period.getVisitorTeamStats());
			
			recPeriodStatsForPlayers(
					period.getHomeTeamPlayers(), 
					this.homeTeamId);
			recPeriodStatsForPlayers(
					period.getVisitorTeamPlayers(), 
					this.visitorTeamId);
			

			/*
			 * for debugging
			 */
			/*
			period.getPeriodEvents().forEach((e) -> {
				System.out.println(e.toCSVString());
			});
			*/
			
			// simulate at least 3 periods and from there to point where
			// the score is not even
		} while(periodNumber < 3 || 
				this.homeTeamGoals == this.visitorTeamGoals);

		// record played game for teams and players
		this.homeTeamStats.incGamesPlayed();
		this.visitorTeamStats.incGamesPlayed();
		
		recordPlayedGameForSkaters();
		
		recordWinsAndLosses(periodVariables.getHomeGoalieId(),
				periodVariables.getVisitorGoalieId());

	}
	
	private void recPeriodStatsForPlayers(
			final HashMap<Integer, Player> players,
			final int teamId) {
		
		ArrayList<Player> list = null;
		
		if (teamId == this.homeTeamId) {
			list = this.homeTeamPlayers;
		} else {
			list = this.visitorTeamPlayers;
		}

		for (Player player : list) {
			if (player.getPosition() == Position.Goalie) {
				GoalieStats periodGs = (GoalieStats)players
						.get(player.getId()).getStatsBySeason(1);
				player.addStats(periodGs);
			} else {
				SkaterStats periodSs = (SkaterStats)players
						.get(player.getId()).getStatsBySeason(1);
				player.addStats(periodSs);
			}
		}

	}

	private void recordPlayedGameForSkaters() {
		for (Player player : this.homeTeamPlayers) {
			if (player.getPosition() != Position.Goalie) {
				SkaterStats sa = new SkaterStats(player.getId(), 1);
				sa.incPlayedGames();
				player.addStats(sa);
			}
		}

		for (Player player : this.visitorTeamPlayers) {
			if (player.getPosition() != Position.Goalie) {
				SkaterStats sa = new SkaterStats(player.getId(), 1);
				sa.incPlayedGames();
				player.addStats(sa);
			}
		}
	}

	private void recordWinsAndLosses(final int homeGoalieId,
			final int visitorGoalieId) {
		// record wins and losses for teams and goalies
		if (this.homeTeamGoals > this.visitorTeamGoals) {
			recordHomeWin(homeGoalieId, visitorGoalieId);
		} else if (this.homeTeamGoals < this.visitorTeamGoals) {
			recordVisitorWin(homeGoalieId, visitorGoalieId);
		}
	}

	private void recordHomeWin(final int homeGoalieId,
			final int visitorGoalieId) {
		this.winnerId = this.homeTeamId;
		// inc home goalie wins
		recordHomeGoalieWin(homeGoalieId);
		// if game ended in regulation
		if (periods.size() == 3) {
			this.homeTeamStats.incWins();
			this.visitorTeamStats.incLosses();
			recordVisitorGoalieRegulationLoss(visitorGoalieId);
			// else if game ended in overtime
		} else if (periods.size() > 3) {
			this.homeTeamStats.incOvertimeWins();
			this.visitorTeamStats.incOtSoLosses();
			recordVisitorGoalieOTLoss(visitorGoalieId);
		}
	}
	
	private void recordHomeGoalieWin(final int homeGoalieId) {
		Player goalie = this.getHomeTeamPlayerById(homeGoalieId, 0, 
				this.homeTeamPlayers.size());
		GoalieStats gs = new GoalieStats(homeGoalieId, 1);
		gs.incPlayedGames();
		gs.incWins();
		goalie.addStats(gs);
	}
	
	private void recordVisitorGoalieRegulationLoss(final int visitorGoalieId) {
		Player goalie = this.getVisitorTeamPlayerById(visitorGoalieId, 0,
				this.visitorTeamPlayers.size());
		GoalieStats gs = new GoalieStats(visitorGoalieId, 1);
		gs.incPlayedGames();
		gs.incLosses();
		goalie.addStats(gs);
	}
	
	private void recordVisitorGoalieOTLoss(final int visitorGoalieId) {
		Player goalie = this.getVisitorTeamPlayerById(visitorGoalieId, 0,
				this.visitorTeamPlayers.size());
		GoalieStats gs = new GoalieStats(visitorGoalieId, 1);
		gs.incPlayedGames();
		gs.incOtSoLosses();
		goalie.addStats(gs);
	}
	
	private void recordVisitorWin(final int homeGoalieId,
			final int visitorGoalieId) {
		this.winnerId = this.visitorTeamId;
		// incvisitor goalie wins
		recordVisitorGoalieWin(visitorGoalieId);
		this.winnerId = this.visitorTeamId;
		if (periods.size() == 3) {
			this.visitorTeamStats.incWins();
			this.homeTeamStats.incLosses();
			recordHomeGoalieRegulationLoss(homeGoalieId);
		} else if (periods.size() > 3) {
			this.visitorTeamStats.incOvertimeWins();
			this.homeTeamStats.incOtSoLosses();
			recordHomeGoalieOTLoss(homeGoalieId);
		}
	}

	private void recordVisitorGoalieWin(final int visitorGoalieId) {
		Player goalie = this.getVisitorTeamPlayerById(visitorGoalieId, 0,
				this.visitorTeamPlayers.size());
		GoalieStats gs = new GoalieStats(visitorGoalieId, 1);
		gs.incPlayedGames();
		gs.incWins();
		goalie.addStats(gs);
	}
	
	private void recordHomeGoalieRegulationLoss(final int homeGoalieId) {
		Player goalie = this.getHomeTeamPlayerById(homeGoalieId, 0, 
				this.homeTeamPlayers.size());
		GoalieStats gs = new GoalieStats(homeGoalieId, 1);
		gs.incPlayedGames();
		gs.incLosses();
		goalie.addStats(gs);
	}
	
	private void recordHomeGoalieOTLoss(final int homeGoalieId) {
		Player goalie = this.getHomeTeamPlayerById(homeGoalieId, 0, 
				this.homeTeamPlayers.size());
		GoalieStats gs = new GoalieStats(homeGoalieId, 1);
		gs.incPlayedGames();
		gs.incOtSoLosses();
		goalie.addStats(gs);
	}

}
