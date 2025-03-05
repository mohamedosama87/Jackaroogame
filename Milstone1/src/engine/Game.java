package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import engine.board.Board;
import model.Colour;
import model.card.Deck;
import model.card.Card;
import model.player.CPU;
import model.player.Player;

public class Game implements GameManager {
    private final Board board;                  
    private final ArrayList<Player> players;    
    private final ArrayList<Card> firePit;      
    private int currentPlayerIndex;
    private int turn;

    public Game(String playerName) throws IOException {
        
        ArrayList<Colour> colours = new ArrayList<>();
        
        Collections.addAll(colours, Colour.RED, Colour.GREEN, Colour.BLUE, Colour.YELLOW);
        Collections.shuffle(colours);

        
        board = new Board(colours, this);

       
        Deck.loadCardPool(board, this);

       
        players = new ArrayList<>();
       
        players.add(new Player(playerName, colours.get(0)));
        
        players.add(new CPU("CPU 1", colours.get(1), board));
        players.add(new CPU("CPU 2", colours.get(2), board));
        players.add(new CPU("CPU 3", colours.get(3), board));

        
        for (Player p : players) {
            p.setHand(Deck.drawCards());
        }

        currentPlayerIndex = 0;
        turn = 0;
        firePit = new ArrayList<>();
    }

}
