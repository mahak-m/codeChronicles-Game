import java.util.ArrayList;
import java.util.Objects;

/**
 * Character Class.
 * This class keeps track of the progress of the character
 * as well as their inventory, along with customization
 *
 *  */
public class Character {
    String characterName = "wizard";
    String characterDesc = "you are (...)";


    /**
     * Character Constructor
     * __________________________
     * Initializes attributes
     *
     * @param characterName
     * @param characterDesc
     */
    public Character(String characterName, String characterDesc) {
        this.characterName = "wizard";
        this.characterDesc = "you are (...)";
    }

    /**
     * takeObject
     * _________________________
     * This method adds an object to a player's inventory (and removes it from the room)
     * if the object is present in the room.  It then returns true.
     * If the object is not present in the room, the method
     * returns false.
     *
     * @param object name of the object to take
     * @return true if object is taken, false otherwise
     */
    // Helper method to check if the current room contains the specified object.
    public boolean takeObject(String object) {
        // problem: somehow keys is not IN the room
        /**
         if (!currentRoom.hasObjects()) {
         return false;
         }
         for (AdventureObject obj : currentRoom.objectsInRoom) {
         if (obj.getName().equalsIgnoreCase(object)) {
         inventory.add(obj); // add object to the players inventory
         currentRoom.removeObject(obj); // remove object from their room
         return true;
         }
         }
         // otherwise, return false if the object was NOT found
         return false;
         */
        return false;
    }

    /**
     * dropObject
     * _________________________
     * This method removes an object from the inventory of the player, if it exists.
     * The object, once dropped, should be added to the current room.
     * If the object is not in the inventory, this method will do nothing.
     *
     * @param s String name of prop or object to be removed to the inventory.
     */
    public void dropObject(String s) {
        /**
         // System.out.println("inventory size: " + inventory.size());
         if (!inventory.isEmpty()) {
         for (AdventureObject k : inventory) {
         if (Objects.equals(k.getName(), s)) {
         currentRoom.objectsInRoom.add(k);
         inventory.remove(k);
         break; // item removed so break
         }
         }
         }
         */
    }
}
