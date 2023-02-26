package org.arivuaata.rummy;

import java.util.Set;

class CardsArrangementAfterDealing {

	private final Card gameJoker;
	private final Set<Card> drawingPile;

	public CardsArrangementAfterDealing(Card gameJoker, Set<Card> drawingPile) {
		// TODO Auto-generated constructor stub
		this.gameJoker = gameJoker;
		this.drawingPile = drawingPile;
	}

	public Card getGameJoker() {
		return gameJoker;
	}

	public Set<Card> getDrawingPile() {
		return drawingPile;
	}

}
