package InteractingWithPlayer;


/**
 * This interface comes in action when the player chooses to trust the character
 */
public interface Trust {


    /**
     * If the player trusts the character, then reveal its identity if it is a SchoolMember or
     * PolymorphicProwler
     */
    public abstract void showCharacterIdentity();

    /**
     * If the player trusts the character and the character turns out to be a
     * PolymorphicProwler, then reduce player's life
     */
    public abstract void reducePlayerLife();


}
