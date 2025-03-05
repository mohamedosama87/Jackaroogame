package model.player;

import model.Colour;

public class Marble {
    private final Colour colour;  // READ ONLY

    public Marble(Colour colour) {
        this.colour = colour;
    }

	public Colour getColour() {
		return colour;
	}

  
}