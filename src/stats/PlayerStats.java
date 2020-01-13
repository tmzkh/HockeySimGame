package stats;

public class PlayerStats {
	private int playerId;
	private int season;
	private int playedGames;
	private int shotAttempts;
	private int shots;
	private int missedShots;
	private int blockedShotsFor;
	private int goals;
	private int assists;
	
	/**
	 * @param playerId
	 */
	public PlayerStats(final int playerId, final int season) {
		this.playerId = playerId;
		this.season = season;
		this.playedGames = 0;
		this.shotAttempts = 0;
		this.shots = 0;
		this.missedShots = 0;
		this.blockedShotsFor = 0;
		this.goals = 0;
		this.assists = 0;
	}

	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(final int playerId) {
		this.playerId = playerId;
	}

	public int getSeason() {
		return season;
	}
	public void setSeason(final int season) {
		this.season = season;
	}

	public int getPlayedGames() {
		return playedGames;
	}
	public void setPlayedGames(final int playedGames) {
		this.playedGames = playedGames;
	}
	public void incPlayedGames() {
		this.playedGames++;
	}
	public void decPlayedGames() {
		if (this.playedGames > 0) {
			this.playedGames -= 1;
		}
	}
	
	public int getShotAttempts() {
		return shotAttempts;
	}
	public void setShotAttempts(final int shotAttemptsFor) {
		this.shotAttempts = shotAttemptsFor;
	}
	public void incShotAttempts() {
		this.shotAttempts++;
	}
	public void decShotAttempts() {
		this.shotAttempts -= 1;
	}
	
	public int getShots() {
		return shots;
	}
	public void setShots(final int shots) {
		this.shots = shots;
	}
	public void incShots() {
		this.shots++;
	}
	public void decShots() {
		this.shots -= 1;
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

	public int getGoals() {
		return goals;
	}
	public void setGoals(final int goals) {
		this.goals = goals;
	}
	public void incGoals() {
		this.goals++;
	}
	public void decGoals() {
		this.goals -= 1;
	}

	public int getAssists() {
		return assists;
	}
	public void setAssists(final int assists) {
		this.assists = assists;
	}
	public void incAssists() {
		this.assists++;
	}
	public void decAssists() {
		this.assists -= 1;
	}
	
	/**
	 * adds stats from game to player's stats
	 */
	protected void addStats(final PlayerStats stats) {
		if (this.playerId == stats.playerId && this.season == stats.season) {
			this.playedGames += stats.playedGames;
			this.shotAttempts += stats.shotAttempts;
			this.shots += stats.shots;
			this.missedShots += stats.missedShots;
			this.blockedShotsFor += stats.blockedShotsFor;
			this.goals += stats.goals;
			this.assists += stats.assists;
		} else if (this.season != stats.season) {
			System.out.println("ei oikea season");
		} else {
			System.out.println("ei oikea id");
		}
	}

}