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
    final int MIN_CODEBYTES = 120;
    /**
     * Adventure Game Player Constructor
     */
    public MechaDoodle() {
        super();
        this.description = "Meet MechaDoodle, a mystical guide for your journey. With the power to " +
                "offer invaluable hints, MechaDoodle aids " +
                "in pivotal moments, yet it is bound by a constraint—only accessible: able to assist in " +
                "the ultimate battle with Incognito Phantom, unable to assist in any other quests.";
    }
}
