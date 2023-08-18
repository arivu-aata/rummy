package org.arivuaata.rummy;

public class PlayADealResult {

	private final Player winner;

	public PlayADealResult(Player winner) {
		this.winner = winner;
	}

	public Player winner() {
		return winner;
	}

}
