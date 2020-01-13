package attributes;

public class PlayerAttributes {
	private int playerId;
	private int speed;
	
	private static int min = 1;
	private static int max = 10;
	
	/**
	 * @param playerId
	 * @param speed
	 */
	public PlayerAttributes(final int playerId, final int speed) {
		this.playerId = playerId;
		if (speed <= max && speed >= min) {
			this.speed = speed;
		}
	}

	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(final int playerId) {
		this.playerId = playerId;
	}

	public int getSpeed() {
		return speed;
	}
	public void setSpeed(final int speed) {
		if (speed <= max && speed >= min) {
			this.speed = speed;
		}
	}
	public void incSpeed() {
		if (this.speed < max) {
			this.speed++;
		}
	}
	public void decSpeed() {
		if (this.speed > min) {
			this.speed--;
		}
	}
}
