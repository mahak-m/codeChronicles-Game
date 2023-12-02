package InteractingWithPlayer;

import InteractingWithPlayer.NonPlayerCharacters.NPC;
import InteractingWithPlayer.NonPlayerCharacters.Prowler;
import InteractingWithPlayer.Player.Player;

/**
 * The player uses Trust Command, if the player wishes to trust the NPC character, interestingly
 * which the player does not know is a prowler or school member.
 */

public class TrustCommand {

    /**
     * This method reveals the identity of the NPC character when the player chooses to trust the character.
     * @param character;
     * @param player;
     */
    public String showCharacterIdentity(NPC character, Player player) {
        if (character instanceof Prowler) {
            return "Danger! You decided to trust a Prowler.";
        }
        else {
            return "You decided to trust a School Member. Hi! You are doing great in the game. " +
                    "Here are 10 code bytes.";
        }
    }

    /**
     * This method reduces the player life reduces if the player trusts a Prowler.
     * But if the player trusts a School Member then the player gets 10 code bytes in return.
     * @param character;
     * @param player;
     */

    public String reducePlayerLife(NPC character, Player player) {
        if (character instanceof Prowler){
            player.loseLife();
            return "You lose a life as you trusted a Prowler!!.";
        }
        else{
            player.updateCodeBytes(10);
            return "Congratulations! You won 10 code bytes.";
        }
    }
}
