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
    public VirtualVulture() {
        super();
        this.description = "Meet VirtualVulture, an enigmatic guide in your journey. " +
                "With a talent for dropping hints that unravel the mysteries " +
                "of quests, VirtualVulture's true power lies in revealing quests' " +
                "answers, becoming an indispensable ally.";
    }



    /**
     * This method reveals the answer for the specific quest.
     *
     */
    private String revealAnswer() {
        return "";
    }
}
