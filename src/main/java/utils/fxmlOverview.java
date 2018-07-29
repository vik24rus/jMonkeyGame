package utils;

import com.jme3.jfx.injme.JmeFxContainer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class fxmlOverview {
    private JmeFxContainer container;
    @FXML
    private Label label1;
    @FXML
    private Label label2;



    @FXML
    private Button on_off_grid;

    public  fxmlOverview() {
    }

    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.

        label1.setText("11111111111111111111");
    }

    private void OLOLO(){

    }

    public void LOL1() throws IOException {
        ///1 в качесте стейджа запихать сюда как то то что мы создаем контейнер в аппстейте
        ///2 создать такой же контейнер как и в appstate только для модального окна --- через app STATE и сдrлать???
//
//        Stage stage;
//        Parent root;
//
//
//            stage = new Stage();
//            root = FXMLLoader.load(getClass().getResource("/utils/lol.fxml"));
//            stage.setScene(new Scene(root));
//            stage.setTitle("My modal window");
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.initOwner(on_off_grid.getScene().getWindow());
//            stage.showAndWait();

    }
}
