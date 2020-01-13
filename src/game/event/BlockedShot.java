package game.event;

public class BlockedShot extends Event {
	private int shooterId;

	public BlockedShot(final EventIds eventIds, final int seconds) {
		super(EventType.BlockedShot, eventIds.getOtherTeamId(), 
				eventIds.getOtherPlayerId(), seconds);
		this.shooterId = eventIds.getPlayerId();
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
