package UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class UIplayerInfoFxml {
    @FXML
    private Button buttonClose;


    @FXML
    private void initialize() {

    }

    public void actionButtonClose() {
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }
}
