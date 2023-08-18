package org.arivuaata.rummy.console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.arivuaata.rummy.DealSettings;
import org.arivuaata.rummy.NonPlayerCards;
import org.arivuaata.rummy.PlayADealResult;
import org.arivuaata.rummy.Player;
import org.arivuaata.rummy.TurnPlayResult;
import org.junit.jupiter.api.Test;

class ConsolePlayTest {

	@Test
	void playADeal() {
		ConsolePlayer player1 = mock(ConsolePlayer.class);
		ConsolePlayer player2 = mock(ConsolePlayer.class);
		
		DealSettings dealSettings = mock(DealSettings.class);
		when(dealSettings.getDealer()).thenReturn(player1);
		
		List<ConsolePlayer> players = new ArrayList<>(2);
		players.add(player1);
		players.add(player2);
		
		NonPlayerCards nonPlayerCards = mock(NonPlayerCards.class);
		when(player1.deal(players)).thenReturn(nonPlayerCards);
		
		TurnPlayResult turnPlayResult = mock(TurnPlayResult.class);
		when(turnPlayResult.getMove()).thenReturn(Player.Move.DROP);
		
		when(player2.turnPlay()).thenReturn(turnPlayResult);
		
		PlayADealResult result = ConsolePlay.playADeal(dealSettings, players);

		verify(player1, times(1)).deal(players);
		verifyNoInteractions(nonPlayerCards);
		
		verify(player1, times(0)).turnPlay();
		
		assertEquals(player1, result.winner());
	}

}
