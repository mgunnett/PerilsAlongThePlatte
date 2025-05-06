package perilsalongtheplatte;
import javax.swing.*;

/**
 * Popups contains the many Java popups the program will display to give educational content. These popups will be used to both collect input from the user
 * (such as names, day to rest, etc.) and display when the user experiences an event. Each popup utilizes the JOptionPane class to show specific popups.
 * @author Parker West
 */
public class Popups {
    private static LandmarkDescriptions landDesc = new LandmarkDescriptions(); //create landmark description instance
    private Perils peril = new Perils(); //create perils instance
    private int tradingCounter = 0; //track how many times the user has traded
    private boolean isGameDone = false; //tracks if the game has ended  

    /*******************
     *OPTION MENU ITEMS*
     *******************/
    //these menu items will be called when the user selects a button option, such as rest

    /**
     * Creates a popup that allows the user to select the number of days to rest. 
     * @return number of days the user chooses to rest (0–7). 
     */
    public int restDays() {
        //create objects to be integrated into the JOptionPane
        int days; //value that will be stored
        final int MAX = 7; //the user can only rest a MAX of 7 days (1 week)
        final int MIN = 0; //useful if the user wants to exit without doing anything

        SpinnerNumberModel model = new SpinnerNumberModel //creates the parameters of the spinner
        (0, MIN, MAX, 1); //sets the bound/increments of the spinner. 
        JSpinner spinner = new JSpinner(model); //creates the actual spinner object

        //popup dialog with a JSpinner to get input for the number of days to rest
        days = JOptionPane.showOptionDialog(
                null,                         //no parent component, centers on screen
                spinner,                      //inputs the spinner into the popup
                "How many days to rest?",    //title
                JOptionPane.OK_CANCEL_OPTION, //which buttons to display. This popup uses a OK/Cancel option to confirm
                JOptionPane.QUESTION_MESSAGE, //what icon to display. This popup uses the question icon
                null,                          //no custom icon
                null,                          //no custom buttons
                null);                         //no initial object selection

        //update days if the OK label is selected
        if (days == JOptionPane.OK_OPTION) {
            days = (Integer) spinner.getValue(); //downcast to an integer value
        }
        return days; 
    }

    /**************
     *EVENT POPUPS*
     **************/
    //these popups run when the user reaches a critical event, such as a landmark, deadly peril, etc. 

    /**
     * Displays a popup when a deadly peril is encountered.
     * @param message Message about the peril the user encountered. 
     */
    public void perilPopup(String message) {
        JOptionPane.showMessageDialog(
                null,                         //no parent
                message,                      //displays the passed String to the dialog area
                "Peril Encountered!",        //<-- displays that title
                JOptionPane.WARNING_MESSAGE); //display warning icon
    }

    /********************
     *EDUCATIONAL POPUPS*
     ********************/
    //these popups run when the user reaches an educational event, such as a landmark,

    /**
     * Displays a basic popup when a landmark is reached.
     * @param Landmark the name of the landmark reached. 
     */
    public void landmarkPopup(String landmark) {
        //show a popup saying what landmark was reached
        JOptionPane.showMessageDialog(
                null,
                "You have reached " + landmark + "!",
                "Landmark Reached!",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows educational content and branching decisions for specific landmarks. 
     * @param landmark The name of the landmark the player reached. 
     */
    public void educationalDescription(String landmark) {
        //if the user encounters a river, prompt them to cross or ferry. 
        if (landmark.equals("Kansas River")) { 
            Object[] options = {"Cross", "Ferry"};
            int choice = JOptionPane.showOptionDialog(
                null,
                landDesc.kansasRiver,
                "Kansas River Information:",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);   // default selection is "Cross"
            //check the response and act based on that
            if (choice == 0) { //choosing to cross
                if (peril.diesInRiver()) { //if the person dies from the river, tell them with a popup
                    JOptionPane.showMessageDialog(null, 
                            "Your Party has died in the river! Better luck next time :-(",
                            "You Lost!", 
                            JOptionPane.WARNING_MESSAGE);
                    isGameDone = true; 
                }
            } else if (choice == 1) {
                // User chose "Ferry"
                System.out.println("You chose to take the ferry.");
            }
        }
        else if(landmark.equals("Big Blue River")){ //another river crossing
            Object[] options = {"Cross", "Ferry"};
            int choice = JOptionPane.showOptionDialog(
                null,
                landDesc.bigBlueRiver,
                "Big Blue River Information:",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);   // default selection is "Cross"
            //check the response and act based on that
            if (choice == 0) { //choosing to cross
                if (peril.diesInRiver()) { //if the person dies from the river, tell them with a popup
                    JOptionPane.showMessageDialog(null, 
                            "Your Party has died in the river! Better luck next time :-(",
                            "You Lost!", 
                            JOptionPane.WARNING_MESSAGE);
                    isGameDone = true; 
                }
            } else if (choice == 1) {
                // User chose "Ferry"
                System.out.println("You chose to take the ferry.");
            }
        }
        else if(landmark.equals("Fort Kearny")){ 
            //show Fort Kearny info
            JOptionPane.showMessageDialog(null, landDesc.fortKearny, "Fort Kearny Information:", JOptionPane.INFORMATION_MESSAGE); 
        }
        else if(landmark.equals("Chimney Rock")){ 
            //show Chimney Rock info
            JOptionPane.showMessageDialog(null, landDesc.chimneyRock, "Chimney Rock Information:", JOptionPane.INFORMATION_MESSAGE); 
        }
        else if(landmark.equals("Fort Loramie")){
            //show Fort Laramie info and end the game
            JOptionPane.showMessageDialog(null, landDesc.fortLaramie, "Fort Laramie Information:", JOptionPane.INFORMATION_MESSAGE); 
            JOptionPane.showMessageDialog(null, landDesc.endingInfo, "The End:", JOptionPane.INFORMATION_MESSAGE);
            isGameDone = true; 
        }
    }

    /**
     * Displays hunting educational content popup.
     */
    public void descriptionHuntingInfo() {
        //show hunting info popup
        JOptionPane.showMessageDialog(null, landDesc.huntingInfo, "Hunting Information:", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays trading educational content popup.
     */
    public void descriptionTradingInfo() {
        //show trading info popup
        JOptionPane.showMessageDialog(null, landDesc.tradingInfo, "Trading Information:", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays illness educational content popup.
     */
    public void descriptionIllnessInfo() {
        //show illness info popup
        JOptionPane.showMessageDialog(null, landDesc.illnessInfo, "Illness Information:", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays the intro content at the start of the game.
     */
    public static void descriptionBeginningInfo() {
        //show introduction popup
        JOptionPane.showMessageDialog(null, landDesc.beginningInfo, "Beginning Introduction:", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows educational info about the Mormon Trail.
     */
    public static void mormonTrailInfo() {
        //show mormon trail info
        JOptionPane.showMessageDialog(null, landDesc.mormonTrail, "Mormon Trail Background:", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows educational info about the California Trail.
     */
    public static void californiaTrailInfo() {
        //show california trail info
        JOptionPane.showMessageDialog(null, landDesc.californiaTrail, "California Trail Background:", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows educational info about the Oregon Trail.
     */
    public static void oregonTrailInfo() {
        //show oregon trail info
        JOptionPane.showMessageDialog(null, landDesc.oregonTrail, "Oregon Trail Background:", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Getter to check whether the game is finished.
     * @return True if the game has ended.
     */
    public boolean isGameDone() {
        return isGameDone; 
    }
}
