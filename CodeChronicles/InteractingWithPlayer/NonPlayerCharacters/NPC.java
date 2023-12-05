package InteractingWithPlayer.NonPlayerCharacters;

import GameModel.Room;
import InteractingWithPlayer.Quest;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// All the NPC character names were taken as a reference from ChatGpt except CrownMark Markus and we added a few names on our own.
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

    // the name of the NPC character
    ///String IntroText;
    // a short description in case the user would like to know more about the NPC
    String NPCGreeting;
    // a short description in case the user would like to know more about the NPC
    String npcName;
    String npcGreetings;
    Room currLocation;
    // the location of the NPC (the room where they are found)

    Quest quest;
    /**
     * NPC Character Constructor
     * __________________________
     * Initializes attributes
     */
    public NPC(String npcName, String npcGreetings) {
        this.npcName = npcName;
        this.NPCGreeting = npcGreetings;
    }

    public String getName(){
        return this.npcName;
    } // getting name of the NPC characters

    public String getIntro(){
        return this.NPCGreeting;
    } // getter method for greeting by the npc
    public void setQuest(Quest quest) {this.quest = quest;} // setting the quest
    public Quest getQuest() {return this.quest;} // getter method for quest
}