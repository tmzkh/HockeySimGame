package game.event.refactored;

public enum EvntType {
	Shot,
	MissedShot,
	BlockedShot,
	Goal,
	Takeaway,
	Giveaway;
	
	@Override
	public String toString() {
		return this.name();
	}
}
