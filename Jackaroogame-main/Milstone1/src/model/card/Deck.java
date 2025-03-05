package model.card;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import engine.GameManager;
import model.card.standard.*;
import model.card.wild.*;
import engine.board.BoardManager;
public class Deck {
    private static final String CARDS_FILE = "Cards.csv";
    private static ArrayList<Card> cardsPool = new ArrayList<>();

    public static void loadCardPool(BoardManager boardManager, GameManager gameManager) throws IOException {
    	cardsPool = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(CARDS_FILE))){
        String line;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            if (values.length < 4) continue;
            
            String code = values[0];
            int frequency = Integer.parseInt(values[1]);
            String name = values[2];
            String description = values[3];
            
            for (int i= 0; i < frequency; i++) {
            	Card card;
            	if (Integer.parseInt(code)<14) {
            		String rank = values[4];
            		String suit = values[5];
            		Suit s = Suit.valueOf(suit);
            		switch(rank) {
            		case "1": card = new Ace(name, description, s, boardManager, gameManager);
            				break;
            		case "13": card = new King(name, description, s, boardManager, gameManager);
    						break;
            		case "12": card = new Queen(name, description, s, boardManager, gameManager);
    						break;
            		case "11": card = new Jack(name, description, s, boardManager, gameManager);
    						break;
            		case "10": card = new Ten(name, description, s, boardManager, gameManager);
    						break;
            		case "7": card = new Seven(name, description, s, boardManager, gameManager);
    						break;
            		case "5": card = new Five(name, description, s, boardManager, gameManager);
    						break;
            		case "4": card = new Four(name, description, s, boardManager, gameManager);
    						break;
    				default:
    					card = new Standard(name, description,Integer.parseInt(rank), s, boardManager, gameManager);
            		}
            	}
            	else {
            		int c = Integer.parseInt(code);
            		if (c==14) card = new Burner(name, description, boardManager, gameManager);
            		else card = new Saver(name, description, boardManager, gameManager); 
            	}
            	cardsPool.add(card);
            	}
       }
     }
        catch (IOException e) {
            e.printStackTrace();
        }
 }

    public static ArrayList<Card> drawCards() {
        Collections.shuffle(cardsPool);
        ArrayList<Card> drawn = new ArrayList<>();
        for (int i = 0; i < 4 && !cardsPool.isEmpty(); i++) {
            drawn.add(cardsPool.remove(0));
        }
        return drawn;
    }
}
