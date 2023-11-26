import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Prowler6 extends Prowler {
    public Prowler6(String name) {
        super("Lumina Wing", new Image(Objects.requireNonNull(Prowler6.class.getResourceAsStream("LuminaWing.png"))));
    }

    public ImageView getCharacterImageView() {
        ImageView imageView = new ImageView(getProwlerImage());
        imageView.setFitHeight(100);
        return imageView;
    }
}
