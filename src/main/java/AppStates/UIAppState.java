package AppStates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.jfx.injme.JmeFxContainer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import java.io.IOException;




public class UIAppState extends BaseAppState {

    private SimpleApplication app;
    private JmeFxContainer container;
    private Parent root;

    //EmbeddedWindow
    //JmeFxContainerImpl jmeFxContainerImpl;

    @Override
    protected void initialize(Application app ) {
        this.app = (SimpleApplication) app;
        Group root2;
        Scene scene;
        container = JmeFxContainer.install(this.app, this.app.getGuiNode());

        if((this.app.getContext().getSettings().getWidth() == 1280)& (this.app.getContext().getSettings().getHeight() == 720)){
            try {
                root = FXMLLoader.load(getClass().getResource("/UI/UImain1280x720.fxml"));
            }catch (IOException e) {
                e.printStackTrace();
            }
            root2 = new Group();
            scene = new Scene(root, 1280, 720);
            scene.setFill(Color.TRANSPARENT);
            container.setScene(scene, root2);
        }else if ((this.app.getContext().getSettings().getWidth() == 800) & (this.app.getContext().getSettings().getHeight() == 600)) {
            try {
                root = FXMLLoader.load(getClass().getResource("/UI/UImain.fxml"));
            }catch (IOException e) {
                e.printStackTrace();
            }
            root2 = new Group();
            scene = new Scene(root, 800, 600);
            scene.setFill(Color.TRANSPARENT);
            container.setScene(scene, root2);
        }

        //EmbeddedWindow
//        jmeFxContainerImpl = JmeFxContainerImpl.install(this.app, this.app.getGuiNode());
//        JmeFxHostInterface jmeFxHostInterface = new JmeFxHostInterface(jmeFxContainerImpl);
//        EmbeddedWindow embeddedWindow = new EmbeddedWindow(jmeFxHostInterface);
//        try {
//            root2 = FXMLLoader.load(getClass().getResource("/utils/lol.fxml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Scene scene2 = new Scene(root2, 100, 100);
//        scene2.setFill(Color.TRANSPARENT);
//        embeddedWindow.setScene(scene2);
//        Group rootNode2 = new Group();
//        jmeFxContainerImpl.setEmbeddedWindow(embeddedWindow);
//        jmeFxContainerImpl.setScene(scene2, rootNode2);


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
        if (container.isNeedWriteToJme()) {
            container.writeToJme();
        }
        // FOR embeddedWindow
        //if (jmeFxContainerImpl.isNeedWriteToJme()) {
        //    jmeFxContainerImpl.writeToJme();
        //}
    }
}
