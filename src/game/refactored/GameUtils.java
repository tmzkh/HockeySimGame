package game.refactored;

import java.util.ArrayList;
import java.util.HashMap;

import player.Player;
import player.Position;

public class GameUtils {
	/**
	 * @param min
	 * @param max
	 * @return random int between min and max
	 */
	public static int getRandomInt(final int min, final int max) {
		final int range = max - min + 1;
		return (int)(Math.random() * range) + min;
	}

	public static int drawTeamId(final int homeId, 
			final int visitorId) {
		final int y = getRandomInt(1, 2);
		if (y == 1) {
			return homeId;
		} else {
			return visitorId;
		}
	}
	
	public static int getRandomAttackerId(final ArrayList<Player> team) {
		for (int i = 0; i < 100; i++) {
			Player a = team.get(GameUtils
					.getRandomInt(0, team.size() - 1));
			if (a.getPosition() == Position.Center || 
					a.getPosition() == Position.LeftWing || 
					a.getPosition() == Position.RightWing) {
				return a.getId();
			}
		}
		return 0;
	}
	
	public static int getRandomPlayerId(final int[] players) {
		return players[GameUtils.getRandomInt(0, players.length - 1)];
	}
	public static int getRandomPlayerId(final ArrayList<Integer> players) {
		return players.get(GameUtils.getRandomInt(0, players.size() - 1));
	}
	
	public static int getRandomSkaterId(final ArrayList<Player> team) {
		for (int i = 0; i < 100; i++) {
			Player a = team.get(
					GameUtils.getRandomInt(0, team.size() - 1));
			if (a.getPosition() != Position.Goalie) {
				return a.getId();
			}
		}
		return 0;
	}
	
	public static int getRandomGoalieId(final ArrayList<Player> team) {
		ArrayList<Integer> possibleValues = new ArrayList<Integer>(0);
		for (Player p : team) {
			if (p.getPosition() == Position.Goalie) {
				possibleValues.add(p.getId());
			}
		}
		int index = getRandomInt(0, possibleValues.size() - 1);
		return possibleValues.get(index);
	}
	
	public static int getRandomGoalieId(final HashMap<Integer, Player> team) {
		ArrayList<Integer> possibleValues = new ArrayList<Integer>(0);
		team.forEach((id, player)->{
			if (player.getPosition() == Position.Goalie) {
				possibleValues.add(id);
			}
		});
		int index = getRandomInt(0, possibleValues.size() - 1);
		return possibleValues.get(index);
	}
}
