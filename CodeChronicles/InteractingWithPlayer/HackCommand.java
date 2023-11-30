package InteractingWithPlayer;


import InteractingWithPlayer.NonPlayerCharacters.NPC;
import InteractingWithPlayer.NonPlayerCharacters.Prowler;
import InteractingWithPlayer.Player.Player;

public class HackCommand {
    // for now

    int MINIMUM_BYTES = 2;


    /**
     * This method reveals the identity of the character when the player
     * chooses to hack the character.
     */

    public void showCharacterIdentity(NPC character, Player player) {
        // check if character type is Prowler, warn the player.
        if (character instanceof Prowler) {
            System.out.println("Danger! You decided to hack a Prowler.");
        }
        // check if character type is school member, greet the player.
        else {
            System.out.println("You decided to hack a School Member. Hi! You are doing great in the game");
        }
        this.countBytes(player);
    }

    /**
     * This method checks if the player has the minimum required code bytes
     * to hack the prowler.
     */

    public void countBytes(Player player) {
        System.out.println("You have " + player.getCodeBytes()  + "code bytes");

        if (polymorphicProwler() && player.getCodeBytes() < this.MINIMUM_BYTES) {
            if (player.getCodeBytes() < this.MINIMUM_BYTES) {
                System.out.println("Danger! You don't have enough code bytes to fight the prowler." +
                    "Look up for School Members to help you win.");
            }
            else if (player.getCodeBytes() >= this.MINIMUM_BYTES) {
                System.out.println("3");
                boolean won = player.playQuest();
                if (won) {
                    System.out.println("won quest");
                }
                else {
                    System.out.println("play again");
                }
            }
        }

    }

    private boolean polymorphicProwler() {
        return true;
    }

}
