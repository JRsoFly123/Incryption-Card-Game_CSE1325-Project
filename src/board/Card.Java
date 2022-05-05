package board;

import javax.swing.*;

class Card {

	// EDIT - JEREMIAH - 5/1/2022 - 11:36PM
	// Transition of "name" variable into the
	// "UnitCard" class
	private String owner;
	private int gridlocation;
	private ImageIcon image;

	public Card() {
		
	}

	public void setImage(ImageIcon image)
	{
		this.image = image;
	}

	public ImageIcon getImage()
	{
		return image;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
		
	}
	
	public int getGridlocation() {
		return gridlocation;
	}
	
	public void setGridlocation(int location) {
		this.gridlocation = location;
	}
	
}


