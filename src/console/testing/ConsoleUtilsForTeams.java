package console.testing;

import stats.TeamStats;
import team.Team;

public class ConsoleUtilsForTeams {

	public static String teamToString(final Team t) {
		String ret = "Id: " + t.getId();
		ret += ", City: " + t.getCity();
		ret += ", Name: " + t.getName();
		for (TeamStats s : t.getStats()) {
			ret += "\n";
			ret += teamStatsToString(s);
		}
		return ret;
	}
	
	public static String teamStatsToString(final TeamStats s) {
		String ret = "";
		//ret += "Season: " + s.getSeason();
		ret += ", Games: " + s.getGamesPlayed();
		ret += ", Wins: " + s.getWins();
		ret += ", Losses: " + s.getLosses();
		ret += ", OT Wins: " + s.getOvertimeWins();
		//ret += ", SO Wins: " + s.getShootoutWins();
		ret += ", OT/SO Losses: " + s.getOtSoLosses();
		ret += ", Shot attempts for: " + s.getShotAttemptsFor();
		// ret += "Shot attempts for: " + s.getShotAttemptsFor();
		ret += ", Shots for: " + s.getShotsFor();
		ret += ", Missed shots: " + s.getMissedShots();
		ret += ", Blocked shots for: " + s.getBlockedShotsFor();
		ret += ", Shot attemps against: " + s.getShotAttemptsAgainst();
		ret += ", Shots against: " + s.getShotsAgainst();
		ret += ", Blocks: " + s.getBlocks();
		ret += ", Goals for: " + s.getGoalsFor();
		ret += ", S%: " + (float)s.getGoalsFor() / (float)s.getShotsFor();
		ret += ", Goals against: " + s.getGoalsAgainst();
		ret += ", Takeaways: " + s.getTakeaways();
		ret += ", Giveaways: " + s.getGiveaways();
		return ret;
	}
	
	public static String teamToCSVString(final Team t) {
		String ret = "" + t.getId();
		ret += ";" + t.getCity();
		ret += ";" + t.getName() + ";";
		for (TeamStats s : t.getStats()) {
			ret += teamStatsToCSVString(s);
		}
		return ret;
	}
	
	public static String teamStatsToCSVString(final TeamStats s) {
		String ret = "";
		//ret += "Season: " + s.getSeason();
		ret += s.getGamesPlayed();
		ret += ";" + s.getWins();
		ret += ";" + s.getLosses();
		ret += ";" + s.getOvertimeWins();
		ret += ";" + s.getOtSoLosses();
		ret += ";" + (2 * s.getWins() + s.getOvertimeWins() + s.getOtSoLosses());
		ret += ";" + s.getShotAttemptsFor();
		ret += ";" + s.getShotsFor();
		ret += ";" + s.getMissedShots();
		ret += ";" + s.getBlockedShotsFor();
		ret += ";" + s.getShotAttemptsAgainst();
		ret += ";" + s.getShotsAgainst();
		ret += ";" + s.getBlocks();
		ret += ";" + s.getGoalsFor();
		ret += ";" + (100.00 * s.getGoalsFor() / s.getShotsFor());
		ret += ";" + s.getGoalsAgainst();
		ret += ";" + s.getTakeaways();
		ret += ";" + s.getGiveaways() + ";";
		ret = ret.replace('.', ',');
		return ret;
	}
}
