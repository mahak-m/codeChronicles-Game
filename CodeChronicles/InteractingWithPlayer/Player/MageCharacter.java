package InteractingWithPlayer.Player;

import GameModel.AdventureObject;
import GameModel.Room;

import java.util.ArrayList;
import java.util.Objects;

import InteractingWithPlayer.Player.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * MageCharacter class
 * _________________________
 * The MageCharacter extends the superclass. This character and has no special powers
 * or features. If no character is chosen, this is the default character.
 *
 * This class sets the character type and assigns an image for the MageCharacter which
 * can later be used in UI. The castSpell method stays the same.
 *
 *  */
public class MageCharacter extends Player {

    public Image characterImage;
    // The image associated with the MageCharacter
    public String playerType;


    /**
     * MageCharacter Constructor
     * __________________________
     * Initializes attributes
     *
     */
    public MageCharacter(Room myCurrLocation) {
        super(myCurrLocation, "Mage");
        isPlayable = true;
        // load the image
        this.characterImage = new Image("OtherFiles/characterImages/mageCharacter.png");
        // change playerType
        this.playerType = "Mage";
    }

    public ImageView getCharacterImageView() {
        ImageView imageView = new ImageView(characterImage);
        // default (change these later as needed):
        imageView.setFitHeight(100);
        return imageView;
    }
    // IMPORTANT: to use the image in UI (i.e. in pane, panel):
    // ImageView mageImageView = mageCharacter.getCharacterImageView();

}
