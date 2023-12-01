package InteractingWithPlayer.Player;

import GameModel.AdventureObject;
import GameModel.Room;

import java.util.ArrayList;
import java.util.Objects;

import InteractingWithPlayer.Player.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * WarriorCharacter class
 * _________________________
 * The WarriorCharacter extends the superclass. This character and has no special powers
 * or features. If no character is chosen, this is the default character.
 * This class sets the character type and assigns an image for the WarriorCharacter which can later
 * be used in UI
 *
 * WarriorCharacter objects hold a diamond shield (guarantees victory in a battle when used).
 * The shield can be used in battle once during the game using useShield().
 *
 * Its usage is tracked through the shieldUsed attribute.
 *
 * The castSpell() method is overridden to reflect the change in WarriorCharacter’s lines when in battle.
 *
 *  */
public class WarriorCharacter extends Player {

    public Image characterImage;
    public String playerType;

    /**
     * WarriorCharacter Constructor
     * __________________________
     * Initializes attributes
     *
     */
    public WarriorCharacter(Room myCurrLocation, String playerName, String playerDesc) {
        super(playerName, playerDesc, myCurrLocation, "Warrior");
        this.isPlayable = true;
        // load the image
        characterImage = new Image("OtherFiles/characterImages/warriorCharacter.png");
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
