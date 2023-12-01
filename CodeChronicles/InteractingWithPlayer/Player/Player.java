package InteractingWithPlayer.Player;

import GameModel.Room;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// TO-DO for this class:
// replace object and class name types later.
// finish the castSpell method (check if item in inventory
// has bool Used as false)

/**
 * Character class
 * _________________________
 * This class keeps track of the characters in the game.
 * There are three types of playable characters: Mage, Alchemist, Warrior.
 * And two non-playing characters: Prowler, NPC.
 *
 * In order to maintain the single responsibility principal, it only
 * handles actions directly relating to the character (i.e. their room, objects)
 *
 * At the start of the game the player will be able to choose the
 * character "type" as indicated by the subclasses.
 *  */
public abstract class Player {

    public Image characterImage;
    String playerName;
    // The name of the character, allow the character to choose
    String characterDesc;
    // The description of the character, allow the character to edit
    Room currLocation;
    // The current location of the character. Must be a Room object.
    String playerType;
    // The type of character [Either Mage, Alchemist or, Warrior]
    boolean isPlayable;
    // Attribute that determines if character is playable
    private int codeBytes;
    // keeps track of the current number of code bytes
    private int lives;


    /**
     * Player Constructor
     * __________________________
     * Initializes attributes
     *
     */
    public Player(Room myCurrLocation, String myPlayerType) {
        this.playerName = "Unnamed Wizard"; // BEFORE the player customizes their name
        this.characterDesc = "You are a wizard that has recently been admitted to Hackwards School of Codecraft. You love to code and are an ambitious student.\";";
        this.currLocation = myCurrLocation;
        this.playerType = myPlayerType;
        this.codeBytes = 5; // initial value
        this.lives = 5; //default value
    }

    /**
     * getPlayerName
     * _________________________
     * Getter method for the character name.
     *
     * This is useful for instances in the story in which the
     * Characters name is said by another character.
     *
     * @return the name of the character.
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * getCurrentLocation
     * _________________________
     * Getter method for the current location attribute.
     *
     * @return current room the player is in.
     */
    public Room getCurrentRoom() {
        return this.currLocation;
    }

    /**
     * setCharacterName
     * _________________________
     * Setter method for the character name.
     *
     * This is useful for instances in the story in which the
     * Characters name is said by another character.
     */
    public void setPlayerName(String name) {
        this.playerName = name;
    }

    /**
     * getPlayerType
     */
    public String getPlayerType() {
        return this.playerType;
    }

    /**
     * getCodeBytes
     */
    public int getCodeBytes() {
        return this.codeBytes;
    }

    public int getLives() {return this.lives;
    }
    public void updateCodeBytes(int codeBytes) {
        this.codeBytes += codeBytes;
    }
    public void loseLife() {
        this.lives -= 1;
    }

    public boolean playQuest() { return true;
        // after quest has been implemented, refer to hia for this implementation
    }
}


