package board;

import java.util.ArrayList;


public class Hand
{
	private ArrayList<UnitCard> cardsInHand; //keeps track of cards the player currently has
	private String playersHand;
	
	public Hand(Player player)
	{
		cardsInHand=new ArrayList<UnitCard>();
		playersHand=player.getName();
	}
	
	//still not sure about this
	public ArrayList<UnitCard> getCardsInHand()
	{
		return cardsInHand;
	}
	
	//just to keep track of who's hand we are looking at
	public String getPlayersHand()
	{
		return playersHand;
	}
	
	//we can only limit the player to get 3 cards at a time
	public void addCardtoHand(UnitCard card)
	{
		if(cardsInHand.size()<3)
		{
			cardsInHand.add(card);
		}
	}
	
	//we also need to remove the add right after since it will be placed
	public void removeCardFromHand(UnitCard card)
	{
		for(int i=0;i<cardsInHand.size();i++)
		{
			if(cardsInHand.get(i)==card)
			{
				cardsInHand.remove(card);
			}
		}
	}
}
