package org.arivuaata.rummy;

import java.util.HashSet;
import java.util.Set;

public class Card {

	private static final class Joker extends Card {

		public Joker(int cardBack) {
			super(cardBack, null, null);
		}

	}

	enum Rank {
		ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN,
		EIGHT, NINE, TEN, JACK, QUEEN, KING
	}

	enum Suit {
		CLUBS, DIAMONDS, HEARTS, SPADES
	}

	private Rank rank;
	private Suit suit;
	private int cardBack;

	private Card(int cardBack, Suit suit, Rank rank) {
		this.cardBack = limitCardBack(cardBack);
		this.suit = suit;
		this.rank = rank;
	}

	private int limitCardBack(int cardBack2) {
		switch (cardBack2) {
		case 1: return 1;
		case 2: return 2;
		default: return 3;
		}
	}

	public static Set<Card> cardsOfAllRanksAndSuits(int cardBack) {
		Set<Card> cardsOfAllRanksAndSuits = new HashSet<>(52);
		
		for (Suit suit : Card.Suit.values()) {
			for (Rank rank : Card.Rank.values()) {
				cardsOfAllRanksAndSuits.add(new Card(cardBack, suit, rank));
			}
		}
		
		cardsOfAllRanksAndSuits.add(new Joker(cardBack));
		
		return cardsOfAllRanksAndSuits;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Card) {
			Card other = (Card) obj;
			
			if (suit == other.suit
			 && rank == other.rank) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return ("" + cardBack + suit + rank).hashCode();
	}

	public Integer getCardBack() {
		return cardBack;
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	public boolean isAJoker() {
		return this instanceof Joker;
	}

}
