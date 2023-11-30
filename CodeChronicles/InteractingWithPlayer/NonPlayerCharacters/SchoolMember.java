package InteractingWithPlayer.NonPlayerCharacters;

import javafx.scene.image.Image;

public class SchoolMember extends NPC {
    /**
     * Character Constructor
     * __________________________
     * Initializes attributes
     *
     * @param characterName
     * @param introText
     * @param revealText
     * @param characterImage
     */
    public SchoolMember(String characterName, String introText, String revealText, Image characterImage) {
        super(characterName, introText, revealText, characterImage);
    }
}
