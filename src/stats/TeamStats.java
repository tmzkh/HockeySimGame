package stats;

import game.event.Event;
import game.event.refactored.Evnt;

public class TeamStats {
	private int teamId;
	private int season;
	private int gamesPlayed;
	private int wins;
	private int losses;
	private int overtimeWins;
	private int shootoutWins;
	private int otSoLosses;
	private int shotAttemptsFor; 
	private int shotsFor;
	private int missedShots;
	private int blockedShotsFor;
	private int shotAttemptsAgainst;
	private int shotsAgainst;
	private int blocks;
	private int goalsFor;
	private int goalsAgainst;
	private int takeaways;
	private int giveaways;
	
	/**
	 * @param teamId
	 * @param season
	 */
	public TeamStats(final int teamId, final int season) {
		super();
		this.teamId = teamId;
		this.season = season;
		this.gamesPlayed = 0;
		this.wins = 0;
		this.losses = 0;
		this.overtimeWins = 0;
		this.shootoutWins = 0;
		this.otSoLosses = 0;
		this.shotAttemptsFor = 0;
		this.shotsFor = 0;
		this.missedShots = 0;
		this.blockedShotsFor = 0;
		this.shotAttemptsAgainst = 0;
		this.shotsAgainst = 0;
		this.blocks = 0;
		this.goalsFor = 0;
		this.goalsAgainst = 0;
		this.takeaways = 0;
		this.giveaways = 0;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(final int teamId) {
		this.teamId = teamId;
	}
	
	public int getSeason() {
		return season;
	}
	public void setSeason(final int season) {
		this.season = season;
	}
	
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	public void setGamesPlayed(final int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	public void incGamesPlayed() {
		this.gamesPlayed++;
	}
	public void decGamesPlayed() {
		this.gamesPlayed -= 1;
	}
	
	
	public int getWins() {
		return wins;
	}
	public void setWins(final int wins) {
		this.wins = wins;
	}
	public void incWins() {
		this.wins++;
	}
	public void decWins() {
		this.wins -= 1;
	}
	
	
	public int getLosses() {
		return losses;
	}
	public void setLosses(final int losses) {
		this.losses = losses;
	}
	public void incLosses() {
		this.losses++;
	}
	public void decLosses() {
		this.losses -= 1;
	}
	
	public int getOvertimeWins() {
		return overtimeWins;
	}
	public void setOvertimeWins(final int overtimeWins) {
		this.overtimeWins = overtimeWins;
	}
	public void incOvertimeWins() {
		this.overtimeWins++;
	}
	public void decOvertimeWins() {
		this.overtimeWins -= 1;
	}
	
	public int getShootoutWins() {
		return shootoutWins;
	}
	public void setShootoutWins(final int shootoutWins) {
		this.shootoutWins = shootoutWins;
	}
	public void incShootoutWins() {
		this.shootoutWins++;
	}
	public void decShootoutWins() {
		this.shootoutWins -= 1;
	}
	
	public int getOtSoLosses() {
		return otSoLosses;
	}
	public void setOtSoLosses(final int otSoLosses) {
		this.otSoLosses = otSoLosses;
	}
	public void incOtSoLosses() {
		this.otSoLosses++;
	}
	public void decOtSoLosses() {
		this.otSoLosses -= 1;
	}
	
	public int getShotsFor() {
		return shotsFor;
	}
	public void setShotsFor(final int shotsFor) {
		this.shotsFor = shotsFor;
	}
	public void incShotsFor() {
		this.shotsFor++;
	}
	public void decShotsFor() {
		this.shotsFor -= 1;
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
		this.shotsAgainst -= 1;
	}
	
	public int getGoalsFor() {
		return goalsFor;
	}
	public void setGoalsFor(final int goalsFor) {
		this.goalsFor = goalsFor;
	}
	public void incGoalsFor() {
		this.goalsFor++;
	}
	public void decGoalsFor() {
		this.goalsFor -= 1;
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
		this.goalsAgainst -= 1;
	}
	
	public int getShotAttemptsFor() {
		return shotAttemptsFor;
	}
	public void setShotAttemptsFor(final int shotAttemptsFor) {
		this.shotAttemptsFor = shotAttemptsFor;
	}
	public void incShotAttemptsFor() {
		this.shotAttemptsFor++;
	}
	public void decShotAttemptsFor() {
		this.shotAttemptsFor -= 1;
	}
	
	public int getMissedShots() {
		return missedShots;
	}
	public void setMissedShots(final int missedShots) {
		this.missedShots = missedShots;
	}
	public void incMissedShots() {
		this.missedShots++;
	}
	public void decMissedShots() {
		this.missedShots -= 1;
	}
	
	public int getBlockedShotsFor() {
		return blockedShotsFor;
	}
	public void setBlockedShotsFor(final int blockedShotsFor) {
		this.blockedShotsFor = blockedShotsFor;
	}
	public void incBlockedShotsFor() {
		this.blockedShotsFor++;
	}
	public void decBlockedShotsFor() {
		this.blockedShotsFor -= 1;
	}
	
	public int getShotAttemptsAgainst() {
		return shotAttemptsAgainst;
	}
	public void setShotAttemptsAgainst(final int shotAttemptsAgainst) {
		this.shotAttemptsAgainst = shotAttemptsAgainst;
	}
	public void incShotAttemptsAgainst() {
		this.shotAttemptsAgainst++;
	}
	public void decShotAttemptsAgainst() {
		this.shotAttemptsAgainst -= 1;
	}
	
	public int getBlocks() {
		return blocks;
	}
	public void setBlocks(final int blocks) {
		this.blocks = blocks;
	}
	public void incBlocks() {
		this.blocks++;
	}
	public void decBlocks() {
		this.blocks -= 1;
	}
	
	public int getTakeaways() {
		return takeaways;
	}
	public void setTakeaways(final int takeaways) {
		this.takeaways = takeaways;
	}
	public void incTakeaways() {
		this.takeaways++;
	}
	public void decTakeaways() {
		this.takeaways -= 1;
	}
	
	public int getGiveaways() {
		return giveaways;
	}
	public void setGiveaways(final int giveaways) {
		this.giveaways = giveaways;
	}
	public void incGiveaways() {
		this.giveaways++;
	}
	public void decGiveaways() {
		this.giveaways -= 1;
	}
	
	public void addTeamStats(final TeamStats addition) {
		if (addition.teamId == this.teamId && 
				addition.season == this.season) {
			this.gamesPlayed += addition.gamesPlayed;
			this.wins += addition.wins;
			this.losses += addition.losses;
			this.overtimeWins += addition.overtimeWins;
			this.shootoutWins += addition.shootoutWins;
			this.otSoLosses += addition.otSoLosses;
			this.shotAttemptsFor += addition.shotAttemptsFor;
			this.shotsFor += addition.shotsFor;
			this.missedShots += addition.missedShots;
			this.blockedShotsFor += addition.blockedShotsFor;
			this.shotAttemptsAgainst += addition.shotAttemptsAgainst;
			this.shotsAgainst += addition.shotsAgainst;
			this.blocks += addition.blocks;
			this.goalsFor += addition.goalsFor;
			this.goalsAgainst += addition.goalsAgainst;
			this.takeaways += addition.takeaways;
			this.giveaways += addition.giveaways;
		} else {
			System.out.println("TeamStats.addTeamStats ei oikea teamId");
		}
	}

	@Override
	public TeamStats clone() {
		TeamStats ret = new TeamStats(this.teamId, this.season);
		ret.gamesPlayed = this.gamesPlayed;
		ret.wins =this.wins;
		ret.losses = this.losses;
		ret.overtimeWins = this.overtimeWins;
		ret.shootoutWins = this.shootoutWins;
		ret.otSoLosses = this.otSoLosses;
		ret.shotAttemptsFor = this.shotAttemptsFor;
		ret.shotsFor = this.shotsFor;
		ret.missedShots = this.missedShots;
		ret.blockedShotsFor = this.blockedShotsFor;
		ret.shotAttemptsAgainst = this.shotAttemptsAgainst;
		ret.shotsAgainst = this.shotsAgainst;
		ret.blocks = this.blocks;
		ret.goalsFor = this.goalsFor;
		ret.goalsAgainst = this.goalsAgainst;
		ret.takeaways = this.takeaways;
		ret.giveaways = this.giveaways;
		
		return ret;
	}
	
	public void recordEventForTeam(final Event event) {
		switch (event.getType()) {
			case Shot:
				if (this.teamId == event.getTeamId()) {
					this.recordShotFor();
				} else {
					this.recordShotAgainst();
				}
				break;
			case MissedShot:
				if (this.teamId == event.getTeamId()) {
					this.recordMissedShot();
				} else {
					this.incShotAttemptsAgainst();
				}
				break;
			case BlockedShot:
				if (this.teamId == event.getTeamId()) {
					this.recordBlockedShotAgainst();
				} else {
					this.recordBlockedShotFor();
				}
				break;
			case Goal:
				if (this.teamId == event.getTeamId()) {
					this.recordGoalFor();
				} else {
					this.recordGoalAgainst();
				}
				break;
			case Takeaway:
				if (this.teamId == event.getTeamId()) {
					this.incTakeaways();
				} else {
					this.incGiveaways();
				}
				break;
			case Giveaway:
				if (this.teamId == event.getTeamId()) {
					this.incGiveaways();
				}
				break;
		}
	}

	public void recordEventForTeam(final Evnt event) {
		switch (event.getType()) {
			case Shot:
				if (this.teamId == event.getTeamId()) {
					this.recordShotFor();
				} else {
					this.recordShotAgainst();
				}
				break;
			case MissedShot:
				if (this.teamId == event.getTeamId()) {
					this.recordMissedShot();
				} else {
					this.incShotAttemptsAgainst();
				}
				break;
			case BlockedShot:
				if (this.teamId == event.getTeamId()) {
					this.recordBlockedShotAgainst();
				} else {
					this.recordBlockedShotFor();
				}
				break;
			case Goal:
				if (this.teamId == event.getTeamId()) {
					this.recordGoalFor();
				} else {
					this.recordGoalAgainst();
				}
				break;
			case Takeaway:
				if (this.teamId == event.getTeamId()) {
					this.incTakeaways();
				} else {
					this.incGiveaways();
				}
				break;
			case Giveaway:
				if (this.teamId == event.getTeamId()) {
					this.incGiveaways();
				}
				break;
		}
	}
	
	private void recordShotFor() {
		this.incShotAttemptsFor();
		this.incShotsFor();
	}
	
	private void recordShotAgainst() {;
		this.incShotAttemptsAgainst();
		this.incShotsAgainst();
	}
	
	private void recordMissedShot() {
		this.incShotAttemptsFor();
		this.incMissedShots();
	}
	
	private void recordBlockedShotFor() {
		this.incShotAttemptsFor();
		this.incBlockedShotsFor();
	}
	
	private void recordBlockedShotAgainst() {
		this.incShotAttemptsAgainst();
		this.incBlocks();
	}
	
	private void recordGoalFor() {
		this.recordShotFor();
		this.incGoalsFor();
	}
	
	private void recordGoalAgainst() {
		this.recordShotAgainst();
		this.incGoalsAgainst();
	}
	
}
