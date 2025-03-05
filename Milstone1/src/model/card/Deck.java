package model.card;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import engine.GameManager;
import model.card.standard.*;
import model.card.wild.*;
import engine.board.BoardManager;
public class Deck {
    private static final String CARDS_FILE = "cards.csv"; // CSV file in project root
    private static ArrayList<Card> cardsPool = new ArrayList<>();

    public static void loadCardPool(BoardManager boardManager, GameManager gameManager) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(CARDS_FILE));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int frequency = Integer.parseInt(parts[1]);
            Card card = null;
            int code = Integer.parseInt(parts[0]);
            if (code == 1) {
                card = new Ace(parts[2], parts[3], Suit.valueOf(parts[5].toUpperCase()), boardManager, gameManager);
            } 
            for (int i = 0; i < frequency; i++) {
                cardsPool.add(card);
            }
        }
        reader.close();
    }

    public static ArrayList<Card> drawCards() {
        Collections.shuffle(cardsPool);
        ArrayList<Card> drawn = new ArrayList<>();
        // Remove the first four cards
        for (int i = 0; i < 4 && !cardsPool.isEmpty(); i++) {
            drawn.add(cardsPool.remove(0));
        }
        return drawn;
    }
}
