package GameModel;
import jdk.jshell.spi.ExecutionControl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * This class keeps track of the progress
 * of the pet in the game.
 */
public abstract class Pet implements Serializable {
    protected Room currentRoom; // The current room that the pet is located in
    public String name; // The name of the pet
    public boolean chosen; // is the pet chosen by the player?
    protected String introduction; // A brief summary of the pet
    protected HashMap<String, Callable> commands; // The commands that the pet supports
    protected Player player; // the player that has chosen this pet

    /**
     * CodeChronicles Pet Constructor
     */
    public Pet(String name) {
        this.name = name;
        this.chosen = false;
    }

    /**
     * This method provides the player with an introduction of this pet.
     *
     * @return the introduction of the pet
     */
    public String introducePet(){
        return this.introduction;
    }

    /**
     * This method changes the location of the pet to the provided room.
     *
     * @param room the room to move the pet to
     */
    public void movePet(Room room) { this.currentRoom = room; }

    /**
     * Getter method for the commands attribute of this pet.
     *
     * @return List of all commands supported by this pet
     */
    public List<String> getCommands() {
        List<String> listOfCommands = new ArrayList<>(this.commands.keySet());
        return listOfCommands;
    }

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
     * This method takes command from the player for the pet.
     *
     * @param command the command given to this pet.
     */
    public abstract void giveCommand(String command) throws ExecutionControl.NotImplementedException;

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
     * This method allows the player to change the pet's name.
     *
     * @param name the new name for the pet.
     */
    public void changeName(String name) {
        this.name = name;
    }

    public String hint() {
        return "";}
}