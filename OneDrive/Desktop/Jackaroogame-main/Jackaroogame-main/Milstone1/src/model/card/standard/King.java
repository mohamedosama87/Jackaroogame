package model.card.standard;
import engine.board.BoardManager;
import engine.GameManager;
public class King extends Standard {


	public King(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 13, suit, boardManager, gameManager);
}
}
