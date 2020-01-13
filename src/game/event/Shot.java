package game.event;

public class Shot extends Event {
	private int goalieId;
	
	public Shot(final EventIds eventIds, final int seconds) {
		super(EventType.Shot, eventIds.getTeamId(), 
				eventIds.getPlayerId(), seconds);
		this.goalieId = eventIds.getGoalieId();
	}

	public int getGoalieId() {
		return goalieId;
	}
	public void setGoalieId(final int goalieId) {
		this.goalieId = goalieId;
	}
	
	@Override
	public String toCSVString() {
		String ret = super.toCSVString();
		ret += ";0;0;0;" + goalieId + ";0";
		return ret;
	}
}
