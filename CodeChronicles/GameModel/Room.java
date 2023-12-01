package GameModel;

import InteractingWithPlayer.NonPlayerCharacters.NPC;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;

/**
 * This class contains the information about a
 * room in the Adventure Game.
 */
public class Room implements Serializable {

    public int xCoord; // The x-coordinate of the room on the map.

    public int yCoord; // The y-coordinate of the room on the map.

    public NPC characterInRoom;

    private String roomName; // The name of the room.

    private String roomDescription; // The description of the room.

    public ArrayList<AdventureObject> objectsInRoom = new ArrayList<AdventureObject>(); // The list of objects in the room.

    private boolean isVisited; // A boolean to store if the room has been visited or not.

    /**
     * AdvGameRoom constructor.
     *
     * @param roomName: The name of the room.
     * @param xCoord: The x-coordinate of the room on the map.
     * @param yCoord: The y-coordinate of the room on the map.
     * @param roomDescription: The description of the room.
     */
    public Room(String roomName, int xCoord, int yCoord, String roomDescription){
        this.roomName = roomName;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.roomDescription = roomDescription;
        this.isVisited = false;
    }

    /**
     * Returns a comma delimited list of every
     * object's description that is in the given room,
     * e.g. "a can of tuna, a beagle, a lamp".
     *
     * @return delimited string of object descriptions
     */
    public String getObjectString() {
        if (!this.objectsInRoom.isEmpty()) {
            String objects = "";
            for (AdventureObject o:this.objectsInRoom) {
                objects = objects + o.getDescription() + ", ";
            } objects = objects.substring(0, objects.length()-2);
            return objects;
        } else {
            return "";
        }

    }

    /**
     * This method checks if an object is in the room.
     *
     * @param objectName Name of the object to be checked.
     * @return true if the object is present in the room, false otherwise.
     */
    public boolean checkIfObjectInRoom(String objectName){
        for(int i = 0; i<objectsInRoom.size();i++){
            if(this.objectsInRoom.get(i).getName().equals(objectName)) return true;
        }
        return false;
    }

    /**
     * Sets the visit status of the room to true.
     */
    public void visit(){
        isVisited = true;
    }

    /**
     * Getter method for the description attribute.
     *
     * @return: description of the room
     */
    public String getRoomDescription(){
        return this.roomDescription.replace("\n", " ");
    }


    /**
     * Getter method for the name attribute.
     *
     * @return: name of the room
     */
    public String getRoomName(){
        return this.roomName;
    }


    /**
     * Getter method for the visit attribute.
     *
     * @return: visit status of the room
     */
    public boolean getVisited(){
        return this.isVisited;
    }

}
