package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameMenu {

    GridPane gridPane = new GridPane(); //to hold images and buttons
    private Stage stage = new Stage();
    private ComboBox<String> musicBox;
    private ComboBox<String> audioBox;
    private ComboBox<String> colourModeBox = new ComboBox<String>();
    private Spinner<Integer> fontSizeBox;
    private Button restartButton = new Button("Restart Game");;
    private Button saveChangesButton = new Button("Save Changes");

    private CodeChroniclesGameView gameView;

    public GameMenu(CodeChroniclesGameView gameView) {
        this.gameView = gameView;
        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.initOwner(gameView.stage);

        // Create Menu Label
        Label menu = new Label("Menu");
        menu.setAlignment(Pos.BASELINE_CENTER);
        menu.setFont(new Font("Helvetica", 30));
        menu.setMaxHeight(50);
        menu.setTextFill(Color.web(this.gameView.colourScheme.accentColour1));
        // Create Colour Mode ComboBox
        Label colourTheme = new Label("Theme");
        colourTheme.setTextFill(Color.web(this.gameView.colourScheme.fontColour1));
        colourTheme.setFont(new Font("Helvetica", this.gameView.fontSize));
        this.colourModeBox = new ComboBox<String>();
        this.colourModeBox.getItems().add("Light");
        this.colourModeBox.getItems().add("High Contrast (Dark)");
        this.colourModeBox.getItems().add("Monochrome");
        this.colourModeBox.getItems().add("Game Theme");
        this.colourModeBox.setValue("Game Theme");
        this.colourModeBox.setMaxWidth(150);
        // Create Font Size Spinner
        Label fontSize = new Label("Font Size");
        fontSize.setTextFill(Color.web(this.gameView.colourScheme.fontColour1));
        fontSize.setFont(new Font("Helvetica", this.gameView.fontSize));
        this.fontSizeBox = new Spinner<Integer>(12, 20, 16);
        this.fontSizeBox.getValueFactory().setValue(this.gameView.fontSize);
        // Create Music Toggle Button
        Label music = new Label("Music");
        music.setTextFill(Color.web(this.gameView.colourScheme.fontColour1));
        menu.setFont(new Font("Helvetica", this.gameView.fontSize));
        this.musicBox = new ComboBox<String>();
        this.musicBox.getItems().add("On");
        this.musicBox.getItems().add("Off");
        this.musicBox.setValue("On");
        // Create Audio Toggle Button
        Label audio = new Label("Audio");
        audio.setTextFill(Color.web(this.gameView.colourScheme.fontColour1));
        audio.setFont(new Font("Helvetica", this.gameView.fontSize));
        this.audioBox = new ComboBox<String>();
        this.audioBox.getItems().add("On");
        this.audioBox.getItems().add("Off");
        this.audioBox.setValue("On");
        // Create Restart Button
        this.restartButton.setId("Restart Game");
        this.restartButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        this.restartButton.setPrefSize(200, 50);
        this.restartButton.setFont(new Font(16));
        this.restartButton.setOnAction(e -> this.stage.close()); //TODO! Restart Game!
        CodeChroniclesGameView.makeButtonAccessible(this.restartButton, "Restart Game", "This is a button to restart the game", "Use this button to restart the game. It will load the game from the beginning, and you will lose your progress.");

        // Create Save Changes Button
        this.saveChangesButton.setId("Save Changes");
        this.saveChangesButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
        this.saveChangesButton.setPrefSize(200, 50);
        this.saveChangesButton.setFont(new Font(16));
        this.saveChangesButton.setOnAction(e ->this.stage.close()); // TODO! Implement Method to Save Changes!
        CodeChroniclesGameView.makeButtonAccessible(this.saveChangesButton, "Save Changes", "This is a button to save your game's settings", "Use this button to save your game's settings. It will update the game based on the changes you have made.");

        // Add Buttons to Horizontal Box
        HBox buttons = new HBox();
        buttons.getChildren().addAll(restartButton, saveChangesButton);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);

        //Set Up GridPane
        ColumnConstraints column = new ColumnConstraints(100);
        RowConstraints row = new RowConstraints(100);
        this.gridPane.getColumnConstraints().addAll(column, column, column, column, column, column, column);
        this.gridPane.getRowConstraints().addAll(row, row, row, row);
        gridPane.setBackground(new Background(new BackgroundFill(
                Color.valueOf(this.gameView.colourScheme.backgroundColour1),
                new CornerRadii(0),
                new Insets(0)
        )));
        // Add Everything to GridPane
        // GridPane.add(columnIndex, rowIndex, columnSpan, rowSpan)
        this.gridPane.add(menu, 3, 0, 1, 1);
        this.gridPane.add(buttons, 0, 3, 7, 1);
        this.gridPane.add(colourTheme, 1, 1, 2, 1);
        this.gridPane.add(this.colourModeBox, 2, 1, 2, 1);
        this.gridPane.add(fontSize, 1, 2, 2, 1);
        this.gridPane.add(this.fontSizeBox, 2, 2, 2, 1);
        this.gridPane.add(audio, 4, 1, 2, 1);
        this.gridPane.add(this.audioBox, 5, 1, 2, 1);
        this.gridPane.add(music, 4, 2, 2, 1);
        this.gridPane.add(this.musicBox, 5, 2, 2, 1);

        Scene dialogScene = new Scene(gridPane, 700, 400);
        this.stage.setScene(dialogScene);
        this.stage.setResizable(false);
        this.stage.show();
    }
}
