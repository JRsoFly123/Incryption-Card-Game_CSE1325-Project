package board;

public class UnitCard extends Card {
	
	private int health;
	private int attack;
	private String name;
	private boolean attacked;
	
	public UnitCard(String name, int attack, int health) {
		
		//might have to super the name
		this.name = name;
		this.attack = attack;
		this.health = health;
		attacked = false;
	}
	
	// EDIT - JEREMIAH - 5/3 - 5:38PM
	public String getName() {
		return name;
	}

	// EDIT - JEREMIAH - 5/3 - 5:38PM
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
}
