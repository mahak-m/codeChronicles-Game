package View;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CharacterCustomizationView {

    GridPane gridPane = new GridPane(); //to hold images and buttons
    private Stage stage = new Stage();
    private Button playButton = new Button("Restart Game");;
    private Button saveChangesButton = new Button("Save Changes");

    private CodeChroniclesGameView gameView;

    public CharacterCustomizationView(CodeChroniclesGameView gameView) {

    }


}
