package org.arivuaata.rummy;

import java.util.HashSet;
import java.util.Set;

public class Rummy {

	public static final int TOTAL_CARDS = 3 * 53;

	private static Set<Card> setOf3x52CardsOfAllRanksAndSuits() {
		Set<Card> setOf3x52CardsOfAllRanksAndSuits = new HashSet<>(TOTAL_CARDS);
		
		for(int i = 0; i < 3; i++) {
			setOf3x52CardsOfAllRanksAndSuits.addAll(Card.cardsOfAllRanksAndSuits(i));
		}
		
		return setOf3x52CardsOfAllRanksAndSuits;
	}

	public static Set<Card> drawingPileOnGameStart() {
		return setOf3x52CardsOfAllRanksAndSuits();
	}

}
