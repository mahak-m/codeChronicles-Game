import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Prowler3 extends Prowler {
    public Prowler3(String name) {
        super("Wealth Wraith", new Image((Objects.requireNonNull(Prowler3.class.getResourceAsStream("WealthWraith.png")))));
    }

    public ImageView getCharacterImageView() {
        ImageView imageView = new ImageView(getProwlerImage());
        imageView.setFitHeight(100);
        return imageView;
    }
}
