import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Prowler7 extends Prowler {
    public Prowler7(String name) {
        super("Dread Marauder", new Image(Objects.requireNonNull(Prowler7.class.getResourceAsStream("Dread Marauder.png"))));
    }

    public ImageView getCharacterImageView() {
        ImageView imageView = new ImageView(getProwlerImage());
        imageView.setFitHeight(100);
        return imageView;
    }
}
