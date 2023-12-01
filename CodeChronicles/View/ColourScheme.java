package View;

public class ColourScheme {

    String colourSchemeName; // Colour Scheme folder name without spaces for path to images in colour scheme folder.
    String backgroundColour;
    String regularFontColour;
    String headingsFontColour;
    String buttonColour1;
    String buttonColour2;

    public ColourScheme (String colourScheme) {
        if (colourScheme.equals("High Contrast")) {
            this.colourSchemeName = "HighContrast";
            this.backgroundColour = "";
            this.regularFontColour = "";
            this.headingsFontColour = "";
            this.buttonColour1 = "";
            this.buttonColour2 = "";
        } else if (colourScheme.equals("Monochrome")) {
            this.colourSchemeName = "Monochrome";
            this.backgroundColour = "";
            this.regularFontColour = "";
            this.headingsFontColour = "";
            this.buttonColour1 = "";
            this.buttonColour2 = "";
        } else { // default is "Game Theme"
            this.colourSchemeName = "GameTheme";
            this.backgroundColour = "#210776";
            this.regularFontColour = "#5CE1E6";
            this.headingsFontColour = "#FFFFFF";
            this.buttonColour1 = "#5CE1E6";
            this.buttonColour2 = "#8C52FF";
        }
    }
}
