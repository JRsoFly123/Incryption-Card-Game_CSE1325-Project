package board;
import cards.Card;

public class Player {
	
	private String name;
	public int health;
	private Hand hand;
	Field field;
	
	//thinkge
	public Player(String name,int health)
	{
		this.name=name;
		this.health=health;
		hand =new Hand(this);
		field=new Field(this);
	}
	
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	
	public void setHealth(int health)
	{
		this.health=health;
	}
	
	public Hand getHand()
	{
		return hand;
	}
	
	public Field getField()
	{
		return field;
	}
	
	//summon card function? not sure if we'll use this
	public void summonCard(UnitCard card)
	{
		field.setCard(card);
		hand.removeCardFromHand(card);
	}
	
	//attack opponent's card
	public void attackCard(UnitCard card, UnitCard opponentCard,Player opponent)
	{
		opponentCard.health=opponentCard.health-(card.getAttack());//getPower gets the cards attack
		
		//will be taken off the field if health is 0 or lower
		if(opponentCard.health<=0)
		{
			opponent.field.removeCard(opponentCard);
		}
	}
	
	//attack player directly
	public void attackOpponent(UnitCard card, Player opponent)
	{
		opponent.health=opponent.health-(card.getAttack());//getPower gets the cards attack
	}
	
	//function to know their turn has ended? not sure about this
	
	
	
	

}

	

	
	
