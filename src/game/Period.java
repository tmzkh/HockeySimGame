package game;

import java.util.ArrayList;
import java.util.HashMap;

import attributes.SkaterAttributes;
import console.testing.ConsoleUtilsForTeams;
import game.event.Event;
import game.event.EventIds;
import game.event.EventType;
import player.Player;
import player.Position;
import stats.GoalieStats;
import stats.SkaterStats;
import stats.TeamStats;

public class Period {
	private int number;
	private PeriodType type;
	private ArrayList<Event> periodEvents;
	private TeamStats homeTeamStats;
	private TeamStats visitorTeamStats;
	private HashMap<Integer, Player> homeTeamPlayers;
	private HashMap<Integer, Player> visitorTeamPlayers;
	private int homeGoals;
	private int visitorGoals;
	
	public Period(final int number, PeriodType type) {
		this.number = number;
		this.type = type;
		this.homeGoals = 0;
		this.visitorGoals = 0;
		this.periodEvents = new ArrayList<Event>();
		this.homeTeamPlayers = new HashMap<Integer, Player>();
		this.visitorTeamPlayers = new HashMap<Integer, Player>();
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
	public ArrayList<Event> getPeriodEvents() {
		return periodEvents;
	}
	public void setPeriodEvents(final ArrayList<Event> periodEvents) {
		this.periodEvents = periodEvents;
	}
	
	public HashMap<Integer, Player> getHomeTeamPlayers() {
		return homeTeamPlayers;
	}
	public void setHomeTeamPlayers(
			final ArrayList<Player> homeTeamPlayers) {
		homeTeamPlayers.forEach(player -> {
			Player plr = player.cloneWOStats();
			if (plr.getPosition() == Position.Goalie) {
				plr.addStats(new GoalieStats(plr.getId(), 1));
			} else {
				plr.addStats(new SkaterStats(plr.getId(), 1));
			}
			this.homeTeamPlayers.put(player.getId(), plr);
			
		});
	}

	public HashMap<Integer, Player> getVisitorTeamPlayers() {
		return visitorTeamPlayers;
	}
	public void setVisitorTeamPlayers(
			final ArrayList<Player> visitorTeamPlayers) {
		visitorTeamPlayers.forEach(player -> {
			Player plr = player.cloneWOStats();
			if (plr.getPosition() == Position.Goalie) {
				plr.addStats(new GoalieStats(plr.getId(), 1));
			} else {
				plr.addStats(new SkaterStats(plr.getId(), 1));
			}
			this.visitorTeamPlayers.put(plr.getId(), plr);
		});
	}

	public String toCSVString() {
		
		String ret = "PeriodData:\r\n";
		ret += "Events:\r\n";
		
		ret += "\r\n";
		
		ret += ";Type;teamId;playerId;otherPlayerId;firstAssistId;secondAssistId;goalieId;otherTeamId\r\n";
		for (Event e : periodEvents) {
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
		ret += ConsoleUtilsForTeams.teamStatsToCSVString(homeTeamStats);
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

	public void simulatePeriod(
			final PeriodVariables periodVariables) {

		// create team stats for period
		this.homeTeamStats = new TeamStats(
				periodVariables.getHomeId(), 1);
		this.visitorTeamStats = new TeamStats(
				periodVariables.getVisitorId(), 1);
		
		setHomeTeamPlayers(periodVariables.getHomeTeamPlayers());
		setVisitorTeamPlayers(periodVariables.getVisitorTeamPlayers());
		
		ArrayList<Integer> currentHomeFwdLine = null;
		ArrayList<Integer> currentHomeDefLine = null;
		ArrayList<Integer> currentVisitorFwdLine = null;
		ArrayList<Integer> currentVisitorDefLine = null;
		
		int homeFwdLineNumber = 1;
		int homeDefLineNumber = 1;
		int visitorFwdLineNumber = 3;
		int visitorDefLineNumber = 1;
		
		int homeFwdLineIceTime = fwdLineIceTime(homeFwdLineNumber, 0);
		int visitorFwdLineIceTime = fwdLineIceTime(visitorFwdLineNumber, 0);
		int homeDefLineIceTime = defLineIceTime(homeDefLineNumber, 0);
		int visitorDefLineIceTime = defLineIceTime(visitorDefLineNumber, 0);
		
		int[][] lineNumbersAndIceTimes = new int[4][2];
		lineNumbersAndIceTimes[0][0] = homeFwdLineNumber;
		lineNumbersAndIceTimes[0][1] = homeFwdLineIceTime;
		lineNumbersAndIceTimes[1][0] = homeDefLineNumber;
		lineNumbersAndIceTimes[1][1] = homeDefLineIceTime;
		lineNumbersAndIceTimes[2][0] = visitorFwdLineNumber;
		lineNumbersAndIceTimes[2][1] = visitorFwdLineIceTime;
		lineNumbersAndIceTimes[3][0] = visitorDefLineNumber;
		lineNumbersAndIceTimes[3][1] = visitorDefLineIceTime;
		
		// looping through time
		for (int seconds = 0; seconds < 1200; seconds++) {
			
//			for (int[] line : lineNumbersAndIceTimes) {
//				if (line[1] == seconds) {
//					line[0] = changeLine(line[0]);
//					line[1] = setLineIceTime(line[1]);
//				}
//			}

			// line changes
			if (seconds == homeFwdLineIceTime) {
				homeFwdLineNumber = setFwdLineNumber(homeFwdLineNumber);
				homeFwdLineIceTime = fwdLineIceTime(homeFwdLineNumber, seconds);
			}
			if (seconds == visitorFwdLineIceTime) {
				visitorFwdLineNumber 
					= setFwdLineNumber(visitorFwdLineNumber);
				visitorFwdLineIceTime 
					= fwdLineIceTime(visitorFwdLineNumber, seconds);
			}
			if (seconds == homeDefLineIceTime) {
				homeDefLineNumber = setDefLineNumber(homeDefLineNumber);
				homeDefLineIceTime = defLineIceTime(homeDefLineNumber, seconds);
			}
			if (seconds == visitorDefLineIceTime) {
				visitorDefLineNumber 
					= setDefLineNumber(visitorDefLineNumber);
				visitorDefLineIceTime 
					= defLineIceTime(visitorDefLineNumber, seconds);
			}
			
			currentHomeFwdLine = periodVariables.getHomeFwdLines()
					.get(homeFwdLineNumber);
			currentVisitorFwdLine = periodVariables.getVisitorFwdLines()
					.get(visitorFwdLineNumber);
			currentHomeDefLine = periodVariables.getHomeDefLines()
					.get(homeDefLineNumber);
			currentVisitorDefLine = periodVariables.getVisitorDefLines()
					.get(visitorDefLineNumber);
			
			incIceTimeForPlayers(currentHomeFwdLine);
			incIceTimeForPlayers(currentHomeDefLine);
			incIceTimeForPlayers(currentVisitorFwdLine);
			incIceTimeForPlayers(currentVisitorDefLine);
			
			// an event occurs
			if (seconds > 0 && seconds % 32 == 0) {
				
				EventIds eventIds = new EventIds();

				// draw teamId
				final int teamId = drawTeamId(
						periodVariables,
						currentHomeFwdLine,
						currentHomeDefLine,
						currentVisitorFwdLine,
						currentVisitorDefLine);
				
				SkaterAttributes skaterAttributes = null, 
						otherSkaterAttributes = null;
				
				// set eventIds
				if (teamId == periodVariables.getHomeId()) {
					eventIds.setEventIds(
							teamId, 
							periodVariables.getVisitorId(), 
							currentHomeFwdLine, 
							currentHomeDefLine,
							currentVisitorFwdLine,
							currentVisitorDefLine,
							periodVariables.getVisitorGoalieId()
							);
					skaterAttributes = periodVariables
							.getHomeTeamSkaterAttributes()
							.get(eventIds.getPlayerId());
					otherSkaterAttributes = periodVariables
							.getVisitorTeamSkaterAttributes()
							.get(eventIds.getOtherPlayerId());
				} else if (teamId == periodVariables.getVisitorId()) {
					eventIds.setEventIds(
							teamId, 
							periodVariables.getHomeId(), 
							currentVisitorFwdLine,
							currentVisitorDefLine,
							currentHomeFwdLine, 
							currentHomeDefLine,
							periodVariables.getHomeGoalieId()
							);
					skaterAttributes = periodVariables
							.getVisitorTeamSkaterAttributes()
							.get(eventIds.getPlayerId());
					otherSkaterAttributes = periodVariables
							.getHomeTeamSkaterAttributes()
							.get(eventIds.getOtherPlayerId());

				}
				
				Event event = new Event();
				// draw event
				event = event.drawEvent(seconds, eventIds, skaterAttributes, 
						otherSkaterAttributes);
				this.periodEvents.add(event);
	
				homeTeamStats.recordEventForTeam(event);
				visitorTeamStats.recordEventForTeam(event);
				
				// record goal for teams
				if (event.getType() == EventType.Goal) {
					if (eventIds.getTeamId() == periodVariables.getHomeId()) {
						this.incHomeGoals();
					} else if (
							eventIds.getTeamId() == periodVariables
							.getVisitorId()) {
						this.incVisitorGoals();
					}
				}
				
				recEventForPlayers(eventIds.getEventPlayerIds(), event);

				if (periodVariables.getPeriodNumber() > 3) {
					if (event.getType() == EventType.Goal) {
						break;
					}
				}
			}
		}
	}

	private int setFwdLineNumber(final int currentLineNumber) {
		if (currentLineNumber < 4) {
			return currentLineNumber + 1;
		} else {
			return 1;
		}
	}
	
	private int setDefLineNumber(final int currentLineNumber) {
		if (currentLineNumber < 3) {
			return currentLineNumber + 1;
		} else {
			return 1;
		}
	}
	
	private int fwdLineIceTime(final int currentLineNumber,
			final int currentTime) {
		switch (currentLineNumber) {
			case 1:
				return currentTime + 45;
			case 2:
				return currentTime + 40;
			case 3:
				return currentTime + 35;
			default:
				return currentTime + 30;
		}
	}
	
	private int defLineIceTime(final int currentLineNumber,
			final int currentTime) {
		switch (currentLineNumber) {
			case 1:
				return currentTime + 45;
			case 2:
				return currentTime + 40;
			default:
				return currentTime + 35;
		}
	}

	private int drawTeamId(final PeriodVariables periodVariables, 
			final ArrayList<Integer> homeFwdLine,
			final ArrayList<Integer> homeDefLine,
			final ArrayList<Integer> visitorFwdLine,
			final ArrayList<Integer> visitorDefLine) {
		
		int homeAttack = getTeamAttack(
				periodVariables.getHomeId(),
				homeFwdLine, homeDefLine,
				periodVariables);
		int visitorAttack = getTeamAttack(
				periodVariables.getVisitorId(),
				visitorFwdLine, visitorDefLine,
				periodVariables);
		
		int random = GameUtils.getRandomInt(1, 
				(homeAttack + visitorAttack));
		
		if (random <= homeAttack) {
			return periodVariables.getHomeId();
		} else {
			return periodVariables.getVisitorId();
		}
	}
	
	private int getTeamAttack(
			final int teamId,
			final ArrayList<Integer> fwdLine,
			final ArrayList<Integer> defLine,
			final PeriodVariables periodVariables) {
		
		int attack = 0;
		
		HashMap<Integer, Player> list = null;
		
		if (teamId == periodVariables.getHomeId()) {
			list = this.homeTeamPlayers;
		} else {
			list = this.visitorTeamPlayers;
		}
		
		for (int id : fwdLine) {
			SkaterAttributes sa = 
					(SkaterAttributes)list.get(id).getAttributes();
			attack += sa.getSpeed();
			attack += sa.getAttackIq();
			attack += sa.getPuckControl();
		}
		
		for (int id : defLine) {
			SkaterAttributes sa = 
					(SkaterAttributes)list.get(id).getAttributes();
			attack += sa.getSpeed();
			attack += sa.getAttackIq();
			attack += sa.getPuckControl();
		}
		return attack;
	}

	private void incIceTimeForPlayers(
			final ArrayList<Integer> playerIds) {
		for (int id : playerIds) {
			Player plr = null;
			// find player
			if (this.homeTeamPlayers.get(id) != null) {
				plr = this.homeTeamPlayers.get(id);
			} else {
				plr = this.visitorTeamPlayers.get(id);
			}
			// inc ice time
			SkaterStats ss = new SkaterStats(id, 1);
			ss.incIceTime();
			plr.addStats(ss);
		}
	}
	
	private void recEventForPlayers(final int[] eventIds,
			final Event event) {
		for (int id : eventIds) {
			if (id == 0) {
				continue;
			}
			Player plr = null;
			// find player
			if (this.homeTeamPlayers.get(id) != null) {
				plr = this.homeTeamPlayers.get(id);
			} else {
				plr = this.visitorTeamPlayers.get(id);
			}
			// record event for player
			if (plr.getPosition() == Position.Goalie) {
				GoalieStats gs = (GoalieStats)plr.getStatsBySeason(1);
				gs.recordEvent(event);
			} else {
				SkaterStats ss = (SkaterStats)plr.getStatsBySeason(1);
				ss.recordEvent(event);
			}
		}
	}
	
	
}
