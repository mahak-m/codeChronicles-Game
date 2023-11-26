import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Prowler4 extends Prowler {
    public Prowler4(String name) {
        super("Sinister Shade", new Image(Objects.requireNonNull(Prowler4.class.getResourceAsStream("SinisterShade.png"))));
    }

    public ImageView getCharacterImageView() {
        ImageView imageView = new ImageView(getProwlerImage());
        imageView.setFitHeight(100);
        return imageView;
    }
}
