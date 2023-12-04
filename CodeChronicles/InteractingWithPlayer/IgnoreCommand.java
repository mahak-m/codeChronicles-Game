package InteractingWithPlayer;

import InteractingWithPlayer.NonPlayerCharacters.NPC;
import InteractingWithPlayer.Player.Player;

/**
 * The player uses Ignore Command if the player wishes to ignore the NPC character.
 */
public class IgnoreCommand implements Command {

    private Player player;
    private NPC npc;

    public IgnoreCommand(Player player, NPC npc) {
        this.player = player;
        this.npc = npc;
    }

    public String executeCommand() {
        return "You decided to ignore the NPC character.";
    };
}
