import board.*;

public class CardGameMain {
    public static void main(String args[])
    {
        // Before starting the game we need the player one and two name!
        // That mean we need to start the game from the start menu...

        // Starting the game here
        Game currentGame = new Game();
        currentGame.startNewGame("player 1", "player 2", "UnitDeck.csv");

    }
}
