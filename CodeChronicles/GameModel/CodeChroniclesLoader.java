package GameModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class AdventureLoader. Loads an adventure from files.
 */
public class CodeChroniclesLoader {

    private CodeChroniclesGame game; //the game to return

    /**
     * Adventure Loader Constructor
     * __________________________
     * Initializes attributes
     * @param game the game that is loaded
     */
    public CodeChroniclesLoader(CodeChroniclesGame game) {
        this.game = game;
    }

     /**
     * Load game from directory
     */
    public void loadGame() throws IOException {
        parseRooms();
        this.game.setHelpText(parseOtherFile("help"));
    }

    /**
     * Parse Rooms File
     */
    public void parseRooms() throws IOException {

        BufferedReader buff = new BufferedReader(new FileReader("OtherFiles/rooms.txt"));

        while (buff.ready()) {
            String roomName = buff.readLine();
            Integer roomXCoord = Integer.parseInt(buff.readLine());
            Integer roomYCoord = Integer.parseInt(buff.readLine());
            String roomDescription = buff.readLine();
            String separator = buff.readLine();
            if (separator != null && !separator.isEmpty())
                System.out.println("Formatting Error!");
            Room newRoom = new Room(roomName, roomXCoord, roomYCoord, roomDescription);
            this.game.rooms.add(newRoom);
        }
    }

    /**
     * Parse Prowlers File
     */
    public void parseProwlers() throws IOException {

        BufferedReader buff = new BufferedReader(new FileReader("OtherFiles/prowlers.txt"));


    }

    /**
     * Parse School Members File
     */
    public void parseSchoolMembers() throws IOException {

        BufferedReader buff = new BufferedReader(new FileReader("OtherFiles/schoolmembers.txt"));


    }



    /**
     * Parse Files other than Rooms, Prowlers and SchoolMembers
     *
     * @param fileName the file to parse
     */
    public String parseOtherFile(String fileName) throws IOException {
        String text = "";
        fileName = "OtherFiles" + "/" + fileName + ".txt";
        BufferedReader buff = new BufferedReader(new FileReader(fileName));
        String line = buff.readLine();
        while (line != null) { // while not EOF
            text += line+"\n";
            line = buff.readLine();
        }
        return text;
    }

}
