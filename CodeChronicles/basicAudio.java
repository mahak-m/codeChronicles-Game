package week3.group_project.group_99.CodeChronicles;

import javax.sound.sampled.AudioSystem;
import java.io.File;

import javax.sound.sampled.*;

public class basicAudio {

    private boolean audible;
    private String introText;
    //An attribute to store the Introductory text of the game.
    private Clip clip;
    private String helpText;
    //A variable to store the Help text of the game. This text is displayed when the user types "HELP" command.

    // first, these methods deal with articulating the descriptions of the room

    /**
     * articulateDescription
     * __________________________
     * Use this method to make the description of the given room audible.
     */
    public void articulateDescription() {

        try {
            String filename;

            //shall we play the long or the short audio description of this room?
            if (!this.isVisited) filename = "./" + this.adventureName + "/sounds/" + this.roomName.toLowerCase() + "-long.wav" ;
            else filename = "./" + this.adventureName + "/sounds/" + this.roomName.toLowerCase() + "-short.wav" ;

            filename = filename.replace(" ","-");

            this.clip = AudioSystem.getClip(); //We can use this to play a .wav file
            this.clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            this.clip.start();

        } catch (Exception e) {
            System.out.println(e.getMessage()); //what happened?
        }

        return;
    }

    /**
     * stopDescription
     * __________________________
     * Use this method to stop voicing the description of the given room.
     * You will need want to stop the clip whenever you exit a room, or else
     * you'll end up with a cacophony of sounds!
     */
    public void stopDescription() {
        if (this.clip != null) this.clip.stop();
    }


    public void playMenuAudio() {
        // work on this part later
        }

    public void playBattleAudio() {

    }
}




