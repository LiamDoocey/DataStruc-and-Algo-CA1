package jewel.ca1;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class addTrayController {

    private Controller mainC;
    @FXML
    private Label fxCaseID;
    @FXML
    private ColorPicker tcp;
    @FXML
    private TextField fxTrayID;
    @FXML
    private TextField fxTrayW;
    @FXML
    private TextField fxTrayH;

    // Setter main controller
    public void setMainC(Controller mainC) {
        this.mainC = mainC;
    }

    // Initialize the case ID
    public void initialize(String caseID){
        fxCaseID.setText(caseID);
    }

    // Submit the tray to the main controller method
    public void submit(){
        try {
            mainC.addTray(fxTrayID.getText(), String.valueOf(tcp.getValue()), Double.parseDouble(fxTrayW.getText()), Double.parseDouble(fxTrayH.getText()));
        }
        catch (Exception e){
            Utils.errorStat("Something went wrong");
        }
    }
}
