package board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.image.*;
import java.net.URL;

public class BoardUI extends JPanel implements ActionListener {

    JLabel playerIcon;
    BufferedImage avatar;

    public BoardUI(Player player,String selectedIcon)
    {
        super(new GridBagLayout());
        GridBagConstraints cardField = new GridBagConstraints();
        GridBagConstraints playerPanel = new GridBagConstraints();
        GridBagConstraints miscSection = new GridBagConstraints();

//        Icon iconInHand = new ImageIcon("CardImage/"+player.getHand().getCardsInHand().get(0)+".png");

        Icon iconInHand = new ImageIcon("CardImage/Archer.png");
        Icon cardIcon1 = new ImageIcon("CardImage/Ninja.png");
        Icon cardIcon2 = new ImageIcon("CardImage/Mage.png");
        ArrayList<Icon> cardIcons = new ArrayList<Icon>();
        cardIcons.add(cardIcon1);
        cardIcons.add(cardIcon2);

        JButton cardInHand=new JButton(iconInHand);
        cardInHand.setBackground(Color.RED);
        JButton endTurn=new JButton("endTurn");
        JButton reRoll=new JButton("Re-Roll");
        JLabel description = new JLabel("<NONE>");
        JLabel playerName = new JLabel(player.getName());
        cardInHand.setPreferredSize(new Dimension(60,70));

        // Distinctly need to set the image up differently
        playerIcon = new JLabel();
        playerIcon.setFont(playerIcon.getFont().deriveFont(Font.ITALIC));
 //       playerIcon.setHorizontalAlignment(JLabel.CENTER);
        makePlayerIcon(player,selectedIcon);
        playerIcon.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        //The preferred size is hard-coded to be the width of the
        //widest image and the height of the tallest image + the border.
        playerIcon.setPreferredSize(new Dimension(120,122+10));

        ArrayList<JButton> cards = new ArrayList<JButton>();
        ArrayList<JButton> playerButtons = new ArrayList<JButton>();
        playerButtons.add(endTurn);
        playerButtons.add(reRoll);
        JLabel gameName = new JLabel("Card-Game-Name");
        gameName.setHorizontalAlignment(JLabel.RIGHT);
        miscSection.fill = GridBagConstraints.HORIZONTAL;
        miscSection.weightx = 0.0;
        miscSection.gridx = 0;
        miscSection.gridy = 0;

        ButtonGroup cardsOnField=new ButtonGroup();
        for(int i = 0; i < 6;i++)
        {
        //    String cardName = "Card "+(i+1);
            cards.add(new JButton(""));
            cards.get(i).setPreferredSize(new Dimension(60,70));
            cardField.fill = GridBagConstraints.HORIZONTAL;
            cardField.ipady = 40;
            cardField.weightx = 0.5;
            cardField.gridx = i;
            cardField.gridy = 1;
            cards.get(i).setBackground(Color.cyan);
            add(cards.get(i),cardField);
            cardsOnField.add(cards.get(i));

            // The playerPanel and width of the Title
            // of the game is dependent
            playerPanel.gridx = i+1;
            miscSection.gridwidth = i+1;
        }
        cardField.gridx = cards.size()-1;
        cardField.gridy = 2;
        add(cardInHand,cardField);

        playerPanel.weightx = 0.7;
        playerPanel.gridy = 0;
        add(playerName,playerPanel);
        playerPanel.gridy = 1;
        add(playerIcon,playerPanel);
        add(gameName, miscSection);


        for(int i=0;i < playerButtons.size();i++)
        {
            playerPanel.gridy = i+2;
            miscSection.gridy = i+3;
            add(playerButtons.get(i),playerPanel);
        }
        add(description,miscSection);
    }

    protected void makePlayerIcon(Player player, String name)
    {
        ImageIcon icon = createImageIcon("CardImage/"+name+".png");
        playerIcon.setIcon(icon);
        playerIcon.setToolTipText(player.getName()+" || HEALTH: " + player.getHealth());
        if (icon != null)
        {
            playerIcon.setText(null);
        }
        else
        {
            playerIcon.setText("Image not found");
        }
    }

    protected static ImageIcon createImageIcon(String path)
    {
        try
        {
            if (path != null)
            {
                return new ImageIcon(path);
            }
        }
        catch(Exception e)
        {
                System.err.println("Couldn't find file: " + path);
                return null;
        }
        return null;
    }

    public void actionPerformed(ActionEvent e)
    {

    }

    // This function is explicitedly used to switch the panels
    // of a frame to an opponents from a start menu
    public static void switchPanels(JFrame frame, JPanel panel)
    {

    }

    // This function may be changed later to fit the start menu
    // But this will change the UI of the frame we have to the
    // Board Game UI where the panel will change constantly
    // for each player (two players,two board UI's in a regular game)
    public static void createAndShowGUI(Player player,String iconName)
    {

        // Create and set up the window
        JFrame frame = new JFrame("CARD-GAME-TEST");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        JComponent newContentPane = new BoardUI(player,iconName);
        frame.setContentPane(newContentPane);

        // Display the Window
        frame.pack();
        frame.setVisible(true);
    }
}
