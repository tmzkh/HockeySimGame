package player;

import java.util.Comparator;

public class SortByPlayerId implements Comparator<Player> {
	public int compare(final Player a, final Player b) {
		return a.getId() - b.getId();
	}
}
