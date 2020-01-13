package console.testing;

import java.util.ArrayList;

import game.event.refactored.Evnt;
import game.event.refactored.Goal;
import game.event.refactored.Shot;
import game.refactored.Perd;
import game.refactored.PeriodType;
import player.Player;
import stats.TeamStats;
import team.Team;

public class TestingUnits {
	
	public Perd p1;
	public Perd p2;
	public Perd p3;
	
	public TestingUnits (Team home, Team visitor, int visitorGoalieId) {
		p1 = new Perd(1, PeriodType.Regular);
		p2 = new Perd(2, PeriodType.Regular);
		p3 = new Perd(3, PeriodType.Regular);
		
		ArrayList<Perd> periods = new ArrayList<Perd>();
		periods.add(p1);
		periods.add(p2);
		periods.add(p3);
		
		staticEventCreation(periods, home, visitor, visitorGoalieId);
		
	}
	
	private static void justTeams(ArrayList<Perd> periods, Team home, Team visitor) {
		periods.forEach(p -> {
			
			Team ht = home.cloneWOStats();
			Team vt = visitor.cloneWOStats();
			
			ht.createNewStatsForGame();
			vt.createNewStatsForGame();
			
			TeamStats hTs = new TeamStats(home.getId(), 1);
			TeamStats vTs = new TeamStats(visitor.getId(), 1);
			
			for (int i = 0; i < 10; i++) {
				incTeamShots(hTs, vTs);
				incTeamShots(vTs, hTs);
				
				if (i > 0 && i % 2 == 0) {
					incTeamMissedShots(hTs, vTs);
					incTeamMissedShots(vTs, hTs);
				}
			}
			
			incTeamShots(hTs, vTs);
			hTs.incGoalsFor();
			vTs.incGoalsAgainst();
			p.incHomeGoals();
			
			ht.addStats(hTs);
			vt.addStats(vTs);
			
			p.setTeams(ht, vt);
		});
	}
	
	private static void staticEventCreation(ArrayList<Perd> periods, Team home, Team visitor, int visitorGoalieId) {
		periods.forEach(p -> {
			
			Team ht = home.cloneWOStats();
			Team vt = visitor.cloneWOStats();
			
			ht.createNewStatsForGame();
			vt.createNewStatsForGame();
			
			
			
			for (int i = 0; i < 10; i++) {
				Player fwd = ht.getPlayerById(i+1);
				Player g = vt.getPlayerById(visitorGoalieId);
				Evnt event = new Shot(fwd, g, 0);
				
				TeamStats hTs = new TeamStats(home.getId(), 1);
				TeamStats vTs = new TeamStats(visitor.getId(), 1);
				
				hTs.recordEventForTeam(event);
				vTs.recordEventForTeam(event);
				
				ht.addStats(hTs);
				vt.addStats(vTs);
				
				ht.recordEventForPlayers(event);
				vt.recordEventForPlayers(event);
			}
			
			
			Evnt event = new Goal(ht.getPlayerById(1), ht.getPlayerById(2), ht.getPlayerById(3), vt.getPlayerById(visitorGoalieId), 0);
			
			TeamStats hTs = new TeamStats(home.getId(), 1);
			TeamStats vTs = new TeamStats(visitor.getId(), 1);
			
			hTs.recordEventForTeam(event);
			vTs.recordEventForTeam(event);
			ht.addStats(hTs);
			vt.addStats(vTs);
			ht.recordEventForPlayers(event);
			vt.recordEventForPlayers(event);
			p.incHomeGoals();

			p.setTeams(ht, vt);
		});
	}
	
	private static void incTeamShots(TeamStats t1s, TeamStats t2s) {
		t1s.incShotAttemptsFor();
		t1s.incShotsFor();
		t2s.incShotAttemptsAgainst();
		t2s.incShotsAgainst();
	}
	
	private static void incTeamMissedShots(TeamStats t1s, TeamStats t2s) {
		t1s.incShotAttemptsFor();
		t1s.incMissedShots();
		t2s.incShotAttemptsAgainst();
	}
	
}
