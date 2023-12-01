package InteractingWithPlayer;

import InteractingWithPlayer.NonPlayerCharacters.NPC;
import InteractingWithPlayer.NonPlayerCharacters.Prowler;
import InteractingWithPlayer.Player.Player;

public class TrustCommand {

    /**
     * As the player decides to trust the character, show the player the identity of the character.
     */

    public void showCharacterIdentity(NPC character, Player player) {
        if (character instanceof Prowler) {
            System.out.println("Danger! You decided to trust a Prowler.");
        }
        else {
            System.out.println("You decided to trust a School Member. Hi! You are doing great in the game. " +
                    "Here are 10 code ");
        }
        this.reducePlayerLife(character, player);

    }
    /**
     * As the player decides to trust the character, show the player life reduces.
     */


    public void reducePlayerLife(NPC character, Player player) {
        if (character instanceof Prowler){
            player.loseLife();
        }
        else{
            player.updateCodeBytes(10);
        }

    }
}
