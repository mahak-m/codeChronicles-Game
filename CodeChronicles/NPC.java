package week3.group_project.group_99.CodeChronicles;

import GameModel.AdventureObject;
import GameModel.Room;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

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
    String NPCname;
    // the name of the NPC character
    String NPCdescription;
    // a short description in case the user would like to know more about the NPC
    Room currLocation;
    // the location of the NPC (the room where they are found)
    private Image NPCimage;
    // the image assigned to the NPC

    /**
     * Character Constructor
     * __________________________
     * Initializes attributes
     */
    public NPC(String characterName, String characterDesc, Room myCurrLocation, Image characterImage) {
        this.NPCname = characterName;
        this.NPCdescription = characterDesc;
        this.currLocation = myCurrLocation;
        // load the image
        NPCimage = characterImage;
    }

    public ImageView getCharacterImageView() {
        ImageView imageView = new ImageView(NPCimage);
        // default (change these later as needed):
        imageView.setFitHeight(100);
        return imageView;
    }
    // IMPORTANT: to use the image in UI (i.e. in pane, panel):
    // ImageView mageImageView = mageCharacter.getCharacterImageView();
}




