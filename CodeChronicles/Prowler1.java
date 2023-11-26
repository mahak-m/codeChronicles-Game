import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Prowler1 extends Prowler {
    public Prowler1(String name) {
        super("Crimson Specter", new Image(Objects.requireNonNull(Prowler1.class.getResourceAsStream("CrimsonSpecter.png"))));
    }
    public ImageView getCharacterImageView() {
        ImageView imageView = new ImageView(getProwlerImage());
        imageView.setFitHeight(100);
        return imageView;
    }
}

