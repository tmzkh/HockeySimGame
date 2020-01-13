package game;

import java.util.ArrayList;
import java.util.HashMap;

import attributes.SkaterAttributes;
import player.Player;

public class PeriodVariables {
	private int periodNumber;
	private int homeId;
	private int visitorId;
	private ArrayList<Player> homeTeamPlayers;
	private ArrayList<Player> visitorTeamPlayers;
	private HashMap<Integer, ArrayList<Integer>> homeFwdLines;
	private HashMap<Integer, ArrayList<Integer>> homeDefLines;
	private HashMap<Integer, ArrayList<Integer>> visitorFwdLines;
	private HashMap<Integer, ArrayList<Integer>> visitorDefLines;
	private int homeGoalieId;
	private int visitorGoalieId;
	private HashMap<Integer, SkaterAttributes> homeTeamSkaterAttributes;
	private HashMap<Integer, SkaterAttributes> visitorTeamSkaterAttributes;

	/**
	 * @param periodNumber
	 * @param homeId
	 * @param visitorId
	 */
	public PeriodVariables(int periodNumber, int homeId, int visitorId) {
		super();
		this.periodNumber = periodNumber;
		this.homeId = homeId;
		this.visitorId = visitorId;
		this.homeTeamPlayers = new ArrayList<Player>();
		this.visitorTeamPlayers = new ArrayList<Player>();
		this.homeGoalieId = 0;
		this.visitorGoalieId = 0;
	}
	
	public int getPeriodNumber() {
		return periodNumber;
	}
	public void setPeriodNumber(final int periodNumber) {
		this.periodNumber = periodNumber;
	}
	
	public int getHomeId() {
		return homeId;
	}
	public void setHomeId(final int homeId) {
		this.homeId = homeId;
	}
	
	public int getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(final int visitorId) {
		this.visitorId = visitorId;
	}
	
	public ArrayList<Player> getHomeTeamPlayers() {
		return homeTeamPlayers;
	}
	public void setHomeTeamPlayers(
			final ArrayList<Player> homeTeamPlayers) {
		this.homeTeamPlayers = homeTeamPlayers;
	}
	
	public ArrayList<Player> getVisitorTeamPlayers() {
		return visitorTeamPlayers;
	}
	public void setVisitorTeamPlayers(
			final ArrayList<Player> visitorTeamPlayers) {
		this.visitorTeamPlayers = visitorTeamPlayers;
	}
	
	public int getHomeGoalieId() {
		return homeGoalieId;
	}
	public void setHomeGoalieId(final int homeGoalieId) {
		this.homeGoalieId = homeGoalieId;
	}
	
	public int getVisitorGoalieId() {
		return visitorGoalieId;
	}
	public void setVisitorGoalieId(final int visitorGoalieId) {
		this.visitorGoalieId = visitorGoalieId;
	}
	
	public HashMap<Integer, SkaterAttributes> getHomeTeamSkaterAttributes() {
		return homeTeamSkaterAttributes;
	}
	public void setHomeTeamSkaterAttributes(
			final HashMap<Integer, SkaterAttributes> 
			homeTeamSkaterAttributes) {
		this.homeTeamSkaterAttributes = homeTeamSkaterAttributes;
	}
	
	public HashMap<Integer, SkaterAttributes> getVisitorTeamSkaterAttributes() {
		return visitorTeamSkaterAttributes;
	}
	public void setVisitorTeamSkaterAttributes(
			final HashMap<Integer, SkaterAttributes> 
			visitorTeamSkaterAttributes) {
		this.visitorTeamSkaterAttributes = visitorTeamSkaterAttributes;
	}

	public HashMap<Integer, ArrayList<Integer>> getHomeFwdLines() {
		return homeFwdLines;
	}

	public void setHomeFwdLines(
			final HashMap<Integer, ArrayList<Integer>> homeFwdLines) {
		this.homeFwdLines = homeFwdLines;
	}

	public HashMap<Integer, ArrayList<Integer>> getHomeDefLines() {
		return homeDefLines;
	}

	public void setHomeDefLines(
			final HashMap<Integer, ArrayList<Integer>> homeDefLines) {
		this.homeDefLines = homeDefLines;
	}

	public HashMap<Integer, ArrayList<Integer>> getVisitorFwdLines() {
		return visitorFwdLines;
	}

	public void setVisitorFwdLines(
			final HashMap<Integer, ArrayList<Integer>> visitorFwdLines) {
		this.visitorFwdLines = visitorFwdLines;
	}

	public HashMap<Integer, ArrayList<Integer>> getVisitorDefLines() {
		return visitorDefLines;
	}

	public void setVisitorDefLines(
			final HashMap<Integer, ArrayList<Integer>> visitorDefLines) {
		this.visitorDefLines = visitorDefLines;
	}

	
}
