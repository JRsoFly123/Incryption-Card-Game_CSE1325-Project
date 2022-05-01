package cardgame;

public class UnitCard extends Card {
	
	private int health;
	private int attack;
	private String name;
	private boolean attacked;
	
	public UnitCard(String name, int attack, int health) {
		
		this.name = name;
		this.attack = attack;
		this.health = health;
		attacked = false;
		
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
	
	
}
