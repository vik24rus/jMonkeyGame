package UI_MVC;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UIModel {
    private final StringProperty label1;
    private final StringProperty label2;

    public UIModel(String label1 , String label2){
        this.label1 = new SimpleStringProperty(label1);
        this.label2 = new SimpleStringProperty(label2);
    }

    public String getLabel1() {
        return label1.get();
    }

    public void setLabel1(String firstName) {
        this.label1.set(firstName);
    }

    public StringProperty Label1Property() {
        return label1;
    }

    public String getLabel2() {
        return label2.get();
    }

    public void setLabel2(String firstName) {
        this.label2.set(firstName);
    }

    public StringProperty Label2Property() {
        return label2;
    }
}
