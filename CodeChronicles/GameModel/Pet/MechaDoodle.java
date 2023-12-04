package GameModel.Pet;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class keeps track of the progress
 * of the player in the game.
 */
public class MechaDoodle extends Pet {
    final int MIN_CODEBYTES = 120;
    /**
     * Adventure Game Player Constructor
     */
    public MechaDoodle(String name) {
        super(name);
        try {
            this.petImage = new Image(new FileInputStream("OtherFiles/petImages/MechaDoodle.jpg"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.description = "Meet MechaDoodle, a mystical guide for your journey. With the power to " +
                "offer invaluable hints, MechaDoodle aids " +
                "in pivotal moments, yet it is bound by a constraint—only accessible: able to assist in " +
                "the ultimate battle with Incognito Phantom, unable to assist in any other quests.";
    }
}
