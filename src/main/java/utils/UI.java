package utils;

import AppStates.GridAppState;
import com.jme3.app.SimpleApplication;

import com.simsilica.lemur.*;
import com.sun.istack.internal.logging.Logger;

import java.util.ArrayList;


public class UI {
    private final Logger log = Logger.getLogger(UI.class.getClass());
    SimpleApplication simpleApplication;

    GridAppState gridAppState;
    boolean gridRender;
   
    ArrayList<Container> listUI = new ArrayList<>();

    public UI(SimpleApplication simpleApplication){
        this.simpleApplication = simpleApplication;

        addGrid();
        addButtonGrid();
        addButtonPlayerInfo();

    }

    private void addGrid(){
        gridAppState = new GridAppState();
        simpleApplication.getStateManager().attach(gridAppState);
        gridRender = true;
    }

    private void addButtonPlayerInfo(){
        Container containerPlayerInfo = new Container();
        containerPlayerInfo.setLocalTranslation(5, 170, 0);
        listUI.add(containerPlayerInfo);

        Button buttonGrid = containerPlayerInfo.addChild(new Button("PLAYER INFO"));
        buttonGrid.addClickCommands(new Command<Button>() {
            @Override
            public void execute( Button source ) {

            }
        });

    }

    private void addButtonGrid(){
        Container containerButtonGrid = new Container();
        // Поместите его куда-нибудь, чтобы мы его увидели.
        // Примечание. Элементы GUI Lemur прирастают из левого верхнего угла.
        containerButtonGrid.setLocalTranslation(5, 200, 0);
        // Добавим некоторые элементы
        //myWindow.addChild(new Label("Hello, World."));
        listUI.add(containerButtonGrid);

        Button buttonGrid = containerButtonGrid.addChild(new Button("Grid OFF"));
        //myWindow.addChild(new ActionButton(new CallMethodAction("Close", myWindow, "removeFromParent")));
        buttonGrid.addClickCommands(new Command<Button>() {
            @Override
            public void execute( Button source ) {
                if(gridRender == true){
                    gridAppState.setEnabled(false);
                    buttonGrid.setText("Grid ON");
                    gridRender = false;
                }else
                {
                    gridAppState.setEnabled(true);
                    buttonGrid.setText("Grid OFF");
                    gridRender = true;
                }
                //Example search AppState by name his class
                //AppState state = stateManager.getState(GridAppState.class);
                //stateManager.detach(state);
            }
        });
    }

    public ArrayList<Container> getListUI(){
        return listUI;
    }
}
