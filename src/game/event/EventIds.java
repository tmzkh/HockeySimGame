package game.event;

import java.util.ArrayList;

import game.GameUtils;

public class EventIds {
	private int teamId,
	playerId,
	otherPlayerId,
	firstAssistId,
	secondAssistId,
	goalieId,
	otherTeamId;
	

	public EventIds() {
		this.playerId = 0;
		this.otherPlayerId = 0;
		this.firstAssistId = 0;
		this.secondAssistId = 0;
		this.goalieId = 0;
	}
	
	/**
	 * @param teamId
	 * @param playerId
	 * @param otherPlayerId
	 * @param firstAssistId
	 * @param secondAssistId
	 * @param goalieId
	 * @param otherTeamId
	 */
	public EventIds(final int teamId, final int playerId, 
			final int otherPlayerId, final int firstAssistId, 
			final int secondAssistId, final int goalieId,
			final int otherTeamId) {
		super();
		this.teamId = teamId;
		this.playerId = playerId;
		this.otherPlayerId = otherPlayerId;
		this.firstAssistId = firstAssistId;
		this.secondAssistId = secondAssistId;
		this.goalieId = goalieId;
		this.otherTeamId = otherTeamId;
	}



	public int getTeamId() {
		return this.teamId;
	}

	public void setTeamId(final int teamId) {
		this.teamId = teamId;
	}

	public int getPlayerId() {
		return this.playerId;
	}

	public void setPlayerId(final int playerId) {
		this.playerId = playerId;
	}

	public int getOtherPlayerId() {
		return otherPlayerId;
	}

	public void setOtherPlayerId(final int otherPlayerId) {
		this.otherPlayerId = otherPlayerId;
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

	public int getOtherTeamId() {
		return otherTeamId;
	}

	public void setOtherTeamId(final int otherTeamId) {
		this.otherTeamId = otherTeamId;
	}



	@Override
	public String toString() {
		return "EventIds [teamId=" + teamId + ", playerId=" + playerId + ", otherPlayerId=" + otherPlayerId
				+ ", firstAssistId=" + firstAssistId + ", secondAssistId=" + secondAssistId + ", goalieId=" + goalieId
				+ ", otherTeamId=" + otherTeamId + "]";
	}
	
	public String toCSVString() {
		return "EventIds;;" + teamId + ";" + playerId + ";" + otherPlayerId
				+ ";" + firstAssistId + ";" + secondAssistId + ";" + goalieId
				+ ";" + otherTeamId;
	}

	public int[] getEventPlayerIds() {
		int[] array = new int[5];
		
		array[0] = this.playerId;
		array[1] = this.otherPlayerId;
		array[2] = this.firstAssistId;
		array[3] = this.secondAssistId;
		array[4] = this.goalieId;
		
		return array;
	}

	
	public void setEventIds(final int teamId, 
			final int otherTeamId, 
			final ArrayList<Integer> team1FwdLine,
			final ArrayList<Integer> team1DefLine,
			final ArrayList<Integer> team2FwdLine, 
			final ArrayList<Integer> team2DefLine, 
			final int goalieId) {
		this.teamId = teamId;
		this.playerId = 
				GameUtils.getRandomPlayerId(team1FwdLine);
		this.otherPlayerId = 
				GameUtils.getRandomPlayerId(team2DefLine);
		final ArrayList<Integer> teamPlayers = new ArrayList<Integer>();
		teamPlayers.addAll(team1FwdLine);
		teamPlayers.addAll(team1DefLine);
		do {
			this.firstAssistId = 
					GameUtils.getRandomPlayerId(teamPlayers);
		} while(this.firstAssistId == this.playerId);
		do {
			this.secondAssistId = 
					GameUtils.getRandomPlayerId(teamPlayers);
		} while(this.secondAssistId == this.firstAssistId || 
				this.secondAssistId == this.playerId);
		
		this.goalieId = goalieId;
		this.otherTeamId = otherTeamId;
	}
}
