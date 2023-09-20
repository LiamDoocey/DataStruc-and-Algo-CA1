package jewel.ca1;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import jewel.ca1.MODELS.DisplayCase;
import jewel.ca1.MODELS.DisplayTray;
import jewel.ca1.MODELS.Item;

public class FullStockController {

    @FXML
    private ListView<Item> stockListView;

    @FXML
    private Text stockValue;

    @FXML
    public void updateStockValue() {

        double totalRetailPrice = 0;

        //Adds the cost of each item to the total
        for (int i =0; i < App.getLinkedListAPI().getCases().size(); i++){
            for (int j = 0; j < App.getLinkedListAPI().getCases().get(i).getTrays().size(); j++){
                for (int k = 0; k < App.getLinkedListAPI().getCases().get(i).getTrays().get(j).getItems().size(); k++){
                    double currentRetailPrice = App.getLinkedListAPI().getCases().get(i).getTrays().get(j).getItems().get(k).getRetail();
                    totalRetailPrice += currentRetailPrice;

                    //Adds each item to the list view
                    stockListView.getItems().add(App.getLinkedListAPI().getCases().get(i).getTrays().get(j).getItems().get(k));
                }
            }
        }

        //Sets the text of the stock value to the total
        stockValue.setText("€" + totalRetailPrice);
    }

    //Drills down to the selected item
    public void drillBtnPressed(){

        //Gets the selected item
        Item selectedItem = stockListView.getSelectionModel().getSelectedItem();

        //Checks if an item is selected
          if (selectedItem != null){
              //Passes parameters to the drillDown method
              drillDown(selectedItem.getDescription(), selectedItem.getGender(), selectedItem.getType(), selectedItem.getImgURL(), selectedItem.getRetail());
          }
          else {
              Utils.errorStat("Please Select an Item");
          }

    }

    public void drillDown(String Description, String gender, String type, String imgURL, double retail){


        try{
            //Loops through the cases, trays and items to find the selected item
            for (int i = 0; i < App.getLinkedListAPI().getCases().size(); i++){
                DisplayCase currentCase = App.getLinkedListAPI().getCases().get(i);
                for (int j = 0; j < currentCase.getTrays().size(); j++){
                    DisplayTray currentTray = currentCase.getTrays().get(j);
                    for (int k = 0; k < currentTray.getItems().size(); k++) {
                        Item currentItem = currentTray.getItems().get(k);

                        //Checks if the item matches the selected item
                        if (currentItem.getDescription().equals(Description) &&
                                currentItem.getGender().equals(gender) &&
                                currentItem.getType().equals(type) &&
                                currentItem.getImgURL().equals(imgURL) &&
                                currentItem.getRetail() == retail) {

                            System.out.println("Item found");

                            //Gets the case and tray ID
                            String CaseID = currentCase.getID();
                            String TrayID = currentTray.getID();

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle(currentItem.getDescription());
                            alert.setHeaderText("Case: " + CaseID + " Tray: " + TrayID);
                            //Displays the item details
                            alert.setContentText(
                                    "Description: " + Description + "\n" +
                                            "Gender: " + gender + "\n" +
                                            "Type: " + type + "\n" +
                                            "Retail Price: €" + retail + "\n" +
                                            "Image URL: " + imgURL + "\n" +
                                            "Materials: " + currentItem.getMaterials().get(i)
                            );
                            alert.showAndWait();
                            return;
                        } else {
                            System.out.println("Item not found");
                        }
                    }
                }
            }
        }
        catch (Exception e){
            Utils.errorStat("Error Drilling Down");
            e.printStackTrace();
        }
    }

    //Initializes the stock value
    public void initialize(){
        updateStockValue();
    }


}
