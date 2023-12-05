import java.io.IOException;
import GameModel.CodeChroniclesGame;
import GameModel.Room;
import InteractingWithPlayer.HackCommand;
import InteractingWithPlayer.IgnoreCommand;
import InteractingWithPlayer.NonPlayerCharacters.NPC;
import InteractingWithPlayer.NonPlayerCharacters.Prowler;
import InteractingWithPlayer.NonPlayerCharacters.SchoolMember;
import InteractingWithPlayer.Player.Player;
import InteractingWithPlayer.TrustCommand;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import View.GameMenu;
import View.CodeChroniclesGameView;


public class CodeChroniclesTest  {

    /**
     * Menu to check the save Commands method
     */
    @Test
    void saveMenuTest() throws IOException{

        GameApp gameApp = new GameApp();
        GameMenu menu = new GameMenu(gameApp.view, true, false, 15, "Monochrome");
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
        Player player = new Player("Lisa",  room , "Mage", 7,7);
        NPC npc = new NPC("Olivia Peterson", "Hi my name is Olivia Peterson. Click below.");
        IgnoreCommand ignoreCommand = new IgnoreCommand(player, npc);
        String enoughBytes = ignoreCommand.executeCommand();
        assertEquals("You decided to ignore the NPC character.", enoughBytes);
        assertEquals(2, player.getCodeBytes());
    }

    /**
     * Hack Command
     */

    @Test
    void HackCommandCodeBytesTest() throws IOException{
        CodeChroniclesGame model = new CodeChroniclesGame(); //change the name of the game if you want to try something bigger!
        CodeChroniclesGameView view = new CodeChroniclesGameView(model, new Stage());
        Room room = new Room("Coders Building", 1, 1, "You stand at the entrance of Coders building, a sleek structure of glass and steel where the distant clicks of keyboards and soft discussions envelop the surroundings.");
        Player player = new Player("Lisa",  room , "Mage", 7,7);
        NPC npc = new NPC("Olivia Peterson", "Hi my name is Olivia Peterson. Click below.");
        HackCommand hack = new HackCommand(player,npc, view );

        String hackCommand = hack.showCharacterIdentity(npc, player, view);
        assertEquals("You don't have enough code bytes to hack this person." +
                " Look around for School Members to help you with collecting code bytes", hackCommand);

    }
    @Test
    void TrustCommandReveal() throws IOException{
        Room room = new Room("Coders Building", 1, 1, "You stand at the entrance of Coders building, a sleek structure of glass and steel where the distant clicks of keyboards and soft discussions envelop the surroundings.");
        Player player = new Player("Lisa",  room , "Mage", 7,7);
        Prowler prowler = new Prowler("Olivia Peterson", "Sinister Shade","Hi my name is Olivia Peterson. Click below." );
        TrustCommand trust = new TrustCommand(player, prowler);
        //with prowler
        int playercodebytes = player.getCodeBytes();
        int playerLife = player.getLives();
        String trustCommandOutput = trust.executeCommand();

        assertEquals("Oh no! You decided to trust a Prowler and lost 1 life", trustCommandOutput);
        assertEquals(playerLife - 1, player.getLives());
        assertEquals(playercodebytes, player.getCodeBytes());

        //with school member
        SchoolMember schoolmember = new SchoolMember("Crownmark Markuss", "Hi my name is Crownmark Markuss.");
        TrustCommand trust2 = new TrustCommand(player, schoolmember);
        //with prowler
        int playercodebytes2 = player.getCodeBytes();
        int playerLife2 = player.getLives();
        String trustCommandOutput2 = trust2.executeCommand();

        assertEquals("You decided to trust a School Member. \n Hi " + player.getPlayerName() + "! I am so happy to hear you're helping us defeat the Polymorphic prowlers. Here are 10 code bytes to help you with your mission.", trustCommandOutput2);
        assertEquals(playerLife2 , player.getLives());
        assertEquals(playercodebytes2 + 10, player.getCodeBytes());


    }

}
