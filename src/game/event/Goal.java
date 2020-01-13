package game.event;

public class Goal extends Event {
	private int firstAssistId;
	private int secondAssistId;
	private int goalieId;
	
	/**
	 * @param eventIds
	 * @param seconds
	 */
	public Goal(final EventIds eventIds, final int seconds) {
		super(EventType.Goal, eventIds.getTeamId(), 
				eventIds.getPlayerId(), seconds);
		this.firstAssistId = eventIds.getFirstAssistId();
		this.secondAssistId = eventIds.getSecondAssistId();
		this.goalieId = eventIds.getGoalieId();
	}

	public int getFirstAssistId() {
		return firstAssistId;
	}
	public void setFirstAssistId(final int firstAssistId) {
		this.firstAssistId = firstAssistId;
	}

	public int getSecondAssistId() {
		return secondAssistId;
	}
	public void setSecondAssistId(final int secondAssistId) {
		this.secondAssistId = secondAssistId;
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
		ret += ";0;" + firstAssistId + ";" + secondAssistId + ";" + goalieId + ";0";
		return ret;
	}
}
