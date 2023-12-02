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
    ///String IntroText;
    // a short description in case the user would like to know more about the NPC
    String NPCGreeting;
    // a short description in case the user would like to know more about the NPC
    Image image;
    // the image assigned to the NPC
    String npcName;
    Image npcImage;
    String npcGreetings;
    Room currLocation;
    // the location of the NPC (the room where they are found)

    /**
     * NPC Character Constructor
     * __________________________
     * Initializes attributes
     */
    public NPC(String npcName, Image npcImage, String npcGreetings) {
        this.name = npcName;
        this.image = npcImage;
        this.NPCGreeting = npcGreetings;

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




