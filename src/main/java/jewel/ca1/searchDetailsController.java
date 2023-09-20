package jewel.ca1;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import jewel.ca1.MODELS.DisplayCase;
import jewel.ca1.MODELS.DisplayTray;
import jewel.ca1.MODELS.Item;
import jewel.ca1.MODELS.Material;

public class searchDetailsController {

    @FXML
    private Text caseLabel;
    @FXML
    private Text trayLabel;
    @FXML
    private ImageView itemImageView;
    @FXML
    private Text itemType;
    @FXML
    private Text itemGender;
    @FXML
    private Text itemDesc;
    @FXML
    private Text numOfMats;
    @FXML
    private Text matsDetails;


    public void initialize(String result){
        update(result);
    }

    public void update(String result) {

        // Loop through cases in the linked list
        for (int i = 0; i < App.getLinkedListAPI().getCases().size(); i++) {
            // Gets the current case
            DisplayCase currentCase = App.getLinkedListAPI().getCase(i);

            // Loop through trays in the current case
            for (int j = 0; j < currentCase.getTrays().size(); j++) {
                // Gets the current tray
                DisplayTray currentTray = currentCase.getTrays().get(j);

                // Loop through items in the current tray
                for (int k = 0; k < currentTray.getItems().size(); k++) {
                    // Gets the current item
                    Item currentItem = currentTray.getItems().get(k);

                    // Loop through materials in the current item
                    for (int h = 0; h < currentItem.getMaterials().size(); h++) {
                        // Gets the current material
                        Material currentMaterial = currentItem.getMaterials().get(h);

                        // Check if the current material matches the given result using a utility function
                        if (Utils.isStringSame(currentMaterial.toString(), result)) {
                            System.out.println("Found Material Link");

                            // Display information about the current case, tray, item, and material
                            caseLabel.setText("Case : " + currentCase.getID());
                            trayLabel.setText("Tray : " + currentTray.getID());
                            itemType.setText("Item Type : " + currentItem.getType());
                            itemGender.setText("Gender : " + currentItem.getGender());
                            itemDesc.setText("Description : " + currentItem.getDescription());
                            numOfMats.setText("Number of Materials : " + currentItem.getMaterials().size());

                            // Loop through materials of the current item and display their details
                            for (int l = 0; l < currentItem.getMaterials().size(); l++) {
                                matsDetails.setText("Materials : " + i + ")" + currentItem.getMaterials().get(l).toString() + "\n");
                            }

                            // Set the image for the current item
                            itemImageView.setImage(new Image(currentItem.getImgURL()));
                        }
                    }

                    // Check if the current item matches the given result using a utility function
                    if (Utils.isStringSame(currentItem.toString(), result)) {
                        System.out.println("Found Item Link");

                        // Display information about the current case, tray, item, and material
                        caseLabel.setText(currentCase.getID());
                        trayLabel.setText(currentTray.getID());
                        itemType.setText("Item Type : " + currentItem.getType());
                        itemGender.setText("Gender : " + currentItem.getGender());
                        itemDesc.setText("Description : " + currentItem.getDescription());
                        numOfMats.setText("Number of Materials : " + currentItem.getMaterials().size());

                        // Loop through materials of the current item and display their details
                        for (int l = 0; l < currentItem.getMaterials().size(); l++) {
                            matsDetails.setText("Materials : " + i + 1 + ")" + currentItem.getMaterials().get(l).toString() + "\n");
                        }

                        // Set the image for the current item
                        itemImageView.setImage(new Image(currentItem.getImgURL()));
                    }
                }
            }
        }
    }
}
