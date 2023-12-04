package InteractingWithPlayer.NonPlayerCharacters;

import GameModel.Room;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * NPC
 * ___________________
 * This class represents the NPC, or the other students in
 * the school.
 *
 * There are 5 NPCs to face throughout the game. Their images are in the
 * folder characterImages --> NPCimages
 */
public class NPC {
    String name;
    // the name of the NPC character
    ///String IntroText;
    // a short description in case the user would like to know more about the NPC
    String NPCGreeting;
    // a short description in case the user would like to know more about the NPC
    String npcName;
    String npcGreetings;
    Room currLocation;
    // the location of the NPC (the room where they are found)

    /**
     * NPC Character Constructor
     * __________________________
     * Initializes attributes
     */
    public NPC(String npcName, String npcGreetings) {
        this.name = npcName;
        this.NPCGreeting = npcGreetings;
    }
}




