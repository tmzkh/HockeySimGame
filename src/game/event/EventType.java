package game.event;

public enum EventType {
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
