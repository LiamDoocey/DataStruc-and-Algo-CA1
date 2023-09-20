package jewel.ca1;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jewel.ca1.MODELS.DisplayCase;
import jewel.ca1.MODELS.DisplayTray;
import jewel.ca1.MODELS.Item;
import jewel.ca1.MODELS.Material;
import java.io.IOException;

public class Controller {

    // FXML components
    @FXML
    ListView<String> CaseList;
    @FXML
    ListView<String> searchResults;
    @FXML
    private Button addTrayBtn;
    @FXML
    private Button printTraysBtn;
    @FXML
    private TextField searchBar;

    // Boolean property to track if an item is selected in CaseList
    private final BooleanProperty isListSelected = new SimpleBooleanProperty(false);
    FullStockController fsc = new FullStockController();

    public void initialize() {
        // Disabling buttons until a case is selected
        addTrayBtn.disableProperty().bind(isListSelected.not());
        printTraysBtn.disableProperty().bind(isListSelected.not());

        // Listen for CaseList selection changes
        CaseList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            isListSelected.set(newValue != null);
        });
    }

    // Loading the "Add Case" page
    public void loadAddPage() throws IOException {
        // Create a new stage for the "Add Case" page
        Stage af = new Stage();

        // Load the FXML file for the "Add Case" page
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("AddFunction.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 271);

        af.setTitle("Jewells for you - Add");
        af.setScene(scene);
        af.setResizable(false);
        af.getIcons().add(new Image(String.valueOf(App.class.getResource("logos/transparent.png"))));
        af.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        af.show();

        // Get the controller for the "Add Case" page and set a reference to this controller
        addFunctionController afc = fxmlLoader.getController();
        afc.setMainC(this);
    }

    // Loading the "Stock" page
    public void loadStockPage() throws IOException {
        // Create a new stage for the "Stock" page
        Stage ds = new Stage();

        // Load the FXML file for the "Stock" page
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FullStock.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 741);

        ds.setTitle("Jewells for you - Stock");
        ds.setScene(scene);
        ds.setResizable(false);
        ds.getIcons().add(new Image(String.valueOf(App.class.getResource("logos/transparent.png"))));
        ds.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        ds.show();
    }

    // Loading the "Add Tray" page
    public void loadAddTray() throws IOException {
        // Get the index of the selected case in CaseList
        int selectedCaseIndex = CaseList.getSelectionModel().getSelectedIndex();

        // Get the ID of the selected case
        String caseID = App.getLinkedListAPI().getCase(selectedCaseIndex).getID();

        // Create a new stage for the "Add Tray" page
        Stage aT = new Stage();

        // Load the FXML file for the "Add Tray" page
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("addTray.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 271);

        // Get the controller for the "Add Tray" page and initialize it with the case ID
        addTrayController caseIDController = fxmlLoader.getController();
        caseIDController.initialize(caseID);

        aT.setTitle("Jewells for you - Add Tray");
        aT.setScene(scene);
        aT.setResizable(false);
        aT.getIcons().add(new Image(String.valueOf(App.class.getResource("logos/transparent.png"))));
        aT.initModality(Modality.APPLICATION_MODAL);
        aT.show();

        // Get the controller for the "Add Tray" page and set a reference to this controller
        addTrayController aTc = fxmlLoader.getController();
        aTc.setMainC(this);
    }

    // Loading the "Add Item" page
    public void loadAddItem() throws IOException {
        // Get the index of the selected case in CaseList
        int caseID = CaseList.getSelectionModel().getSelectedIndex();
        String caseUID = App.getLinkedListAPI().getCase(caseID).getID();

        // Create a new stage for the "Add Item" page
        Stage aI = new Stage();

        // Load the FXML file for the "Add Item" page
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("addItem.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);

        // Get the controller for the "Add Item" page and initialize it with the case ID
        addItemController Tcon = fxmlLoader.getController();
        Tcon.initialize(caseID, caseUID);

        aI.setTitle("Jewells for you - Add Item");
        aI.setScene(scene);
        aI.setResizable(false);
        aI.getIcons().add(new Image(String.valueOf(App.class.getResource("logos/transparent.png"))));
        aI.initModality(Modality.APPLICATION_MODAL);
        aI.show();
    }

    // Method to add a new case
    public void addCase(String ID, String type, String lighting, int position) {
        // Check if the Case ID already exists
        for (int i = 0; i < App.getLinkedListAPI().getCases().size(); i++) {
            if (App.getLinkedListAPI().getCase(i).getID().equals(ID)) {
                Utils.errorStat("Case ID already exists");
                return;
            }
        }

        // Create a new case object
        DisplayCase newCase = new DisplayCase(ID, type, lighting, position);

        // Add the new case to the linked list
        App.getLinkedListAPI().getCases().add(newCase);
    }

    // Method to add a new tray
    public void addTray(String ID, String colour, double width, double height) {
        try {
            // Get the index of the selected case in CaseList
            int CaseID = CaseList.getSelectionModel().getSelectedIndex();

            if (!Utils.validTrayID(ID)) {
                Utils.errorStat("Case was not added");
                return;
            }

            // Check if the Tray ID already exists
            for (int i = 0; i < App.getLinkedListAPI().getCase(i).getTrays().size(); i++) {
                if (App.getLinkedListAPI().getCase(i).getTrays().get(i).getID().equals(ID)) {
                    Utils.errorStat("Tray ID already exists");
                    return;
                }
            }

            // Add a new tray to the selected case
            App.getLinkedListAPI().getCases().get(CaseID).getTrays().add(new DisplayTray(String.valueOf(CaseID), ID, colour, width, height));

            // Show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Tray added successfully");
            alert.setContentText("Tray ID: " + ID + "\n" +
                    "Tray Colour: " + colour + "\n" +
                    "Tray Width: " + width + "\n" +
                    "Tray Height: " + height + "\n");
            alert.showAndWait();
        }
        catch (Exception e) {
            Utils.errorStat("An error has occurred");
        }
    }

    // Method to update the CaseList
    public void updateCaseList() {
        // Check if the linked list is empty
        if (App.getLinkedListAPI().getCases().size() == 0) {
            CaseList.getItems().clear();
        } else {
            // Clear the CaseList
            if (App.getLinkedListAPI().getCases().size() != 0) {
                CaseList.getItems().clear();
            }
            // Add the cases to the CaseList
            for (int i = 0; i < App.getLinkedListAPI().getCases().size(); i++) {
                DisplayCase currentCase = App.getLinkedListAPI().getCase(i);
                if (currentCase != null) {
                    CaseList.getItems().add(currentCase.toString());
                }
            }
        }
    }

    // Method to update the search results list
    public void updateSearchList(String results) {
        // Check if the results are null
        if (results == null) {
            searchResults.getItems().clear();
            return;
        }
        // Add the results to the search results list
        searchResults.getItems().add(results);
    }

    // Method to perform a search
    public void searchFor() {
        try {
            String searchTerm = searchBar.getText();

            App.getLinkedListAPI().getResults().clear();
            searchResults.getItems().clear();

            if (searchTerm == null || searchTerm == " ") {
                Utils.errorStat("Please enter a search term.");
            } else {
                //Loops through the linked list and checks if the search term matches any of the objects
                for (int i = 0; i < App.getLinkedListAPI().getCases().size(); i++) {
                    //Gets the current case
                    DisplayCase currentCase = App.getLinkedListAPI().getCase(i);
                    for (int j = 0; j < currentCase.getTrays().size(); j++) {
                        //Gets the current tray
                        DisplayTray currentTray = currentCase.getTrays().get(j);
                        for (int k = 0; k < currentTray.getItems().size(); k++) {
                            //Gets the current item
                            Item currentItem = currentTray.getItems().get(k);
                            for (int h = 0; h < currentItem.getMaterials().size(); h++) {
                                //Gets the current material
                                Material currentMaterial = currentItem.getMaterials().get(h);
                                if (currentMaterial.getType().equals(searchTerm) || currentMaterial.getDescription().equals(searchTerm) || String.valueOf(currentMaterial.getQuantity()).equals(searchTerm) || String.valueOf(currentMaterial.getQuality()).equals(searchTerm)) {
                                    System.out.println("Result found");
                                    //Adds the result to the results list
                                    App.getLinkedListAPI().getResults().add(currentMaterial.toString());
                                }
                            }
                            if (currentItem.getDescription().equals(searchTerm) || currentItem.getGender().equals(searchTerm) || currentItem.getType().equals(searchTerm) || String.valueOf(currentItem.getRetail()).equals(searchTerm) || currentItem.getImgURL().equals(searchTerm)) {
                                System.out.println("Result found");
                                //Adds the result to the results list
                                App.getLinkedListAPI().getResults().add(currentItem.toString());
                            }
                        }
                    }
                }
                //Adds the results to the search results listview
                for (int i = 0; i < App.getLinkedListAPI().getResults().size(); i++) {
                    updateSearchList(App.getLinkedListAPI().getResults().get(i));
                }
            }
            //Checks if there are no results
            if (searchResults.getItems().size() == 0) {
                Utils.infoStat("No results found");
            }
        } catch (Exception e) {
            Utils.errorStat("An error has occured");
            e.printStackTrace();
        }
    }

    public void moreSearchDetail() throws IOException{
        String result = searchResults.getSelectionModel().getSelectedItem();

        //Load the FXML file for the "Search Details" page
        Stage sD = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("SearchDetails.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        sD.setTitle("Jewells for you - Search Details");
        sD.setScene(scene);
        sD.setResizable(false);
        sD.getIcons().add(new Image(String.valueOf(App.class.getResource("logos/transparent.png"))));
        sD.initModality(Modality.APPLICATION_MODAL);

        //get controller and pass result
        searchDetailsController sDc = fxmlLoader.getController();
        sDc.initialize(result);

        sD.show();

    }

    // Method to flush the system
    public void flushSystem() {
        // Display a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.WARNING, "", ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle("Flush System");
        alert.setHeaderText("Are you sure you want to flush the system?");
        alert.setContentText("This will delete all cases and trays etc.");
        alert.initModality(Modality.APPLICATION_MODAL);

        // Handle the user's response
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                App.getLinkedListAPI().destroySystem();
                updateCaseList();
                updateSearchList(null);
            }
        });
    }

    // Method to exit the application
    public void exit() {
        System.exit(0);
    }
}