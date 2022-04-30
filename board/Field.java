package board;

import java.util.ArrayList;
import cards.Card;

public class Field 
{
	
	private String fieldSide;
	private ArrayList<Card> cards;
	
	public Field(Player player) 
	{
		fieldSide=player.getName();//to check the name of the player
		cards =new ArrayList<Card>();
	}
	
	public void setFieldSide(String fieldSide)
	{
		this.fieldSide=fieldSide;
	}
	
	public String getFieldSide()
	{
		return fieldSide;
	}
	
	public ArrayList<Card> getCards()
	{
		return cards;
	}
	
	//size 3 since there are only 3 places to set cards
	public void setCard(Card card)
	{
		if(cards.size()<3)
		{
			cards.add(card);
		}
	}
	
	public void removeCard(Card card)
	{
		cards.remove(card);
	}
	
	
	

}
