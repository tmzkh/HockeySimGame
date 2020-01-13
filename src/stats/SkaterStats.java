package stats;

import game.event.BlockedShot;
import game.event.Event;
import game.event.Goal;
import game.event.Shot;
import game.event.Takeaway;
import game.event.refactored.Evnt;

public class SkaterStats extends PlayerStats implements Cloneable {
	private int plusMinus;
	private int blocks;
	private int takeaways;
	private int giveaways;
	private int iceTime;

	/**
	 * @param playerId
	 * @param season
	 */
	public SkaterStats(int playerId, int season) {
		super(playerId, season);
		this.plusMinus = 0;
		this.blocks = 0;
		this.takeaways = 0;
		this.giveaways = 0;
		this.iceTime = 0;
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

	/**
	 * adds stats from game to player's stats
	 */
	public void addStats(final SkaterStats stats) {
		if (stats.getPlayerId() == this.getPlayerId() && 
				stats.getSeason() == this.getSeason()) {
			super.addStats(stats);
			this.plusMinus += stats.plusMinus;
			this.blocks += stats.blocks;
			this.takeaways += stats.takeaways;
			this.giveaways += stats.giveaways;
			this.iceTime += stats.iceTime;
		} else if (stats.getSeason() != this.getSeason()) {
			System.out.println("ei oikea season");
		} else {
			System.out.println("ei oikea id");
		}
		
	}
	
	/**
	 * @param event
	 * @return
	 */
	public void recordEvent(final Event event) {
		switch (event.getType()) {
			case Shot:
				final Shot s = (Shot)event;
				if (s.getPlayerId() == this.getPlayerId()) {
					this.recordShotFor();	
				}
				break;
			case MissedShot:
				if (event.getPlayerId() == this.getPlayerId()) {
					this.recordMissedShot();	
				}
				break;
			case BlockedShot:
				final BlockedShot bs = (BlockedShot)event;
				if (bs.getShooterId() == this.getPlayerId()) {
					this.recordBlockedShotFor();
				} else if (bs.getPlayerId() == this.getPlayerId()) {
					this.recordBlock();
				}
				break;
			case Goal:
				final Goal g = (Goal)event;
				if (g.getPlayerId() == this.getPlayerId()) {
					this.recordGoal();	
				} else if (this.getPlayerId() == g.getFirstAssistId()
						|| this.getPlayerId() == g.getSecondAssistId()) {
					this.recordAssist();
				}
				break;
			case Takeaway:
				final Takeaway t = (Takeaway)event;
				if (this.getPlayerId() == t.getPlayerId()) {
					this.recordTakeaway();
				} else if (this.getPlayerId() == t.getGiverId()) {
					this.recordGiveaway();
				}
				break;
			case Giveaway:
				if (this.getPlayerId() == event.getPlayerId()) {
					this.recordGiveaway();
				}
				break;
			default:
				System.out.println(event.toString());
				System.out.println("skaterstats playerid: " + this.getPlayerId());
				break;
		}
	}
	
	public void recordEvent(final Evnt event) {
		switch (event.getType()) {
			case Shot:
				final game.event.refactored.Shot s = (game.event.refactored.Shot)event;
				if (s.getPlayerId() == this.getPlayerId()) {
					this.recordShotFor();	
				}
				break;
			case MissedShot:
				if (event.getPlayerId() == this.getPlayerId()) {
					this.recordMissedShot();	
				}
				break;
			case BlockedShot:
				final game.event.refactored.BlockedShot bs = (game.event.refactored.BlockedShot)event;
				if (bs.getShooterId() == this.getPlayerId()) {
					this.recordBlockedShotFor();
				} else if (bs.getPlayerId() == this.getPlayerId()) {
					this.recordBlock();
				}
				break;
			case Goal:
				final game.event.refactored.Goal g = (game.event.refactored.Goal)event;
				if (g.getPlayerId() == this.getPlayerId()) {
					this.recordGoal();	
				} else if (this.getPlayerId() == g.getFirstAssistId()
						|| this.getPlayerId() == g.getSecondAssistId()) {
					this.recordAssist();
				}
				break;
			case Takeaway:
				final game.event.refactored.Takeaway t = (game.event.refactored.Takeaway)event;
				if (this.getPlayerId() == t.getPlayerId()) {
					this.recordTakeaway();
				} else if (this.getPlayerId() == t.getGiverId()) {
					this.recordGiveaway();
				}
				break;
			case Giveaway:
				if (this.getPlayerId() == event.getPlayerId()) {
					this.recordGiveaway();
				}
				break;
			default:
				System.out.println(event.toString());
				System.out.println("skaterstats playerid: " + this.getPlayerId());
				break;
		}
	}
	
	private void recordShotFor() {
		this.incShotAttempts();
		this.incShots();
	}
	
	private void recordMissedShot() {
		this.incShotAttempts();
		this.incMissedShots();
	}
	
	private void recordBlockedShotFor() {
		this.incShotAttempts();
		this.incBlockedShotsFor();
	}
	
	private void recordBlock() {
		this.incBlocks();
	}
	
	private void recordGoal() {
		this.recordShotFor();
		this.incGoals();
	}
	
	private void recordAssist() {
		this.incAssists();
	}
	
	private void recordGiveaway() {
		this.incGiveaways();
	}
	
	private void recordTakeaway() {
		this.incTakeaways();
	}
	
	public int getIceTime() {
		return iceTime;
	}



	public void setIceTime(final int iceTime) {
		this.iceTime = iceTime;
	}
	public void incIceTime() {
		this.iceTime++;
	}
	
	



	@Override
	public SkaterStats clone() {
		SkaterStats ret = new SkaterStats(this.getPlayerId(), 
        		this.getSeason());
        ret.setPlayedGames(this.getPlayedGames());
        ret.setShotAttempts(this.getShotAttempts());
        ret.setShots(this.getShots());
        ret.setMissedShots(this.getMissedShots());
        ret.setBlockedShotsFor(this.getBlockedShotsFor());
        ret.setGoals(this.getGoals());
        ret.setAssists(this.getAssists());
        ret.plusMinus = this.plusMinus;
        ret.blocks = this.blocks;
        ret.takeaways = this.takeaways;
        ret.giveaways = this.giveaways;
        ret.iceTime = this.iceTime;
        return ret;
	}
}
