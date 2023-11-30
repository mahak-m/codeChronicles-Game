package View;

import GameModel.CodeChroniclesGame;
import InteractingWithPlayer.Player.AlchemistCharacter;
import InteractingWithPlayer.Player.MageCharacter;
import InteractingWithPlayer.Player.WarriorCharacter;
import javafx.animation.PauseTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.AccessibleRole;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

import static javafx.scene.control.ContentDisplay.TOP;
import static javafx.scene.layout.GridPane.getColumnIndex;
import static javafx.scene.layout.GridPane.getRowIndex;

/**
 * Class AdventureGameView.
 *
 * This is the Class that will visualize the model.
 */
public class CodeChroniclesGameView {

    CodeChroniclesGame game; //model of the game
    Integer fontSize;
    ColourScheme colourScheme;
    Stage stage; //stage on which all is rendered
    Button menuButton, helpButton, mapButton; //buttons
    Boolean helpToggle = false; //is help on display?
    Boolean music;
    Boolean audio;
    GridPane gridPane = new GridPane(); //to hold images and buttons
    Label roomDescLabel = new Label(); //to hold room description and/or instructions
    VBox objectsInRoom = new VBox(); //to hold room items
    VBox objectsInInventory = new VBox(); //to hold inventory items
    ImageView roomImageView; //to hold room image
    private MediaPlayer mediaPlayer; //to play audio
    private boolean mediaPlaying; //to know if the audio is playing

    /**
     * Adventure Game View Constructor
     * __________________________
     * Initializes attributes
     */
    public CodeChroniclesGameView(CodeChroniclesGame game, Stage stage) {
        this.game = game;
        this.stage = stage;
        this.colourScheme = new ColourScheme("Game Theme");
        this.fontSize = 16;
        intiUI();
    }

    /**
     * Initialize the UI
     */
    public void intiUI() {

        // SETTING UP THE STAGE
        this.stage.setTitle("Code Chronicles: Wizard's Quest");

        // CREATE LOADING SCREEN
        GridPane gamePane = new GridPane();
        gamePane.setStyle("-fx-background-image: url('OtherFiles/StartScreen.jpg');");
        var scene1 = new Scene(gamePane ,  1000, 800);
        this.stage.setScene(scene1);
        this.stage.setResizable(false);
        this.stage.show();

        // AFTER LOADING SCREEN SHOW CHARACTER CUSTOMIZATION SCREEN
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            this.stage.setScene(this.setCharacterCustomizationScene());
        });
        pause.play();
    }

    public Scene setCharacterCustomizationScene() {

        // SETUP GRIDPANE
        GridPane characterGridPane = new GridPane();
        // characterGridPane.setPadding(new Insets(20));
        characterGridPane.setBackground(new Background(new BackgroundFill(
                Color.valueOf(this.colourScheme.backgroundColour1),
                new CornerRadii(0),
                new Insets(0)
        )));
        // Row and Column Constraints
        ColumnConstraints column1 = new ColumnConstraints(50);
        ColumnConstraints column2 = new ColumnConstraints(300);
        ColumnConstraints column3 = new ColumnConstraints(300);
        ColumnConstraints column4 = new ColumnConstraints(300);
        ColumnConstraints column5 = new ColumnConstraints(50);
        RowConstraints row1 = new RowConstraints(50);
        RowConstraints row2 = new RowConstraints( 30);
        RowConstraints row3 = new RowConstraints(600);
        RowConstraints row4 = new RowConstraints(120);
        characterGridPane.getColumnConstraints().addAll(column1 , column2 , column3, column4, column5);
        characterGridPane.getRowConstraints().addAll(row1 , row2 , row3, row4);

        //SETUP OTHER BUTTONS AND LABELS

        // "Character Customization" Label
        Label characterCustomization = new Label("Character Customization");
        characterCustomization.setFont(new Font("Helvetica", 25));
        characterCustomization.setTextFill(Color.web(this.colourScheme.fontColour1));
        characterCustomization.setAlignment(Pos.CENTER);
        characterGridPane.add(characterCustomization, 2, 0, 1, 1);
        characterGridPane.setHalignment(characterCustomization, HPos.CENTER);

        // Character Selection Label
        Label selectedPlayerLabel = new Label("Please select a player.");
        selectedPlayerLabel.setFont(new Font("Helvetica", this.fontSize));
        selectedPlayerLabel.setTextFill(Color.web(this.colourScheme.fontColour2));
        selectedPlayerLabel.setAlignment(Pos.CENTER);
        characterGridPane.add(selectedPlayerLabel, 2, 1, 1, 1);
        characterGridPane.setHalignment(selectedPlayerLabel, HPos.CENTER);

        // Input Text Field for Player Name

        // Play Game Button
        Button playButton = new Button("Play");
        playButton.setId("Play");
        playButton.setAlignment(Pos.CENTER);
        customizeButton(playButton,100, 50);
        makeButtonAccessible(playButton, "Play", "Play Game", "Click to play game with selected character.");
        playButton.setOnAction(e -> {
            if (this.game.player != null) {
                setRoomScene();
            }
        });
        characterGridPane.add(playButton, 3, 3, 1, 1);
        characterGridPane.setHalignment(playButton, HPos.RIGHT);

        // CHARACTER SELECTION BUTTONS

        // Alchemist Player
        Button alchemistButton = new Button("ALCHEMIST CHARACTER \n \n character description here");
        alchemistButton.setId("Alchemist Character");
        alchemistButton.setAlignment(Pos.CENTER);
        customizeButton(alchemistButton, 280, 550);
        Image alchemistImage = new Image("OtherFiles/characterImages/alchemistCharacter.png");
        ImageView alchemistView = new ImageView(alchemistImage);
        alchemistView.setFitHeight(300);
        alchemistView.setFitWidth(250);
        alchemistView.setAccessibleRole(AccessibleRole.IMAGE_VIEW);
        alchemistView.setAccessibleText("DESCRIPTION");
        alchemistButton.setGraphic(alchemistView);
        alchemistButton.setContentDisplay(TOP);
        alchemistButton.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        makeButtonAccessible(alchemistButton, "Alchemist Character", "Alchemist Character", "DESCRIPTION");
        alchemistButton.setOnAction(e -> {
            selectedPlayerLabel.setText("You have selected: Alchemist Character");
            this.game.player = new AlchemistCharacter(this.game.rooms.get(1));
        });

        // Mage Player
        Button mageButton = new Button("   MAGE CHARACTER \n \n character description here");
        mageButton.setId("Mage Character");
        mageButton.setAlignment(Pos.CENTER);
        customizeButton(mageButton, 280, 550);
        Image mageImage = new Image("OtherFiles/characterImages/mageCharacter.png");
        ImageView mageView = new ImageView(mageImage);
        mageView.setFitHeight(300);
        mageView.setFitWidth(250);
        mageView.setAccessibleRole(AccessibleRole.IMAGE_VIEW);
        mageView.setAccessibleText("DESCRIPTION");
        mageButton.setGraphic(mageView);
        mageButton.setContentDisplay(TOP);
        mageButton.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        makeButtonAccessible(mageButton, "Mage Character", "Mage Character", "DESCRIPTION");
        mageButton.setOnAction(e -> {
            selectedPlayerLabel.setText("You have selected: Mage Character");
            this.game.player = new MageCharacter(this.game.rooms.get(1));
        });

        // Warrior Player
        Button warriorButton = new Button("WARRIOR CHARACTER \n \n character description here");
        warriorButton.setId("Warrior Character");
        warriorButton.setAlignment(Pos.CENTER);
        customizeButton(warriorButton, 280, 550);
        Image warriorImage = new Image("OtherFiles/characterImages/warriorCharacter.png");
        ImageView warriorView = new ImageView(warriorImage);
        warriorView.setFitHeight(300);
        warriorView.setFitWidth(250);
        warriorView.setAccessibleRole(AccessibleRole.IMAGE_VIEW);
        warriorView.setAccessibleText("DESCRIPTION");
        warriorButton.setGraphic(warriorView);
        warriorButton.setContentDisplay(TOP);
        warriorButton.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        makeButtonAccessible(warriorButton, "Warrior Character", "Warrior Character", "DESCRIPTION");
        warriorButton.setOnAction(e -> {
            selectedPlayerLabel.setText("You have selected: Warrior Character");
            this.game.player = new WarriorCharacter(this.game.rooms.get(1));
        });

        // Add character buttons to grid pane.
        characterGridPane.add(alchemistButton, 1, 2, 1, 1 );
        characterGridPane.setHalignment(alchemistButton, HPos.CENTER);
        characterGridPane.add(mageButton, 2, 2, 1, 1 );
        characterGridPane.setHalignment(mageButton, HPos.CENTER);
        characterGridPane.add(warriorButton, 3, 2, 1, 1 );
        characterGridPane.setHalignment(warriorButton, HPos.CENTER);

        // SETUP SCENE
        Scene scene = new Scene(characterGridPane ,  1000, 800);
        scene.setFill(Color.valueOf(this.colourScheme.backgroundColour1));
        return scene;
    }

    public void setRoomScene() {
        GridPane roomGridPane = new GridPane();
        roomGridPane.setAlignment(Pos.CENTER);

        // GridPane
        roomGridPane.setPadding(new Insets(20));
        roomGridPane.setBackground(new Background(new BackgroundFill(
                Color.valueOf(this.colourScheme.backgroundColour1),
                new CornerRadii(0),
                new Insets(0)
        )));

        // Row and Column Constraints
        ColumnConstraints column1 = new ColumnConstraints(50);
        ColumnConstraints column2 = new ColumnConstraints(300);
        ColumnConstraints column3 = new ColumnConstraints(300);
        ColumnConstraints column4 = new ColumnConstraints(300);
        ColumnConstraints column5 = new ColumnConstraints(50);
        RowConstraints row1 = new RowConstraints(80);
        RowConstraints row2 = new RowConstraints( 20);
        RowConstraints row3 = new RowConstraints(600);
        RowConstraints row4 = new RowConstraints(100);
        roomGridPane.getColumnConstraints().addAll(column1 , column2 , column3, column4, column5);
        roomGridPane.getRowConstraints().addAll(row1 , row2 , row3, row4);

        // Buttons
        menuButton = new Button("Menu");
        menuButton.setId("Save");
        customizeButton(menuButton, 200, 50);
        makeButtonAccessible(menuButton, "Menu Button", "This button loads the menu.", "This button loads the menu and settings. Click it in order to change your settings.");
        addMenuEvent();

        helpButton = new Button("Instructions");
        helpButton.setId("Instructions");
        customizeButton(helpButton, 200, 50);
        makeButtonAccessible(helpButton, "Help Button", "This button gives game instructions.", "This button gives instructions on the game controls. Click it to learn how to play.");
        addInstructionEvent();

        mapButton = new Button("Map");
        mapButton.setId("Map");
        customizeButton(mapButton, 200, 50);
        makeButtonAccessible(mapButton, "Map Button", "This button loads the game map.", "This button loads the game map. Click on it to see where you are and navigate to other rooms.");
        addMapEvent();

        HBox topButtons = new HBox();
        topButtons.getChildren().addAll(menuButton, helpButton, mapButton);
        topButtons.setSpacing(10);
        topButtons.setAlignment(Pos.CENTER);


        //add all the widgets to the GridPane
        roomGridPane.add(topButtons, 1, 0, 5, 1 );  // Add buttons
        roomGridPane.setHalignment(topButtons, HPos.CENTER);
        // add characters
        ImageView character = new ImageView(this.game.player.characterImage);
        character.setFitWidth(300);
        character.setFitHeight(500);
        Button characterButton = new Button();
        characterButton.setGraphic(character);
        roomGridPane.add(characterButton, 2, 2, 1, 1);

        // add background
        roomGridPane.setStyle("-fx-background-image: url('OtherFiles/roomImages/FrontGate.jpg');");

        var scene = new Scene( roomGridPane ,  1000, 800);
        scene.setFill(Color.valueOf(this.colourScheme.backgroundColour1));
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.show();
    }


    /**
     * makeButtonAccessible
     * __________________________
     * For information about ARIA standards, see
     * https://www.w3.org/WAI/standards-guidelines/aria/
     *
     * @param inputButton the button to add screenreader hooks to
     * @param name ARIA name
     * @param shortString ARIA accessible text
     * @param longString ARIA accessible help text
     */
    public static void makeButtonAccessible(Button inputButton, String name, String shortString, String longString) {
        inputButton.setAccessibleRole(AccessibleRole.BUTTON);
        inputButton.setAccessibleRoleDescription(name);
        inputButton.setAccessibleText(shortString);
        inputButton.setAccessibleHelp(longString);
        inputButton.setFocusTraversable(true);
    }

    /**
     * customizeButton
     * __________________________
     *
     * @param inputButton the button to make stylish :)
     * @param w width
     * @param h height
     */
    private void customizeButton(Button inputButton, int w, int h) {
        inputButton.setPrefSize(w, h);
        inputButton.setFont(new Font("Arial", this.fontSize));
        inputButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
    }

    /**
     * formatText
     * __________________________
     *
     * Format text for display.
     * 
     * @param textToDisplay the text to be formatted for display.
     */
    private void formatText(String textToDisplay) {
        if (textToDisplay == null || textToDisplay.isBlank()) {
            String roomDesc = this.game.getPlayer().getCurrentRoom().getRoomDescription() + "\n";
            String objectString = this.game.getPlayer().getCurrentRoom().getObjectString();
            if (objectString != null && !objectString.isEmpty()) roomDescLabel.setText(roomDesc + "\n\nObjects in this room:\n" + objectString);
            else roomDescLabel.setText(roomDesc);
        } else roomDescLabel.setText(textToDisplay);
        roomDescLabel.setStyle("-fx-text-fill: white;");
        roomDescLabel.setFont(new Font("Arial", this.fontSize));
        roomDescLabel.setAlignment(Pos.CENTER);
    }

    /**
     * getRoomImage
     * __________________________
     *
     * Get the image for the current room and place 
     * it in the roomImageView 
     */
    private ImageView getRoomImage() {
        String roomImage = "OtherFiles/roomImages/" + this.game.player.getCurrentRoom().getRoomName() + ".jpg";

        Image roomImageFile = new Image(roomImage);
        roomImageView = new ImageView(roomImageFile);
        roomImageView.setPreserveRatio(true);
        roomImageView.setFitWidth(400);
        roomImageView.setFitHeight(400);

        //set accessible text
        roomImageView.setAccessibleRole(AccessibleRole.IMAGE_VIEW);
        roomImageView.setAccessibleText(this.game.getPlayer().getCurrentRoom().getRoomDescription());
        roomImageView.setFocusTraversable(true);
        return roomImageView;
    }

    /*
     * Show the game instructions.
     *
     * If helpToggle is FALSE:
     * -- display the help text in the CENTRE of the gridPane (i.e. within cell 1,1)
     * -- use whatever GUI elements to get the job done!
     * -- set the helpToggle to TRUE
     * -- REMOVE whatever nodes are within the cell beforehand!
     *
     * If helpToggle is TRUE:
     * -- redraw the room image in the CENTRE of the gridPane (i.e. within cell 1,1)
     * -- set the helpToggle to FALSE
     * -- Again, REMOVE whatever nodes are within the cell beforehand!
     */
    public void showInstructions() {
        GridPane gridPane = new GridPane();

        // First remove nodes in the cell beforehand
        for (Node node: gridPane.getChildren()) {
            if ((getRowIndex(node) == 1) && (getColumnIndex(node) == 1)) {
                gridPane.getChildren().remove(node);
                break;
            }
        }
        // If helpToggle is false
        if (!this.helpToggle) {
            Label instrLabel =  new Label(this.game.getInstructions());
            instrLabel.setAlignment(Pos.CENTER);
            instrLabel.setFont(new Font("Arial", this.fontSize));
            instrLabel.setStyle("-fx-text-fill: white;");
            instrLabel.setWrapText(true);
            ScrollPane sp = new ScrollPane();
            sp.setContent(instrLabel);
            sp.setPadding(new Insets(25));
            sp.setStyle("-fx-background: #000000; -fx-background-color:transparent; -fx-border-color:royalblue;");
            sp.setFitToWidth(true);
            gridPane.add(sp, 1, 1, 1, 1 );  // Add label
            this.helpToggle = true;
        }
        // If helpToggle is true
        else {
            this.helpToggle = false;
        }
    }

    /**
     * This method handles the event related to the
     * help button.
     */
    public void addInstructionEvent() {
        helpButton.setOnAction(e -> {
            stopArticulation();
            showInstructions();
        });
    }

    private void addMenuEvent() {
        menuButton.setOnAction(e -> {
            stopArticulation();
            gridPane.requestFocus();
            GameMenu menu = new GameMenu(this);
        });
    }

    private void addMapEvent() {
        mapButton.setOnAction(e -> {
            stopArticulation();
            gridPane.requestFocus();
            GameMap map = new GameMap(this);
        });
    }


    /**
     * This method articulates Room Descriptions
     */
    public void articulateRoomDescription() {
        String musicFile;
        String roomName = this.game.getPlayer().getCurrentRoom().getRoomName();

        if (!this.game.getPlayer().getCurrentRoom().getVisited()) musicFile = "OtherFiles/sounds/" + roomName.toLowerCase() + "-long.mp3" ;
        else musicFile = "OtherFiles/sounds/" + roomName.toLowerCase() + "-short.mp3" ;
        musicFile = musicFile.replace(" ","-");

        Media sound = new Media(new File(musicFile).toURI().toString());

        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        mediaPlaying = true;

    }

    /**
     * This method stops articulations 
     * (useful when transitioning to a new room or loading a new game)
     */
    public void stopArticulation() {
        if (mediaPlaying) {
            mediaPlayer.stop(); //shush!
            mediaPlaying = false;
        }
    }
}

