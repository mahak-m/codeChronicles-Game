package GameModel.Pet;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class keeps track of the progress
 * of the player in the game.
 */
public class NanoBunny extends Pet {
    /**
     * Adventure Game Player Constructor
     */
    public NanoBunny(String name) {
        super(name);
        try {
            this.petImage = new Image(new FileInputStream("OtherFiles/petImages/NanoBunny.jpg"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.description = "Meet NanoBunny, the trusty first companion on any quest. " +
                "NanoBunny becomes an invaluable guide throughout adventures by " +
                "aiding the journey with its " +
                "ability to provide essential clues.";
    }
}
