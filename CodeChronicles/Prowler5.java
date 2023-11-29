import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Prowler5 extends Prowler {
    public Prowler5(String name) {
        super("NightShade Serpent", new Image(Objects.requireNonNull(Prowler5.class.getResourceAsStream("NightShadeSerpent.png"))));
    }

    public ImageView getCharacterImageView() {
        ImageView imageView = new ImageView(getProwlerImage());
        imageView.setFitHeight(100);
        return imageView;
    }
}
