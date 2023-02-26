package org.arivuaata.rummy;

import java.util.Set;
import java.util.Stack;

class CardsArrangementAfterDealing {

	private final Card gameJoker;
	private final Set<Card> drawingPile;
	private final Stack<Card> openPile;

	public CardsArrangementAfterDealing(Card gameJoker, Set<Card> drawingPile, Stack<Card> openPile) {
		// TODO Auto-generated constructor stub
		this.gameJoker = gameJoker;
		this.drawingPile = drawingPile;
		this.openPile = openPile;
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

}
