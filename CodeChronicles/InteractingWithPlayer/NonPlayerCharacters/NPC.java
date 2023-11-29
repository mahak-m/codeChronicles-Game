package InteractingWithPlayer.NonPlayerCharacters;

import GameModel.Room;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * NPC
 * ___________________
 * This class represents the NPC, or the other students in
 * the school.
 *
 * There are 5 NPCs to face throughout the game. Their images are in the
 * folder characterImages --> NPCimages
 */
public class NPC {
    String name;
    // the name of the NPC character
    String IntroText;
    // a short description in case the user would like to know more about the NPC

    String CharacterRevealText;
    // a short description in case the user would like to know more about the NPC

    Room currLocation;
    // the location of the NPC (the room where they are found)
    private Image image;
    // the image assigned to the NPC

    /**
     * Character Constructor
     * __________________________
     * Initializes attributes
     */
    public NPC(String characterName, String introText, String revealText, Image characterImage) {
        this.name = characterName;
        this.IntroText = introText;
        this.CharacterRevealText = revealText;
        // load the image
        this.image = characterImage;
    }

    public ImageView getCharacterImageView() {
        ImageView imageView = new ImageView(image);
        // default (change these later as needed):
        imageView.setFitHeight(100);
        return imageView;
    }
    // IMPORTANT: to use the image in UI (i.e. in pane, panel):
    // ImageView mageImageView = mageCharacter.getCharacterImageView();
}




