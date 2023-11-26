import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Prowler {

    private final String name; //name of the 7 prowlers
    private final Image prowlerImage; // Image of the prowler
    private boolean defeated; //keeps track of if the prowler is defeated or not

    /**
     * Constructor
     */
    public Prowler(String name, Image prowlerImage) {
        this.name = name;
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

