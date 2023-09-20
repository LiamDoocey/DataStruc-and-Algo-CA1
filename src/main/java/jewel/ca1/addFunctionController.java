package jewel.ca1;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class addFunctionController {

    @FXML
    private TextField IDtxtBox;

    @FXML
    private RadioButton wallRad;

    @FXML
    private RadioButton freestandingRad;

    @FXML
    private RadioButton litRad;

    @FXML
    private RadioButton unlitRad;

    private int CurrentPos = 1;

    private Controller mainC;

    // Setting the reference to the main controller
    public void setMainC(Controller mainC) {
        this.mainC = mainC;
    }

    // Initialize method called by JavaFX
    public void initialize() {
        // Create toggle groups for radio buttons
        ToggleGroup lighting = new ToggleGroup();
        ToggleGroup type = new ToggleGroup();

        // Associate radio buttons with their respective toggle groups
        wallRad.setToggleGroup(type);
        freestandingRad.setToggleGroup(type);
        litRad.setToggleGroup(lighting);
        unlitRad.setToggleGroup(lighting);
    }

    // Method called when the "Submit" button is clicked
    public void submit() {
        // Check if the maximum number of cases has been reached
        if (CurrentPos > 14) {
            Utils.errorStat("You have reached the maximum amount of cases -> 14");
            return;
        }

        // Get the selected toggle groups for lighting and type
        ToggleGroup lighting = litRad.getToggleGroup();
        ToggleGroup type = wallRad.getToggleGroup();

        // Get and clean the entered ID
        String ID = IDtxtBox.getText().trim(); // Removes trailing and leading whitespace

        // Check if the ID is empty or contains only spaces
        if (ID == null || Utils.isStringSame(ID, "")) {
            Utils.errorStat("Please enter an ID");
            return;
        }

        // Check if both lighting and type options are selected
        if (lighting.getSelectedToggle() == null || type.getSelectedToggle() == null) {
            Utils.errorStat("Please make sure you have selected one option for each");
            return;
        }

        // Get the selected type and lighting values from the radio buttons
        String selectedType = ((RadioButton) type.getSelectedToggle()).getText();
        String selectedLighting = ((RadioButton) lighting.getSelectedToggle()).getText();

        // Call the addCase method in the main controller to add the new case
        mainC.addCase(ID, selectedType, selectedLighting, CurrentPos);

        // Increment the position counter
        CurrentPos++;

        // Update the case list in the main controller
        mainC.updateCaseList();
    }
}