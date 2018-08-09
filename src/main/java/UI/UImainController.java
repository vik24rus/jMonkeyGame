package UI;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.MyStateManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UImainController {



    boolean gridOn;

    List<Employee> employees = Arrays.<Employee>asList(
            new Employee("Ethan Williams", "ethan.williams@example.com"),
            new Employee("Emma Jones", "emma.jones@example.com"),
            new Employee("Michael Brown", "michael.brown@example.com"),
            new Employee("Anna Black", "anna.black@example.com"),
            new Employee("Rodger York", "roger.york@example.com"),
            new Employee("Susan Collins", "susan.collins@example.com"));

//    private final ImageView depIcon = new ImageView (
//            new Image(getClass().getResourceAsStream("department.png"))
//    );

    final TreeItem<Employee> root = new TreeItem<>(new Employee("Sales Department", "") /*, depIcon*/);




    @FXML
    private Button buttonPlayerInfo;

    @FXML
    private TreeTableView overview;

    @FXML
    private TitledPane info_panel;

    @FXML
    private void initialize() {
        gridOn = true;
        info_panel.setExpanded(false);
//        //Creating tree items
//        final TreeItem<String> childNode1 = new TreeItem<>("Child Node 1");
//        final TreeItem<String> childNode2 = new TreeItem<>("Child Node 2");
//        final TreeItem<String> childNode3 = new TreeItem<>("Child Node 3");
//
//        //Creating the root element
//        final TreeItem<String> root = new TreeItem<>("Root node");
//        root.setExpanded(true);
//
//        //Adding tree items to the root
//        root.getChildren().setAll(childNode1, childNode2, childNode3);
//
//
//        //Creating a column
//        TreeTableColumn<String,String> column = new TreeTableColumn<>("Column");
//        column.setPrefWidth(150);
//
//        //Defining cell content
//        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<String, String> p) ->
//                new ReadOnlyStringWrapper(p.getValue().getValue()));
//
//        overview.setRoot(root);
//        overview.getColumns().add(column);
//        overview.setShowRoot(true);

        root.setExpanded(true);
        employees.stream().forEach((employee) -> {
            root.getChildren().add(new TreeItem<>(employee));
        });
        TreeTableColumn<Employee, String> empColumn =
                new TreeTableColumn<>("Employee");
        empColumn.setPrefWidth(150);
        empColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Employee, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getName())
        );

        TreeTableColumn<Employee, String> emailColumn =
                new TreeTableColumn<>("Email");
        emailColumn.setPrefWidth(190);
        emailColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Employee, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getEmail())
        );
        overview.setRoot(root);
        overview.getColumns().setAll(empColumn, emailColumn);
        overview.setTableMenuButtonVisible(true);
        overview.setShowRoot(true);

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

    public class Employee {

        private SimpleStringProperty name;
        private SimpleStringProperty email;
        public SimpleStringProperty nameProperty() {
            if (name == null) {
                name = new SimpleStringProperty(this, "name");
            }
            return name;
        }
        public SimpleStringProperty emailProperty() {
            if (email == null) {
                email = new SimpleStringProperty(this, "email");
            }
            return email;
        }
        private Employee(String name, String email) {
            this.name = new SimpleStringProperty(name);
            this.email = new SimpleStringProperty(email);
        }
        public String getName() {
            return name.get();
        }
        public void setName(String fName) {
            name.set(fName);
        }
        public String getEmail() {
            return email.get();
        }
        public void setEmail(String fName) {
            email.set(fName);
        }
    }
}
