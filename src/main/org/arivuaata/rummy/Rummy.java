package org.arivuaata.rummy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Rummy {

	public static final int TOTAL_CARDS = 3 * 53;
//	private static int randomized_call_num;

	private static Set<Card> setOf3x53CardsOfAllRanksAndSuits() {
		Set<Card> setOf3x53CardsOfAllRanksAndSuits = new HashSet<>(TOTAL_CARDS);
		
		for(int i = 0; i < 3; i++) {
			setOf3x53CardsOfAllRanksAndSuits.addAll(Card.cardsOfAllRanksAndSuits(i));
		}
		
		return setOf3x53CardsOfAllRanksAndSuits;
	}

	public static Set<Card> drawingPileOnGameStart() {
		return randomized(setOf3x53CardsOfAllRanksAndSuits());
	}

	private static Set<Card> randomized(Set<Card> cards) {
//		randomized_call_num++;
		
		Random rand = new Random();
		int cardsCount = cards.size();
		Map<Integer, Set<Card>> randomizedCardsMap = new HashMap<>(cardsCount);
		
		for (Card card : cards) {
			int rand_int1 = rand.nextInt(cardsCount);// + randomized_call_num;
			
			Set<Card> set = randomizedCardsMap.get(rand_int1);
			if (set == null) {
				randomizedCardsMap.put(rand_int1, new LinkedHashSet<>(Arrays.asList(card)));
			} else {
				set.add(card);
			}
		}
		
		Set<Card> randomizedCards = new LinkedHashSet<>();
		for (Set<Card> set : randomizedCardsMap.values()) {
			randomizedCards.addAll(set);
		}

		return randomizedCards;
	}

}
