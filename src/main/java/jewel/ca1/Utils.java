package jewel.ca1;

import javafx.scene.control.Alert;

public class Utils {
    public static void errorStat(String error){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText(error);
        alert.showAndWait();
    }

    public static boolean validTrayID(String ID){
        // Regex for 1 letter followed by number
        String regex = "[a-zA-Z][0-9]+";
        if(!ID.matches(regex)){
            errorStat("Invalid Tray ID, Please use one letter followed by a number");
            return false;
        }
        return true;
    }

    public static boolean isStringSame(String val1, String val2) {
        if (val1 == null || val2 == null) {
            return false;
        }

        if (val1.length() != val2.length()) {
            return false;
        }

        for (int i = 0; i < val1.length(); i++) {
            if (val1.charAt(i) != val2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void infoStat(String info){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Information");
        alert.setContentText(info);
        alert.showAndWait();
    }
}
