package InteractingWithPlayer;

import InteractingWithPlayer.Player.Player;

/**
 * The player can ignore a character by using 5 code bytes
 */


public class IgnoreCommand implements Ignore {

    Player player;

    int codeBytes = player.getCodeBytes();

    /**
     * Constructor
     */
    public IgnoreCommand(int bytes){
        this.codeBytes = bytes;

    }


    @Override
    public void playerIgnore() {
        //Simply ignore the character
        if (this.codeBytes >= 5){
            this.codeBytes -= 5;
            System.out.println("You decided to ignore the character.");
        }

    }


}
