package InteractingWithPlayer;


import InteractingWithPlayer.NonPlayerCharacters.NPC;
import InteractingWithPlayer.NonPlayerCharacters.Prowler;
import InteractingWithPlayer.Player.Player;


/**
 * The player uses Hack Command, if the player wishes to hack in the NPC character's mind
 * which the player does not know is a prowler or school member.
 */
public class HackCommand {
    // for now

    int MINIMUM_BYTES = 2; //the minimum number of code bytes the player should have


    /**
     * This method reveals the identity of the NPC character when the player
     * chooses to hack the character.
     * @param player ;
     * @param character ;
     * @return the identity of the NPC character.
     */

    public String showCharacterIdentity(NPC character, Player player) {
        // check if character type is Prowler, warn the player.
        if (character instanceof Prowler) {
            return "Danger! You decided to hack a Prowler.";
        }
        // check if character type is school member, greet the player.
        else {
            return "You decided to hack a School Member. Hi! You are doing great in the game," ;
        }

    }

    /**
     * This method checks if the player has the minimum required code bytes
     * to hack the prowler.
     * @param player ;
     * @return the number of code bytes the player has.
     */

    public String countBytes(Player player) {
        return "You have " + player.getCodeBytes() + " code bytes";
    }

    /**
     * This method checks if it is a prowler and the player does not have MINIMUM_BYTES to fight the prowler,
     * the player has to play the quest.
     * @param player;
     * @return result of the quest
     */
    public String prowlerQuest(Player player){
        if (polymorphicProwler() && player.getCodeBytes() < this.MINIMUM_BYTES) {
            if (player.getCodeBytes() < this.MINIMUM_BYTES) {
                return "Danger! You don't have enough code bytes to fight the prowler." +
                        "Look up for School Members to help you win.";
            }
            else if (player.getCodeBytes() >= this.MINIMUM_BYTES) {
                boolean won = player.playQuest();
                if (won) {
                    return "Congratulations! You won the quest";
                }
                else {
                    return "You lost the quest. Better luck next time.";
                }
            }
        }

        return "You are safe. You encountered a School Member.";
    }

    private boolean polymorphicProwler() {
        return true;
    }

}
