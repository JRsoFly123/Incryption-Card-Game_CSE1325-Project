package board;

import java.util.ArrayList;
import cards.Card;

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
	
	//size 3 since there are only 3 places to set cards
	public void setCard(UnitCard card)
	{
		if(cards.size()<3)
		{
			cards.add(card);
		}
	}
	
	public void removeCard(UnitCard card)
	{
		cards.remove(card);
	}
}
