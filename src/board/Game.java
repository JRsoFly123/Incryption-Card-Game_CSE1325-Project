package board;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
	
	private Player player;
	private Player opponent;
	private Player currentPlayer;
	private Player winner;
	
	public Game() {
		
	}
	public Game(Player player, Player opponent)
	{
		this.player = player;
		this.opponent = opponent;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player newPlayer, int health) {
		this.player = newPlayer;
		player.health = health;
		
	}
	
	public Player getOpponent() {
		return opponent;
	}
	
	public void setOpponent(Player opponent, int health) {
		this.opponent = opponent;
		opponent.health = health;
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
	
	
	public void createDeck(String fileName) {
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
				opponent.getDeck().addCardToDeck(unitCard); // JEREMIAH EDIT - 5/1/2022 - 11:16PM
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	// JEREMIAH EDIT - 5/1/2022 - 11:16
	// This edit is to change the function for the new
	// change of two players and various files of decks. This change is evident by every *.
	public void startNewGame(String player1_name, String player2_name, String filename,JFrame frame) { // *
		this.player = new Player(player1_name,8000,"Wizard");    // *
		this.opponent = new Player(player2_name,8000,"Ninja");  // *
		this.player.setOpponent(opponent);
		this.opponent.setOpponent(player);

		System.out.println("(DEBUG) (startNewGame || before drawing) Player Name: "+player.getName());
		System.out.println("(DEBUG) (startNewGame || before drawing) Opponent Name: "+player.getOpponent().getName());


		createDeck(filename); // *
		createDeck(filename);
		
		player.getDeck().shuffleDeck();
		opponent.getDeck().shuffleDeck();

		// Drawing and setting cards down
		for(int i =0; i<2; i++)
		{
			player.drawCard();
			UnitCard cardPlay = player.getHand().getCardsInHand().get(i);
			player.field.setCard(cardPlay);
			opponent.drawCard();
			UnitCard cardOpp = opponent.getHand().getCardsInHand().get(i);
			opponent.field.setCard(cardOpp);
		}
		currentPlayer = player;


		System.out.println("(DEBUG) (startNewGame || After drawing) Player Name: "+player.getName());
		System.out.println("(DEBUG) (startNewGame || After drawing) Opponent Name: "+player.getOpponent().getName());

		// Create and set up the window
		CardLayout cardLayout = new CardLayout();
		JPanel mainPanel = new JPanel(cardLayout);
		BoardUI playerBoard = new BoardUI(player,mainPanel,cardLayout,player.getIconName());
		BoardUI opponentBoard = new BoardUI(opponent,mainPanel,cardLayout,player.getIconName());
		player.setBoard(playerBoard);
		opponent.setBoard(opponentBoard);
		mainPanel.add(player.getBoard(), player1_name);
		mainPanel.add(opponent.getBoard(), player2_name);

		// Now it's time to run the GUI!
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run() {BoardUI.createAndShowGUI(frame,mainPanel);}
		});


	}
	
	
}
