package player;

import java.util.ArrayList;

public class PlayerFinder {
	private ArrayList<Player> players;
	
	public PlayerFinder (final ArrayList<Player> players) {
		this.players = players;
	}
	
	public Player getPlayerById(final int playerId,
			final int begin, final int end) {
		if (begin < end) {
			final int middle = begin + (end - begin) / 2;
			if (playerId < this.players.get(middle).getId()) {
				return getPlayerById(playerId, begin, middle);
			} else if (playerId > this.players.get(middle).getId()) {
				return getPlayerById(playerId, middle + 1, end);
			} else {
				return this.players.get(middle);
			}
		}
		return null;
	}
}
