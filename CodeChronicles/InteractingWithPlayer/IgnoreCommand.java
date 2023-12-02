package InteractingWithPlayer;

import InteractingWithPlayer.Player.Player;

/**
 * The player uses Ignore Command if the player wishes to ignore the NPC character.
 */
public class IgnoreCommand {

    /**
     * The player can ignore the NPC character simply by using 5 code bytes.
     * @param player;
     * @return that the player ignored the NPC character.
     */
    public String playerIgnore(Player player) {
        //Simply ignore the NPC character
        if (player.getCodeBytes() >= 5){
            player.updateCodeBytes(-5);
            return "You decided to ignore the NPC character.";
        }
        return "Sorry you cannot ignore this NPC character. Please find another NPC character.";
    }
}
