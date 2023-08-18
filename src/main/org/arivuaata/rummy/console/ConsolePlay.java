package org.arivuaata.rummy.console;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.arivuaata.rummy.DealSettings;
import org.arivuaata.rummy.PlayADealResult;
import org.arivuaata.rummy.Player.Move;
import org.arivuaata.rummy.TurnPlayResult;

public class ConsolePlay {

	public static PlayADealResult playADeal(DealSettings dealSettings, List<ConsolePlayer> players) {
		// TODO Auto-generated method stub
		int dealerIndex = players.indexOf(dealSettings.getDealer());
		ConsolePlayer dealer = players.get(dealerIndex);
		
		dealer.deal(players);
		
		int totalPlayers = players.size();
		List<Integer> playOrder = playOrder(dealerIndex, totalPlayers);
		
		List<ConsolePlayer> orderedPlayers = orderPlayers(players, playOrder);

		for (Iterator<ConsolePlayer> iterator = orderedPlayers.iterator(); iterator.hasNext();) {
			if (orderedPlayers.size() == 1) {
				break;
			}
			
			ConsolePlayer playingPlayer = iterator.next();
			TurnPlayResult tpResult = playingPlayer.turnPlay();
			
			if (tpResult.getMove() == Move.DROP) {
				iterator.remove();
			}
		}
		
		if (orderedPlayers.size() == 1) {
			PlayADealResult result = new PlayADealResult(orderedPlayers.get(0));
			return result;
		}
		
		return null;
	}

	private static List<ConsolePlayer> orderPlayers(List<ConsolePlayer> players, List<Integer> playOrder) {
		List<ConsolePlayer> orderedPlayers = new ArrayList<>(players.size());
		for (int i : playOrder) {
			orderedPlayers.add(players.get(i));
		}
		return orderedPlayers;
	}

	private static List<Integer> playOrder(int dealerIndex, int totalPlayers) {
		List<Integer> playOrder = new ArrayList<>(totalPlayers);
		
		for (int i = dealerIndex + 1; i < totalPlayers; i++) {
			playOrder.add(i);
		}
			
		for (int i = 0; i <= dealerIndex; i++) {
			playOrder.add(i);
		}
		
		return playOrder;
	}

}
