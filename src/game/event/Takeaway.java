package game.event;

public class Takeaway extends Event {
	private int giverId;

	public Takeaway(final EventIds eventIds, 
			final int seconds) {
		super(EventType.Takeaway, eventIds.getOtherTeamId(), 
				eventIds.getOtherPlayerId(), seconds);
		this.giverId = eventIds.getPlayerId();
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
