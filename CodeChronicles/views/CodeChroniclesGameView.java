package views;

import GameModel.CodeChroniclesGame;
import GameModel.AdventureObject;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import javafx.event.EventHandler; //you will need this too!
import javafx.scene.AccessibleRole;

import java.io.File;

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
    Stage stage; //stage on which all is rendered
    Button menuButton, helpButton; //buttons
    Boolean helpToggle = false; //is help on display?

    GridPane gridPane = new GridPane(); //to hold images and buttons
    Label roomDescLabel = new Label(); //to hold room description and/or instructions
    VBox objectsInRoom = new VBox(); //to hold room items
    VBox objectsInInventory = new VBox(); //to hold inventory items
    ImageView roomImageView; //to hold room image
    TextField inputTextField; //for user input

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
        intiUI();
    }

    /**
     * Initialize the UI
     */
    public void intiUI() {

        // setting up the stage
        this.stage.setTitle("Code Chronicles: Wizard's Quest");

        //Inventory + Room items
        objectsInInventory.setSpacing(10);
        objectsInInventory.setAlignment(Pos.TOP_CENTER);
        objectsInRoom.setSpacing(10);
        objectsInRoom.setAlignment(Pos.TOP_CENTER);

        // GridPane, anyone?
        gridPane.setPadding(new Insets(20));
        gridPane.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#000000"),
                new CornerRadii(0),
                new Insets(0)
        )));

        //Three columns, three rows for the GridPane
        ColumnConstraints column1 = new ColumnConstraints(150);
        ColumnConstraints column2 = new ColumnConstraints(650);
        ColumnConstraints column3 = new ColumnConstraints(150);
        column3.setHgrow( Priority.SOMETIMES ); //let some columns grow to take any extra space
        column1.setHgrow( Priority.SOMETIMES );

        // Row constraints
        RowConstraints row1 = new RowConstraints(90);
        RowConstraints row2 = new RowConstraints( 550 );
        RowConstraints row3 = new RowConstraints();
        row1.setVgrow( Priority.SOMETIMES );
        row3.setVgrow( Priority.SOMETIMES );

        gridPane.getColumnConstraints().addAll( column1 , column2 , column1 );
        gridPane.getRowConstraints().addAll( row1 , row2 , row1 );

        // Buttons
        menuButton = new Button("Menu");
        menuButton.setId("Save");
        customizeButton(menuButton, 100, 50);
        makeButtonAccessible(menuButton, "Menu Button", "This button loads the menu.", "This button loads the menu and settings. Click it in order to change your settings.");
        addMenuEvent();

        helpButton = new Button("Instructions");
        helpButton.setId("Instructions");
        customizeButton(helpButton, 200, 50);
        makeButtonAccessible(helpButton, "Help Button", "This button gives game instructions.", "This button gives instructions on the game controls. Click it to learn how to play.");
        addInstructionEvent();

        HBox topButtons = new HBox();
        topButtons.getChildren().addAll(menuButton, helpButton);
        topButtons.setSpacing(10);
        topButtons.setAlignment(Pos.CENTER);

        inputTextField = new TextField();
        inputTextField.setFont(new Font("Arial", 16));
        inputTextField.setFocusTraversable(true);

        inputTextField.setAccessibleRole(AccessibleRole.TEXT_AREA);
        inputTextField.setAccessibleRoleDescription("Text Entry Box");
        inputTextField.setAccessibleText("Enter commands in this box.");
        inputTextField.setAccessibleHelp("This is the area in which you can enter commands you would like to play.  Enter a command and hit return to continue.");
        addTextHandlingEvent(); //attach an event to this input field

        //labels for inventory and room items
        Label objLabel =  new Label("Objects in Room");
        objLabel.setAlignment(Pos.CENTER);
        objLabel.setStyle("-fx-text-fill: white;");
        objLabel.setFont(new Font("Arial", 16));

        Label invLabel =  new Label("Your Inventory");
        invLabel.setAlignment(Pos.CENTER);
        invLabel.setStyle("-fx-text-fill: white;");
        invLabel.setFont(new Font("Arial", 16));

        //add all the widgets to the GridPane
        gridPane.add( objLabel, 0, 0, 1, 1 );  // Add label
        gridPane.add( topButtons, 1, 0, 1, 1 );  // Add buttons
        gridPane.add( invLabel, 2, 0, 1, 1 );  // Add label

        Label commandLabel = new Label("What would you like to do?");
        commandLabel.setStyle("-fx-text-fill: white;");
        commandLabel.setFont(new Font("Arial", 16));

        updateScene(""); //method displays an image and whatever text is supplied
        updateItems(); //update items shows inventory and objects in rooms

        // adding the text area and submit button to a VBox
        VBox textEntry = new VBox();
        textEntry.setStyle("-fx-background-color: #000000;");
        textEntry.setPadding(new Insets(20, 20, 20, 20));
        textEntry.getChildren().addAll(commandLabel, inputTextField);
        textEntry.setSpacing(10);
        textEntry.setAlignment(Pos.CENTER);
        gridPane.add( textEntry, 0, 2, 3, 1 );

        // Render everything
        var scene = new Scene( gridPane ,  1000, 800);
        scene.setFill(Color.BLACK);
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
        inputButton.setFont(new Font("Arial", 16));
        inputButton.setStyle("-fx-background-color: #17871b; -fx-text-fill: white;");
    }

    /**
     * addTextHandlingEvent
     * __________________________
     * Add an event handler to the inputTextField attribute
     *
     * Your event handler should respond when users 
     * hits the ENTER or TAB KEY. If the user hits 
     * the ENTER Key, strip white space from the
     * input to inputTextField and pass the stripped
     * string to submitEvent for processing.
     *
     * If the user hits the TAB key, move the focus 
     * of the scene onto any other node in the scene 
     * graph by invoking requestFocus method.
     */
    private void addTextHandlingEvent() {
        TextField textField = this.inputTextField;

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String text = textField.getText().trim();
                submitEvent(text);
                textField.clear();
            }
        };
        // when enter is pressed
        inputTextField.setOnAction(event);

    }


    /**
     * submitEvent
     * __________________________
     *
     * @param text the command that needs to be processed
     */
    private void submitEvent(String text) {

        text = text.strip(); //get rid of white space
        stopArticulation(); //if speaking, stop

        if (text.equalsIgnoreCase("LOOK") || text.equalsIgnoreCase("L")) {
            String roomDesc = this.game.getPlayer().getCurrentRoom().getRoomDescription();
            String objectString = this.game.getPlayer().getCurrentRoom().getObjectString();
            if (!objectString.isEmpty()) roomDescLabel.setText(roomDesc + "\n\nObjects in this room:\n" + objectString);
            articulateRoomDescription(); //all we want, if we are looking, is to repeat description.
            return;
        } else if (text.equalsIgnoreCase("HELP") || text.equalsIgnoreCase("H")) {
            showInstructions();
            return;
        } else if (text.equalsIgnoreCase("COMMANDS") || text.equalsIgnoreCase("C")) {
            showCommands(); //this is new!  We did not have this command in A1
            return;
        }

        //try to move!
        String output = this.game.interpretAction(text); //process the command!

        if (output == null || (!output.equals("GAME OVER") && !output.equals("FORCED") && !output.equals("HELP"))) {
            updateScene(output);
            updateItems();
        } else if (output.equals("GAME OVER")) {
            updateScene("");
            updateItems();
            PauseTransition pause = new PauseTransition(Duration.seconds(10));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
        } else if (output.equals("FORCED")) {
            //write code here to handle "FORCED" events!
            //Your code will need to display the image in the
            //current room and pause, then transition to
            //the forced room.
            updateScene(this.game.player.getCurrentRoom().getRoomDescription());
            updateItems();
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(event -> {
                submitEvent("FORCED");
            });
            pause.play();
        }
    }


    /**
     * showCommands
     * __________________________
     *
     * update the text in the GUI (within roomDescLabel)
     * to show all the moves that are possible from the 
     * current room.
     */
    private void showCommands() {
        String commands = this.game.getPlayer().getCurrentRoom().getCommands();
        this.roomDescLabel.setText("You can move in these directions:\n" + commands);
    }


    /**
     * updateScene
     * __________________________
     *
     * Show the current room, and print some text below it.
     * If the input parameter is not null, it will be displayed
     * below the image.
     * Otherwise, the current room description will be dispplayed
     * below the image.
     * 
     * @param textToDisplay the text to display below the image.
     */
    public void updateScene(String textToDisplay) {

        getRoomImage(); //get the image of the current room
        formatText(textToDisplay); //format the text to display
        roomDescLabel.setPrefWidth(500);
        roomDescLabel.setPrefHeight(500);
        roomDescLabel.setTextOverrun(OverrunStyle.CLIP);
        roomDescLabel.setWrapText(true);
        VBox roomPane = new VBox(roomImageView,roomDescLabel);
        roomPane.setPadding(new Insets(10));
        roomPane.setAlignment(Pos.TOP_CENTER);
        roomPane.setStyle("-fx-background-color: #000000;");

        gridPane.add(roomPane, 1, 1);
        stage.sizeToScene();

        //finally, articulate the description
        if (textToDisplay == null || textToDisplay.isBlank()) articulateRoomDescription();
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
        roomDescLabel.setFont(new Font("Arial", 16));
        roomDescLabel.setAlignment(Pos.CENTER);
    }

    /**
     * getRoomImage
     * __________________________
     *
     * Get the image for the current room and place 
     * it in the roomImageView 
     */
    private void getRoomImage() {

        int roomNumber = this.game.getPlayer().getCurrentRoom().getRoomNumber();
        String roomImage = "OtherFiles/room-images/" + roomNumber + ".png";

        Image roomImageFile = new Image(roomImage);
        roomImageView = new ImageView(roomImageFile);
        roomImageView.setPreserveRatio(true);
        roomImageView.setFitWidth(400);
        roomImageView.setFitHeight(400);

        //set accessible text
        roomImageView.setAccessibleRole(AccessibleRole.IMAGE_VIEW);
        roomImageView.setAccessibleText(this.game.getPlayer().getCurrentRoom().getRoomDescription());
        roomImageView.setFocusTraversable(true);
    }

    /**
     * updateItems
     * __________________________
     *
     * This method is partially completed, but you are asked to finish it off.
     *
     * The method should populate the objectsInRoom and objectsInInventory Vboxes.
     * Each Vbox should contain a collection of nodes (Buttons, ImageViews, you can decide)
     * Each node represents a different object.
     * 
     * Images of each object are in the assets 
     * folders of the given adventure game.
     */
    public void updateItems() {
        //please use setAccessibleText to add "alt" descriptions to your images!
        //the path to the image of any is as follows:
        //this.model.getDirectoryName() + "/objectImages/" + objectName + ".jpg";
        this.objectsInRoom.getChildren().clear();
        this.objectsInInventory.getChildren().clear();
        //write some code here to add images of objects in a given room to the objectsInRoom Vbox
        for (AdventureObject obj : this.game.getPlayer().getCurrentRoom().objectsInRoom) {
            // Creating the image
            Image objIm = new Image("OtherFiles/objectImages/" + obj.getName() + ".jpg");
            ImageView objView = new ImageView(objIm);
            objView.setAccessibleRole(AccessibleRole.IMAGE_VIEW);
            objView.setAccessibleText(obj.getDescription());
            objView.setFocusTraversable(true);
            objView.setFitWidth(100);
            // Creating the button
            Button objButton = new Button(obj.getName());
            objButton.setId(obj.getName());
            objButton.setGraphic(objView);
            objButton.setContentDisplay(TOP);
            objButton.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
            makeButtonAccessible(objButton, obj.getName(), obj.getName(), obj.getDescription());
            // Action
            objButton.setOnAction(e -> {
                if (this.objectsInRoom.getChildren().contains(objButton)) {
                    this.game.player.takeObject(obj.getName());
                    this.updateItems();
                } else if (this.objectsInInventory.getChildren().contains(objButton)) {
                    this.game.player.dropObject(obj.getName());
                    this.updateItems();
                }
            });
            this.objectsInRoom.getChildren().add(objButton);
        }
        //write some code here to add images of objects in a player's inventory room to the objectsInInventory Vbox
        for (AdventureObject obj : this.game.getPlayer().inventory) {
            // Creating the image
            Image objIm = new Image("OtherFiles/objectImages/" + obj.getName() + ".jpg");
            ImageView objView = new ImageView(objIm);
            objView.setAccessibleRole(AccessibleRole.IMAGE_VIEW);
            objView.setAccessibleText(obj.getDescription());
            objView.setFocusTraversable(true);
            objView.setFitWidth(100);
            // Creating the button
            Button objButton = new Button(obj.getName());
            objButton.setId(obj.getName());
            objButton.setGraphic(objView);
            objButton.setContentDisplay(TOP);
            objButton.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
            makeButtonAccessible(objButton, obj.getName(), obj.getName(), obj.getDescription());
            // Action
            objButton.setOnAction(e -> {
                if (this.objectsInRoom.getChildren().contains(objButton)) {
                    this.game.player.takeObject(obj.getName());
                    this.updateItems();
                } else if (this.objectsInInventory.getChildren().contains(objButton)) {
                    this.game.player.dropObject(obj.getName());
                    this.updateItems();
                }
            });
            // Adding the button to Vbox
            this.objectsInInventory.getChildren().add(objButton);
        }
        ScrollPane scO = new ScrollPane(objectsInRoom);
        scO.setPadding(new Insets(10));
        scO.setStyle("-fx-background: #000000; -fx-background-color:transparent;");
        scO.setFitToWidth(true);
        gridPane.add(scO,0,1);

        ScrollPane scI = new ScrollPane(objectsInInventory);
        scI.setFitToWidth(true);
        scI.setStyle("-fx-background: #000000; -fx-background-color:transparent;");
        gridPane.add(scI,2,1);

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
        // First remove nodes in the cell beforehand
        for (Node node: this.gridPane.getChildren()) {
            if ((getRowIndex(node) == 1) && (getColumnIndex(node) == 1)) {
                this.gridPane.getChildren().remove(node);
                break;
            }
        }
        // If helpToggle is false
        if (!this.helpToggle) {
            Label instrLabel =  new Label(this.game.getInstructions());
            instrLabel.setAlignment(Pos.CENTER);
            instrLabel.setFont(new Font("Arial", 16));
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
            updateScene("");
            this.helpToggle = false;
        }
    }

    public void showMenu() {
        // First remove nodes in the cell beforehand
        for (Node node: this.gridPane.getChildren()) {
            if ((getRowIndex(node) == 1) && (getColumnIndex(node) == 1)) {
                this.gridPane.getChildren().remove(node);
                break;
            }
        }
        // If helpToggle is false
        if (!this.helpToggle) {
            Label instrLabel =  new Label(this.game.getInstructions());
            instrLabel.setAlignment(Pos.CENTER);
            instrLabel.setFont(new Font("Arial", 16));
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
            updateScene("");
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
