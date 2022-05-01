package board;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Game {
	
	private Player player;
	private Player opponent;
	private Player currentPlayer;
	private Player winner;
	
	public Game() {
		
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player newPlayer, int health) {
		this.player = newPlayer;
		player.lifepoints = lifepoints;
		
	}
	
	public Player getOpponent() {
		return opponent;
	}
	
	public void setOpponent(Player oppoenent, int health) {
		this.opponent = opponent;
		opponenent.health = health;
	}
	
	public Player getWinner() {
		return winner;
	}
	
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public void getCurrentWinner() {
		if(player.getHealth() <= 0) {
			setWinner(opponent);
		}
		
		if(opponent.getHealth() <= 0) {
			setWinner(player);
		}
	}
	
	public void switchPlayer() {
		if(winner == null) {
			if(currentPlayer == player) {
				currentPlayer = opponent;
			}
			else {
				currentPlayer = player;
			}
		}
	}
	
	
	public void createDeck(Player player, String fileName)
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			ArrayList<String[]> lines = new ArrayList<>();
			String line = "";
			String headerLine = reader.readLine();
			while((line = reader.readLine()) !=null) {
				String[] lineSplit = line.split(",");
				lines.add(lineSplit);
			}
			
			for(int i=0; i<lines.size(); i++) {
				UnitCard unitCard = new UnitCard(lines.get(i)[0], Integer.parseInt(lines.get(i)[1]), Integer.parseInt(lines.get(i)[2]));
				player.getDeck().addCardToDeck(unitCard);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	
	public void startNewGame() {
		player = new Player("Player 1",8000); //Player1 will need to be changed to whatever the user enters as their name
		opponent = new Player("Player 2",8000);// this one as well
		
		createDeck(player,"UnitDeck.csv");
		createDeck(opponent, "UnitDeck.csv");
		
		player.getDeck().shuffleDeck();
		opponent.getDeck().shuffleDeck();
		
		for(int i =0; i<5; i++) {
			player.drawCard();
			opponent.drawCard();
		}
		
		currentPlayer = player;
	}
	
	
}
