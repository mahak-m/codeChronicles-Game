package InteractingWithPlayer;

public interface Hack {
    /**
     * If the player decides to hack show identity of the character.
     */
    public abstract void showCharacterIdentity();


    /**
     * If the character is a prowler then count if there are enough number of code bytes. If not, then notify the player that collect more.
     */

    public abstract void countBytes();

    /**
     * If the player wants to hack the character then count the number of code bytes. If there are enough code bytes then hack,
     * otherwise play the quest to win and move forward.
     */
    public abstract void playQuest();

}
