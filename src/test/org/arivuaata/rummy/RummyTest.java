package org.arivuaata.rummy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.arivuaata.rummy.Card.Rank;
import org.arivuaata.rummy.Card.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RummyTest {

	@Test
	void drawingPileHas3x53CardsOfAllRanksAndSuitsOnGameStart() {
		Map<Integer, Integer> cardBackCount = initCardBackCount();
		Map<Suit, Integer> suitCount = initSuitCount();
		Map<Card.Rank, Integer> rankCount = initRankCount();
		int jokerCount = 0;
		
		Set<Card> drawingPileOnGameStart = Rummy.drawingPileOnGameStart();
		for (Card card : drawingPileOnGameStart) {
			updateCardBackCount(cardBackCount, card);
			updateSuitCount(suitCount, card);
			updateRankCount(rankCount, card);
			
			if (card.isAJoker()) {
				jokerCount++;
			}
		}

		assertCardBackCount(cardBackCount);
		assertSuitCount(suitCount);
		assertRankCount(rankCount);
		
		assertEquals(3, jokerCount);
		assertEquals(Rummy.TOTAL_CARDS, drawingPileOnGameStart.size());
	}

	private void updateRankCount(Map<Rank, Integer> rankCount, Card card) {
		Rank rank = card.getRank();
		Integer count = rankCount.get(rank);
		if (count != null) {
			rankCount.put(rank, ++count);
		}
	}

	private Map<Rank, Integer> initRankCount() {
		Map<Rank, Integer> rankCount = new HashMap<>(13);
		for (Rank iterable_element : Rank.values()) {
			rankCount.put(iterable_element, 0);
		}
		return rankCount;
	}

	private void assertRankCount(Map<Rank, Integer> rankCount) {
		assertEquals(13, rankCount.size());
		for (Rank iterable_element : Rank.values()) {
			assertEquals(3 * 4, rankCount.get(iterable_element));
		}
	}

	private void updateSuitCount(Map<Suit, Integer> suitCount, Card card) {
		Suit suit = card.getSuit();
		Integer count = suitCount.get(suit);
		if (count != null) {
			suitCount.put(suit, ++count);
		}
	}

	private void assertSuitCount(Map<Suit, Integer> suitCount) {
		assertEquals(4, suitCount.size());
		for (Suit iterable_element : Suit.values()) {
			assertEquals(3 * 13, suitCount.get(iterable_element));
		}
	}

	private Map<Card.Suit, Integer> initSuitCount() {
		Map<Suit, Integer> suitCount = new HashMap<>(4);
		for (Suit iterable_element : Suit.values()) {
			suitCount.put(iterable_element, 0);
		}
		return suitCount;
	}

	private void assertCardBackCount(Map<Integer, Integer> cardBackCount) {
		for (int cardBack = 1; cardBack <= 3; cardBack++) {
			assertEquals(53, cardBackCount.get(cardBack), "cardBack" + cardBack);
		}
	}

	private void updateCardBackCount(Map<Integer, Integer> cardBackCount, Card card) {
		Integer cardBack = card.getCardBack();
		Integer count2 = cardBackCount.get(cardBack);
		if (count2 != null) {
			cardBackCount.put(cardBack, ++count2);
		}
	}

	private Map<Integer, Integer> initCardBackCount() {
		Map<Integer, Integer> cardBackCount = new HashMap<>(3);
		cardBackCount.put(1, 0);
		cardBackCount.put(2, 0);
		cardBackCount.put(3, 0);
		return cardBackCount;
	}

	@ParameterizedTest
	@CsvSource({
		"0, 3",
		"-10, 3",
		"1, 1",
		"2, 2"
	})
	void cardBackLimitedTo3Values(int inputCardBack, int expected) throws Exception {
		for (Card card : Card.cardsOfAllRanksAndSuits(inputCardBack)) {
			assertEquals(expected, card.getCardBack());
		}
	}
	
	@Test
	void totalCards() throws Exception {
		assertEquals(3 * 53, Rummy.TOTAL_CARDS);
	}
	
	@Test
	void drawingPileOnGameStartIsRandomized() throws Exception {
		Set<Card> pile1 = Rummy.drawingPileOnGameStart();
		Set<Card> pile2 = Rummy.drawingPileOnGameStart();
		
		int equalityCount = 0;
		int comparisonCount = 0;

		for (Iterator<Card> iterator1 = pile1.iterator(), iterator2 = pile2.iterator();
				iterator1.hasNext() && iterator2.hasNext();) {
			Card card1 = iterator1.next();
			Card card2 = iterator2.next();
			
			if (card1.equals(card2)) {
				equalityCount++;
			}
			comparisonCount++;
		}
		
		assertEquals(pile1, pile2);
		assertEquals(comparisonCount, Rummy.TOTAL_CARDS, "comparisonCount");
		assertTrue(equalityCount < comparisonCount);
	}
	
	@ParameterizedTest
	@CsvSource({
		"2, 2",
		"1, 2",
		"0, 2",
		"-5, 2",
		"3, 3",
		"4, 4",
		"5, 5",
		"6, 6",
		"7, 6",
		"10, 6",
		"20, 6"
	})
	void dealOnGameStart(int requestedTotalPlayers, int actualTotalPlayers) throws Exception {
		CardsArrangementAfterDealing arrangement = Rummy.deal(requestedTotalPlayers);
		
		Card gameJoker = arrangement.getGameJoker();
		assertTrue(gameJoker instanceof Card);
		
		Stack<Card> openPile = arrangement.getOpenPile();
		assertEquals(1, openPile.size());
		
		List<Set<Card>> playersCards = arrangement.getPlayersCards();
		assertEquals(actualTotalPlayers, playersCards.size());
		
		for (Set<Card> playerCards : playersCards) {
			assertEquals(13, playerCards.size());
		}
		
		Set<Card> drawingPile = arrangement.getDrawingPile();
		assertEquals(Rummy.TOTAL_CARDS - (1 + 1 + actualTotalPlayers*13), drawingPile.size());
		
		assertDisjointness(gameJoker, openPile, playersCards, drawingPile);
		assertTotality(gameJoker, openPile, playersCards, drawingPile);
	}

	private void assertTotality(Card gameJoker, Stack<Card> openPile,
			List<Set<Card>> playersCards, Set<Card> drawingPile) {
		Set<Card> allCards = new HashSet<>(drawingPile);
		allCards.add(gameJoker);
		allCards.addAll(openPile);
		
		for (Set<Card> playerCards : playersCards) {
			allCards.addAll(playerCards);
		}
		
		assertEquals(allCards, Rummy.drawingPileOnGameStart());
	}

	private void assertDisjointness(Card gameJoker, Stack<Card> openPile,
			List<Set<Card>> playersCards, Set<Card> drawingPile) {
		assertFalse(openPile.contains(gameJoker));
		assertFalse(drawingPile.contains(gameJoker));
		
		assertTrue(Collections.disjoint(openPile, drawingPile));
		
		for (int i = 0; i < playersCards.size() - 1; i++) {
			Set<Card> playerCards = playersCards.get(i);
			for (int j = i + 1; j < playersCards.size(); j++) {
				Set<Card> nextPlayerCards = playersCards.get(j);
				assertTrue(Collections.disjoint(playerCards, nextPlayerCards));
			}
		}
		
		for (Set<Card> playerCards : playersCards) {
			assertFalse(playerCards.contains(gameJoker));
			assertTrue(Collections.disjoint(playerCards, openPile));
			assertTrue(Collections.disjoint(playerCards, drawingPile));
		}
	}
	
	@Test
	void play() throws Exception {
		RummyUI rummyUI = null;
		Rummy.play(rummyUI);
	}
}
