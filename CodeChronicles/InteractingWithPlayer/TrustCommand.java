package InteractingWithPlayer;

public class TrustCommand implements Trust {

    private int life = 5;
    int codeBytes = Character.getCodeBytes();

    /**
     * As the player decides to trust the character, show the player the identity of the character.
     */
    @Override
    public void showCharacterIdentity() {
        if (characterType == "Prowler"){
            System.out.println("Danger! You decided to trust a Prowler.");
        }
        else {
            System.out.println("You decided to trust a School Member. Hi! You are doing great in the game. " +
                    "Here are 10 code ");
        }

    }
    /**
     * As the player decides to trust the character, show the player life reduces.
     */

    @Override
    public void reducePlayerLife() {
        if (characterType == "Prowler"){
            life = life - 1;
        }
        else{
            this.codeBytes  = this.codeBytes + 10;
        }

    }
}
