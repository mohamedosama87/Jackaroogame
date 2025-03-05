package engine.board;

import java.util.ArrayList;
import java.util.Random;

import engine.Game;
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
        initializeTrack();
        for(int i = 0;i<8;i++)
        	assignTrapCell(); 
       for(int i = 0;i<4;i++){
    	   Colour c = colourOrder.get(i);
    	   safeZones.add(new SafeZone(c));
       }
    }
    
    public ArrayList<Cell> getTrack() {
		return track;
	}
    
    
	public ArrayList<SafeZone> getSafeZones() {
		return safeZones;
	}
	public void initializeTrack() {
	    for (int i = 0; i < 100; i++) {
	        track.add(new Cell(CellType.NORMAL));
	    }

	    track.set(0, new Cell(CellType.BASE));
	    for (int i = 1; i < 4; i++) {
	        int basePosition = i * 25;
	        track.set(basePosition, new Cell(CellType.BASE));
	        track.set(basePosition-2,new Cell(CellType.ENTRY));
	     }
	    track.set(98,new Cell(CellType.ENTRY));
	}
	private void assignTrapCell() {
        Random rand = new Random();
        int index= 0;
         while (track.get(index).getCellType() != CellType.NORMAL || track.get(index).isTrap()){
        	 index = rand.nextInt(track.size());
         }
        track.get(index).setTrap(true);
    }

    public int getSplitDistance() {
        return splitDistance;
    }

    public void setSplitDistance(int splitDistance) {
        this.splitDistance = splitDistance;
    }
  
}
