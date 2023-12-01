package InteractingWithPlayer.Player;

import GameModel.AdventureObject;
import GameModel.Room;

import java.util.ArrayList;
import java.util.Objects;

import InteractingWithPlayer.Player.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * AlchemistCharacter class
 * _________________________
 * The AlchemistCharacter extends the superclass. This character and has no special powers
 * or features. If no character is chosen, this is the default character.
 * This class sets the character type and assigns an image for the AlchemistCharacter which can later
 * be used in UI
 *
 * AlchemistCharacter objects have an elementalAffinity attribute (i.e. fire, water, wind, earth).
 * It can be used in battle once during the game using castAffinity().
 *
 * Its usage is tracked through the affinityUsed.
 *
 * The castSpell() method is overridden to reflect the change in AlchemistCharacter’s lines when in battle.
 *
 *  */
public class AlchemistCharacter extends Player {

    public Image characterImage;
    private AdventureObject philosophersStone;
    private AdventureObject elementalAffinity;
    public boolean affinityUsed;
    public String playerType;


    /**
     * AlchemistCharacter Constructor
     * __________________________
     * Initializes attributes
     *
     */
    public AlchemistCharacter(Room myCurrLocation, String playerName, String playerDesc) {
        super(myCurrLocation, "Alchemist", playerName, playerDesc);
        isPlayable = true;
        this.affinityUsed = false;
        // load the image
        characterImage = new Image("OtherFiles/characterImages/alchemistCharacter.png");
        // change playerType
        playerType = "Alchemist";
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

