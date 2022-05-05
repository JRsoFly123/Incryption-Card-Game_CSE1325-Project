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
    ArrayList<JButton> cards = new ArrayList<JButton>();
    ArrayList<JButton> playerButtons = new ArrayList<JButton>();
    JButton endTurn = new JButton("endTurn");
    JButton reRoll = new JButton("Re-Roll");
    JButton cardInHand;
    JLabel description;
    JLabel playerName;
    Player player;
    JPanel mainPanel;
    CardLayout cardLayout;
    GridBagConstraints cardField = new GridBagConstraints();
    GridBagConstraints playerPanel = new GridBagConstraints();
    GridBagConstraints miscSection = new GridBagConstraints();
    Object cardGrid[];

    public BoardUI(Player player, JPanel mainPanel, CardLayout cardLayout, String iconName) {
        // Setting variables from Class
        super(new GridBagLayout());
        this.player = player;
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;

        // Right now getting Icon for the two cards drawn and placement in dialog
        String[] startingCards = new String[2];
        System.out.println("[DEBUG] START GUI...handsize: " + player.getHand().getCardsInHand().size());
        for (int i = 0; i < player.getHand().getCardsInHand().size(); i++) {
            startingCards[i] = player.getHand().getCardsInHand().get(i).getName();
            System.out.println("[DEBUG] START GUI...CardsList: " + startingCards[i]);
        }
        //  JComboBox startCardsComboBox = new JComboBox(startingCards);

        /* TEST INFORMATION
        Icon iconInHand = new ImageIcon("CardImage/Archer.png");
        Icon cardIcon1 = new ImageIcon("CardImage/Ninja.png");
        Icon cardIcon2 = new ImageIcon("CardImage/Mage.png");
        ArrayList<Icon> cardIcons = new ArrayList<Icon>();
        cardIcons.add(cardIcon1);
        cardIcons.add(cardIcon2);*/

        // Setting up all buttons
        cardInHand = new JButton("");
        cardInHand.setBackground(Color.RED);
        description = new JLabel("<NONE>");
        playerName = new JLabel(this.player.getName());
        cardInHand.setPreferredSize(new Dimension(60, 70));
        playerButtons.add(endTurn);
        playerButtons.add(reRoll);
        cardInHand.addActionListener(this);
        for (int i = 0; i < playerButtons.size(); i++) {
            playerButtons.get(i).addActionListener(this);
        }


        // Distinctly need to set the image up differently
        playerIcon = new JLabel();
        playerIcon.setFont(playerIcon.getFont().deriveFont(Font.ITALIC));
        //       playerIcon.setHorizontalAlignment(JLabel.CENTER);
        makePlayerIcon(iconName);
        playerIcon.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        //The preferred size is hard-coded to be the width of the
        //widest image and the height of the tallest image + the border.
        playerIcon.setPreferredSize(new Dimension(120, 122 + 10));

        JLabel gameName = new JLabel("Card-Game-Name");
        gameName.setHorizontalAlignment(JLabel.RIGHT);
        miscSection.fill = GridBagConstraints.HORIZONTAL;
        miscSection.weightx = 0.0;
        miscSection.gridx = 0;
        miscSection.gridy = 0;

        ButtonGroup cardsOnField = new ButtonGroup();
        for (int i = 0; i < 6; i++) {
            cards.add(new JButton(""));
            cards.get(i).setPreferredSize(new Dimension(60, 70));
            cardField.fill = GridBagConstraints.HORIZONTAL;
            cardField.ipady = 40;
            cardField.weightx = 0.5;
            cardField.gridx = (i % 3);
            cardField.gridy = (i / 3) + 1;
            cards.get(i).setBackground(Color.cyan);
            cards.get(i).addActionListener(this);
            add(cards.get(i), cardField);
            cardsOnField.add(cards.get(i));

            // The playerPanel and width of the Title
            // of the game is dependent
            playerPanel.gridx = i + 1;
            miscSection.gridwidth = i;
        }
        cardField.gridx = (cards.size() - 1) % 3;
        cardField.gridy = (((cards.size() - 1) / 3) + 1) + 1;
        add(cardInHand, cardField);

        playerPanel.weightx = 0.7;
        playerPanel.gridy = 0;
        add(playerName, playerPanel);
        playerPanel.gridy = 1;
        add(playerIcon, playerPanel);
        add(gameName, miscSection);


        for (int i = 0; i < playerButtons.size(); i++) {
            playerPanel.gridy = i + 2;
            miscSection.gridy = i + 3;
            add(playerButtons.get(i), playerPanel);
        }
        add(description, miscSection);

        // Now with every button in place, time to introduce cards and
        // place them!

        Object cardGrid[] = new Object[cards.size()];
        for (int v = 0; v < cards.size(); v++) {
            cardGrid[v] = v + 1;
        }

        int maxStartingCards = startingCards.length;
        for (int i = 0; i < maxStartingCards; i++)
        {
            String result = (String) JOptionPane.showInputDialog(null, "Which cards do you want(" + player.getName() + ")?", "Start Hands(" + player.getName() + ")"
                    , JOptionPane.QUESTION_MESSAGE, player.getImage(), startingCards, startingCards[0]);
            System.out.println("[DEBUG] RESULT STARTING DIALOG FOR CARDS: " + result);
            int handMaxSize = player.getHand().getCardsInHand().size();
            for (int k = 0; k < handMaxSize; k++)
            {
                if (result == player.getHand().getCardsInHand().get(k).getName())
                {
                    int cardSlot = (int) JOptionPane.showInputDialog(null, "Which slot do you want to place the card?", "Card Placement",
                            JOptionPane.QUESTION_MESSAGE, player.getImage(), cardGrid, cardGrid[0]);
                    System.out.println("[DEBUG] cardSlot Dialog for Cards: " + cardSlot);
                    // Getting rid of the slot option for next input...
                    cardGrid[cardSlot - 1] = null;
                    for(int g = cardSlot - 1; g < cardGrid.length-1;g++)
                    {
                        cardGrid[g] = cardGrid[g+1];
                    }
                   /* for (int j = 0; j < cardGrid.length; j++)
                    {
                        if (cardSlot == (int) cardGrid[j])
                        {
                            cardGrid[j] = null;
                            int currentj = j;
                            for (j = currentj; j < cardGrid.length - 1; j++)
                            {
                                cardGrid[j] = cardGrid[j + 1];
                            }
                            cardGrid[cardGrid.length - 1] = null;
                            break;
                        }
                    }*/
                    // cardSlot 1 is input:
                    // 0 - 1 - 2
                    // 3 - 4 - 5
                    // Output desired is 0
                    cards.get(cardSlot - 1).setIcon(player.getHand().getCardsInHand().get(k).getImage());
                    cards.get(cardSlot - 1).setToolTipText("HEALTH: " + player.getHand().getCardsInHand().get(k).getHealth() + "\n"
                            + "ATTACK: " + player.getHand().getCardsInHand().get(k).getAttack());
                    cards.get(cardSlot - 1).setName(player.getHand().getCardsInHand().get(k).getName());
                    player.getHand().getCardsInHand().get(k).setGridlocation(cardSlot);
                    player.summonCard(player.getHand().getCardsInHand().get(k));
                    System.out.println("[DEBUG] k: " + k);
                    System.out.println("[DEBUG] StartingCards: " + startingCards[k]);
                    /*if (result == startingCards[i]) {
                        if (startingCards[i + 1] != "\0") {
                            startingCards[i] = startingCards[i + 1];
                            startingCards[i + 1] = "\0";
                        } else {
                            startingCards[i] = "\0";
                        }
                        break;
                    } */

                    for (int g = 0; g < startingCards.length; g++)
                    {
                        if(startingCards[g] == result)
                        {
                            startingCards[g] = null;
                            for(int v = g; v < startingCards.length-1;v++)
                            {
                                startingCards[g] = startingCards[g+1];
                            }
                                break;
                        }
                    }

                    break;
                }
            }
        }
        // BoardUI name now...
        this.setName(this.player.getName());
    }

    protected void makePlayerIcon(String iconName) {
        ImageIcon icon = createImageIcon("CardImage/" + iconName + ".png");
        playerIcon.setIcon(icon);
        playerIcon.setToolTipText(player.getName() + " || HEALTH: " + player.getHealth());
        if (icon != null) {
            playerIcon.setText(null);
        } else {
            playerIcon.setText("Image not found");
        }
    }

    protected static ImageIcon createImageIcon(String path) {
        try {
            if (path != null) {
                return new ImageIcon(path);
            }
        } catch (Exception e) {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
        return null;
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        for (int i = 0; i < playerButtons.size(); i++) {
            if (button == playerButtons.get(i)) {
                if (button == endTurn) {
                    System.out.println("FOUND SWITCH PANEL");
                    System.out.println("[DEBUG] ENDTURN || Opponent name: " + player.getOpponent().getBoard().getName());
                    System.out.println("[DEBUG] ENDTURN || player name: " + player.getBoard().getName());
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to end your turn?");
                    switch (result)
                    {
                        case JOptionPane.YES_OPTION:
                            System.out.println("[Confirm End Turn] Yes pressed");
                            for (int f = 0; f < player.getField().getCards().size(); f++) {
                                player.getField().getCards().get(f).setGetAttacked(false);
                            }
                            cardLayout.show(mainPanel, player.getOpponent().getBoard().getName());
                            break;
                        case JOptionPane.NO_OPTION:
                            System.out.println("[Confirm End Turn] No");
                            break;
                        case JOptionPane.CANCEL_OPTION:
                            System.out.println("[Confirm End Turn] Cancel");
                            break;
                        case JOptionPane.CLOSED_OPTION:
                            System.out.println("[Confirm End Turn] Closed");
                            break;
                    }
                } else if (button == reRoll) {
                    if (player.getHand().getCardsInHand().size() < 1)
                    {
                        UnitCard card = player.getDeck().drawOneCard();
                        player.getHand().addCardtoHand(card);
                        cardInHand.setIcon(card.getImage());
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Can't add you already have a card in your hand!");
                    }
                }
            }
        }
        for (int i = 0; i < cards.size(); i++) {
            if (button == cards.get(i))
            {
                System.out.println("[DEBUG] Card Button name: "+cards.get(i).getName());
                if (cards.get(i).getIcon() != null)
                {
                    // Need to get object array of showInputDialog for which card to use on opponent
                    /*Object listOfFieldCards[] = new Object[player.getField().getCards().size()];
                    for (int k = 0; k < player.getField().getCards().size(); k++)
                    {
                        listOfFieldCards[k] = player.getField().getCards().get(k).getName();
                    }*/
                    ////////////////////////////////////////////////////////////////////////////////
                    for (int v = 0; v < player.getField().getCards().size(); v++)
                    {

                        if ((cards.get(i).getIcon() == player.getField().getCards().get(v).getImage()) && (player.getField().getCards().get(v).getAttacked())) {
                            // If a card already have attacked
                            System.out.println("Can't attack this card has already attacked!");
                            description.setText("This card have already attacked!");
                            return;
                        }
                    }
                    if (player.getOpponent().getField().getCards().size() == 0)
                    {
                        // Attacking Opponent not cards
                        // Getting which card to use
                       /* String attackCardStr = (String) JOptionPane.showInputDialog(null, "Opportunity! Your opponent is wide open.\nWhich card do you want to us to attack the opponent?", "Card Selection (" + player.getName() + ")",
                                JOptionPane.QUESTION_MESSAGE, player.getImage(), listOfFieldCards, listOfFieldCards[0]);*/
                        // Need to decipher the name and card...
                        JOptionPane.showMessageDialog(null,"Your "+cards.get(i).getName()+
                                " had just attacked "+player.getOpponent().getName()+"!");
                        UnitCard attackCard;
                        attackCard = strToPlayerUnitCard(player, cards.get(i).getName());
                        player.attackOpponent(attackCard, player.getOpponent());

                        description.setText("Attacked " + player.getOpponent().getName() + " with "
                                + attackCard.getAttack() + " attack power.\n" + player.getOpponent().getName() + "'s health: " + player.getOpponent().getHealth());
                        if (player.gameOver)
                        {
                            System.out.println("Winner is: " + player.getName());
                            System.out.println("~~~~~~ GAME OVER ~~~~~~");
                            System.exit(0);
                        }

                        // Updating Tooltip
                        player.getOpponent().getBoard().playerIcon.setToolTipText(player.getOpponent().getName() + " || HEALTH: " + player.getOpponent().getHealth());

                        // This Card have attacked now, set true until end of turn
                        for (int v = 0; v < player.getField().getCards().size(); v++)
                        {
                            if (cards.get(i).getIcon() == player.getField().getCards().get(v).getImage())
                            {

                                player.getField().getCards().get(v).setGetAttacked(true);
                            }
                        }
                    }
                    else if(player.getOpponent().getField().getCards().size() != 0)
                    {
                        // Attack Opponent cards!
                        Object listOfOPPCards[] = new Object[player.getOpponent().getField().getCards().size()];
                        for (int k = 0; k < player.getOpponent().getField().getCards().size(); k++)
                        {
                            listOfOPPCards[k] = player.getOpponent().getField().getCards().get(k).getName();
                        }
                        //////////////////
                        /*String attackCardStr = (String) JOptionPane.showInputDialog(null, "More troops on opponent side!\nWhich card do you want to attack the opponent?", "Card Selection (" + player.getName() + ")",
                                JOptionPane.QUESTION_MESSAGE, player.getImage(), listOfFieldCards, listOfFieldCards[0]); */
                        description.setText("Attacking with "+cards.get(i).getName());
                        String victimCardStr = (String) JOptionPane.showInputDialog(null, "Which of the opponent's card do you want to to attack?", "Card Selection (" + player.getName() + ")",
                                JOptionPane.QUESTION_MESSAGE, player.getImage(), listOfOPPCards, listOfOPPCards[0]);

                        UnitCard attackCard = strToPlayerUnitCard(player, cards.get(i).getName());
                        UnitCard victimCard = strToPlayerUnitCard(player.getOpponent(), victimCardStr);
                        int fieldCardSize_preAttack = player.getOpponent().getField().getCards().size();

                        player.attackCard(attackCard, victimCard, player.getOpponent());
                        // Setting BOOLEAN ATTACK TRUE
                        for (int v = 0; v < player.getField().getCards().size(); v++)
                        {
                            if (cards.get(i).getIcon() == player.getField().getCards().get(v).getImage())
                            {
                                player.getField().getCards().get(v).setGetAttacked(true);
                            }
                        }
                        description.setText(player.getName() + " attacked " + player.getOpponent().getName() +
                                "'s" + victimCard.getName() + ". " + victimCard.getName() + "'s Health: " + victimCard.getHealth());

                        // Now we need the index of button card and cards from field
                        int buttonOpponentIndex = 0;
                        for (int w = 0; w < player.getOpponent().getBoard().cards.size(); w++) {
                            if (victimCard.getImage() == player.getOpponent().getBoard().cards.get(w).getIcon()) {
                                buttonOpponentIndex = w;
                                player.getOpponent().getBoard().cards.get(w).setToolTipText("HEALTH: " + victimCard.getHealth() + " || "
                                        + " ATTACK: " + victimCard.getAttack());
                                break;
                            }
                        }
                        int cardOpponentIndex = 0;
                        for (int w = 0; w < player.getOpponent().getField().getCards().size(); w++)
                        {
                            if (victimCard.getName() == player.getOpponent().getField().getCards().get(w).getName())
                            {
                                cardOpponentIndex = w;
                                player.getOpponent().getField().getCards().set(w, victimCard);
                            }
                        }

                        // When the card dies it needs to remove icon
                        if (player.getOpponent().getField().getCards().size() < fieldCardSize_preAttack)
                        {
                            player.getOpponent().getBoard().cards.get(buttonOpponentIndex).setIcon(null);
                            player.getOpponent().getBoard().cards.get(buttonOpponentIndex).setToolTipText("");
                            player.getOpponent().getBoard().cardGrid[victimCard.getGridlocation()-1] = victimCard.getGridlocation();

                        }
                    }
                }
                else
                {
                    System.out.println("Invalid card, please pick a card with an icon!");
                    description.setText("Invalid card, please pick a card with an icon!");
                }
            }
        }
        if (button == cardInHand) {
            // If the player has no card OR field have two cards already
            if ((player.getHand().getCardsInHand().size() == 0) || (player.getField().getCards().size() == 2))
            {
                //Dialog.Can't place card!
                if ((player.getHand().getCardsInHand().size() == 0))
                {
                    description.setText("Can't we have no card in Hand!");
                }
                else if ((player.getField().getCards().size() == 2))
                {
                    description.setText("Can't we have too many cards in the field!");
                }
            }
            else
            {
                UnitCard activeHand = player.getHand().getCardsInHand().get(0);
                int cardSlot = (int) JOptionPane.showInputDialog(null, "Which slot do you want to place the card?", "Card Placement",
                        JOptionPane.QUESTION_MESSAGE, player.getImage(), cardGrid, cardGrid[0]);
                player.summonCard(activeHand);

            }
            return;

        }
    }

    // Function for converting string to Unit Card from a player...
    public UnitCard strToPlayerUnitCard(Player player, String cardName) {
        UnitCard attackCard = new UnitCard();
        for (int t = 0; t < player.getField().getCards().size(); t++) {
            // Finding the player attacking card from the name...
            if (cardName == player.getField().getCards().get(t).getName()) {
                attackCard = player.getField().getCards().get(t);
                break;
            }
        }
        return attackCard;
    }

    // This function may be changed later to fit the start menu
    // But this will change the UI of the frame we have to the
    // Board Game UI where the panel will change constantly
    // for each player (two players,two board UI's in a regular game)
    public static void createAndShowGUI(JFrame frame, JPanel panel) {
        // Create and set up the content pane.
        JComponent newContentPane = panel;
        frame.setContentPane(newContentPane);

        // Display the Window
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}

