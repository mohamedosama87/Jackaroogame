package model.card.standard;
import engine.board.BoardManager;
import engine.GameManager;
public class Jack extends Standard {


	public Jack(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 11, suit, boardManager, gameManager);
}
}
