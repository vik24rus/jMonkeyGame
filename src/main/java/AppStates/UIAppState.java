package AppStates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.simsilica.lemur.Container;
import utils.UI;

import java.util.ArrayList;
import java.util.logging.Logger;

public class UIAppState extends BaseAppState {
    private SimpleApplication app;
    private final Logger log = Logger.getLogger(String.valueOf(UIAppState.class.getClass()));
    private UI userUI;

    @Override
    protected void initialize(Application app) {
        this.app = (SimpleApplication) app;
        userUI = new UI(this.app);
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
        ArrayList<Container> listUI ;
        listUI = userUI.getListUI();
        for (int i=0 ; i<listUI.size();i++){
            app.getGuiNode().attachChild(listUI.get(i));
        }
        //app.getGuiNode().attachChild(userUI.getListUI());

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
    }

}
