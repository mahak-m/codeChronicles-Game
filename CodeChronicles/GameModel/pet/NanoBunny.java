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
public class NanoBunny extends Pet {
    /**
     * Adventure Game Player Constructor
     */
    public NanoBunny() {
        super();
        this.description = "Meet NanoBunny, the trusty first companion on any quest. " +
                "NanoBunny becomes an invaluable guide throughout adventures by " +
                "aiding the journey with its " +
                "ability to provide essential clues.";
    }
}
