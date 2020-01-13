package console.testing;

import player.Player;
import player.Position;
import stats.GoalieStats;
import stats.PlayerStats;
import stats.SkaterStats;

public class ConsoleUtilsForPlayers {
	
	public static String playerToString(final Player player) {
		if (player.getPosition() == Position.Goalie) {
			return goalieToString(player);
		} else {
			return skaterToString(player);
		}
	}
	
	private static String skaterToString(final Player skater) {
		String ret = "Id: " + skater.getId() + ", ";
		ret += "Name: " + skater.getFirstName() + " " + skater.getLastName() 
			+ ", #" + skater.getPlayerNumber() + ", Position: " + 
				skater.getPosition();
		for (PlayerStats season : skater.getStats()) {
			ret += "\n";
			final SkaterStats s = (SkaterStats)season;
			ret += skaterStatsToString(s);
		}
		return ret;
	}
	
	public static String skaterStatsToString(final SkaterStats season) {
		String ret = "";
		ret += "Season: " + season.getSeason() + ", ";
		ret += "Games: " + season.getPlayedGames() + ", ";
		ret += "Goals: " + season.getGoals() + ", ";
		ret += "Assists: " + season.getAssists() + ", ";
		ret += "Shot attempts: " + season.getShotAttempts() + ", ";
		ret += "Shots: " + season.getShots() + ", ";
		ret += "Missed shots: " + season.getMissedShots() + ", ";
		ret += "Blocked shots for: " + season.getBlockedShotsFor() + ", ";
		ret += "Blocks: " + season.getBlocks() + ", ";
		ret += "Takeaways: " + season.getTakeaways() + ", ";
		ret += "Giveaways: " + season.getGiveaways();
		//ret += "+/-: " + s.getPlusMinus();
		return ret;
	}
	
	private static String goalieToString(final Player goalie) {
		String ret = "Id: " + goalie.getId() + ", ";
		ret += "Name: " + goalie.getFirstName() + " " + goalie.getLastName() 
			+ ", ";
		ret += "#" + goalie.getPlayerNumber();
		for (PlayerStats season : goalie.getStats()) {
			ret += "\n";
			final GoalieStats s = (GoalieStats)season;
			ret += goalieStatsToString(s);
		}
		return ret;
	}
	
	public static String goalieStatsToString(final GoalieStats s) {
		String ret = "";
		ret += "Season: " + s.getSeason() + ", ";
		ret += "Games: " + s.getPlayedGames() + ", ";
		ret += "Wins: " + s.getWins() + ", ";
		ret += "Losses: " + s.getLosses() + ", ";
		ret += "Ot / So Losses: " + s.getOtSoLosses() + ", ";
		ret += "Shutouts: " + s.getShutOuts() + ", ";
		ret += "Saves: " + s.getSaves() + ", ";
		ret += "Goals against: " + s.getGoalsAgainst() + ", ";
		ret += "save%: " + Math.round(((double)s.getSaves() / 
				s.getShotsAgainst()) * 10000.0) / 100.0 + ", ";
		ret += "Assists: " + s.getAssists();
		return ret;
	}
	
	public static String skaterToCSVString(final Player skater,
			final String teamName) {
		String ret = "" + skater.getId();
		ret += ";" + teamName;
		ret += ";" + skater.getFirstName() + 
				";" + skater.getLastName() + 
				";" + skater.getPlayerNumber() + 
				";" + skater.getPosition();
		for (PlayerStats season : skater.getStats()) {
			final SkaterStats s = (SkaterStats)season;
			ret += skaterStatsToCSVString(s);
		}
		return ret;
	}
	
	public static String skaterStatsToCSVString(final PlayerStats s) {
		
		SkaterStats season = (SkaterStats)s;
		String ret = "";
		ret += ";" + season.getPlayedGames();
		ret += ";" + season.getGoals();
		ret += ";" + season.getAssists();
		ret += ";" + (season.getAssists() + season.getGoals());
		ret += ";" + season.getShotAttempts();
		ret += ";" + season.getShots();
		ret += ";" + season.getMissedShots();
		ret += ";" + season.getBlockedShotsFor();
		ret += ";" + season.getBlocks();
		ret += ";" + season.getTakeaways();
		ret += ";" + season.getGiveaways();
		ret += ";" + (season.getShots() > 0 ? season.getGoals() * 100.00 / season.getShots() : 0);
		ret += ";" + season.getIceTime();
		//ret += "+/-: " + s.getPlusMinus();
		ret = ret.replace('.', ',');
		return ret;
	}

	public static String goalieToCSVString(final Player goalie,
			final String teamName) {
		String ret = "" + goalie.getId();
		ret += ";" + teamName;
		ret += ";" + goalie.getFirstName() + 
				";" + goalie.getLastName() + 
				";" + goalie.getPlayerNumber() + 
				";" + goalie.getPosition();
		for (PlayerStats season : goalie.getStats()) {
			final GoalieStats s = (GoalieStats)season;
			ret += goalieStatsToCSVString(s);
		}
		return ret;
	}
	
	
	private static String goalieStatsToCSVString(GoalieStats s) {
		String ret = "";
		ret += ";" + s.getPlayedGames();
		ret += ";" + s.getWins();
		ret += ";" + s.getLosses();
		ret += ";" + s.getOtSoLosses();
		ret += ";" + s.getShutOuts();
		ret += ";" + s.getSaves();
		ret += ";" + s.getGoalsAgainst();
		ret += ";" + Math.round(((double)s.getSaves() / 
				s.getShotsAgainst()) * 10000.0) / 100.0;
		ret += ";" + s.getAssists();
		ret = ret.replace('.', ',');
		return ret;
	}
	
}

