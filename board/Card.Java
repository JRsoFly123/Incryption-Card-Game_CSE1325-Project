package board;

import javax.swing.*;

public class Card{

	// EDIT - JEREMIAH - 5/1/2022 - 11:36PM
	// Transition of "name" variable into the
	// "UnitCard" class
	private String owner;
	private String location;
	private ImageIcon image;

	public Card() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
		
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
}


