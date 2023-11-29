import GameModel.AdventureObject;
import GameModel.Room;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Prowler class
 * ____________________
 * The Prowler class represents the Polymorphic Prowlers. Due to their
 * polymorphic nature, they can "shape-shift" and hence, are indistinguishable
 * by appearence.
 *
 * There are 5 prowlers to face. Their images are in the
 * folder characterImages --> ProwlerImages
 */
public class Prowler extends Character{
    private int strength;
    // the prowler's strength can range between 1-10 bytes (needed to destroy)
    public Image prowlerImage;

    /**
     * Prowler Constructor
     * __________________________
     * Initializes attributes
     *
     */
    public Prowler(String characterName, String characterDesc, Room myCurrLocation, int myStrength) {
        super(characterName, characterDesc, myCurrLocation, "Prowler");
        isPlayable = false;
        this.strength = myStrength;
    }

    public int getStrength() {
        return strength;
    }

}




