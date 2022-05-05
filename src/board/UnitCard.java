package board;

import javax.swing.*;

public class UnitCard extends Card {

	private String name;
	private int health;
	private int attack;
	private boolean attacked;

	public UnitCard() {}
	public UnitCard(String name, int attack, int health) {
		this.name = name;
		this.attack = attack;
		this.health = health;
		attacked = false;
		setImage(new ImageIcon("CardImage/"+name+".png"));
	}

	public String getName(){
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public boolean getAttacked(){
		return attacked;
	}
	
	public void setGetAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	// EDIT - JEREMIAH - 5/1/2022 - 11:23PM
	public int getHealth() { return health;}

	public void setHealth(int health) { this.health = health;}
}
