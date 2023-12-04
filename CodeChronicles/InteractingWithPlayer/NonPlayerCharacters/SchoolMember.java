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
     * @param characterImage
     */
    public SchoolMember(String characterName, String introText, Image characterImage) {
        super(characterName, characterImage, introText);
    }
}
