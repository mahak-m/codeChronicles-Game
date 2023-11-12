package View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameMenu {

    private Button saveSettingsButton = new Button("Close Window");

    private CodeChroniclesGameView gameView;

    public GameMenu(CodeChroniclesGameView gameView) {
        this.gameView = gameView;
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(gameView.stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.setStyle("-fx-background-color: #121212;");

        saveSettingsButton = new Button("Save Settings");
        saveSettingsButton.setId("Save Settings");
        saveSettingsButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        saveSettingsButton.setPrefSize(200, 50);
        saveSettingsButton.setFont(new Font(16));
        saveSettingsButton.setOnAction(e -> dialog.close());
        CodeChroniclesGameView.makeButtonAccessible(saveSettingsButton, "Save Settings", "This is a button to save your game's settings", "Use this button to save your game's settings. It will update the game based on the changes you have made.");

        Scene dialogScene = new Scene(dialogVbox, 400, 400);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
