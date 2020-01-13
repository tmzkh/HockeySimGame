package game.refactored;

import java.util.ArrayList;
import java.util.HashMap;

import console.testing.ConsoleUtilsForTeams;
import game.event.refactored.Evnt;
import game.event.refactored.EvntType;
import player.Player;
import stats.TeamStats;
import team.Team;

public class Perd {
	private int number;
	private PeriodType type;
	private ArrayList<Evnt> periodEvents;
	private Team home;
	private Team visitor;
	private int homeGoals;
	private int visitorGoals;
	
	public Perd(final int number, PeriodType type) {
		this.number = number;
		this.type = type;
		this.homeGoals = 0;
		this.visitorGoals = 0;
		this.periodEvents = new ArrayList<Evnt>();
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(final int number) {
		this.number = number;
	}
	
	public PeriodType getType() {
		return type;
	}
	public void setType(final PeriodType type) {
		this.type = type;
	}
	
	public int getHomeGoals() {
		return homeGoals;
	}
	public void setHomeGoals(final int homeGoals) {
		this.homeGoals = homeGoals;
	}
	public void incHomeGoals() {
		this.homeGoals += 1;
	}
	public void decHomeGoals() {
		this.homeGoals -= 1;
	}
	
	
	public int getVisitorGoals() {
		return visitorGoals;
	}
	public void setVisitorGoals(final int visitorGoals) {
		this.visitorGoals = visitorGoals;
	}
	public void incVisitorGoals() {
		this.visitorGoals += 1;
	}
	public void decVisitorGoals() {
		this.visitorGoals -= 1;
	}
	
	public int getHomeTeamId() {
		return this.home.getId();
	}
	
	public int getVisitorTeamId() {
		return this.visitor.getId();
	}
	
	/**
	 * for testing purposes only!!!
	 * @param home
	 * @param visitor
	 */
	public void setTeams(Team home, Team visitor) {
		this.home = home.clone();
		this.visitor = visitor.clone();
	}
	
	public TeamStats getHomeTeamStats() {
		//TODO fiksumpi ratkasu
		return home.getStats().get(0);
	}
	public void setHomeTeamStats(final TeamStats homeTeamStats) {
		this.home.addStats(homeTeamStats);
	}
	public TeamStats getVisitorTeamStats() {
		//TODO fiksumpi ratkasu
		return visitor.getStats().get(0);
	}
	public void setVisitorTeamStats(final TeamStats visitorTeamStats) {
		this.visitor.addStats(visitorTeamStats);
	}
	public ArrayList<Evnt> getPeriodEvents() {
		return periodEvents;
	}
	public void setPeriodEvents(final ArrayList<Evnt> periodEvents) {
		this.periodEvents = periodEvents;
	}
	
	public HashMap<Integer, Player> getHomeTeamPlayers() {
		return home.getPlayers();
	}
	
	public HashMap<Integer, Player> getVisitorTeamPlayers() {
		return visitor.getPlayers();
	}

	public String toCSVString() {
		
		String ret = "PeriodData:\r\n";
		ret += "Events:\r\n";
		
		ret += "\r\n";
		
		ret += ";Type;teamId;playerId;otherPlayerId;firstAssistId;secondAssistId;goalieId;otherTeamId\r\n";
		for (Evnt e : periodEvents) {
			ret += e.toCSVString();
			ret += "\r\n";
		}
		ret += "\r\n";
		ret += "\r\n";
		ret += "\r\n";
		ret += "\r\n";
		
		ret += "homeTeamStats:\r\n";
		ret += "\r\n";
		ret += "ID;Name;City;Games;Wins;Losses;OTW;OTL;PTS;SAF;SF;MS;BSF;SAA;SA;BS;GF;S%;GA;TAs;GAs";
		ret += "\r\n";
		ret += ";;;";
		ret += ConsoleUtilsForTeams.teamStatsToCSVString(home.getStats().get(0));
		ret += "\r\n";
		ret += "\r\n";
		
		ret += "visitorTeamStats:\r\n";
		ret += "\r\n";
		ret += "ID;Name;City;Games;Wins;Losses;OTW;OTL;PTS;SAF;SF;MS;BSF;SAA;SA;BS;GF;S%;GA;TAs;GAs";
		ret += "\r\n";
		ret += ";;;";
		ret += ConsoleUtilsForTeams.teamStatsToCSVString(visitor.getStats().get(0));
		ret += "\r\n";
		ret += "\r\n";
		
		ret += "playerStats:\r\n";
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
		
		ret += "PeriodData [homeGoals=" + homeGoals + ", visitorGoals=" + visitorGoals+"]";
	
		return ret;
	
	}
	
	public void simPeriod(Team home, Team visitor,
			int homeGoalieId, int visitorGoalieId) {
		this.home = home.cloneWOStats();
		this.visitor = visitor.cloneWOStats();
		
		this.home.createNewStatsForGame();
		this.visitor.createNewStatsForGame();
		
		this.home.changeLines(0);
		this.visitor.changeLines(0);
		
		for (int seconds = 1; seconds <= 1200; seconds++) {
			this.home.changeLines(seconds);
			this.visitor.changeLines(seconds);
			incIceTimeForPlayers();

			if (seconds > 0 && seconds % 32 == 0) {
				Evnt event = Evnt.drawEvent(this.home, this.visitor, 
						homeGoalieId, visitorGoalieId, seconds);
				
				this.home.recordEvent(event);
				this.visitor.recordEvent(event);
				
				if (event.getType() == EvntType.Goal) {
					if (event.getTeamId() == this.home.getId()) {
						this.homeGoals++;
					} else {
						this.visitorGoals++;
					}
				}
				
			}
		}
	}
	
	private void incIceTimeForPlayers() {
		this.home.incPlayersIceTime();
		this.visitor.incPlayersIceTime();
	}
}
