package InteractingWithPlayer;


import InteractingWithPlayer.NonPlayerCharacters.NPC;
import InteractingWithPlayer.NonPlayerCharacters.Prowler;
import InteractingWithPlayer.Player.Player;


/**
 * The player uses Hack Command, if the player wishes to hack in the NPC character's mind
 * which the player does not know is a prowler or school member.
 */
public class HackCommand implements Command {

    private Player player;
    private NPC npc;
    // for now
    int minBytes = 2; //the minimum number of code bytes the player should have

    public HackCommand(Player player, NPC npc) {
        this.player = player;
        this.npc = npc;
    }

    public String executeCommand() {
        return showCharacterIdentity(this.npc, this.player);
    };


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
            return prowlerQuest(player, character);
        }
        // check if character type is school member, greet the player.
        else {
            player.loseLife();
            return "Oh no! You tried to hack a School Member, and lost 1 life." ;
        }

    }

    /**
     * This method checks if the player has the minimum required code bytes
     * to hack the prowler.
     * @param player ;
     * @return the number of code bytes the player has.
     */

    public String countBytes(Player player) {
        return "You have " + player.getCodeBytes() + "code bytes";
    }

    /**
     * This method checks if it is a prowler and the player does not have MINIMUM_BYTES to fight the prowler,
     * the player has to play the quest.
     * @param player;
     * @return result of the quest
     */
    public String prowlerQuest(Player player, NPC npc) {
        if (npc instanceof Prowler && player.getCodeBytes() < this.minBytes) {
            if (player.getCodeBytes() < this.minBytes) {
                return "You don't have enough code bytes to hack this person." +
                        "Look around for School Members to help you with collecting code bytes.";
            }
            else if (player.getCodeBytes() >= this.minBytes) {
                boolean won = player.playQuest();
                if (won) {
                    return "Congratulations! You won the quest";
                }
                else {
                    return "You lost the quest. Better luck next time.";
                }
            }
        }
        player.loseLife();
        return "Oh no!, you are safe but you tried hacking a School Member and lost 1 life.";
    }
}
