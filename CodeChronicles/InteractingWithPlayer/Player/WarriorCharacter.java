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
    private AdventureObject diamondShield;
    public boolean shieldUsed;
    private ArrayList<AdventureObject> inventory;
    public String playerType;

    /**
     * WarriorCharacter Constructor
     * __________________________
     * Initializes attributes
     *
     */
    public WarriorCharacter(Room myCurrLocation) {
        super(myCurrLocation, "Warrior");
        this.inventory = new ArrayList<AdventureObject>();
        this.isPlayable = true;
        this.shieldUsed = false;
        // load the image
        this.characterImage = new Image("OtherFiles/characterImages/warriorCharacter.png");
        // add special objects to inventory
        this.inventory.add(diamondShield);
        // change playerType
        this.playerType = "Warrior";
    }

    /**
     * equiptItem
     * ________________________
     * This method picks up and adds an object to a characters inventory, removing it from the room.
     * if the object is present in the room.  It then returns true.
     * If the object is not present in the room, the method
     * returns false.
     *
     * @param object (name of the magical object to take)
     * @return true/false (depending on if the object was or was not taken)
     */
    public boolean equiptItem(String object) {
        if (!currLocation.checkIfObjectInRoom(object)) {
            return false;
        }
        for (AdventureObject obj : currLocation.objectsInRoom) {
            if (obj.getName().equalsIgnoreCase(object)) {
                inventory.add(obj); // add object to the players inventory
                // currLocation(obj); // remove object from their room
                return true;
            }
        }
        // otherwise, return false if the object was NOT found
        return false;
    }

    /**
     * unEquiptItem
     * _______________________
     * This method "drops" an object, removing it from the character's inventory
     * as a result. (This only happens if the object exists AND is currently
     * in the players inventory. If either of those conditions are not met,
     * this method should do nothing.)
     * The object, once dropped, should be added to the current room.
     * <p>
     * * @param s String name of prop or object to be removed to the inventory.
     */
    public void unEquiptItem(String s) {
        // System.out.println("inventory size: " + inventory.size());
        if (!inventory.isEmpty()) {
            for (AdventureObject k : inventory) {
                if (Objects.equals(k.getName(), s)) {
                    currLocation.objectsInRoom.add(k);
                    inventory.remove(k);
                    break; // item removed so break
                }
            }
        }
    }

    /**
     * getInventory
     * _________________________
     * Getter method for the character's inventory.
     *
     * @return current character's inventory.
     */
    public String[] getInventory() {
        return inventory.toArray(new String[inventory.size()]);
    }

    public void useShield() {
        // add this in later
    }

    // override castSpell

}
