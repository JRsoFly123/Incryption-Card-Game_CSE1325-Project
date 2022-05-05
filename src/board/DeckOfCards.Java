package board;

import java.util.ArrayList;
import java.util.Collections;

class DeckOfCards {
	
	public ArrayList<UnitCard> deck;
	
	public DeckOfCards() {
		deck = new ArrayList<UnitCard>();
				
	}
	
	public ArrayList<UnitCard> getDeck(){
		return deck;
	}
	
	public void shuffleDeck() {
		Collections.shuffle(getDeck());
	}
	
	public void addCardToDeck(UnitCard card){
		deck.add(card);
	}
	
	public UnitCard drawOneCard() {
		UnitCard card = deck.get(0);
		deck.remove(0);
		return card;
	}
	
	

}
