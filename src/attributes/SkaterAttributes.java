package attributes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import utils.MySql.DataBase;

public class SkaterAttributes extends PlayerAttributes 
implements Cloneable {
	private int attackIq;
	private int shooting;
	private int puckControl;
	private int defenseIq;
	private int stickChecking;
	
	private static int min = 1;
	private static int max = 10;
	
	/**
	 * @param playerId
	 * @param speed
	 * @param attackIq
	 * @param shooting
	 * @param puckControl
	 * @param defenseIq
	 * @param stickChecking
	 */
	public SkaterAttributes(final int playerId, final int speed, 
			final int attackIq, final int shooting, final  int puckControl,
			final int defenseIq, final int stickChecking) {
		super(playerId, speed);
		if (attackIq <= max && attackIq >= min) {
			this.attackIq = attackIq;
		}
		if (shooting <= max && shooting >= min) {
			this.shooting = shooting;
		}
		if (puckControl <= max && puckControl >= min) {
			this.puckControl = puckControl;
		}
		if (defenseIq <= max && defenseIq >= min) {
			this.defenseIq = defenseIq;
		}
		if (stickChecking <= max && stickChecking >= min) {
			this.stickChecking = stickChecking;
		}
	}
	public int getAttackIq() {
		return attackIq;
	}
	public void setAttackIq(final int attackIq) {
		if (attackIq <= max && attackIq >= min) {
			this.attackIq = attackIq;
		}
	}
	public void incAttackIq() {
		if (this.attackIq < max) {
			this.attackIq++;
		}
	}
	public void decAttackIq() {
		if (this.attackIq > min) {
			this.attackIq--;
		}
	}
	
	public int getShooting() {
		return shooting;
	}
	public void setShooting(final int shooting) {
		if (shooting <= max && shooting >= min) {
			this.shooting = shooting;
		}
	}
	public void incShooting() {
		if (this.shooting < max) {
			this.shooting++;
		}
	}
	public void decShooting() {
		if (this.shooting > min) {
			this.shooting--;
		}
	}
	
	public int getPuckControl() {
		return puckControl;
	}
	public void setPuckControl(final int puckControl) {
		if (puckControl <= max && puckControl >= min) {
			this.puckControl = puckControl;
		}
	}
	public void incPuckControl() {
		if (this.puckControl < max) {
			this.puckControl++;
		}
	}
	public void decPuckControl() {
		if (this.puckControl > min) {
			this.puckControl--;
		}
	}
	
	public int getDefenseIq() {
		return defenseIq;
	}
	public void setDefenseIq(final int defenseIq) {
		if (defenseIq <= max && defenseIq >= min) {
			this.defenseIq = defenseIq;
		}
	}
	public void incDefenseIq() {
		if (this.defenseIq < max) {
			this.defenseIq++;
		}
	}
	public void decDefenseIq() {
		if (this.defenseIq > min) {
			this.defenseIq--;
		}
	}
	
	public int getStickChecking() {
		return stickChecking;
	}
	public void setStickChecking(final int stickChecking) {
		if (stickChecking <= max && stickChecking >= min) {
			this.stickChecking = stickChecking;
		}
	}
	public void incStickChecking() {
		if (this.stickChecking < max) {
			this.stickChecking++;
		}
	}
	public void decStickChecking() {
		if (this.stickChecking > min) {
			this.stickChecking--;
		}
	}
	
	@Override
	public SkaterAttributes clone() {
		SkaterAttributes ret = new SkaterAttributes(this.getPlayerId(), 
				this.getSpeed(), this.attackIq,
				this.shooting, this.puckControl,
				this.defenseIq, this.stickChecking);
		return ret;
	}
	
	/**
	 * @param id
	 * @return
	 */
	public static SkaterAttributes fetchAttributesByPlayerId(final int id) {
		DataBase db = new DataBase();
		String query = "SELECT * FROM skaterAttributes WHERE playerId = " + id;
		Connection con = null;
		Statement stmt = null;
		
		SkaterAttributes pa = null;
		
		try {
			con = db.connect();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next()) {
				final int speed = rs.getInt(3);
				final int attackIq = rs.getInt(4);
				final int shooting = rs.getInt(5);
				final int puckControl = rs.getInt(6);
				final int defenseIq = rs.getInt(7);
				final int stickChecking = rs.getInt(8);
				pa = new SkaterAttributes(id, speed, attackIq, shooting, 
						puckControl, defenseIq, stickChecking);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return pa;
	}
	
	/**
	 * @param _teamId
	 * @return
	 */
	public static HashMap<Integer, SkaterAttributes> fetchSkaterAttributesByTeamId(
			final int _teamId) {
		DataBase db = new DataBase();
		String query = "SELECT players.teamId, skaterAttributes.* " + 
				"FROM skaterAttributes LEFT JOIN players " + 
				"ON players.id = skaterAttributes.playerId WHERE teamId = " + 
				_teamId;
		Connection con = null;
		Statement stmt = null;
		
		HashMap<Integer, SkaterAttributes> pal = new HashMap<Integer, SkaterAttributes>();
		
		try {
			con = db.connect();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				final int id = rs.getInt(3);
				final int speed = rs.getInt(4);
				final int attackIq = rs.getInt(5);
				final int shooting = rs.getInt(6);
				final int puckControl = rs.getInt(7);
				final int defenseIq = rs.getInt(8);
				final int stickChecking = rs.getInt(9);
				final SkaterAttributes pa = new SkaterAttributes(id, speed, attackIq, shooting, 
						puckControl, defenseIq, stickChecking);
				pal.put(id, pa);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return pal;
	}
	
	@Override
	public String toString() {
		return "SkaterAttributes [attackIq=" + attackIq + ", shooting=" 
				+ shooting + ", puckControl=" + puckControl
				+ ", defenseIq=" + defenseIq + ", stickChecking=" 
				+ stickChecking + "]";
	}
	
	public String toCSVString () {
		return this.getSpeed() + ";" + attackIq + ";" + shooting + ";" 
				+ puckControl + ";" + defenseIq + ";" + stickChecking + ";";
	}
}
