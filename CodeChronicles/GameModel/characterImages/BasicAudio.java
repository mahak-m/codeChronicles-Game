package GameModel.characterImages;

import GameModel.CodeChroniclesGame;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * This class deals with basic audio (mostly background music!)
 * It initiates the music depending on the view:
 * START: is your hair real - Kensuke Ushio - A Silent Voice soundtrack
 * MENU: minecraft -- biome fest/ minecraft [music box]
 * BATTLE: Furina: All the World's a Stage/Genshin Impact [Music Box]
 * GAMEPLAY: Doraemon theme (music box)
 * QUEST: Pied Pepper/Bangtan Boys(BTS) [Music Box]
 */

public class BasicAudio {
    CodeChroniclesGame model; //model of the game
    private MediaPlayer mediaPlayer; //to play audio
    private boolean mediaPlaying; //to know if the audio is playing


    /**
     * This method plays the menu music
     */
    public void menuMusic(
    ) {
        String audioPath = "path/to/firefly_forest.mp3";
        Media media = new Media(new File(audioPath).toURI().toString());

        // create mediaplayer
        mediaPlayer = new MediaPlayer(media);
        // set mediaplayer to play indefinetly
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        // start playing
        // remove this from here and add to the view file ****
        mediaPlayer.play();
        mediaPlaying = true;
    }


    /**
     * This method articulates Room Descriptions
     */
    public void articulateRoomDescription() {
        String musicFile;
        // String adventureName = this.model.getDirectoryName();
        String roomName = this.model.getPlayer().getCurrentRoom().getRoomName();

        // if (!this.model.getPlayer().getCurrentRoom().getVisited()) musicFile = "./" + adventureName + "/sounds/" + roomName.toLowerCase() + "-long.mp3" ;
        // else musicFile = "./" + adventureName + "/sounds/" + roomName.toLowerCase() + "-short.mp3" ;
        // musicFile = musicFile.replace(" ","-");

        // Media sound = new Media(new File(musicFile).toURI().toString());

        // mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        mediaPlaying = true;
    }

    /**
     * This method stops articulations
     * (useful when transitioning to a new room or loading a new game)
     * ** also use this when transition
     */
    public void stopArticulation() {
        if (mediaPlaying) {
            mediaPlayer.stop(); //shush!
            mediaPlaying = false;
        }
    }



}
