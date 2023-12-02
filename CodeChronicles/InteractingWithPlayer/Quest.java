package InteractingWithPlayer;

import java.util.ArrayList;
import InteractingWithPlayer.NonPlayerCharacters.Prowler;

public class Quest {
    public String questName; // The quest's name.
    public String questQuestion; // The quest's question.
    public ArrayList<String> questionOptions; // The options of the MCQ.
    protected String questAnswer; // The answer to the question.
    protected String questHint; // The hint for the question.
    public Prowler prowler; // The prowler who proposes this question.

    /**
     * Quest Constructor
     *
     * @param name the quest's name
     * @param question the quest's question
     * @param options the quest's question's options
     * @param answer the answer to the question
     * @param hint the hint for the question
     * @param prowler the prowler who proposes this question
     */
    public Quest(String name, String question, ArrayList<String>options, String answer, String hint, Prowler prowler) {
        this.questName = name;
        this.questQuestion = question;
        this.questionOptions = options;
        this.questAnswer = answer;
        this.questHint = hint;
        this.prowler = prowler;
    }

    // incomplete right now
}