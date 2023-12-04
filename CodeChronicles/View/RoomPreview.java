package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RoomPreview {

    private Label previewText;
    private CodeChroniclesGameView gameView;

    public RoomPreview(RoomIcon icon, CodeChroniclesGameView gameView) {
        this.gameView = gameView;
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(this.gameView.stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.setStyle("-fx-background-color: " + this.gameView.colourScheme.buttonColour1 + ";");

        VBox preview = new VBox();
        preview.getChildren().addAll(icon.getRoomImage(), new Label(icon.getRoom().getRoomName()),new Label(icon.getPreviewText()));
        preview.setAlignment(Pos.BASELINE_CENTER);
        preview.setSpacing(20);

        Scene dialogScene = new Scene(preview, 400, 400);
        dialogScene.setFill(Color.valueOf(this.gameView.colourScheme.backgroundColour));
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
