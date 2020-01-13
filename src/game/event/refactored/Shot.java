package game.event.refactored;

import player.Player;

public class Shot extends Evnt {
	private int goalieId;
	
	/**
	 * @param forward
	 * @param goalie
	 * @param seconds
	 */
	public Shot(final Player forward, final Player goalie,
			final int seconds) {
		super(EvntType.Shot, forward.getTeamId(), 
				forward.getId(), seconds);
		this.goalieId = goalie.getId();
		super.getPlayerIdsForSet()[4] = goalie.getId();
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
