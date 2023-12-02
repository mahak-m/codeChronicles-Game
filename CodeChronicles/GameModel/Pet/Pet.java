package GameModel.Pet;
import GameModel.Room;
import InteractingWithPlayer.Player.Player;

import java.io.Serializable;

/**
 * This class keeps track of the progress
 * of the pet in the game.
 */
public abstract class Pet implements Serializable {
    protected Room currentRoom; // The current room that the pet is located in
    public String name; // The name of the pet
    public boolean chosen; // is the pet chosen by the player?
    protected String description; // A brief summary of the pet
    protected Player player; // the player that has chosen this pet

    /**
     * CodeChronicles Pet Constructor
     */
    public Pet() {
        this.chosen = false;
    }

    /**
     * This method provides the player with an introduction of this pet.
     *
     * @return the introduction of the pet
     */
    public String introducePet(){
        return this.description;
    }

    /**
     * This method changes the location of the pet to the provided room.
     *
     * @param room the room to move the pet to
     */
    public void movePet(Room room) { this.currentRoom = room; }

    /**
     * Getter method for the current room attribute.
     *
     * @return the current room of the player.
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    /**
     * This method allows the player to choose this pet.
     *
     */
    public void choosePet() {
        this.player = player;
        this.currentRoom = this.player.getCurrentRoom();
        this.chosen = true;
    }

    /**
     * This method allows the player to "let go" of this pet.
     *
     */
    public void leavePet() {
        this.player = null;
        this.currentRoom = null;
        this.chosen = false;
    }

    /**
     * This method takes a quest from the player, and returns a hint for it.
     *
     * @return hint the hint requested for this quest
     */
    public String giveHint() {
        return "";}
}