import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Prowler2 extends Prowler {
    public Prowler2(String name) {
        super("Dark Howl", new Image(Objects.requireNonNull(Prowler2.class.getResourceAsStream("DarkHowl.png"))));
    }

    public ImageView getCharacterImageView() {
        ImageView imageView = new ImageView(getProwlerImage());
        imageView.setFitHeight(100);
        return imageView;
    }
}

