package InteractingWithPlayer.NonPlayerCharacters;

import GameModel.Room;
import InteractingWithPlayer.NonPlayerCharacters.NPC;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Prowler extends NPC {
    private final Image prowlerImage; // Image of the prowler
    private boolean defeated; //keeps track of if the prowler is defeated or not

    /**
     * Constructor
     */
    public Prowler(String characterName, String introText, String revealText, Image characterImage, Image prowlerImage) {
        super(characterName, introText, revealText, characterImage);
        this.prowlerImage = prowlerImage;
        this.defeated = true;

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

}

