package engine.board;

import java.util.ArrayList;
import java.util.Random;
import engine.GameManager;
import model.Colour;

public class Board implements BoardManager {
    private final GameManager gameManager;      
    private final ArrayList<Cell> track;          
    private final ArrayList<SafeZone> safeZones;   
    private int splitDistance;                    

    public Board(ArrayList<Colour> colourOrder, GameManager gameManager) {
        this.gameManager = gameManager;
        this.track = new ArrayList<>();
        this.safeZones = new ArrayList<>();
        this.splitDistance = 3;
        assignTrapCell(); 
        for (Colour c : colourOrder) {
            safeZones.add(new SafeZone(c));
        }
    }

    public void assignTrapCell() {
        Random rand = new Random();
        int index;
        do {
            index = rand.nextInt(track.size());
        } while (track.get(index).getCellType() != CellType.NORMAL || track.get(index).isTrap());
        track.get(index).setTrap(true);
    }

    @Override
    public int getSplitDistance() {
        return splitDistance;
    }

    public void setSplitDistance(int splitDistance) {
        this.splitDistance = splitDistance;
    }
}
