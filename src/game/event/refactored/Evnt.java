package game.event.refactored;

import java.util.ArrayList;
import java.util.HashMap;

import attributes.SkaterAttributes;
import game.GameUtils;
import line.Line;
import player.Player;
import team.Team;


public class Evnt {
	private EvntType type;
	private int teamId;
	private int playerId;
	private int seconds;
	private int[] playerIds; 
	
	/**
	 * @param type
	 * @param teamId
	 * @param playerId
	 * @param seconds
	 */
	public Evnt(final EvntType type, int teamId, int playerId, int seconds) {
		this.type = type;
		this.teamId = teamId;
		this.playerId = playerId;
		this.seconds = seconds;
		this.playerIds = new int[5];
		for(int i = 0; i < playerIds.length; i++) {
			this.playerIds[i] = 0;
		}
		this.playerIds[0] = playerId;
	}
	
	
	public Evnt(final EvntType type, Player player, int seconds) {
		this.type = type;
		this.teamId = player.getTeamId();
		this.playerId = player.getId();
		this.seconds = seconds;
		this.playerIds = new int[5];
		for(int i = 0; i < playerIds.length; i++) {
			this.playerIds[i] = 0;
		}
		this.playerIds[0] = playerId;
	}

	public EvntType getType() {
		return type;
	}

	public void setType(final EvntType type) {
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
		String ret = "Event;" + type.toString() + ";" + teamId + ";";
		for (int id : this.playerIds) {
			ret += id + ";";
		}
		return ret;
	}
	
	public int[] getPlayerIds() {
		int[] ret = new int[this.playerIds.length];
		for (int i = 0; i < this.playerIds.length; i++) {
			ret[i] = this.playerIds[i];
		}
		return ret;
	}
	
	int[] getPlayerIdsForSet() {
		return this.playerIds;
	}


	/**
	 * @param home
	 * @param visitor
	 * @param homeGoalieId
	 * @param visitorGoalieId
	 * @param seconds
	 * @return
	 */
	public static Evnt drawEvent(Team home, Team visitor,
			int homeGoalieId, int visitorGoalieId,
			int seconds) {
		int teamId = drawTeamId(home, visitor);
		Player forward = drawAttackingPlayer(
				teamId == home.getId() ? home : visitor);
		//System.out.println(forward);
		Player defenseman = drawDefensivePlayer(
				teamId == home.getId() ? visitor : home);
		Player goalie = teamId == home.getId() ? 
				visitor.getPlayerById(visitorGoalieId) : 
					home.getPlayerById(homeGoalieId);

		final int gSpeed = 8,
				gGlove = 8,
				gStick = 8,
				gLegs = 8;
		
		if (checkIfShotAttempt(forward, defenseman)) {
			if (checkIfShotTowardsGoal(forward)) { 
				if (checkIfBlock(forward, defenseman)) {
					return new BlockedShot(forward, defenseman, seconds);
				} else {
					if (checkIfDangerousShot(forward)) {
						if (checkIfSave(gSpeed, gGlove, gStick, gLegs, true)) {
							return new Shot(forward, goalie, seconds);
						} else {
							Player firstAssist = null, secondAssist = null;
							do {
								firstAssist = randomPlayerFromIce(
										teamId == home.getId() ? home : visitor);
								secondAssist = randomPlayerFromIce(
										teamId == home.getId() ? home : visitor);
							} while (forward.getId() == firstAssist.getId() ||
									forward.getId() == secondAssist.getId() ||
									secondAssist.getId() == firstAssist.getId());
							return new Goal(forward, firstAssist, 
									secondAssist, goalie, seconds);
						}
					} else {
						if (checkIfSave(gSpeed, gGlove, gStick, gLegs, false)) {
							return new Shot(forward, goalie, seconds);
						} else {
							Player firstAssist = null, secondAssist = null;
							do {
								firstAssist = randomPlayerFromIce(
										teamId == home.getId() ? home : visitor);
								secondAssist = randomPlayerFromIce(
										teamId == home.getId() ? home : visitor);
							} while (forward.getId() == firstAssist.getId() ||
									forward.getId() == secondAssist.getId() ||
									secondAssist.getId() == firstAssist.getId());
							return new Goal(forward, firstAssist, 
									secondAssist, goalie, seconds);
						}
					}
				}
			} else {
				return new Evnt(EvntType.MissedShot, forward, seconds);
			}
		} else if (checkIfTakeaway(forward, defenseman)) {
			return new Takeaway(forward, defenseman, seconds);
		} else {
			return new Evnt(EvntType.Giveaway, forward, seconds);
		}
	}
	
	private static Player drawAttackingPlayer(final Team team) {
		
		return team.getPlayerById(getRandomPlayerId(team.getFwdLineOnIce().getPlayers())).cloneWOStats();
				
		
//		for (int i = 1; i < team.getForwardLines().size(); i++) {
//			if (team.getForwardLines().get(i).getOnIce()) {
//				int attackerId = getRandomPlayerId(
//						team.getForwardLines().get(i).getPlayers());
//				//System.out.println(team.getForwardLines().get(i).getPlayers());
//				return team.getPlayerById(attackerId).cloneWOStats();
//			}
//		}
//		return null;
	}
	
	private static Player drawDefensivePlayer(final Team team) {
		
		return team.getPlayerById(getRandomPlayerId(team.getDefLineOnIce().getPlayers())).cloneWOStats();
		
//		for (int i = 1; i < team.getDefenseLines().size(); i++) {
//			if (team.getDefenseLines().get(i).getOnIce()) {
//				int defId = getRandomPlayerId(
//						team.getDefenseLines().get(i).getPlayers());
//				return team.getPlayerById(defId).cloneWOStats();
//			}
//		}
//		return null;
	}
	
	private static Player randomPlayerFromIce(final Team team) {
		ArrayList<Integer> lineOnIce = new ArrayList<Integer>();
		lineOnIce.addAll(team.getFwdLineOnIce().getPlayers());
		lineOnIce.addAll(team.getDefLineOnIce().getPlayers());
		int playerId = getRandomPlayerId(lineOnIce);
		return team.getPlayerById(playerId).cloneWOStats();
	}
	
	private static int getRandomPlayerId(final ArrayList<Integer> playerIds) {
		return playerIds.get(GameUtils.getRandomInt(0, playerIds.size() - 1));
	}
	
	private static int drawTeamId(Team home, Team visitor) {
		int homeAttack = getTeamAttack(home);
		int visitorAttack = getTeamAttack(visitor);
		int random = GameUtils.getRandomInt(1, 
				(homeAttack + visitorAttack));
		if (random <= homeAttack) {
			return home.getId();
		} else {
			return visitor.getId();
		}
	}
	
	private static int getTeamAttack(Team team) {
		int attack = 0;
		
		//TODO getPlayerWOStats
		HashMap<Integer, Player> list = team.getPlayers();

		Line line = team.getFwdLineOnIce();
		
		attack += getLineAttack(line.getPlayers(), list);
		line = team.getDefLineOnIce();
		attack += getLineAttack(line.getPlayers(), list);
		return attack;
	}
	
	private static int getLineAttack(ArrayList<Integer> ids, 
			HashMap<Integer, Player> players) {
		int ret = 0;
		for (int i = 0; i < ids.size(); i++) {
			int id = ids.get(i);
			if (players.get(id) == null) {
				System.out.println("getLineAttack failed on id: " + id);
				continue;
			}
			SkaterAttributes sa = 
					(SkaterAttributes)players.get(id).getAttributes();
			ret += sa.getSpeed();
			ret += sa.getAttackIq();
			ret += sa.getPuckControl();
		}
		return ret;
	}

	/**
	 * @param fAttackIq
	 * @param fSpeed
	 * @param dDefenseIq
	 * @param dSpeed
	 * @return
	 */
	private static Boolean checkIfShotAttempt(final Player forward, 
			final Player defenseman) {
	    final int i = 80;
	    
	    SkaterAttributes aAttr = (SkaterAttributes) forward.getAttributes();
	    SkaterAttributes bAttr = (SkaterAttributes) defenseman.getAttributes();

	    final int att = ((2 * aAttr.getAttackIq() + aAttr.getSpeed()) / 3);
	    final int def = ((2 * bAttr.getDefenseIq() + bAttr.getSpeed()) / 3);

	    final int variable = att - def;

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
	private static Boolean checkIfShotTowardsGoal(final Player forward) {
		SkaterAttributes attr = (SkaterAttributes) forward.getAttributes();
		final int i = 70;
		final int variable = 
				(attr.getAttackIq() + (3 * attr.getShooting())) / 4;
		
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
	private static Boolean checkIfBlock(final Player forward, 
			final Player defenseman) {
		
		SkaterAttributes aAttr = (SkaterAttributes) forward.getAttributes();
	    SkaterAttributes bAttr = (SkaterAttributes) defenseman.getAttributes();
		
	    final int i = 25;

	    final int j = ((2 * aAttr.getAttackIq() + aAttr.getSpeed()) / 3 );
	    final int k = ((2 * bAttr.getDefenseIq() + bAttr.getSpeed()) / 3);

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
	private static Boolean checkIfTakeaway(final Player forward, 
			final Player defenseman) {
		
		SkaterAttributes aAttr = 
				(SkaterAttributes) forward.getAttributes();
	    SkaterAttributes bAttr = 
	    		(SkaterAttributes) defenseman.getAttributes();
		
		final int i = 60;
		
		final int j = (aAttr.getAttackIq() + 4 * aAttr.getPuckControl()) / 5;
		final int k = (bAttr.getDefenseIq() + 2 * bAttr.getStickChecking()) / 3;
		
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
	private static Boolean checkIfSave(final int gSpeed, final int gGlove, 
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
	private static Boolean checkIfDangerousShot(final Player forward) {
		SkaterAttributes aAttr = (SkaterAttributes) forward.getAttributes();
		final int i = 90;
		final int variable = 
				((aAttr.getAttackIq() + (3 * aAttr.getShooting())) / 4) / 2;
		
		final int x = GameUtils.getRandomInt(1, 100);
		
		if (x > i - variable) {
			return true;
		} else {
			return false;
		}
	}
	


}
