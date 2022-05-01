package board;

public class Player {
	
	private String name;
	public int health;
	//hand? don't know if the user should have the whole deck or just have the cards they chose
	//field this is the area where the user can place the cards
	
	public Player(String name,int health)
	{
		this.name=name;
		this.health=health;
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
	
	

	
	
