package AppStates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.jfx.injme.JmeFxContainer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;


import java.util.logging.Logger;

public class UIAppState extends BaseAppState {
    private SimpleApplication app;
    private final Logger log = Logger.getLogger(String.valueOf(UIAppState.class.getClass()));

    private GridAppState gridAppState;
    private UIAppStatePlayerInfo uiAppStatePlayerInfo;
    private boolean gridRender , containerPlayerInfoShow;



    //Container /*containerPlayerInfo ,*/ containerButtonGrid , containerButtonPlayerInfo;
    private JmeFxContainer container;
    @Override
    protected void initialize(Application app) {
        this.app = (SimpleApplication) app;
        container = JmeFxContainer.install(this.app, this.app.getGuiNode());

       Button button = new Button("BUTTON");
       Group rootNode = new Group(button);
       Scene scene = new Scene(rootNode, 600, 600);
       scene.setFill(Color.TRANSPARENT);

        container.setScene(scene, rootNode);
        //containerPlayerInfo = new Container();
        //uiAppStatePlayerInfo = new UIAppStatePlayerInfo();
        //app.getStateManager().attach(uiAppStatePlayerInfo);
        //uiAppStatePlayerInfo.setEnabled(false);
        //containerPlayerInfoShow = false;
        //containerPlayerInfo.setLocalTranslation(250,400,0);
       // addGrid();
        //addButtonGrid();
        //addButtonPlayerInfo();
        //////////////userUI = new UI(this.app);
        //Технически безопасно выполнять всю инициализацию и очистку в методах
        //onEnable()/onDisable(). Выбор использовать initialize() и
        //cleanup() для этого, это вопрос специфики производительности для
        //разработчика.
        //TODO: Инициализация AppState, например, присоединение spatials к rootNode
    }

    @Override
    protected void cleanup(Application app) {
        //TODO: очистить то, что вы инициализировали в методе initialize,
        //например, удалить все spatials из rootNode
    }

    //onEnable()/onDisable() может использоваться для управления вещами, которые должны
    //существовать только при включенном state. Основным примером были
    //прикрепленный граф сцены или прикрепленный слушатель ввода.
    @Override
    protected void onEnable() {
        //Вызывается, когда state полностью включено, то есть: установлено и
        //isEnabled() является истинным или когда статус setEnabled() изменяется после
        //прикрепления state.
        //app.getGuiNode().attachChild(containerButtonGrid);
        //app.getGuiNode().attachChild(containerPlayerInfo);
        //app.getGuiNode().attachChild(containerButtonPlayerInfo);

    }

    @Override
    protected void onDisable() {
        //Вызывается, когда state было ранее включено, но теперь отключено
        //либо потому, что вызывается setEnabled (false), либо состояние
        //очищается.

    }

    @Override
    public void update(float tpf) {
        //TODO: реализовать поведение во время выполнения
//        super.update(tpf);
        if (container.isNeedWriteToJme()) {
            container.writeToJme();
        }
    }
    private void addGrid(){
        gridAppState = new GridAppState();
        app.getStateManager().attach(gridAppState);
        gridRender = true;
    }

    private void addButtonPlayerInfo(){
        //containerButtonPlayerInfo = new Container();
        //containerButtonPlayerInfo.setLocalTranslation(5, 170, 0);
       // Button buttonGrid = containerButtonPlayerInfo.addChild(new Button("PLAYER INFO"));
//        buttonGrid.addClickCommands(new Command<Button>() {
//            @Override
//            public void execute( Button source ) {
//
//                if (containerPlayerInfoShow == false){
//                    uiAppStatePlayerInfo.setEnabled(true);
//                    containerPlayerInfoShow = true;
//                }else {
//                    uiAppStatePlayerInfo.setEnabled(false);
//                    containerPlayerInfoShow = false;
//                }
//            }
//        });

    }

    private void addButtonGrid(){
        //containerButtonGrid = new Container();
        // Поместите его куда-нибудь, чтобы мы его увидели.
        // Примечание. Элементы GUI Lemur прирастают из левого верхнего угла.
       // containerButtonGrid.setLocalTranslation(5, 200, 0);
        // Добавим некоторые элементы
        //myWindow.addChild(new Label("Hello, World."));

        //Button buttonGrid = containerButtonGrid.addChild(new Button("Grid OFF"));
        //myWindow.addChild(new ActionButton(new CallMethodAction("Close", myWindow, "removeFromParent")));
        //buttonGrid.addClickCommands(new Command<Button>() {
//            @Override
//            public void execute( Button source ) {
//                if(gridRender == true){
//                    gridAppState.setEnabled(false);
//                    buttonGrid.setText("Grid ON");
//                    gridRender = false;
//                }else
//                {
//                    gridAppState.setEnabled(true);
//                    buttonGrid.setText("Grid OFF");
//                    gridRender = true;
//                }
                //Example search AppState by name his class
                //AppState state = stateManager.getState(GridAppState.class);
                //stateManager.detach(state);
           // }
        //});
    }

}
