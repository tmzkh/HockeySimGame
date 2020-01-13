package game.event.refactored;

import player.Player;

public class Goal extends Evnt {
	private int firstAssistId;
	private int secondAssistId;
	private int goalieId;
	
	/**
	 * @param forward
	 * @param firstAssist
	 * @param secondAssist
	 * @param goalie
	 * @param seconds
	 */
	public Goal(final Player forward, final Player firstAssist,
			final Player secondAssist, final Player goalie,
			final int seconds) {
		super(EvntType.Goal, forward.getTeamId(), 
				forward.getId(), seconds);
		this.firstAssistId = firstAssist.getId();
		this.secondAssistId = secondAssist.getId();
		this.goalieId = goalie.getId();
		super.getPlayerIdsForSet()[2] = firstAssist.getId();
		super.getPlayerIdsForSet()[3] = secondAssist.getId();
		super.getPlayerIdsForSet()[4] = goalie.getId();
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
