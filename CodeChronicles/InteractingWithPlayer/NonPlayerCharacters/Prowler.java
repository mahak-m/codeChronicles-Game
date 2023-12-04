package InteractingWithPlayer.NonPlayerCharacters;

import GameModel.Room;
import InteractingWithPlayer.NonPlayerCharacters.NPC;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Prowler extends NPC {

    private boolean defeated;

    private String prowlerName;



    /**
     * Constructor
     */
    public Prowler(String prowlerName, String npcName, String npcGreetings) {
        super( npcName, npcGreetings);
        this.prowlerName = prowlerName;
        this.defeated = false;

    }

    /**
     * Defeat method that keeps track if the prowler was defeated or not
     */
    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }

    public String getName(){
        return name;
    }
    public String getProwlerName() {
        return this.prowlerName;
    }

}

