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
    private ArrayList<AdventureObject> inventory;
    public String playerType;


    /**
     * AlchemistCharacter Constructor
     * __________________________
     * Initializes attributes
     *
     */
    public AlchemistCharacter(Room myCurrLocation) {
        super(myCurrLocation, "Alchemist");
        this.inventory = new ArrayList<AdventureObject>();
        this.isPlayable = true;
        this.affinityUsed = false;
        // load the image
        this.characterImage = new Image("OtherFiles/characterImages/alchemistCharacter.png");
        // add special objects to inventory
        this.inventory.add(elementalAffinity);
        // change playerType
        this.playerType = "Alchemist";
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

    /**
     * useElementalAffinity
     * ______________________
     * If elementalAffinity has not already been used, it is used in battle.
     * If it has been used, this method does nothing.
     */
    public void useElementalAffinity() {
        if (!affinityUsed) {
            // check if the player is currently in battle with a prowler
            // then, release the spell and change affinityUsed to true
            affinityUsed = true;
        }
    }

    public void castSpell() {
        // finish
    }

    // override castSpell
}

