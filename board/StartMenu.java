package board;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StartMenu 
{
	JFrame frame;
	String player1Name;
	String player2Name;
	JPanel panel;
	private JButton startButton;
	private JButton helpButton;
	private JButton exitButton;

	public StartMenu() 
	{	
		frame = new JFrame("Card Masters");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900,600);
		
		panel = new JPanel(null);
		panel.setSize(900,600);
		panel.setBackground(new Color(1,10,17));
		
		startButton = new JButton("Start Game");
		helpButton = new JButton("Tutorial");
		exitButton = new JButton("Exit Game");
		startButton.setBounds(375,150,150,50);
		helpButton.setBounds(375,220,150,50);
		exitButton.setBounds(375,290,150,50);
		startButton.addActionListener(new ClickListener());
		helpButton.addActionListener(new ClickListener());
		exitButton.addActionListener(new ClickListener());
		
		panel.add(new Header());
		panel.add(startButton);
		panel.add(helpButton);
		panel.add(exitButton);
		
		
		frame.add(panel);
		frame.setVisible(true);
	}
	
	class Header extends JPanel 
	{
		Header() 
		{
			setSize(900, 200);
		}
		@Override
		public void paintComponent(Graphics g) 
		{
			g.setFont(new Font("Verdana", Font.BOLD, 50));
			g.setColor(Color.white);
			g.drawString("CARD MASTERS", 225, 100);
		}	
	}
	
	class ClickListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource() == startButton) 
			{
				JTextField player1Field = new JTextField(15);
				JTextField player2Field = new JTextField(15);
				
				JPanel infoPanel = new JPanel(new GridLayout(3,3));
				JLabel p1 = new JLabel("Player 1 Name:");
				JLabel p2 = new JLabel("Player 2 Name:");
				JLabel warning = new JLabel("Please try again");
				warning.setForeground(Color.red);
				
				infoPanel.add(p1);
				infoPanel.add(player1Field);
				infoPanel.add(p2);
				infoPanel.add(player2Field);
				
				int choice = JOptionPane.showConfirmDialog(frame, infoPanel, "Enter Player Information", JOptionPane.OK_CANCEL_OPTION);
				while (choice == JOptionPane.OK_OPTION && (player1Field.getText().equals("") || player2Field.getText().equals("")))
				{
					infoPanel.add(warning);
					choice = JOptionPane.showConfirmDialog(frame, infoPanel, "Enter Player Information", JOptionPane.OK_CANCEL_OPTION);
				}
				
				if (choice == JOptionPane.OK_OPTION)
				{
					player1Name = player1Field.getText();
					player2Name = player2Field.getText();
					panel.setVisible(false);
					
					//We can start game here, since both names have been received
				}	
			} 
			else if (e.getSource() == helpButton) 
			{
				
				JPanel instructionPanel = new JPanel();
				JTextArea instructions = new JTextArea("Two players take turns placing cards that have health and attack power on a 2x3 grid board. "
								+ "A player has three options when it is their turn. They can either PLACE the given card on the board, "
								+ "GET INFO on the card, or REROLL for a new card. "
						 		+ "Once the card is placed on the board and the player hits END TURN, the card attacks the opponent's card directly above/below. "
								+ "If there is no card above/below, the player will lose health depending on the card's attack power. "
								+ "Players keep taking turns placing cards until one player has taken too much damage.");
				instructions.setFont(new Font("Arial", Font.PLAIN, 18));
		        	instructions.setLineWrap(true);
		       	 	instructions.setWrapStyleWord(true);
		        	instructions.setOpaque(false);
		        	instructions.setEditable(false);
		        	instructions.setPreferredSize(new Dimension(470,225));
				instructionPanel.add(instructions);
				JOptionPane.showMessageDialog(frame,instructionPanel,"Tutorial",JOptionPane.INFORMATION_MESSAGE);
			} 
			else if (e.getSource() == exitButton) 
			{	
				int choice = JOptionPane.showConfirmDialog(frame,"Are you sure you want to exit?","Confirm Exit",JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) 
				{
					System.exit(0);
				}
			}
			
		}
		
	}
	
	public JFrame getJFrame() 
	{
		return frame;
	}
}
