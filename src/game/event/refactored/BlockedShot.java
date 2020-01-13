package game.event.refactored;

import player.Player;

public class BlockedShot extends Evnt {
	private int shooterId;

	public BlockedShot(final Player forward, final Player defenseman,
			final int seconds) {
		super(EvntType.BlockedShot, defenseman.getTeamId(), 
				defenseman.getId(), seconds);
		this.shooterId = forward.getId();
		super.getPlayerIdsForSet()[1] = forward.getId();
	}

	public int getShooterId() {
		return shooterId;
	}
	public void setShooterId(final int blockerId) {
		this.shooterId = blockerId;
	}

	@Override
	public String toCSVString() {
		String ret = super.toCSVString();
		ret += ";" + shooterId + ";0;0;0;0";
		return ret;
	}
}
