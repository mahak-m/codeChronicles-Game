package View;
import GameModel.Pet.VirtualVulture;
import InteractingWithPlayer.Quest;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import InteractingWithPlayer.Player.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class QuestView {
    BorderPane borderPane = new BorderPane();
    private CodeChroniclesGameView gameView;
    private Stage stage = new Stage();
    private Button continueButton = new Button("Continue");
    private Button challengeButton = new Button("Accept the challenge!");
    private Button submitButton = new Button("Submit");
    private Button exitButton = new Button("Exit");
    private Quest quest;
    private Player player;

    public QuestView(CodeChroniclesGameView gameView, Quest quest, Player player) {
        this.gameView = gameView;
        this.quest = quest;
        this.player = player;

        // SET THE STAGE UP
        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.initOwner(gameView.stage);
        this.stage.setTitle(this.quest.questName);
        this.stage.initStyle(StageStyle.UNDECORATED);

        // Set up the gridPane
        this.borderPane.setPadding(new Insets(10));

        // Create alertLabel
        Label alertLabel = new Label("You have decided to hack " + this.quest.prowler.getProwlerName() + "... A PROWLER!");
        alertLabel.setPadding(new Insets(20));
        alertLabel.setFont(new Font("Georgia", 22));
        alertLabel.setTextFill(Color.valueOf("white"));
        alertLabel.setBackground(new Background(new BackgroundFill(Color.valueOf("#000000"), CornerRadii.EMPTY, Insets.EMPTY)));
        BorderPane.setAlignment(alertLabel, Pos.TOP_CENTER);
        this.borderPane.setTop(alertLabel);

        // Create continueButton
        this.continueButton.setId("Continue");
        this.continueButton.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
        this.continueButton.setPrefSize(90, 30);
        this.continueButton.setFont(new Font("Georgia", 16));
        makeButtonAccessible(continueButton, "Continue", "Continue", "Continue to the next screen.");
        this.continueButton.setOnAction(e -> {
            setInstructionScene();
        });

        BorderPane.setAlignment(continueButton, Pos.BOTTOM_RIGHT);
        this.borderPane.setBottom(continueButton);

        // Set up borderPane
        try {
            Image image = new Image(new FileInputStream("OtherFiles/prowlerImages/" + this.quest.prowler.getProwlerName() + ".png"));
            borderPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(600, 600, true, true, true, false))));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // CREATE A SCREEN
        var questScene1 = new Scene(borderPane, 600, 600);
        this.stage.setScene(questScene1);
        this.stage.setResizable(false);
        this.stage.show();
    }

    public void setInstructionScene() {
        BorderPane borderPane2 = new BorderPane();
        // Set up the gridPane
        borderPane2.setPadding(new Insets(10));

        // Set up borderPane
        try {
            Image image = new Image(new FileInputStream("OtherFiles/questScreens/instruction screen.png"));
            borderPane2.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(600, 600, true, true, true, false))));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Create challengeButton
        this.challengeButton.setId("Accept the Challenge!");
        this.challengeButton.setPrefSize(200, 30);
        this.challengeButton.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
        this.challengeButton.setFont(new Font("Georgia", 16));
        makeButtonAccessible(challengeButton, "Challenge", "Accept the challenge!", "Accept the challenge and see the question.");
        challengeButton.setOnAction(e -> {
            setQuestionScene(false);
        });
        borderPane2.setAlignment(challengeButton, Pos.BOTTOM_RIGHT);
        borderPane2.setBottom(challengeButton);

        // Create questIntroLabel
        Label questIntroLabel = new Label("Now, you must player their Quest!");
        questIntroLabel.setFont(new Font("Georgia", 30));
        questIntroLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        borderPane2.setAlignment(questIntroLabel, Pos.TOP_CENTER);
        borderPane2.setTop(questIntroLabel);

        // Create instructionsLabel
        Label instructionsLabel =
                new Label("Welcome to " + this.quest.prowler.getProwlerName() + "'s Quest, " + this.quest.questName +". Here are the instructions:\n" +
                "1. Objective: To win, you must get the correct answer in two attempts or less.\n" +
                        "2. Game Format: You'll be presented with one multiple-choice question (MCQ) where you have to choose the correct answer out of the four provided.\n" +
                        "3. Attempts:\n" +
                        "- First Attempt: Answer the question. If you get it right on the first try, you will win and earn 10 code bytes.\n" +
                        "- Second Attempt: If you don't answer correctly in the first try, you get a hint from your pet. But this will only " +
                        "get you 6 code bytes, if you answer correctly.\n" +
                        "- Virtual Vulture: If you have the virtual vulture, you can use it to reveal the answer, but this will only get you " +
                        "2 code bytes, if you answer correctly.\n" +
                        "4. Winning and losing: If you get the right answer, you win, but if you don't get it even after 2 attempts, you lose " +
                        "a life and get 0 codebytes.\n" +
                        "Remember to use your attempts wisely and make appropriate choices. You may now accept the challenge.");
        instructionsLabel.setWrapText(true);
        instructionsLabel.setFont(new Font("Georgia", 18));
        instructionsLabel.setTextFill(Color.valueOf("black"));
        instructionsLabel.setBackground(new Background(new BackgroundFill(Color.valueOf("#37a6a4"), CornerRadii.EMPTY, Insets.EMPTY)));
        instructionsLabel.setOpacity(0.90);
        instructionsLabel.setStyle("-fx-border-color: yellow;");
        instructionsLabel.setPadding(new Insets(10));
        borderPane2.setAlignment(instructionsLabel, Pos.CENTER_LEFT);
        borderPane2.setCenter(instructionsLabel);

        var questScene2 = new Scene(borderPane2, 600, 600);
        this.stage.setScene(questScene2);
        this.stage.setResizable(false);
        this.stage.show();
    }

    public void setQuestionScene(boolean doneTwoAttempts) {
        BorderPane borderPane3 = new BorderPane();
        // Set up the gridPane
        borderPane3.setPadding(new Insets(10));

        // Set up borderPane
        try {
            Image image = new Image(new FileInputStream("OtherFiles/questScreens/question screen.png"));
            borderPane3.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(600, 600, true, true, true, false))));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Create submitButton
        this.submitButton.setId("Submit Answer");
        this.submitButton.setPrefSize(200, 30);
        this.submitButton.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
        this.submitButton.setFont(new Font("Georgia", 16));
        makeButtonAccessible(submitButton, "Submit", "Submit answer", "Submit your answer to be evaluated.");
        borderPane3.setAlignment(submitButton, Pos.BOTTOM_RIGHT);
        borderPane3.setBottom(submitButton);

        // Create Options Toggle Group
        final ToggleGroup optionsGroup = new ToggleGroup();

        RadioButton optionA = new RadioButton(this.quest.questionOptions.get(0)); RadioButton optionB = new RadioButton(this.quest.questionOptions.get(1));

        optionA.setToggleGroup(optionsGroup); optionB.setToggleGroup(optionsGroup);

        RadioButton optionC = new RadioButton(this.quest.questionOptions.get(2));  RadioButton optionD = new RadioButton(this.quest.questionOptions.get(3));

        optionC.setToggleGroup(optionsGroup); optionD.setToggleGroup(optionsGroup);

        ArrayList<RadioButton> radioButtons = new ArrayList<RadioButton>();
        radioButtons.add(optionA); radioButtons.add(optionB); radioButtons.add(optionC); radioButtons.add(optionD);

        for (RadioButton radioButton : radioButtons) {
            radioButton.setMinHeight(80);
            radioButton.setMinWidth(250);
            radioButton.setFont(new Font("Georgia", 16));
            radioButton.setStyle("-fx-background-color: #7286b8; -fx-text-fill: white; -fx-border-color: black;");
            radioButton.setPadding(new Insets(10));
            radioButton.setOpacity(0.9);
            radioButton.setWrapText(true);
        }

        // Create VBox for options A and C
        VBox optionsLeft = new VBox();
        optionsLeft.setSpacing(10);
        optionsLeft.getChildren().addAll(optionA, optionC);
        optionsLeft.setAlignment(Pos.CENTER_LEFT);

        // Create VBox for options B and D
        VBox optionsRight = new VBox();
        optionsRight.setSpacing(10);
        optionsRight.getChildren().addAll(optionB, optionD);
        optionsRight.setAlignment(Pos.CENTER_RIGHT);

        // Create HBox to hold optionsLeft and optionsRight
        HBox options = new HBox();
        options.setSpacing(10);
        options.getChildren().addAll(optionsLeft, optionsRight);
        options.setAlignment(Pos.CENTER);

        borderPane3.setAlignment(options, Pos.CENTER);
        borderPane3.setCenter(options);

        // Create questionLabel
        Label questionLabel = new Label(this.quest.questQuestion);
        questionLabel.setFont(new Font("Georgia", 30));
        questionLabel.setWrapText(true);
        questionLabel.setTextFill(Color.valueOf("white"));
        questionLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        borderPane3.setAlignment(questionLabel, Pos.TOP_CENTER);
        borderPane3.setTop(questionLabel);

        this.submitButton.setOnAction(e -> {
            RadioButton selectedRadioButton = (RadioButton) optionsGroup.getSelectedToggle();
            if (selectedRadioButton != null) {
                if (selectedRadioButton.getText().equals(this.quest.getQuestAnswer())) {
                    setEndScene("won");
                }
                else if (! selectedRadioButton.getText().equals(this.quest.getQuestAnswer()) & ! doneTwoAttempts) {
                    setHintScene();
                }
                else if (! selectedRadioButton.getText().equals(this.quest.getQuestAnswer()) & doneTwoAttempts) {
                    setEndScene("lost");
                }
            }
        });

        var questScene3 = new Scene(borderPane3, 600, 600);
        this.stage.setScene(questScene3);
        this.stage.setResizable(false);
        this.stage.show();
    }

    public void setEndScene(String status) {
        BorderPane borderPane4 = new BorderPane();
        // Set up the gridPane
        borderPane4.setPadding(new Insets(10));

        // Set up borderPane
        try {
            Image image = new Image(new FileInputStream("OtherFiles/questScreens/question screen.png"));
            borderPane4.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(600, 600, true, true, true, false))));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Create questOutroLabel
        String endLine = (status.equals("won")) ? "You won the Quest!" : "You lost the Quest!";
        Label questOutroLabel = new Label(endLine);
        questOutroLabel.setFont(new Font("Georgia", 60));
        questOutroLabel.setStyle("-fx-background-color: #000000;");
        questOutroLabel.setTextFill(Color.valueOf("white"));
        borderPane4.setAlignment(questOutroLabel, Pos.CENTER);
        borderPane4.setCenter(questOutroLabel);

        //Create prowlerDialogueLabel
//        Label prowlerDialogueLabel = new Label("I can't believe this! How dare those pathetic humans\n" +
//                                                "defeat me? Me, a prowler of unparalleled power! They\n" +
//                                                "think they can just come in with their feeble tricks\n" +
//                                                "and take me down? I'll show them! I'll rise again, fiercer\n" +
//                                                "and stronger than ever before, and crush their puny\n" +
//                                                "existence into dust beneath my claws! They'll regret\n" +
//                                                "the day they dared to challenge me!");

        // Create exitButton
        this.exitButton.setId("Exit");
        this.exitButton.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
        this.exitButton.setPrefSize(90, 30);
        this.exitButton.setFont(new Font("Georgia", 16));
        makeButtonAccessible(exitButton, "Exit", "Exit the quest", "Exit the quest and return to the room.");
        this.exitButton.setOnAction(e -> {
            this.stage.close();
        });

        borderPane4.setAlignment(this.exitButton, Pos.BOTTOM_RIGHT);
        borderPane4.setBottom(this.exitButton);

        var questScene4 = new Scene(borderPane4, 600, 600);
        this.stage.setScene(questScene4);
        this.stage.setResizable(false);
        this.stage.show();
    }

    public void setHintScene() {
        BorderPane borderPane5 = new BorderPane();
        // Set up the gridPane
        borderPane5.setPadding(new Insets(10));

        // Set up borderPane
        try {
            Image image = new Image(new FileInputStream("OtherFiles/questScreens/question screen.png"));
            borderPane5.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(600, 600, true, true, true, false))));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Create a hintLabel
        Label hintLabel = new Label("Oops! Wrong Answer. Please select one of the following options to help you " +
                "in the next attempt.");
        hintLabel.setFont(new Font("Georgia", 30));
        hintLabel.setWrapText(true);
        hintLabel.setTextFill(Color.valueOf("white"));
        hintLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-background-color: #000000");
        hintLabel.setPadding(new Insets(10));
        borderPane5.setAlignment(hintLabel, Pos.TOP_CENTER);
        borderPane5.setTop(hintLabel);

        // Create a hintButton
        Button hintButton = new Button("Hint");
        makeButtonAccessible(hintButton, "Hint", "This is a hint to the question.", this.quest.getQuestHint());
        hintButton.setMinHeight(80);
        hintButton.setMinWidth(250);
        hintButton.setFont(new Font("Georgia", 18));
        hintButton.setStyle("-fx-background-color: #7286b8; -fx-text-fill: white; -fx-border-color: black;");
        hintButton.setPadding(new Insets(10));
        hintButton.setOpacity(0.9);
        hintButton.setWrapText(true);
        hintButton.setOnAction(e -> {
            hintButton.setText(this.quest.getQuestHint());
            this.quest.usedHint();
        });


        // Create a revealAnswerButton
        Button revealAnswerButton = new Button("Reveal Answer");
        makeButtonAccessible(revealAnswerButton, "Reveal Answer", "This is the answer to the question", "Please click on the button to see the answer");
        revealAnswerButton.setMinHeight(80);
        revealAnswerButton.setMinWidth(250);
        revealAnswerButton.setFont(new Font("Georgia", 18));
        revealAnswerButton.setStyle("-fx-background-color: #7286b8; -fx-text-fill: white; -fx-border-color: black;");
        revealAnswerButton.setPadding(new Insets(10));
        revealAnswerButton.setOpacity(0.9);
        revealAnswerButton.setWrapText(true);
        hintButton.setWrapText(true);
//        this.player.setPet(new NanoBunny("Bunny"));
        revealAnswerButton.setOnAction(e -> {
            if (this.player.getPet() instanceof VirtualVulture) {
                revealAnswerButton.setText(this.quest.getQuestAnswer());
                this.quest.usedAnswer();
            }
            else {
                revealAnswerButton.setText("Option not available; you don't have a Virtual Vulture as a pet.");
            }
        });

        // Create HBox for the buttons
        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.getChildren().addAll(hintButton, revealAnswerButton);
        buttons.setAlignment(Pos.CENTER);

        borderPane5.setAlignment(buttons, Pos.CENTER);
        borderPane5.setCenter(buttons);

        // Create Back button
        Button back = new Button("Back to the Question");
        back.setStyle("-fx-background-color: #000000; -fx-text-fill: white;");
        back.setMinWidth(100);
        back.setMinHeight(30);
        back.setFont(new Font("Georgia", 16));
        makeButtonAccessible(back, "Back", "Back to the question", "Go back to the question using this button.");
        back.setOnAction(e -> {
            if (this.quest.isWithHint() || this.quest.isWithAnswer()) {
                setQuestionScene(true);
            }
        });
        borderPane5.setAlignment(back, Pos.BOTTOM_RIGHT);
        borderPane5.setBottom(back);

        var questScene5 = new Scene(borderPane5, 600, 600);
        this.stage.setScene(questScene5);
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
}


