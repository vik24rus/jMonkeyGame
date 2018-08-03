package UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.MyStateManager;

import java.io.IOException;

public class UImainController {

    boolean gridOn;

    @FXML
    private Button buttonPlayerInfo;
    @FXML
    private Button buttonGridControl;

    @FXML
    private void initialize() {
        gridOn = true;
    }

    public void actionButtonPlayerInfo() {
        Stage stage;
        Parent root;
        stage = new Stage();
        try {
            root = FXMLLoader.load(getClass().getResource("/UI/UIplayerInfo.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Player Info");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(buttonPlayerInfo.getScene().getWindow());
        stage.showAndWait();
    }

    public void buttonGridControl() {
        if (gridOn == true){
            MyStateManager.delGrid();
            gridOn = false;
        }else {
            MyStateManager.addGrid();
            gridOn = true;
        }

    }
}
