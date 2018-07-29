package AppStates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.jfx.injme.JmeFxContainer;

import com.jme3.jfx.injme.JmeFxContainerImpl;
import com.jme3.jfx.injme.JmeFxContainerInternal;
import com.jme3.jfx.injme.JmeFxHostInterface;
import com.sun.javafx.embed.EmbeddedSceneInterface;
import com.sun.javafx.stage.EmbeddedWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.ClientMain;
import utils.PopUP;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.logging.Logger;


import static javafx.geometry.Pos.CENTER;

public class UIAppState extends BaseAppState {
    private SimpleApplication app;
    private final Logger log = Logger.getLogger(String.valueOf(UIAppState.class.getClass()));

    private GridAppState gridAppState;
    private UIAppStatePlayerInfo uiAppStatePlayerInfo;
    private boolean gridRender = true;



    //Container /*containerPlayerInfo ,*/ containerButtonGrid , containerButtonPlayerInfo;
    private JmeFxContainer container;


    private Parent root;
    private Parent root2;

    JmeFxContainerImpl jmeFxContainerImpl;
    public UIAppState() {

    }

    @Override
    protected void initialize(Application app) {
        this.app = (SimpleApplication) app;

        /*
        container = JmeFxContainer.install(this.app, this.app.getGuiNode());

        try {
            root = FXMLLoader.load(getClass().getResource("/utils/fxml_example.fxml"));



        } catch (IOException e) {
            e.printStackTrace();
        }





        Scene scene = new Scene(root, 800, 600);
        scene.setFill(Color.TRANSPARENT);




        Group rootNode = new Group();

        container.setScene(scene, rootNode);

        */
        //EmbeddedWindow embeddedWindow;
        ///JmeFxHostInterface jmeFxHostInterface;
        //JmeFxContainerImpl jmeFxContainerImpl;
        //JmeFxContainerInternal->JmeFxHostInterface->EmbendedWinow


        /*
        jmeFxContainerImpl = JmeFxContainerImpl.install(this.app, this.app.getGuiNode());

        JmeFxHostInterface jmeFxHostInterface = new JmeFxHostInterface(jmeFxContainerImpl);
        EmbeddedWindow embeddedWindow = new EmbeddedWindow(jmeFxHostInterface);

        try {
            root2 = FXMLLoader.load(getClass().getResource("/utils/lol.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene2 = new Scene(root2, 100, 100);
        scene2.setFill(Color.TRANSPARENT);
        embeddedWindow.setScene(scene2);
        Group rootNode2 = new Group();
        jmeFxContainerImpl.setEmbeddedWindow(embeddedWindow);
        jmeFxContainerImpl.setScene(scene2, rootNode2);
        */

        //createControls(container);
        //configureSize(container);
//        Button button = new Button("BUTTON");
//
//        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                if (gridRender == true){
//                    button.setText("CLIK");
//                    app.getStateManager().detach(gridAppState);
//                    gridRender = false;
//                }else{
//                    button.setText("SETTT");
//                    app.getStateManager().attach(gridAppState);
//                    gridRender = true;
//                }
//
//            }
//        });

        //containerPlayerInfo = new Container();
        //uiAppStatePlayerInfo = new UIAppStatePlayerInfo();
        //app.getStateManager().attach(uiAppStatePlayerInfo);
        //uiAppStatePlayerInfo.setEnabled(false);
        //containerPlayerInfoShow = false;
        //containerPlayerInfo.setLocalTranslation(250,400,0);

        addGrid();

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
        //super.update(tpf);
        //if (container.isNeedWriteToJme()) {
        //    container.writeToJme();
       // }
        //if (jmeFxContainerImpl.isNeedWriteToJme()) {
        //    jmeFxContainerImpl.writeToJme();
        //}
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
}
