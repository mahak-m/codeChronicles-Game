import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

import GameModel.CodeChroniclesGame;
import GameModel.Room;
import InteractingWithPlayer.HackCommand;
import InteractingWithPlayer.IgnoreCommand;
import InteractingWithPlayer.NonPlayerCharacters.NPC;
import InteractingWithPlayer.NonPlayerCharacters.Prowler;
import InteractingWithPlayer.Player.Player;
import InteractingWithPlayer.TrustCommand;
import com.sun.javafx.collections.ObservableMapWrapper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import javafx.scene.control.Menu;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import View.GameMenu;
import View.CodeChroniclesGameView;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.testfx.framework.junit5.ApplicationTest;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.*;


public class CodeChroniclesTest  {
    @Test
    void saveMenuTest() throws IOException{
        GameApp gameApp = new GameApp();
        GameMenu menu = new GameMenu(gameApp.view);
        menu.getFontSizeBox().getValueFactory().setValue(15);
        menu.getColourModeBox().setValue("Monochrome");
        menu.getMusicBox().setValue("On");
        menu.getAudioBox().setValue("Off");
        menu.save_changes();

        assertEquals(15, gameApp.view.fontSize);
        assertEquals("Monochrome", gameApp.view.colourScheme.colourSchemeName);
        assertTrue(gameApp.view.music);
        assertFalse(gameApp.view.audio);
    }

    /**
     * Ignore Command case, where the player
     * has enough codeBytes to ignore character that is only 5 codeBytes(initially)
     * and as the player wants to ignore the character,
     * the player is left only with 0 bytes now.
     * @throws IOException
     */
    @Test
    void IgnoreCommandTest() throws IOException{
        Room room = new Room("Coders Building", 1, 1, "You stand at the entrance of Coders building, a sleek structure of glass and steel where the distant clicks of keyboards and soft discussions envelop the surroundings.");
        Player player = new Player("Lisa", "Hi, I am Mage", room , "Mage");
        IgnoreCommand ignoreCommand = new IgnoreCommand();
        String enoughBytes = ignoreCommand.playerIgnore(player);
        assertEquals("You decided to ignore the NPC character.", enoughBytes);
        assertEquals(0, player.getCodeBytes());
    }

    /**
     * Hack Command
     */
    @Test
    void HackCommandCodeBytesTest() throws IOException{
        Room room = new Room("Coders Building", 1, 1, "You stand at the entrance of Coders building, a sleek structure of glass and steel where the distant clicks of keyboards and soft discussions envelop the surroundings.");
        Player player = new Player("Lisa", "Hi, I am Mage", room , "Mage");
        HackCommand hack = new HackCommand();
        player.setPLayerBytes(5);
        String hackCommand = hack.countBytes(player);
        assertEquals("You have 5 code bytes", hackCommand);

    }
    @Test
    void restart() throws IOException{

    }

}
