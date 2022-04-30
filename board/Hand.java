package board;

import java.util.ArrayList;
import cards.Card;

public class Hand 
{
	private ArrayList<Card> cardsInHand; //keeps track of cards the player currently has
	private String playersHand;
	
	public Hand(Player player)
	{
		cardsInHand=new ArrayList<Card>();
		playersHand=player.getName();
	}
	
	//still not sure about this
	public ArrayList<Card> getCardsInHand()
	{
		return cardsInHand;
	}
	
	//just to keep track of who's hand we are looking at
	public String getPlayersHand()
	{
		return playersHand;
	}
	
	//we can only limit the player to get 2 cards at a time
	public void addCardtoHand(Card card)
	{
		if(cardsInHand.size()<2)
		{
			cardsInHand.add(card);
		}
	}
	
	//we also need to remove the card right after since it will be placed
	public void removeCardFromHand(Card card)
	{
		for(int i=0;i<cardsInHand.size();i++)//doubt we need this since we know which cards are in the hand
		{
			if(cardsInHand.get(i)==card)
			{
				cardsInHand.remove(card);
			}
		}
	}
}
