package cardgame;

import java.util.ArrayList;

public class Arena {
	
	private String arenaSide;
	private ArrayList<UnitCard> unitZone;
	private ArrayList<Card> banished ;
	private String turn;
	
	public Arena(Player player) {
		arenaSide = player.getPlayerName();
		unitZone = new ArrayList<UnitCard>();
		banished  = new ArrayList<Card>();
		turn = "Turn 1";
	}
	
	public String getArenaSide() {
		return arenaSide;
	}
	
	public void setArenaSide(String arenaSide) {
		this.arenaSide = arenaSide;
	}
	
	public ArrayList<UnitCard> getUnit(){
		return unitZone;
	}
	
	public void setUnit(UnitCard unitCard) {
		if(unitZone.size() < 6) {
			unitZone.add(unitCard);
		}
	}
	
	public String getTurn() {
		return turn;
	}
	
	public void setTurn(String turn) {
		this.turn = turn;
	}
	
	public ArrayList<Card> getBanished(){
		return banished;
	}
	
	public void removeUnit(UnitCard unitCard) {
		unitZone.remove(unitCard);
	}
	
	public void addToBanished(Card card) {
		banished.add(card);
	}
	

}
