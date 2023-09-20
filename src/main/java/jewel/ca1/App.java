package jewel.ca1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    // LinkedListAPI is a static variable so that it can be accessed from any controller
    private static LinkedListAPI LinkedListAPI;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        stage.setTitle("Jewells for you");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(String.valueOf(App.class.getResource("logos/transparent.png"))));
        stage.show();

        // Initialize the LinkedListAPI
        LinkedListAPI = new LinkedListAPI();

    }

    // Getter for the LinkedListAPI
    public static LinkedListAPI getLinkedListAPI() {
        return LinkedListAPI;
    }

    public static void main(String[] args) {
        launch();
    }
}