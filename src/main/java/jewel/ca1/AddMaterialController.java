package jewel.ca1;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jewel.ca1.MODELS.Material;

public class AddMaterialController {

    @FXML
    private TextField typeMatTxt;
    @FXML
    private TextField QualMatTxt;
    @FXML
    private ListView MatListView;
    @FXML
    private TextField QuanMatTxt;
    @FXML
    private TextArea DescMat;

    private int selectedTrayIndex;
    private int selectedItemIndex;
    private int selectedCase;


    //initializes the selected tray, item and case
    public void initialize(int selectedTrayIndex, int selectedItemIndex, int selectedCase) {
        this.selectedTrayIndex = selectedTrayIndex;
        this.selectedItemIndex = selectedItemIndex;
        this.selectedCase = selectedCase;

        //updates the list view
        updateMatList();
    }


    public void updateMatList(){
        //clears the list view
        if (App.getLinkedListAPI().getCase(selectedCase).getTrays().get(selectedTrayIndex).getItems().get(selectedItemIndex).getMaterials().size() != 0) {
            MatListView.getItems().clear();
        }
        //adds the materials to the list view
        for (int i = 0; i < App.getLinkedListAPI().getCase(selectedCase).getTrays().get(selectedTrayIndex).getItems().get(selectedItemIndex).getMaterials().size(); i++){
            MatListView.getItems().add(App.getLinkedListAPI().getCase(selectedCase).getTrays().get(selectedTrayIndex).getItems().get(selectedItemIndex).getMaterials().get(i).toString());
        }
    }

    //submits the material
    public void submit(){
        try {
            String type = typeMatTxt.getText();
            String desc = DescMat.getText();
            int quantity = Integer.parseInt(QuanMatTxt.getText());
            int quality = Integer.parseInt(QualMatTxt.getText());

            if (selectedItemIndex == -1) {
                Utils.errorStat("Please select an item before adding a material");
                return;
            }

            //calls the addMaterial method with the parameters
            addMaterial(type, desc, quantity, quality);
        }
        catch (Exception e){
            Utils.errorStat("Please fill in all fields");
            e.printStackTrace();
        }
    }


    //adds the material to the list
    public void addMaterial(String type, String desc, int quantity, int quality){
        try {
            if (Utils.isStringSame(typeMatTxt.getText(), " ")|| Utils.isStringSame(QualMatTxt.getText(), " ")|| Utils.isStringSame(QuanMatTxt.getText(), " ")|| Utils.isStringSame(DescMat.getText(), " ")) {
                Utils.errorStat("Please fill in all fields");
            } else {
                //adds the material to the list
                App.getLinkedListAPI().getCase(selectedCase).getTrays().get(selectedTrayIndex).getItems().get(selectedItemIndex).getMaterials().add(new Material(type, desc, quantity, quality));
                updateMatList();
            }
        }
        catch (Exception e){
            Utils.errorStat("Please fill in all fields");
        }
    }






}
