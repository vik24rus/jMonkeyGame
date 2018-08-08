package AppStates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.light.AmbientLight;
import com.jme3.light.SpotLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;


public class LightAppState  extends BaseAppState {
    SimpleApplication app;

    @Override
    protected void initialize(Application app ) {
        this.app = (SimpleApplication) app;

        SpotLight spot = new SpotLight();
        spot.setSpotRange(100f);                           // расстояние
        spot.setSpotInnerAngle(85f * FastMath.DEG_TO_RAD); // внутренний световой конус (центральный луч)
        spot.setSpotOuterAngle(85f * FastMath.DEG_TO_RAD); // наружный световой конус (край света)
        spot.setColor(ColorRGBA.White.mult(1.3f));         // светлый цвет
        spot.setPosition(new Vector3f(-3,0,-3));               // shine from camera loc
        spot.setDirection(new Vector3f(1,0,1));             // shine forward from camera loc
        this.app.getRootNode().addLight(spot);


        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.3f));
        this.app.getRootNode().addLight(al);

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

    }
}