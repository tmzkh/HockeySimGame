package game.event.refactored;

import player.Player;

public class Takeaway extends Evnt {
	private int giverId;

	public Takeaway(final Player forward, final Player defenseman, 
			final int seconds) {
		super(EvntType.Takeaway, defenseman.getTeamId(),
				defenseman.getId(), seconds);
		this.giverId = forward.getId();
		super.getPlayerIdsForSet()[1] = forward.getId();
	}

	public int getGiverId() {
		return giverId;
	}
	public void setGiverId(final int giverId) {
		this.giverId = giverId;
	}

	@Override
	public String toCSVString() {
		String ret = super.toCSVString();
		ret += ";" + giverId + ";0;0;0;0";
		return ret;
	}
}
