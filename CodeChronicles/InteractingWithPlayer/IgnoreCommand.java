package InteractingWithPlayer;

import InteractingWithPlayer.Player.Player;

/**
 * The player can ignore a character by using 5 code bytes
 */


public class IgnoreCommand {


    public void playerIgnore(Player player) {
        //Simply ignore the character
        if (player.getCodeBytes() >= 5){
            player.updateCodeBytes(-5);
            System.out.println("You decided to ignore the character.");
        }

    }


}
