package InteractingWithPlayer;

public class HackCommand implements Hack{
    // for now

    int MINIMUM_BYTES = 2;
    int codeBytes = Character.getCodeBytes();
    String playQuest = Player.playQuest();
    //constructor
    public HackCommand(int numberOfBytes){
        this.codeBytes = numberOfBytes;
    }

    /**
     * This method reveals the identity of the character when the player
     * chooses to hack the character.
     */
    @Override
    public void showCharacterIdentity() {
        // check if character type is Prowler, warn the player.
        if (characterType == "Prowler") {
            System.out.println("Danger! You decided to hack a Prowler.");
        }
        // check if character type is school member, greet the player.
        else {
            System.out.println("You decided to hack a School Member. Hi! You are doing great in the game");
        }
    }

    /**
     * This method checks if the player has the minimum required code bytes
     * to hack the prowler.
     */
    @Override
    public void countBytes() {
        System.out.println("You have "+ this.codeBytes + "code bytes");
        //conditions
        if (polymorphicProwler() && this.codeBytes < this.MINIMUM_BYTES){
            System.out.println("Danger! You don't have enough code bytes to fight the prowler." +
                    "Look up for School Members to help you win.");
        }
        // If you have more than the minimum code bytes you play the quest.
        else {
            String playQuest1 = this.playQuest;

        }
    }

    private boolean polymorphicProwler() {
        return true;
    }

    @Override
    public void playQuest() {

        // just play the quest
        // if you win - you get +10 code bytes
        // if you lose - pet helps +6 or + 2 code bytes
        // else you lose entire life

        if (this.codeBytes <= MINIMUM_BYTES){
            System.out.println("Congratulations! You won the Quest. You hacked into Prowler's mind");
        }
        else {
            System.out.println("You lost the Quest!!Now you have 1 chance remaining. Use your pet to help you in the quest");
        }

    }
}
