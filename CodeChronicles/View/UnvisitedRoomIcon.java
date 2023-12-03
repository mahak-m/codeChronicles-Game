package View;

import GameModel.CodeChroniclesGame;
import GameModel.Room;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.AccessibleRole;

import java.io.File;

import static javafx.scene.control.ContentDisplay.TOP;
import static javafx.scene.layout.GridPane.getColumnIndex;
import static javafx.scene.layout.GridPane.getRowIndex;
import static jdk.dynalink.linker.support.Guards.isInstance;

public class UnvisitedRoomIcon implements RoomIcon {

    Room room;
    CodeChroniclesGame game;
    CodeChroniclesGameView gameView;
    Button iconButton = new Button();
    ImageView roomImage;
    String description = "Unlock room to view description.";

    public UnvisitedRoomIcon(CodeChroniclesGame game, CodeChroniclesGameView gameView, Room room) {
        this.room = room;
        this.game = game;
        this.gameView = gameView;
        this.iconButton = new Button("Unknown Place");
        String roomName = room.getRoomName().replaceAll("\\s", "");
        Image image = new Image("OtherFiles/Images/" + this.gameView.colourScheme.colourSchemeName + "/roomImagesBlurred/" + roomName + ".jpg");
        this.roomImage = new ImageView(image);
        this.formatIcon();
    }

    public void formatIcon() {
        // Format Visuals
        this.roomImage.setFitWidth(170);
        this.roomImage.setFitHeight(150);
        this.iconButton.setMinWidth(175);
        this.iconButton.setMinHeight(200);
        this.iconButton.setId(this.room.getRoomName());
        this.iconButton.setGraphic(this.roomImage);
        this.iconButton.setContentDisplay(TOP);
        this.iconButton.setStyle("-fx-background-color: "+ this.gameView.colourScheme.buttonColour1 + "; -fx-text-fill: white;");
        this.iconButton.setAlignment(Pos.CENTER);
        // Create preview of room is player clicks on room button.
        this.iconButton.setOnAction(e -> {
            RoomPreview preview = new RoomPreview(this);
        });
    }

    public Button getRoomButton() {
        return this.iconButton;
    }

    public void movePlayerToRoom() {

    }

    public void showRoomPreview() {

    }

}
