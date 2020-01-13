package console.testing;

import java.util.ArrayList;
import java.util.HashMap;

import attributes.SkaterAttributes;
import game.Game;
import game.GameUtils;
import game.refactored.Gme;
import player.*;
import stats.GoalieStats;
import stats.PlayerStats;
import stats.SkaterStats;
import stats.TeamStats;
import team.Team;

public class Main {

	public static void main(String[] args) {
		
		final int t1id = 2, t2id = 3, t3id = 4, t4id = 5;
		
		Team t1 = Team.fetchTeamById(t1id);
		HashMap<Integer, Player> t1Players = Player.fetchPlayersByTeamId(t1id);
		
		Team t2 = Team.fetchTeamById(t2id);
		HashMap<Integer, Player> t2Players = Player.fetchPlayersByTeamId(t2id);
		
		Team t3 = Team.fetchTeamById(t3id);
		HashMap<Integer, Player> t3Players = Player.fetchPlayersByTeamId(t3id);
		
		Team t4 = Team.fetchTeamById(t4id);
		HashMap<Integer, Player> t4Players = Player.fetchPlayersByTeamId(t4id);
		
		
		HashMap<Integer, Team> teams = new HashMap<Integer, Team>();
		teams.put(t1id, t1);
		teams.put(t2id, t2);
		teams.put(t3id, t3);
		teams.put(t4id, t4);
		
		HashMap<Integer, HashMap<Integer, Player>> players = new HashMap<Integer, HashMap<Integer, Player>>();
		players.put(t1id, t1Players);
		players.put(t2id, t2Players);
		players.put(t3id, t3Players);
		players.put(t4id, t4Players);
		
		TeamStats t1stats = new TeamStats(t1id, 1);
		TeamStats t2stats = new TeamStats(t2id, 1);
		TeamStats t3stats = new TeamStats(t3id, 1);
		TeamStats t4stats = new TeamStats(t4id, 1);
		
		HashMap<Integer, TeamStats> teamStats = new HashMap<Integer, TeamStats>();
		teamStats.put(t1id, t1stats);
		teamStats.put(t2id, t2stats);
		teamStats.put(t3id, t3stats);
		teamStats.put(t4id, t4stats);
		
		HashMap<Integer, SkaterAttributes> t1SkaterAttributes = SkaterAttributes.fetchSkaterAttributesByTeamId(t1id);
		HashMap<Integer, SkaterAttributes> t2SkaterAttributes = SkaterAttributes.fetchSkaterAttributesByTeamId(t2id);
		HashMap<Integer, SkaterAttributes> t3SkaterAttributes = SkaterAttributes.fetchSkaterAttributesByTeamId(t3id);
		HashMap<Integer, SkaterAttributes> t4SkaterAttributes = SkaterAttributes.fetchSkaterAttributesByTeamId(t4id);
		
		HashMap<Integer, HashMap<Integer, SkaterAttributes>> skaterAttributesByTeam = new HashMap<Integer, HashMap<Integer,SkaterAttributes>>();
		skaterAttributesByTeam.put(t1id, t1SkaterAttributes);
		skaterAttributesByTeam.put(t2id, t2SkaterAttributes);
		skaterAttributesByTeam.put(t3id, t3SkaterAttributes);
		skaterAttributesByTeam.put(t4id, t4SkaterAttributes);
		
		HashMap<Integer, PlayerStats> t1pstats = new HashMap<Integer, PlayerStats>();
		t1Players.forEach((id, player) -> {
			if (player.getPosition() == Position.Goalie) {
				GoalieStats gs = new GoalieStats(id, 1);
				t1pstats.put(id, gs);
				player.addStats(gs);
			} else {
				SkaterStats ss = new SkaterStats(id, 1);
				t1pstats.put(id, ss);
				player.addStats(ss);
				player.setAttributes(t1SkaterAttributes.get(id));
			}
		});
		
		HashMap<Integer, PlayerStats> t2pstats = new HashMap<Integer, PlayerStats>();
		t2Players.forEach((id, player) -> {
			if (player.getPosition() == Position.Goalie) {
				GoalieStats gs = new GoalieStats(id, 1);
				t2pstats.put(id, gs);
				player.addStats(gs);
			} else {
				SkaterStats ss = new SkaterStats(id, 1);
				t2pstats.put(id, ss);
				player.addStats(ss);
				player.setAttributes(t2SkaterAttributes.get(id));
			}
		});
		
		HashMap<Integer, PlayerStats> t3pstats = new HashMap<Integer, PlayerStats>();
		t3Players.forEach((id, player) -> {
			if (player.getPosition() == Position.Goalie) {
				GoalieStats gs = new GoalieStats(id, 1);
				t3pstats.put(id, gs);
				player.addStats(gs);
			} else {
				SkaterStats ss = new SkaterStats(id, 1);
				t3pstats.put(id, ss);
				player.addStats(ss);
				player.setAttributes(t3SkaterAttributes.get(id));
			}
		});
		
		HashMap<Integer, PlayerStats> t4pstats = new HashMap<Integer, PlayerStats>();
		t4Players.forEach((id, player) -> {
			if (player.getPosition() == Position.Goalie) {
				GoalieStats gs = new GoalieStats(id, 1);
				t4pstats.put(id, gs);
				player.addStats(gs);
			} else {
				SkaterStats ss = new SkaterStats(id, 1);
				t4pstats.put(id, ss);
				player.addStats(ss);
				player.setAttributes(t4SkaterAttributes.get(id));
			}
		});
		
		t1.setPlayers(t1Players);
		t2.setPlayers(t2Players);
		t3.setPlayers(t3Players);
		t4.setPlayers(t4Players);

		
		HashMap<Integer, HashMap<Integer, PlayerStats>> playerStats = new HashMap<Integer, HashMap<Integer,PlayerStats>>();
		playerStats.put(t1id, t1pstats);
		playerStats.put(t2id, t2pstats);
		playerStats.put(t3id, t3pstats);
		playerStats.put(t4id, t4pstats);
		
		teams.get(t1id).addStats(teamStats.get(t1id));
		teams.get(t2id).addStats(teamStats.get(t2id));
		teams.get(t3id).addStats(teamStats.get(t3id));
		teams.get(t4id).addStats(teamStats.get(t4id));

		class Local {
			
			void simulateAndRecordGame(Team homeTeam, Team visitorTeam) {
//				Game g = new Game(htId, vtId, 
//						players.get(htId), players.get(vtId));
				
				Gme g = new Gme(homeTeam, visitorTeam);
				
				g.simGame(false);

				teams.get(homeTeam.getId()).addStats(g.getHomeTeamStats());
				teams.get(visitorTeam.getId()).addStats(g.getVisitorTeamStats());
				
				g.getHomeTeamPlayers().forEach((id, player) -> {
					//System.out.println(player.getStatsBySeason(1));
					players.get(homeTeam.getId()).get(player.getId()).addStats(player.getStatsBySeason(1));
				}); 

				g.getVisitorTeamPlayers().forEach((id, player) -> {
					//System.out.println(player.getStatsBySeason(1));
					if (player.getStatsBySeason(1) == null) {
						System.out.println(ConsoleUtilsForPlayers.playerToString(player) + " " + id);
					}
					players.get(visitorTeam.getId()).get(player.getId()).addStats(player.getStatsBySeason(1));
				});
			}
			
			void printOutStatistics() {
				teams.forEach((id, team) -> {
					System.out.println("ID;Name;City;Games;Wins;Losses;OTW;OTL;PTS;SAF;SF;MS;BSF;SAA;SA;BS;GF;S%;GA;TAs;GAs");
					
					System.out.println(ConsoleUtilsForTeams.teamToCSVString(teams.get(id)));
					System.out.println("");
					System.out.println("");
					
					System.out.println("ID;Team;FirstName;LastName;#;Position;Games;G;A;PTS;SA;S;MS;BSF;BS;TAs;GAs;S%;TOI;speed;attackIq;shooting;puckControl;defenseIq;stickChecking;");
					
					ArrayList<Player> goalies = new ArrayList<Player>();
					
					players.get(id).forEach((id1, p) -> {
						if (p.getPosition() != Position.Goalie) {
							SkaterAttributes sa = (SkaterAttributes)p.getAttributes();
							System.out.println(ConsoleUtilsForPlayers.skaterToCSVString(p, teams.get(id).getName())
									+ ";" + sa.toCSVString());
						} else {
							goalies.add(p);
						}
					}); 
					System.out.println("");
					System.out.println("");
					System.out.println("");
					
					System.out.println("ID;Team;FirstName;LastName;#;Position;G;W;L;OTL;SOs;Saves;GA;S%;A");
					
					goalies.forEach(g -> {
						System.out.println(ConsoleUtilsForPlayers.goalieToCSVString(g, teams.get(id).getName()));
					});
					
					System.out.println("");
					System.out.println("");
					System.out.println("");
					System.out.println("");
				});
			}
		}
		
		Local l = new Local();
		
		
		for (int i = 0; i < 10; i++) {
			l.simulateAndRecordGame(t1, t2);
			l.simulateAndRecordGame(t1, t3);
			l.simulateAndRecordGame(t4, t1);
			l.simulateAndRecordGame(t4, t2);
			l.simulateAndRecordGame(t3, t4);
			l.simulateAndRecordGame(t2, t3);
		}
		
		l.printOutStatistics();

	}
}
