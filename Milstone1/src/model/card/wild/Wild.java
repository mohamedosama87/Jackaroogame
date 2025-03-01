package model.card.wild;

import engine.board.BoardManager;
import engine.GameManager;
import model.card.Card;

public abstract class Wild extends Card {
    public Wild(String name, String description, BoardManager boardManager, GameManager gameManager) {
        super(name, description, boardManager, gameManager);
    }
}