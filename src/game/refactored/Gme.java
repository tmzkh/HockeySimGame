package game.refactored;

import java.util.ArrayList;
import java.util.HashMap;

import console.testing.ConsoleUtilsForTeams;
import console.testing.TestingUnits;
import player.Player;
import player.Position;
import stats.GoalieStats;
import stats.TeamStats;
import team.Team;

public class Gme {
	private int id;
	private Team homeTeam;
	private Team visitorTeam;
	private int homeTeamGoals;
	private int visitorTeamGoals;
	private ArrayList<Perd> perds;
	private int winnerId;

	
	/**
	 * @param homeTeamId
	 * @param visitorTeamId
	 * @param homeTeamPlayers
	 * @param visitorTeamPlayers
	 */

	public Gme(final Team homeTeam, final Team visitorTeam) {
		this.id = 0;
		this.homeTeamGoals = 0;
		this.visitorTeamGoals = 0;
		this.perds = new ArrayList<Perd>(3);
		this.homeTeam = homeTeam.cloneWOStats();
		this.visitorTeam = visitorTeam.cloneWOStats();
	}
	
	public int getId() {
		return id;
	}
	public void setId(final int id) {
		this.id = id;
	}

	public int getHomeTeamId() {
		return homeTeam.getId();
	}

	public int getVisitorTeamId() {
		return visitorTeam.getId();
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

	public ArrayList<Perd> getPeriods() {
		return perds;
	}
	public void setPeriods(final ArrayList<Perd> perds) {
		this.perds = perds;
	}
	public void addPeriods(final Perd perd) {
		this.perds.add(perd);
	}

	public int getWinnerId() {
		return winnerId;
	}
	public void setWinnerId(int winnerId) {
		this.winnerId = winnerId;
	}	
	
	public TeamStats getHomeTeamStats() {
		//TODO fiksumpi ratkasu
		return this.homeTeam.getStats().get(0);
	}
	
	public TeamStats getVisitorTeamStats() {
		//TODO fiksumpi ratkasu
		return this.visitorTeam.getStats().get(0);
	}
	
	public HashMap<Integer, Player> getHomeTeamPlayers() {
		return homeTeam.getPlayers();
	}
	
	public HashMap<Integer, Player> getVisitorTeamPlayers() {
		return visitorTeam.getPlayers();
	}
	
	public String toCSVString() {
		String ret = "";
		
		ret += "homeTeamStats:\r\n";
		ret += "\r\n";
		ret += "ID;Name;City;Games;Wins;Losses;OTW;OTL;PTS;SAF;SF;MS;BSF;SAA;SA;BS;GF;S%;GA;TAs;GAs";
		ret += "\r\n";
		ret += ";;;";
		ret += ConsoleUtilsForTeams.teamStatsToCSVString(this.homeTeam.getStats().get(0));
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
		ret += ConsoleUtilsForTeams.teamStatsToCSVString(this.visitorTeam.getStats().get(0));
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

	public void simGame(boolean testing) {
		
		this.homeTeam.createNewStatsForGame();
		this.visitorTeam.createNewStatsForGame();
		
		//System.out.println(homeTeam.getPlayerById(19).getStats().get(0));
		//System.out.println(homeTeam.getPlayerById(20).getStats().get(0));
		
		// draw goalies and set them to periodVariables
		int homeGoalieId = GameUtils.getRandomGoalieId(
						this.homeTeam.getPlayers());
		int visitorGoalieId = GameUtils.getRandomGoalieId(
						this.visitorTeam.getPlayers());

		int periodNumber = 0;
		
		TestingUnits tu = null;
		
		if (testing) {tu = new TestingUnits(this.homeTeam, this.visitorTeam, visitorGoalieId); }
		
		do {
			periodNumber++;
			
			// simulate period
			Perd period = new Perd(periodNumber, 
					((periodNumber <= 3) ? PeriodType.Regular : 
						PeriodType.Overtime));
			if (testing) {
				switch (periodNumber) {
					case 1:
						period = tu.p1;
						break;
					case 2:
						period = tu.p2;
						break;
					case 3:
						period = tu.p3;
						break;
				}
				
				
			} else {
				period.simPeriod(this.homeTeam, 
						this.visitorTeam,
						homeGoalieId, visitorGoalieId);
				
			}
			
			this.perds.add(period);
			
			// add goals
			this.addHomeTeamGoals(period.getHomeGoals());
			this.addVisitorTeamGoals(period.getVisitorGoals());
				
			this.homeTeam.recordPeriod(period);
			this.visitorTeam.recordPeriod(period);
			

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
		
		recordPlayedGameForSkaters();
		
		recordWinsAndLosses(homeGoalieId,
				visitorGoalieId);

	}
	
	
	private void recordPlayedGameForSkaters() {
		final ArrayList<Integer> ids = new ArrayList<Integer>();
		this.homeTeam.getPlayers().forEach((id, player) -> {
			if (player.getPosition() != Position.Goalie) {
				ids.add(id);
			}
		});
		this.homeTeam.incSkatersGamesPlayed(ids);
		final ArrayList<Integer> otherIds = new ArrayList<Integer>();
		this.visitorTeam.getPlayers().forEach((id, player) -> {
			if (player.getPosition() != Position.Goalie) {
				otherIds.add(id);
			}
		});
		this.visitorTeam.incSkatersGamesPlayed(otherIds);
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
		this.winnerId = this.homeTeam.getId();
		// inc home goalie wins
		this.homeTeam.addSinglePlayerStats(recodrGoalieWin(homeGoalieId));
		TeamStats homeTs = new TeamStats(homeTeam.getId(), 1);
		TeamStats visitorTs = new TeamStats(visitorTeam.getId(), 1);
		homeTs.incGamesPlayed();
		visitorTs.incGamesPlayed();
		// if game ended in regulation
		if (perds.size() == 3) {
			homeTs.incWins();
			visitorTs.incLosses();
			visitorTeam.addSinglePlayerStats(
					recordGoalieLoss(visitorGoalieId, true));
			// else if game ended in overtime
		} else if (perds.size() > 3) {
			homeTs.incOvertimeWins();
			visitorTs.incOtSoLosses();
			visitorTeam.addSinglePlayerStats(
					recordGoalieLoss(visitorGoalieId, false));
		}
		this.homeTeam.addStats(homeTs);
		this.visitorTeam.addStats(visitorTs);
	}
	
	private GoalieStats recodrGoalieWin(int goalieId) {
		GoalieStats gs = new GoalieStats(goalieId, 1);
		gs.incPlayedGames();
		gs.incWins();
		return gs;
	}
	
	private GoalieStats recordGoalieLoss(final int visitorGoalieId, 
			boolean regulation) {
		GoalieStats gs = new GoalieStats(visitorGoalieId, 1);
		gs.incPlayedGames();
		if (regulation) {
			gs.incLosses();
		} else {
			gs.incOtSoLosses();
		}
		return gs;
	}
	
	private void recordVisitorWin(final int homeGoalieId,
			final int visitorGoalieId) {
		this.winnerId = this.visitorTeam.getId();
		// incvisitor goalie wins
		this.visitorTeam.addSinglePlayerStats(
				recodrGoalieWin(visitorGoalieId));
		TeamStats homeTs = new TeamStats(homeTeam.getId(), 1);
		TeamStats visitorTs = new TeamStats(visitorTeam.getId(), 1);
		homeTs.incGamesPlayed();
		visitorTs.incGamesPlayed();
		if (perds.size() == 3) {
			visitorTs.incWins();
			homeTs.incLosses();
			homeTeam.addSinglePlayerStats(
					recordGoalieLoss(homeGoalieId, true));
		} else if (perds.size() > 3) {
			visitorTs.incOvertimeWins();
			homeTs.incOtSoLosses();
			homeTeam.addSinglePlayerStats(
					recordGoalieLoss(homeGoalieId, false));
		}
		this.homeTeam.addStats(homeTs);
		this.visitorTeam.addStats(visitorTs);
	}

}
