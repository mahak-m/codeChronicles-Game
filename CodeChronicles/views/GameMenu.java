package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameMenu {

    private Button closeWindowButton = new Button("Close Window");

    private CodeChroniclesGameView gameView;

    public GameMenu(CodeChroniclesGameView gameView) {
        this.gameView = gameView;
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(gameView.stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.setStyle("-fx-background-color: #121212;");

        closeWindowButton = new Button("Close Window");
        closeWindowButton.setId("closeWindowButton"); // DO NOT MODIFY ID
        closeWindowButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        closeWindowButton.setPrefSize(200, 50);
        closeWindowButton.setFont(new Font(16));
        closeWindowButton.setOnAction(e -> dialog.close());
        CodeChroniclesGameView.makeButtonAccessible(closeWindowButton, "close window", "This is a button to close the save game window", "Use this button to close the save game window.");

        Scene dialogScene = new Scene(dialogVbox, 400, 400);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
