package jewel.ca1;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jewel.ca1.MODELS.Item;

import java.io.IOException;

public class addItemController {

    private Controller mainC;
    @FXML
    private ChoiceBox<String> jtype;
    @FXML
    private ChoiceBox<String> gtype;
    @FXML
    private ListView<String> itemListView;
    @FXML
    private ListView<String> trayListView;
    @FXML
    private TextField rPrice;
    @FXML
    private TextField imgTxt;
    @FXML
    private TextArea descTxt;
    @FXML
    private Button removeItemBtn;
    @FXML
    private Text caseLabel;

    private int caseID;
    private String caseUID;

    //Boolean properties for the list views
    private final BooleanProperty isListSelected = new SimpleBooleanProperty(false);
    private final BooleanProperty isItemListSelected = new SimpleBooleanProperty(false);

    //Initialize the controller
    public void initialize(int caseID, String caseUID) {
        //Set the caseID
        this.caseID = caseID;
        this.caseUID = caseUID;

        caseLabel.setText("Trays in case: " + caseUID);

        //Bind the buttons to the boolean properties
        rPrice.disableProperty().bind(isListSelected.not());
        imgTxt.disableProperty().bind(isListSelected.not());
        descTxt.disableProperty().bind(isListSelected.not());
        jtype.disableProperty().bind(isListSelected.not());
        gtype.disableProperty().bind(isListSelected.not());

        //Bind the list views to the boolean properties
        trayListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            isListSelected.set(newValue != null);
            //Update the items list when a tray is selected
            updateItemsList(trayListView.getSelectionModel().getSelectedIndex());
        });

        //Bind the remove item button to the boolean property
        removeItemBtn.disableProperty().bind(isItemListSelected.not());
        //Bind the item list view to the boolean property
        itemListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            isItemListSelected.set(newValue != null);
        });

        //Add the tray types to the tray choice box
        gtype.getItems().add(0, "Male");
        gtype.getItems().add(1, "Female");
        gtype.getItems().add(2, "Unisex");

        //Add the jewellery types to the jewellery choice box
        jtype.getItems().add(0, "Ring");
        jtype.getItems().add(1, "Necklace");
        jtype.getItems().add(2, "Bracelet");
        jtype.getItems().add(3, "Earrings");
        jtype.getItems().add(4, "Watch");

        //Update the tray list
        updateTrayList();
    }

    //Update the tray list
    public void updateTrayList(){
        if (App.getLinkedListAPI().getCase(caseID).getTrays().size() != 0){
            trayListView.getItems().clear();
        }
        for (int i = 0; i < App.getLinkedListAPI().getCase(caseID).getTrays().size(); i++) {
            trayListView.getItems().add(App.getLinkedListAPI().getCase(caseID).getTrays().get(i).toString());
        }
    }

    //Update the items list
    public void updateItemsList(int selectedTray){

        if (App.getLinkedListAPI().getCase(caseID).getTrays().get(selectedTray).getItems().size() == 0){
            itemListView.getItems().clear();
        }
        else
        {
            if (App.getLinkedListAPI().getCase(caseID).getTrays().get(selectedTray).getItems().size() != 0) {
                itemListView.getItems().clear();
            }
            for (int i = 0; i < App.getLinkedListAPI().getCase(caseID).getTrays().get(selectedTray).getItems().size(); i++) {
                itemListView.getItems().add(App.getLinkedListAPI().getCase(caseID).getTrays().get(selectedTray).getItems().get(i).toString());
            }
        }
    }

    //Submit the item
    public void submit() {
        try {
            String desc = descTxt.getText().trim();
            String img = imgTxt.getText().trim();
            double price = Double.parseDouble(rPrice.getText().trim());
            String type = jtype.getValue();
            String gender = gtype.getValue();

            //Pass item fields in addItem method
            addItem(desc, img, price, type, gender);

        }
        catch (Exception e) {
            Utils.errorStat("Please Fill in all fields");
        }
    }

    //Add the item to the tray
    public void addItem(String desc, String img, double price, String type, String gender){
        try{
            //Get the selected tray index
            int selectedTrayIndex = trayListView.getSelectionModel().getSelectedIndex();

            //Add the item to the tray
            App.getLinkedListAPI().getCase(caseID).getTrays().get(selectedTrayIndex).getItems().add(new Item(desc, gender, type, img, price));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Item added successfully");
            alert.setContentText("Type: " + type + "\n" +
                    "Gender: " + gender + "\n" +
                    "Price: €" + price + "\n" +
                    "Description: " + desc + "\n" +
                    "Image: " + img + "\n");

            alert.showAndWait();

            //Update the items list passing the selected tray index
            updateItemsList(selectedTrayIndex);
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }

    //Load the add material window
    public void loadAddMaterial() throws IOException {

        //Get the selected tray and item index
        int selectedTrayIndex = trayListView.getSelectionModel().getSelectedIndex();
        int selectedItemIndex = itemListView.getSelectionModel().getSelectedIndex();

        if (selectedItemIndex == -1){
            Utils.errorStat("Please select an item before adding a material");
            return;
        }

        Stage aM = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("addMaterial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        //Pass the selected tray and item index to the add material controller along with CASE ID
        AddMaterialController aMc = fxmlLoader.getController();
        aMc.initialize(selectedTrayIndex, selectedItemIndex, caseID);

        aM.setTitle("Jewells for you - Add Material");
        aM.setScene(scene);
        aM.setResizable(false);
        aM.getIcons().add(new Image(String.valueOf(App.class.getResource("logos/transparent.png"))));
        aM.initModality(Modality.APPLICATION_MODAL);
        aM.show();
    }

    //remove Item
    public void removeItem(){
        //Get the selected tray and item index and pass them to the remove item method
        App.getLinkedListAPI().getCase(caseID).getTrays().get(trayListView.getSelectionModel().getSelectedIndex()).getItems().remove(itemListView.getSelectionModel().getSelectedIndex());
        //Update the items list
        updateItemsList(trayListView.getSelectionModel().getSelectedIndex());
    }
}
