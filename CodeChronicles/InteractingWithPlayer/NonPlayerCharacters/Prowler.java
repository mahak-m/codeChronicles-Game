package InteractingWithPlayer.NonPlayerCharacters;

import GameModel.Room;
import InteractingWithPlayer.NonPlayerCharacters.NPC;
import InteractingWithPlayer.Quest;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Prowler extends NPC {

    private String prowlerName;



    /**
     * Constructor
     */
    public Prowler(String npcName, String prowlerName, String npcGreetings) {
        super(npcName, npcGreetings);
        this.prowlerName = prowlerName;

    }

    /**
     * Defeat method that keeps track if the prowler was defeated or not
     */

    public String getProwlerName() {
        return this.prowlerName;
    }

}

