package View;

public class ColourScheme {

    String backgroundColour1;
    String backgroundColour2;
    String fontColour1;
    String fontColour2;
    String accentColour1;
    String accentColour2;
    String accentColour3;

    public ColourScheme (String colourScheme) {
        if (colourScheme.equals("Light")) {
            this.backgroundColour1 = "#FFFFFF";
            this.backgroundColour2 = "D7CEDB";
            this.fontColour1 = "000000";
            this.fontColour2 = "260F5E";
            this.accentColour1 = "4A1385";
            this.accentColour2 = "131B91";
            this.accentColour3 = "136591";
        } else if (colourScheme.equals("High Contrast (Dark)")) {
            this.backgroundColour1 = "";
            this.backgroundColour2 = "";
            this.fontColour1 = "";
            this.fontColour2 = "";
            this.accentColour1 = "";
            this.accentColour2 = "";
            this.accentColour3 = "";
        } else if (colourScheme.equals("Monochrome")) {
            this.backgroundColour1 = "";
            this.backgroundColour2 = "";
            this.fontColour1 = "";
            this.fontColour2 = "";
            this.accentColour1 = "";
            this.accentColour2 = "";
            this.accentColour3 = "";
        } else if (colourScheme.equals("Game Theme")) {
            this.backgroundColour1 = "#210776";
            this.backgroundColour2 = "0F0440";
            this.fontColour1 = "#5CE1E6";
            this.fontColour2 = "#FFFFFF";
            this.accentColour1 = "#5CE1E6";
            this.accentColour2 = "#8C52FF";
            this.accentColour3 = "#086DCC";
        } else { // default is "Game Theme"
            this.backgroundColour1 = "#210776";
            this.backgroundColour2 = "0F0440";
            this.fontColour1 = "#5CE1E6";
            this.fontColour2 = "#FFFFFF";
            this.accentColour1 = "#5CE1E6";
            this.accentColour2 = "#8C52FF";
            this.accentColour3 = "#086DCC";
        }
    }
}
