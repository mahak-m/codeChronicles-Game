import GameModel.AdventureObject;
import GameModel.Room;

import java.util.ArrayList;
import java.util.Objects;

// replace object and class name types later.

/**
 * Character class
 * _________________________
 * This class keeps track of the progress of the players character
 * including their location, current inventory and general
 * information such as their name and description.
 *
 * In order to maintain the single responsibility principal, it only
 * handles actions directly relating to the character (i.e. their room, objects)
 *
 * At the start of the game the player will be able to choose the
 * character "type" as indicated by the subclasses.
 *  */
public class Character {
    String characterName;
    // The name of the character, allow the character to choose
    String characterDesc;
    // The description of the character, allow the character to edit
    ArrayList<AdventureObject> inventory;
    // The inventory of the character. May only contain items of type AdventureObject.
    Room currLocation;
    // The current location of the character. Must be a Room object.
    String characterType;
    // The type of character. The player may choose their character type at the
    // start of the game.


    /**
     * Character Constructor
     * __________________________
     * Initializes attributes
     *
     * @param characterName
     * @param characterDesc
     */
    public Character(String characterName, String characterDesc) {
        this.characterName = "Unnamed Wizard";
        this.characterDesc = "You are a wizard that has recently been admitted to Hackwards School of Codecraft. You love to code and are an ambitious student.\";";
        this.inventory = new ArrayList<AdventureObject>();
        this.currLocation = null; // change this null value later
        this.characterType = null; // change this null value later
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
     * getCharacterName
     * _________________________
     * Getter method for the character name.
     *
     * This is useful for instances in the story in which the
     * Characters name is said by another character.
     *
     * @return the name of the character.
     */
    public String getCharacterName() {
        return this.characterName;
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
    public void setCharacterName(String name) {
        this.characterName = name;
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
}

