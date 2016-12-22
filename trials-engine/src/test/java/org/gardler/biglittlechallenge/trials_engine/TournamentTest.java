package org.gardler.biglittlechallenge.trials_engine;

import org.gardler.biglittlechallenge.core.model.AbstractGame;
import org.gardler.biglittlechallenge.core.model.AbstractGameAPI;
import org.gardler.biglittlechallenge.core.model.GameStatus;
import org.gardler.biglittlechallenge.core.model.Player;
import org.gardler.biglittlechallenge.trials.engine.Tournament;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class TournamentTest extends TestCase {
	
	private AbstractGame tournament;
	private static Logger logger = LoggerFactory.getLogger(TournamentTest.class);

	public void testToString() {
		this.tournament = new Tournament();
		String result = this.tournament.toString();
		logger.info("Tournament toString is: " + result);
		assertTrue("We don;t appear to have set the rounds in the tournament correctly", result.contains("consists of 4 events"));
	}
	
	public void testFullGame() {
		String uiClassName = "org.gardler.biglittlechallenge.trials.ai.DumbAIUI";
		Player player = new Player("Test AI Player", uiClassName); 

		this.tournament = new Tournament();
		tournament.abortGame(); // This is to ensure we are not using a previously created game
		
		AbstractGameAPI api = tournament.getAPIEngine();
		GameStatus status = api.postJoinGame(player);
		assertEquals("Game status is not 'WaitingForPlayers'", GameStatus.State.WaitingForPlayers, status.getState());
		
		player = new Player("Second Test AI Player", uiClassName);
		status = api.postJoinGame(player);
		assertEquals("Game status is not 'Playing' after second player joins", GameStatus.State.Playing, status.getState());		
	}
}
