package InteractingWithPlayer.NonPlayerCharacters;

import GameModel.Room;
import InteractingWithPlayer.NonPlayerCharacters.NPC;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Prowler extends NPC {
    Image prowlerImage; // Image of the prowler

    private boolean defeated;


    private String prowlerName;



    /**
     * Constructor
     */
    public Prowler(String prowlerName, Image prowlerImage, String npcName, Image NpcImage, String npcGreetings) {
        super( npcName, NpcImage, npcGreetings);
        this.prowlerName = prowlerName;
        this.prowlerImage = prowlerImage;
        this.defeated = false;

    }

    /**
     * Defeat method that keeps track if the prowler was defeated or not
     */
    public void setDefeated(boolean defeated) {

        this.defeated = defeated;
    }
    public Image getProwlerImage(){
        return prowlerImage;
    }
    public String getName(){
        return name;
    }
    public String getProwlerName() {
        return this.prowlerName;
    }

}

