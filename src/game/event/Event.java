package game.event;

import attributes.SkaterAttributes;
import game.GameUtils;

public class Event {
	private EventType type;
	private int teamId;
	private int playerId;
	private int seconds;
	
	public Event() {
		
	}
	
	/**
	 * @param type
	 * @param teamId
	 * @param playerId
	 * @param seconds
	 */
	public Event(final EventType type, final int teamId, final int playerId, final int seconds) {
		this.type = type;
		this.teamId = teamId;
		this.playerId = playerId;
		this.seconds = seconds;
	}

	public EventType getType() {
		return type;
	}

	public void setType(final EventType type) {
		this.type = type;
	}

	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(final int teamId) {
		this.teamId = teamId;
	}

	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(final int playerId) {
		this.playerId = playerId;
	}

	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(final int seconds) {
		this.seconds = seconds;
	}
	
	public String toCSVString() {
		return "Event;" + type.toString() + ";" + teamId + ";" + playerId;
	}
	
	
	/**
	 * @param seconds
	 * @param eventIds
	 * @param skaterAttributes
	 * @param otherSkaterAttributes
	 * @return
	 */
	public Event drawEvent(final int seconds, final EventIds eventIds, 
			final SkaterAttributes skaterAttributes,
			final SkaterAttributes otherSkaterAttributes) {
		Event e = null;
		
		final int fAttackIq = skaterAttributes.getAttackIq(), 
				fSpeed = skaterAttributes.getSpeed(),
				fShooting = skaterAttributes.getShooting(),
				fPuckControl = skaterAttributes.getPuckControl(),
				dDefenseIq = otherSkaterAttributes.getDefenseIq(),
				dSpeed = otherSkaterAttributes.getSpeed(),
				dStickChecking = otherSkaterAttributes.getStickChecking(),
				gSpeed = 8,
				gGlove = 8,
				gStick = 8,
				gLegs = 8;
		
		if (checkIfShotAttempt(fAttackIq, fSpeed, dDefenseIq, dSpeed)) {
			if (checkIfShotTowardsGoal(fAttackIq, fShooting)) { 
				if (checkIfBlock(fAttackIq, fSpeed, dDefenseIq, dSpeed)) {
					e = setEvent(EventType.BlockedShot, eventIds, seconds);
				} else {
					if (checkIfDangerousShot(fAttackIq, fShooting)) {
						if (checkIfSave(gSpeed, gGlove, gStick, gLegs, true)) {
							e = setEvent(EventType.Shot, eventIds, seconds);
						} else {
							e = setEvent(EventType.Goal, eventIds, seconds);
						}
					} else {
						if (checkIfSave(gSpeed, gGlove, gStick, gLegs, false)) {
							e = setEvent(EventType.Shot, eventIds, seconds);
						} else {
							e = setEvent(EventType.Goal, eventIds, seconds);
						}
					}
				}
			} else {
				e = setEvent(EventType.MissedShot, eventIds, seconds);
			}
		} else if (checkIfTakeaway(fAttackIq, fPuckControl, dDefenseIq, 
				dStickChecking)) {
			e = setEvent(EventType.Takeaway, eventIds, seconds);
		} else {
			e = setEvent(EventType.Giveaway, eventIds, seconds);
		}
		
		return e;
	}

	/**
	 * @param fAttackIq
	 * @param fSpeed
	 * @param dDefenseIq
	 * @param dSpeed
	 * @return
	 */
	private Boolean checkIfShotAttempt(final int fAttackIq,
			final int fSpeed, final int dDefenseIq, final int dSpeed) {
	    final int i = 80;

	    final int j = ((2 * fAttackIq + fSpeed) / 3);
	    final int k = ((2 * dDefenseIq + dSpeed) / 3);

	    final int variable = j - k;

	    final int x = GameUtils.getRandomInt(1, 100);
	    if (x <= i + variable) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	/**
	 * @param fAttackIq
	 * @param fShooting
	 * @return
	 */
	private Boolean checkIfShotTowardsGoal(final int fAttackIq,
			final int fShooting) {
		
		final int i = 70;
		final int variable = (fAttackIq + (3 * fShooting)) / 4;
		
		final int x = GameUtils.getRandomInt(1, 100);
		
		if (x < i + variable) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @param fAttackIq
	 * @param fSpeed
	 * @param dDefenseIq
	 * @param dSpeed
	 * @return
	 */
	private Boolean checkIfBlock(final int fAttackIq,
			final int fSpeed, final int dDefenseIq, final int dSpeed) {
	    final int i = 25;

	    final int j = ((2 * fAttackIq + fSpeed) / 3 );
	    final int k = ((2 * dDefenseIq + dSpeed) / 3);

	    final int variable = k - j;

	    final int x = GameUtils.getRandomInt(1, 100);
	    if (x <= i + variable) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	/**
	 * @param fAttackIq
	 * @param fPuckControl
	 * @param dDefenseIq
	 * @param dStickChecking
	 * @return
	 */
	private Boolean checkIfTakeaway(final int fAttackIq,
			final int fPuckControl, final int dDefenseIq, 
			final int dStickChecking) {
		final int i = 60;
		
		final int j = (fAttackIq + 4 * fPuckControl) / 5;
		final int k = (dDefenseIq + 2 * dStickChecking) / 3;
		
		final int variable = k - j;
		
		final int x = GameUtils.getRandomInt(1, 100);
		if (x < i + variable) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @param gSpeed
	 * @param gGlove
	 * @param gStick
	 * @param gLegs
	 * @param dangerousShot
	 * @return
	 */
	private Boolean checkIfSave(final int gSpeed, final int gGlove, 
			final int gStick, final int gLegs, final Boolean dangerousShot) {
		int i;
		
		if (dangerousShot) {
			i = 74;
		} else {
			i = 84;
		}
		
		final int variable = (gSpeed + gGlove + gStick + gLegs) / 4;
		
		final int x = GameUtils.getRandomInt(1, 100);
		if (x <= i + variable) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @param fAttackIq
	 * @param fShooting
	 * @return
	 */
	private Boolean checkIfDangerousShot(final int fAttackIq,
			final int fShooting) {
		final int i = 90;
		final int variable = ((fAttackIq + (3 * fShooting)) / 4) / 2;
		
		final int x = GameUtils.getRandomInt(1, 100);
		
		if (x > i - variable) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @param type
	 * @param eventIds
	 * @param seconds
	 * @return
	 */
	private Event setEvent(final EventType type, 
			final EventIds eventIds, final int seconds) {
		Event e = null;
		
		switch (type) {
			case Shot:
				e = new Shot(eventIds, seconds);
				break;
			case MissedShot:
				this.type = EventType.MissedShot; 
				this.teamId = eventIds.getTeamId();
				this.playerId = eventIds.getPlayerId(); 
				this.seconds = seconds;
				return this;
			case BlockedShot:
				e = new BlockedShot(eventIds, seconds);
				break;
			case Goal:
				e = new Goal(eventIds, seconds);
				break;
			case Takeaway:
				e = new Takeaway(eventIds, seconds);
				break;
			case Giveaway:
				this.type = EventType.Giveaway; 
				this.teamId = eventIds.getTeamId();
				this.playerId = eventIds.getPlayerId(); 
				this.seconds = seconds;
				return this;
		}
		return e;
	}
}
