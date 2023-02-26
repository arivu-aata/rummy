package org.arivuaata.rummy;

import java.util.List;
import java.util.Set;
import java.util.Stack;

class CardsArrangementAfterDealing {

	private final Card gameJoker;
	private final Stack<Card> openPile;
	private final List<Set<Card>> playersCards;
	private final Set<Card> drawingPile;

	public CardsArrangementAfterDealing(Card gameJoker, Stack<Card> openPile,
			List<Set<Card>> playersCards, Set<Card> drawingPile) {
		this.gameJoker = gameJoker;
		this.openPile = openPile;
		this.playersCards = playersCards;
		this.drawingPile = drawingPile;
	}

	public Card getGameJoker() {
		return gameJoker;
	}

	public Set<Card> getDrawingPile() {
		return drawingPile;
	}

	public Stack<Card> getOpenPile() {
		return openPile;
	}

	public List<Set<Card>> getPlayersCards() {
		return playersCards;
	}

}
