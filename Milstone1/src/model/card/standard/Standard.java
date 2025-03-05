package model.card.standard;

import engine.board.BoardManager;
import engine.GameManager;
import model.card.Card;

public class Standard extends Card {
    private final int rank;    // READ ONLY
    private final Suit suit;   // READ ONLY

    public Standard(String name, String description, int rank, Suit suit,
                    BoardManager boardManager, GameManager gameManager) {
        super(name, description, boardManager, gameManager);
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

	public Suit getSuit() {
		return suit;
	}

   
}