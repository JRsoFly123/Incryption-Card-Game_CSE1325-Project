package board;

import java.util.ArrayList;


public class Field 
{
	
	private String fieldSide;
	private ArrayList<UnitCard> cards;
	
	public Field(Player player) 
	{
		fieldSide=player.getName();//to check the name of the player ummm thinkge
		cards =new ArrayList<UnitCard>();
	}
	
	public void setFieldSide(String fieldSide)
	{
		this.fieldSide=fieldSide;
	}
	
	public String getFieldSide()
	{
		return fieldSide;
	}
	
	public ArrayList<UnitCard> getCards()
	{
		return cards;
	}
	
	//size 3 since there are only 2 places to set cards
	public void setCard(UnitCard card)
	{
		if(cards.size()<3)
		{
			cards.add(card);
		}
	}
	// this is when the card's hp is 0
	public void removeCard(UnitCard card)
	{
		cards.remove(card);
	}
}

