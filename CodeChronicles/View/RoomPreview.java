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

import java.io.FileNotFoundException;

import static View.CodeChroniclesGameView.makeButtonAccessible;

public class RoomPreview {

    private Label previewText;
    private CodeChroniclesGameView gameView;

    public RoomPreview(RoomIcon icon, CodeChroniclesGameView gameView) {

        // Setting up scene.
        this.gameView = gameView;
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(this.gameView.stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.setStyle("-fx-background-color: " + this.gameView.colourScheme.backgroundColour + ";");

        this.gameView.articulateRoomDescription(); // try this rn

        // Creating Preview Text Content
        this.previewText = new Label(icon.getPreviewName() + "\n\n" + icon.getPreviewText());

        // Creating "Go Here" Button
        // Play Game Button
        Button goButton = new Button("Go Here");
        goButton.setId("Go");
        goButton.setAlignment(Pos.CENTER);
        goButton.setPrefSize(100, 50);
        goButton.setFont(new Font("Arial", this.gameView.fontSize));
        goButton.setStyle("-fx-background-color: " + this.gameView.colourScheme.buttonColour2 + "; -fx-text-fill: white;");
        makeButtonAccessible(goButton, "Go", "Go Here", "Click to go to the place previewed.");
        goButton.setOnAction(e -> {
            this.gameView.game.getPlayer().setCurrentRoom(icon.getRoom());
            try {
                this.gameView.setRoomScene();
                dialog.close();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Adding Content to VBox
        VBox preview = new VBox();
        preview.getChildren().addAll(icon.getRoomImage(), this.previewText, goButton);
        preview.setAlignment(Pos.BASELINE_CENTER);
        preview.setSpacing(20);

        // Adding VBox to Scene
        Scene dialogScene = new Scene(preview, 400, 400);
        dialogScene.setFill(Color.valueOf(this.gameView.colourScheme.backgroundColour));
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
