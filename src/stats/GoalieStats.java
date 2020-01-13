package stats;

import game.event.Event;
import game.event.Goal;
import game.event.Shot;
import game.event.refactored.Evnt;

public class GoalieStats extends PlayerStats implements Cloneable {
	private int shotsAgainst;
	private int saves;
	private int goalsAgainst;
	private int wins;
	private int losses;
	private int otSoLosses;
	private int shutOuts;
	

	/**
	 * @param playerId
	 * @param season
	 */
	public GoalieStats(final int playerId, final int season) {
		super(playerId, season);
		this.shotsAgainst = 0;
		this.saves = 0;
		this.goalsAgainst = 0;
	}
	public int getShotsAgainst() {
		return shotsAgainst;
	}
	public void setShotsAgainst(final int shotsAgainst) {
		this.shotsAgainst = shotsAgainst;
	}
	public void incShotsAgainst() {
		this.shotsAgainst++;
	}
	public void decShotsAgainst() {
		this.shotsAgainst--;
	}
	
	
	public int getSaves() {
		return saves;
	}
	public void setSaves(final int saves) {
		this.saves = saves;
	}
	public void incSaves() {
		this.saves++;
	}
	public void decSaves() {
		this.saves--;
	}
	
	
	public int getGoalsAgainst() {
		return goalsAgainst;
	}
	public void setGoalsAgainst(final int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}
	public void incGoalsAgainst() {
		this.goalsAgainst++;
	}
	public void decGoalsAgainst() {
		this.goalsAgainst--;
	}
	
	
	
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public void incWins() {
		this.wins++;
	}
	public void decWins() {
		this.wins--;
	}
	
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public void incLosses() {
		this.losses++;
	}
	public void decLosses() {
		this.losses--;
	}
	
	public int getOtSoLosses() {
		return otSoLosses;
	}
	public void setOtSoLosses(int otSoLosses) {
		this.otSoLosses = otSoLosses;
	}
	public void incOtSoLosses() {
		this.otSoLosses++;
	}
	public void decOtSoLosses() {
		this.otSoLosses--;
	}
	
	public int getShutOuts() {
		return shutOuts;
	}
	public void setShutOuts(int shutOuts) {
		this.shutOuts = shutOuts;
	}
	public void incShutOuts() {
		this.shutOuts++;
	}
	public void decShutOuts() {
		this.shutOuts--;
	}
	
	/**
	 * adds stats from game to player's stats
	 */
	public void addStats(final GoalieStats stats) {
		if (stats.getPlayerId() == this.getPlayerId() && 
				stats.getSeason() == this.getSeason()) {
			super.addStats(stats);
			this.shotsAgainst += stats.shotsAgainst;
			this.saves += stats.saves;
			this.goalsAgainst += stats.goalsAgainst;
			this.wins += stats.wins;
			this.losses += stats.losses;
			this.otSoLosses += stats.otSoLosses;
			this.shutOuts += stats.shutOuts;
		}
	}
	
	@Override
	public GoalieStats clone() {
		GoalieStats ret = new GoalieStats(this.getPlayerId(), 
        		this.getSeason());
        ret.setPlayedGames(this.getPlayedGames());
        ret.setShotAttempts(this.getShotAttempts());
        ret.setShotAttempts(this.getShots());
        ret.setMissedShots(this.getMissedShots());
        ret.setBlockedShotsFor(this.getBlockedShotsFor());
        ret.setGoals(this.getGoals());
        ret.setAssists(this.getAssists());
        ret.shotsAgainst = this.shotsAgainst;
    	ret.saves = this.saves;
    	ret.goalsAgainst = this.goalsAgainst;
    	ret.wins = this.wins;
    	ret.losses = this.losses;
    	ret.otSoLosses = this.otSoLosses;
    	ret.shutOuts = this.shutOuts;
        return ret;
	}
	
	/**
	 * @param event
	 * @return
	 */
	public void recordEvent(final Event event) {
		switch (event.getType()) {
			case Shot:
				final Shot s = (Shot)event;
				if (s.getGoalieId() == this.getPlayerId()) {
					this.recordSave();
				}
				break;
			case MissedShot:
				break;
			case BlockedShot:
				break;
			case Goal:				
				final Goal g = (Goal)event;
				if (g.getGoalieId() == this.getPlayerId()) {
					this.recordGoalAgainst();
				} else if (this.getPlayerId() == g.getFirstAssistId()
						|| this.getPlayerId() == g.getSecondAssistId()) {
					this.recordAssist();
				}
				break;
			case Takeaway:
				break;
			case Giveaway:
				break;
			default:
				System.out.println(event.toString());
				System.out.println("goaliestats playerid: " + this.getPlayerId());
				break;
		}
	}
	
	public void recordEvent(final Evnt event) {
		switch (event.getType()) {
			case Shot:
				final game.event.refactored.Shot s = (game.event.refactored.Shot)event;
				if (s.getGoalieId() == this.getPlayerId()) {
					this.recordSave();
				}
				break;
			case MissedShot:
				break;
			case BlockedShot:
				break;
			case Goal:				
				final game.event.refactored.Goal g = (game.event.refactored.Goal)event;
				if (g.getGoalieId() == this.getPlayerId()) {
					this.recordGoalAgainst();
				} else if (this.getPlayerId() == g.getFirstAssistId()
						|| this.getPlayerId() == g.getSecondAssistId()) {
					this.recordAssist();
				}
				break;
			case Takeaway:
				break;
			case Giveaway:
				break;
			default:
				System.out.println(event.toString());
				System.out.println("goaliestats playerid: " + this.getPlayerId());
				break;
		}
	}
	
	private void recordSave() {
		this.incShotsAgainst();
		this.incSaves();
	}
	
	private void recordGoalAgainst() {
		this.incShotsAgainst();
		this.incGoalsAgainst();
		}
	
	private void recordAssist() {
		this.incAssists();
	}
	
}
