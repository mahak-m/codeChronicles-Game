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
        createRooms();
        this.game.setHelpText(parseOtherFile("help"));
    }

     /**
     * Parse Rooms File
     */
    private void createRooms() throws IOException {
        // Create Room 1: Syntax Square
        Room room1 = new Room("Front Gate", 0, 0, "[INSERTROOMDESCRIPTION]");
        this.game.rooms.put(1, room1);
        // Create Room 2: Byte Camp
        Room room2 = new Room("Main Entrance", 1, 0, "[INSERTROOMDESCRIPTION]");
        this.game.rooms.put(2, room2);
        // Create Room 3: Hack Harbour
        Room room3 = new Room("Administrations Office", 2, 0, "[INSERTROOMDESCRIPTION]");
        this.game.rooms.put(3, room3);
        // Create Room 4: Coder's Cove
        Room room4 = new Room("Dorm Rooms", 3, 0, "[INSERTROOMDESCRIPTION]");
        this.game.rooms.put(4, room4);
        // Create Room 5: Algorithm Alley
        Room room5 = new Room("Hackers Hallway", 0, 1, "[INSERTROOMDESCRIPTION]");
        this.game.rooms.put(5, room5);
        // Create Room 6: Logic Lab
        Room room6 = new Room("Coders Building", 1, 1, "[INSERTROOMDESCRIPTION]");
        this.game.rooms.put(6, room6);
        // Create Room 7: Tech Tower
        Room room7 = new Room("Library", 2, 1, "[INSERTROOMDESCRIPTION]");
        this.game.rooms.put(7, room7);
        // Create Room 8: Code Sphere
        Room room8 = new Room("CodeCraft Classroom", 3, 1, "[INSERTROOMDESCRIPTION]");
        this.game.rooms.put(8, room8);
        // Create Room 9: Pixel Plex
        Room room9 = new Room("Locker Room", 0, 2, "[INSERTROOMDESCRIPTION]");
        this.game.rooms.put(9, room9);
        // Create Room 10: Quantum Quarters
        Room room10 = new Room("Fourth Floor", 1, 2, "[INSERTROOMDESCRIPTION]");
        this.game.rooms.put(10, room10);
        // Create Room 11: Data Den
        Room room11 = new Room("Cafeteria", 2, 2, "[INSERTROOMDESCRIPTION]");
        this.game.rooms.put(11, room11);
        // Create Room 12: Chamber of Code
        Room room12 = new Room("SideEntrance", 3, 2, "[INSERTROOMDESCRIPTION]");
        this.game.rooms.put(12, room12);
    }


    /**
     * Parse Files other than Rooms, Objects and Synonyms
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
