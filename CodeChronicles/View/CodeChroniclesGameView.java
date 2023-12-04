package View;

import GameModel.CodeChroniclesGame;
import GameModel.Room;
import InteractingWithPlayer.HackCommand;
import InteractingWithPlayer.IgnoreCommand;
import InteractingWithPlayer.NonPlayerCharacters.NPC;
import InteractingWithPlayer.Player.AlchemistCharacter;
import InteractingWithPlayer.Player.MageCharacter;
import InteractingWithPlayer.Player.WarriorCharacter;
import InteractingWithPlayer.TrustCommand;
import javafx.animation.PauseTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static javafx.scene.control.ContentDisplay.TOP;
import static jdk.dynalink.linker.support.Guards.isInstance;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

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
    Button menuButton, instructionsButton, mapButton; //buttons
    Boolean helpToggle = false; //is help on display?
    Boolean mapToggle = false; //is map on display?
    Boolean music;
    Boolean audio;
    GridPane gridPane = new GridPane(); //to hold images and buttons
    Label roomDescLabel = new Label(); //to hold room description and/or instructions
    ImageView roomImageView; //to hold room image
    // the media players
    private MediaPlayer backgroundMusicPlayer;
    private MediaPlayer introductionAudioPlayer;
    private MediaPlayer roomAudioPlayer;
    private boolean backgroundMediaPlaying; //to know if the room descriptions are playing
    private boolean roomMediaPlaying; //to know if the background audio is playing
    private boolean characterMediaPlaying; //to know if the character audio is playing

    // attributes for the background
    Long currentFrame;
    Clip clip;

    // current status of clip
    String status;

    AudioInputStream audioInputStream;
    static String filePath;

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

        // call the method to play reduced background music indefinitely
        this.playBackgroundMusic();
    }

    /**
     * Initialize the UI
     */
    public void intiUI() {

        // SETTING UP THE STAGE
        this.stage.setTitle("Code Chronicles: Wizard's Quest");

        this.gridPane.setAlignment(Pos.CENTER);

        // Setup Playing GridPane
        this.gridPane.setPadding(new Insets(0));
        this.gridPane.setBackground(new Background(new BackgroundFill(
                Color.valueOf(this.colourScheme.backgroundColour),
                new CornerRadii(0),
                new Insets(0)
        )));

        // CREATE LOADING SCREEN
        GridPane gamePane = new GridPane();
        gamePane.setStyle("-fx-background-image: url('OtherFiles/StartScreen.jpg');");
        var scene1 = new Scene(gamePane ,  1000, 800);
        this.stage.setScene(scene1);
        this.stage.setResizable(false);
        this.stage.show();

        // AFTER LOADING SCREEN SHOW CHARACTER CUSTOMIZATION SCREEN
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            this.stage.setScene(this.setCharacterCustomizationScene());
        });
        pause.play();
    }

    public Scene setCharacterCustomizationScene() {

        // SETUP GRID PANE
        GridPane characterGridPane = new GridPane();
        characterGridPane.setBackground(new Background(new BackgroundFill(
                Color.valueOf(this.colourScheme.backgroundColour),
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
        characterCustomization.setTextFill(Color.web(this.colourScheme.regularFontColour));
        characterCustomization.setAlignment(Pos.CENTER);
        characterGridPane.add(characterCustomization, 2, 0, 1, 1);
        characterGridPane.setHalignment(characterCustomization, HPos.CENTER);

        // Character Selection Label
        Label selectedPlayerLabel = new Label("Please select a player.");
        selectedPlayerLabel.setFont(new Font("Helvetica", this.fontSize));
        selectedPlayerLabel.setTextFill(Color.web(this.colourScheme.regularFontColour));
        selectedPlayerLabel.setAlignment(Pos.CENTER);
        characterGridPane.add(selectedPlayerLabel, 2, 1, 1, 1);
        characterGridPane.setHalignment(selectedPlayerLabel, HPos.CENTER);

        // Input Text Field for Player Name
        // TODO

        // Play Game Button
        Button playButton = new Button("Play");
        playButton.setId("Play");
        playButton.setAlignment(Pos.CENTER);
        customizeButton(playButton,100, 50, this.colourScheme.buttonColour2);
        makeButtonAccessible(playButton, "Play", "Play Game", "Click to play game with selected character.");
        playButton.setOnAction(e -> {
            if (this.game.player != null) {
                try {
                    setRoomScene();
                    stopIntroductionAudio(); // add this to stop the introduction audio before transitioning
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        characterGridPane.add(playButton, 3, 3, 1, 1);
        characterGridPane.setHalignment(playButton, HPos.RIGHT);

        // CHARACTER SELECTION BUTTONS

        // Alchemist Player
        Button alchemistButton = new Button("       ALCHEMIST CHARACTER \n \n As an alchemist, you will use the alchemy of programming languages to brew potions and concoct coding elixirs that unravel the secrets of the digital universe. \n \n Lives: 5 \n Code Bytes: 10");
        alchemistButton.setId("Alchemist Character");
        alchemistButton.setAlignment(Pos.TOP_CENTER);
        customizeButton(alchemistButton, 280, 550, this.colourScheme.buttonColour2);
        alchemistButton.wrapTextProperty().setValue(true);
        Image alchemistImage = new Image("OtherFiles/characterImages/alchemistCharacter.png");
        ImageView alchemistView = new ImageView(alchemistImage);
        alchemistView.setFitHeight(300);
        alchemistView.setFitWidth(250);
        alchemistView.setAccessibleRole(AccessibleRole.IMAGE_VIEW);
        alchemistView.setAccessibleText("As an alchemist, you will use the alchemy of programming languages to brew potions and concoct coding elixirs that unravel the secrets of the digital universe.");
        alchemistButton.setGraphic(alchemistView);
        alchemistButton.setContentDisplay(TOP);
        alchemistButton.setStyle("-fx-background-color: "+ this.colourScheme.buttonColour1 + "; -fx-text-fill: white;");
        makeButtonAccessible(alchemistButton, "Alchemist Character", "Alchemist Character", "As an alchemist, you will use the alchemy of programming languages to brew potions and concoct coding elixirs that unravel the secrets of the digital universe.");
        alchemistButton.setOnAction(e -> {
            selectedPlayerLabel.setText("You have selected: Alchemist Character");
            this.game.player = new AlchemistCharacter(this.game.rooms.get("Front Gate"), "Harry Potter"); //TODO: PLAYER NAME
            // play introduction audio if selected by passing audio file to method
            playIntroductionAudio("alchemistDescription.wav");
        });

        // Mage Player
        Button mageButton = new Button("           MAGE CHARACTER \n \n As a mage, you will control the digital realms by wielding spells that manifest as intricate lines of code dancing through the air. \n \n \n Lives: 7 \n Code Bytes: 7");
        mageButton.setId("Mage Character");
        mageButton.setAlignment(Pos.TOP_CENTER);
        customizeButton(mageButton, 280, 550, this.colourScheme.buttonColour2);
        mageButton.wrapTextProperty().setValue(true);
        Image mageImage = new Image("OtherFiles/characterImages/mageCharacter.png");
        ImageView mageView = new ImageView(mageImage);
        mageView.setFitHeight(300);
        mageView.setFitWidth(250);
        mageView.setAccessibleRole(AccessibleRole.IMAGE_VIEW);
        mageView.setAccessibleText("As a mage, you will control the digital realms by wielding spells that manifest as intricate lines of code dancing through the air.");
        mageButton.setGraphic(mageView);
        mageButton.setContentDisplay(TOP);
        mageButton.setStyle("-fx-background-color: "+ this.colourScheme.buttonColour1 + "; -fx-text-fill: white;");
        makeButtonAccessible(mageButton, "Mage Character", "Mage Character", "As a mage, you will control the digital realms by wielding spells that manifest as intricate lines of code dancing through the air.");
        mageButton.setOnAction(e -> {
            selectedPlayerLabel.setText("You have selected: Mage Character");
            this.game.player = new MageCharacter(this.game.rooms.get("Front Gate"), "Harry Potter"); //TODO: PLAYER NAME
            // play introduction audio if selected by passing audio file to method
            playIntroductionAudio("mageDescription.wav");
        });

        // Warrior Player
        Button warriorButton = new Button("         WARRIOR CHARACTER \n \n As a warrior, you will use your digital blade to embody strength, resilience, and martial prowess as you fight coding battles. \n \n \n Lives: 10 \n Code Bytes: 5");
        warriorButton.setId("Warrior Character");
        warriorButton.setAlignment(Pos.TOP_CENTER);
        customizeButton(warriorButton, 280, 550, this.colourScheme.buttonColour2);
        warriorButton.wrapTextProperty().setValue(true);
        Image warriorImage = new Image("OtherFiles/characterImages/warriorCharacter.png");
        ImageView warriorView = new ImageView(warriorImage);
        warriorView.setFitHeight(300);
        warriorView.setFitWidth(250);
        warriorView.setAccessibleRole(AccessibleRole.IMAGE_VIEW);
        warriorView.setAccessibleText("As a warrior, you will use your digital blade to embody strength, resilience, and martial prowess as you fight coding battles.");
        warriorButton.setGraphic(warriorView);
        warriorButton.setContentDisplay(TOP);
        warriorButton.setStyle("-fx-background-color: "+ this.colourScheme.buttonColour1 + "; -fx-text-fill: white;");
        makeButtonAccessible(warriorButton, "Warrior Character", "Warrior Character", "As a warrior, you will use your digital blade to embody strength, resilience, and martial prowess as you fight coding battles.");
        warriorButton.setOnAction(e -> {
            selectedPlayerLabel.setText("You have selected: Warrior Character");
            this.game.player = new WarriorCharacter(this.game.rooms.get("Front Gate"), "Harry Potter"); //TODO: PLAYER NAME
            // play introduction audio if selected by passing audio file to method
            playIntroductionAudio("warriorDescription.wav");
        });

        this.game.rooms.get("Front Gate").visit();

        // Add character buttons to grid pane.
        characterGridPane.add(alchemistButton, 1, 2, 1, 1 );
        characterGridPane.setHalignment(alchemistButton, HPos.CENTER);
        characterGridPane.add(mageButton, 2, 2, 1, 1 );
        characterGridPane.setHalignment(mageButton, HPos.CENTER);
        characterGridPane.add(warriorButton, 3, 2, 1, 1 );
        characterGridPane.setHalignment(warriorButton, HPos.CENTER);

        // SETUP SCENE
        Scene scene = new Scene(characterGridPane ,  1000, 800);
        scene.setFill(Color.valueOf(this.colourScheme.backgroundColour));
        return scene;
    }

    public void showAnimalAlert() {

        // SETUP GRID PANE
        GridPane animalGridPane = new GridPane();
        animalGridPane.setBackground(new Background(new BackgroundFill(
                Color.valueOf(this.colourScheme.backgroundColour),
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
        animalGridPane.getColumnConstraints().addAll(column1 , column2 , column3, column4, column5);
        animalGridPane.getRowConstraints().addAll(row1 , row2 , row3, row4);

        // Play Game Button
        Button playButton = new Button("Play");
        playButton.setId("Play");
        playButton.setAlignment(Pos.CENTER);
        customizeButton(playButton,100, 50, this.colourScheme.buttonColour2);
        makeButtonAccessible(playButton, "Play", "Play Game", "Click to play game with selected character.");
        playButton.setOnAction(e -> {
            if (this.game.player != null) {
                try {
                    setRoomScene();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        animalGridPane.add(playButton, 3, 3, 1, 1);
        animalGridPane.setHalignment(playButton, HPos.RIGHT);

        // SETUP SCENE
        Scene scene = new Scene(animalGridPane ,  1000, 800);
        scene.setFill(Color.valueOf(this.colourScheme.backgroundColour));
        // return scene;
    }

    public void setRoomScene() throws FileNotFoundException {

        GridPane roomPane = new GridPane();
        this.setupGridPane(roomPane);
        this.addGameHeader(roomPane);

        // add characters and NPCs to the GridPane
        ImageView characterView = this.getCharacterImageView();
        ImageView NPCView = this.getNPCImageView(this.game.player.getCurrentRoom().getNPC());
        Button NPCButton = new Button();
        NPCButton.setGraphic(NPCView);
        NPCButton.setBackground(null);
        roomPane.add(characterView, 3, 2);
        roomPane.add(NPCButton, 1, 2);
        roomPane.setValignment(characterView, VPos.CENTER);
        roomPane.setHalignment(characterView, HPos.CENTER);
        roomPane.setValignment(NPCButton, VPos.CENTER);
        roomPane.setHalignment(NPCButton, HPos.CENTER);

        // add room description to GridPane
        this.roomDescLabel.setText(this.game.player.getCurrentRoom().getRoomDescription());
        this.roomDescLabel.setWrapText(true);
        this.roomDescLabel.setPadding(new Insets(30));
        this.roomDescLabel.setAlignment(Pos.TOP_CENTER);
        this.roomDescLabel.setFont(new Font("Helvetica", this.fontSize));
        this.roomDescLabel.setTextFill(Color.web(this.colourScheme.regularFontColour));
        this.roomDescLabel.setMinWidth(1000);
        this.roomDescLabel.setMinHeight(350);
        this.roomDescLabel.setBackground(new Background(new BackgroundFill(Color.valueOf(this.colourScheme.backgroundColour), CornerRadii.EMPTY, Insets.EMPTY)));
        roomPane.add(this.roomDescLabel, 0, 3, 5, 1);

        // add background
        String roomName = this.game.player.getCurrentRoom().getRoomName().replaceAll("\\s", "");
        roomPane.setStyle("-fx-background-image: url('OtherFiles/Images/" + this.colourScheme.colourSchemeName + "/roomImages/" + roomName + ".jpg');");

        this.gridPane = roomPane;
        var scene = new Scene( this.gridPane ,  1000, 800);
        scene.setFill(Color.valueOf(this.colourScheme.backgroundColour));
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.show();

        // What happens when the character clicks on the other character in the room?
        NPCButton.setOnAction(e -> {
            this.roomDescLabel.setText(this.game.player.getCurrentRoom().getNPC().getIntro());
            this.addInteractionCommands();
        });
    }

    public void addInteractionCommands() {
        // Create Buttons
        Button avoidButton = new Button("Avoid");
        avoidButton.setId("Avoid");
        customizeButton(avoidButton, 150, 50, this.colourScheme.buttonColour1);
        makeButtonAccessible(avoidButton, "Menu Button", "This button loads the menu.", "This button loads the menu and settings. Click it in order to change your settings.");
        avoidButton.setOnAction(e -> {
            IgnoreCommand command = new IgnoreCommand(this.game.getPlayer(), this.game.getPlayer().getCurrentRoom().getNPC());
            this.roomDescLabel.setText(command.executeCommand());
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                try {
                    this.setRoomScene();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
            pause.play();
        });

        Button trustButton = new Button("Trust");
        trustButton.setId("Trust");
        customizeButton(trustButton, 150, 50, this.colourScheme.buttonColour1);
        makeButtonAccessible(trustButton, "Help Button", "This button gives game instructions.", "This button gives instructions on the game controls. Click it to learn how to play.");
        addInstructionEvent();
        trustButton.setOnAction(e -> {
            TrustCommand command = new TrustCommand(this.game.getPlayer(), this.game.getPlayer().getCurrentRoom().getNPC());
            this.roomDescLabel.setText(command.executeCommand());
        });

        Button hackButton = new Button("Hack");
        hackButton.setId("Hack");
        customizeButton(hackButton, 150, 50, this.colourScheme.buttonColour1);
        makeButtonAccessible(hackButton, "Map Button", "This button loads the game map.", "This button loads the game map. Click on it to see where you are and navigate to other rooms.");
        addMapEvent();
        hackButton.setOnAction(e -> {
            HackCommand command = new HackCommand(this.game.getPlayer(), this.game.getPlayer().getCurrentRoom().getNPC());
            this.roomDescLabel.setText(command.executeCommand());
        });

        HBox commandButtons = new HBox();
        commandButtons.getChildren().addAll(avoidButton, trustButton, hackButton);
        commandButtons.setSpacing(10);
        commandButtons.setAlignment(Pos.CENTER);

        //add all the widgets to the GridPane
        this.gridPane.add(commandButtons, 1, 3, 3, 1 );  // Add buttons
        this.gridPane.setHalignment(commandButtons, HPos.CENTER);
    }

    public void setupGridPane(GridPane gridPane) {
        gridPane.setBackground(new Background(new BackgroundFill(
                Color.valueOf(this.colourScheme.backgroundColour),
                new CornerRadii(0),
                new Insets(0)
        )));
        // Row and Column Constraints
        ColumnConstraints column1 = new ColumnConstraints(30);
        ColumnConstraints column2 = new ColumnConstraints(320);
        ColumnConstraints column3 = new ColumnConstraints(300);
        ColumnConstraints column4 = new ColumnConstraints(320);
        ColumnConstraints column5 = new ColumnConstraints(30);
        RowConstraints row1 = new RowConstraints(80);
        RowConstraints row2 = new RowConstraints( 20);
        RowConstraints row3 = new RowConstraints(500);
        RowConstraints row4 = new RowConstraints(200);
        gridPane.getColumnConstraints().addAll(column1 , column2 , column3, column4, column5);
        gridPane.getRowConstraints().addAll(row1 , row2 , row3, row4);
    }

    public void addGameHeader(GridPane gridPane) {
        // Create Buttons
        menuButton = new Button("Menu");
        menuButton.setId("Save");
        customizeButton(menuButton, 200, 50, this.colourScheme.buttonColour2);
        makeButtonAccessible(menuButton, "Menu Button", "This button loads the menu.", "This button loads the menu and settings. Click it in order to change your settings.");
        addMenuEvent();

        instructionsButton = new Button("Instructions");
        instructionsButton.setId("Instructions");
        customizeButton(instructionsButton, 200, 50, this.colourScheme.buttonColour2);
        makeButtonAccessible(instructionsButton, "Help Button", "This button gives game instructions.", "This button gives instructions on the game controls. Click it to learn how to play.");
        addInstructionEvent();

        mapButton = new Button("Map");
        mapButton.setId("Map");
        customizeButton(mapButton, 200, 50, this.colourScheme.buttonColour2);
        makeButtonAccessible(mapButton, "Map Button", "This button loads the game map.", "This button loads the game map. Click on it to see where you are and navigate to other rooms.");
        addMapEvent();

        // Player Stats
        Label stats = new Label("Lives: " + this.game.player.getLives() + "\nCode Bytes: " + this.game.player.getCodeBytes());
        stats.setFont(new Font("Helvetica", this.fontSize));
        stats.setTextFill(Color.web(this.colourScheme.regularFontColour));

        HBox header = new HBox();
        header.getChildren().addAll(menuButton, instructionsButton, mapButton, stats);
        header.setSpacing(10);
        header.setAlignment(Pos.CENTER);

        //add all the widgets to the GridPane
        gridPane.add(header, 1, 0, 3, 1 );  // Add buttons
        gridPane.setHalignment(header, HPos.CENTER);
    }

    public ImageView getCharacterImageView() {
        if (this.game.player instanceof AlchemistCharacter) {
            Image playerImage = new Image("OtherFiles/characterImages/alchemistCharacter.png");
            ImageView imageView = new ImageView(playerImage);
            imageView.setFitWidth(400);
            imageView.setFitHeight(500);
            return imageView;
        } else if (this.game.player instanceof MageCharacter) {
            Image playerImage = new Image("OtherFiles/characterImages/mageCharacter.png");
            ImageView imageView = new ImageView(playerImage);
            imageView.setFitWidth(400);
            imageView.setFitHeight(500);
            return imageView;
        } else {
            Image playerImage = new Image("OtherFiles/characterImages/warriorCharacter.png");
            ImageView imageView = new ImageView(playerImage);
            imageView.setFitWidth(400);
            imageView.setFitHeight(500);
            return imageView;
        }
    }

    public ImageView getNPCImageView(NPC character) throws FileNotFoundException {
        FileInputStream path = new FileInputStream("OtherFiles/Images/" + this.colourScheme.colourSchemeName + "/npcImages/" + this.game.player.getCurrentRoom().getNPC().getName() + ".png");
        Image image = new Image(path);
        ImageView view = new ImageView(image);
        view.setFitWidth(400);
        view.setFitHeight(500);
        view.setAccessibleRole(AccessibleRole.IMAGE_VIEW);
        view.setAccessibleText(character.getIntro());
        return view;
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
    private void customizeButton(Button inputButton, int w, int h, String colour) {
        inputButton.setPrefSize(w, h);
        inputButton.setFont(new Font("Arial", this.fontSize));
        inputButton.setStyle("-fx-background-color: " + colour + "; -fx-text-fill: white;");
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
    public void showInstructions() throws FileNotFoundException {
        // If helpToggle is false, add instructions to the grid pane.
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
            this.gridPane.add(sp, 1, 1, 1, 1 );  // Add label
            this.helpToggle = true;
        }
        // If helpToggle is true, show the room scene again.
        else {
            this.setRoomScene();
            this.helpToggle = false;
        }
    }

    public void showMap() throws FileNotFoundException {
        // If the mapToggle is false, show the map on the grid pane.
        if (!this.mapToggle) {
            // Create map background.
            this.gridPane = new GridPane();
            this.setupGridPane(this.gridPane);
            this.addGameHeader(this.gridPane);

            // Populate the room icons.
            HBox roomsRow0 = new HBox();
            HBox roomsRow1 = new HBox();
            HBox roomsRow2 = new HBox();
            VBox allRooms = new VBox();
            roomsRow0.setAlignment(Pos.CENTER);
            roomsRow1.setAlignment(Pos.CENTER);
            roomsRow2.setAlignment(Pos.CENTER);
            roomsRow0.setSpacing(10);
            roomsRow1.setSpacing(10);
            roomsRow2.setSpacing(10);
            allRooms.setSpacing(10);
            for (String roomName : this.game.rooms.keySet()) {
                Room room = this.game.rooms.get(roomName);
                // create room icon
                RoomIcon roomIcon = new UnvisitedRoomIcon(this.game, this, room);
                if (room.getVisited()) {
                    roomIcon = new VisitedRoomIcon(this.game, this, room);
                }
                // add button to map box
                if (room.yCoord == 0) {
                    roomsRow0.getChildren().add(roomIcon.getRoomButton());
                } else if (room.yCoord == 1) {
                    roomsRow1.getChildren().add(roomIcon.getRoomButton());
                } else if (room.yCoord == 2) {
                    roomsRow2.getChildren().add(roomIcon.getRoomButton());
                }
            } allRooms.getChildren().addAll(roomsRow0, roomsRow1, roomsRow2);
            this.gridPane.add(allRooms, 1, 2, 5, 2);
            this.gridPane.setHalignment(allRooms, HPos.CENTER);
            var scene = new Scene( this.gridPane ,  1000, 800);
            scene.setFill(Color.valueOf(this.colourScheme.backgroundColour));
            this.stage.setScene(scene);
            this.stage.setResizable(false);
            this.stage.show();
            this.mapToggle = true;
        }
        // If mapToggle is true, show the room scene again.
        else {
            this.setRoomScene();
            this.mapToggle = false;
        }
    }

    /**
     * This method handles the event related to the instructions button.
     */
    public void addInstructionEvent() {
        instructionsButton.setOnAction(e -> {
            stopArticulation();
            try {
                showInstructions();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void addMenuEvent() {
        menuButton.setOnAction(e -> {
            stopArticulation();
            gridPane.requestFocus();
//            QuestView view = new QuestView(this, this.game.quests.get(0), this.game.player);
//            GameMenu menu = new GameMenu(this);
            LastBattleView view = new LastBattleView(this, this.game.player);
        });
    }

    private void addMapEvent() {
        mapButton.setOnAction(e -> {
            stopArticulation();
            try {
                showMap();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }


    // these method is for playing the room descriptions (long and short)
    /**
     * This method articulates Room Descriptions
     * ____________________
     * Each room has a "short" and "long" audio that can be found
     * in the sub folders audio --> roomDescriptions
     */
    public void articulateRoomDescription() {
        String musicFile;
        String roomName = this.game.getPlayer().getCurrentRoom().getRoomName();

        if (!this.game.getPlayer().getCurrentRoom().getVisited()) musicFile = "audio/roomDescriptionAudio/" + roomName.toLowerCase() + "-long.mp3" ;
        // ^^ the "long" files have the description
        else musicFile = "OtherFiles/sounds/" + roomName.toLowerCase() + "-short.mp3" ;
        // ^^ the "short" files have the room names
        musicFile = musicFile.replace(" ","-");

        Media sound = new Media(new File(musicFile).toURI().toString());

        roomAudioPlayer = new MediaPlayer(sound);
        roomAudioPlayer.play();
        roomMediaPlaying = true;
    }

    /**
     * stopArticulation()
     * ______________________
     * This method stops articulations 
     * (useful when transitioning to a new room or loading a new game)
     */
    public void stopArticulation() {
        if (roomMediaPlaying) {
            roomAudioPlayer.stop(); //shush!
            roomMediaPlaying = false;
        }
    }

    // these methods are for the main background music
    /**
     * playBackgroundMusic
     * ______________________
     * This method controls the main background music for the game.
     * It plays at 50% volume and runs indefinitely for the duration of the game.
     * The background music should be found in audio -> backgroundMusic -> backgroundMusic.wav
     */
    public void playBackgroundMusic() {
        //later switched to a "try/catch" format to fix MediaException errors
        try {
            String musicFile = "audio/backgroundMusic/backgroundMusic.wav";

            //create a media object and media player
            Media sound = new Media(new File(musicFile).toURI().toString());
            backgroundMusicPlayer = new MediaPlayer(sound);

            //self volume to 50% and play in a loop while the view is up
            backgroundMusicPlayer.setVolume(0.2);
            backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundMusicPlayer.play();

        } catch (Exception e) {
            System.out.println("Error loading background music: " + e.getMessage());
        }
    }

    // these methods are for the character introduction audio (during character selection)
    /**
     * playIntroductionAudio
     * ______________________
     * This method plays the character description audio for a character when they are
     * selected in the character customization screen.
     *
     */
    private void playIntroductionAudio(String audioFileName) {
        // changed to a try/catch format to avoid errors
        try {
            String musicFile = "audio/characterDescriptionAudio/" + audioFileName;
            Media sound = new Media(new File(musicFile).toURI().toString());

            // UPDATE: now the audio's should not overlap!
            // check to see if there is any audio playing and stop it
            if (introductionAudioPlayer != null && introductionAudioPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                introductionAudioPlayer.stop();
            }

            introductionAudioPlayer = new MediaPlayer(sound);
            // ^^ that creates a new media player
            introductionAudioPlayer.play();
            // ^^ that plays the new media player
        } catch (Exception e) {
            System.out.println("There's an error with the audio: " + e.getMessage());
        }
    }

    /**
     * stopIntroductionAudio
     * ______________________
     * this method is to stop the introduction audio. It is useful for when the screen
     * is done, or the player has moved on from the "choose character" view.
     *
     */
    private void stopIntroductionAudio() {
        if (introductionAudioPlayer != null && introductionAudioPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            introductionAudioPlayer.stop();
        }
    }
}

