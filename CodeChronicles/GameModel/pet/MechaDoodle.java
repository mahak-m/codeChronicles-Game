package GameModel.pet;

import GameModel.Pet;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.HashMap;
import java.util.List;

/**
 * This class keeps track of the progress
 * of the player in the game.
 */
public class MechaDoodle extends Pet {
    /**
     * Adventure Game Player Constructor
     */
    public MechaDoodle(String name) {
        super(name);
        this.introduction = "";
        this.commands = new HashMap<>();
    }

    /**
     * This method takes command from the player for the pet.
     *
     * @param command the command given to this pet.
     */
    public void giveCommand(String command) {
        ;
    }

}
