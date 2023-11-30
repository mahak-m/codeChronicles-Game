package GameModel.pet;

import GameModel.Pet;

import java.util.HashMap;

/**
 * This class keeps track of the progress
 * of the player in the game.
 */
public class VirtualVulture extends Pet {
    /**
     * Adventure Game Player Constructor
     */
    public VirtualVulture(String name) {
        super(name);
        this.introduction = "";
        this.commands = new HashMap<>();
    }

    /**
     * This method takes command from the player for the pet.
     *
     * @param command the command given to this pet.
     */
    public void giveCommand(String command) {;}

    /**
     * This method returns the code byte in the current room.
     *
     */
    private String findCodeByte() {
        return "";
    }
}
