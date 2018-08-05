package AppStates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.*;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.CameraNode;


public class CameraAppState extends BaseAppState {
    SimpleApplication app;
    CameraNode cameranode;
    Vector3f mainCameraPosition;
    private Boolean drag=false;
    @Override
    protected void initialize(Application app ) {
        this.app = (SimpleApplication) app;

        cameranode = new CameraNode("Main Camera", app.getCamera());
        //cameranode.setControlDir(CameraControl.ControlDirection.SpatialToCamera); //??? WTF
        ((SimpleApplication) app).getRootNode().attachChild(cameranode);
        cameranode.setLocalTranslation(-4,3,3);
        Quaternion q2 = new Quaternion();
        float angle_vertical = 45*FastMath.DEG_TO_RAD; //наклон
        float angle_turn = 90*FastMath.DEG_TO_RAD; //разворот
        cameranode.setLocalRotation(q2.fromAngles(angle_vertical,angle_turn,0));
        initKeys();
        //Технически безопасно выполнять всю инициализацию и очистку в методах
        //onEnable()/onDisable(). Выбор использовать initialize() и
        //cleanup() для этого, это вопрос специфики производительности для
        //разработчика.
        //TODO: Инициализация AppState, например, присоединение spatials к rootNode
    }

    private void initKeys() {
        // You can map one or several inputs to one named action
        app.getInputManager().addMapping("Drag", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        app.getInputManager().addMapping("Left", new MouseAxisTrigger(MouseInput.AXIS_X, true));
        app.getInputManager().addMapping("Right", new MouseAxisTrigger(MouseInput.AXIS_X, false));
        app.getInputManager().addMapping("Up", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
        app.getInputManager().addMapping("Down", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        app.getInputManager().addMapping("defaultCamPosition",  new KeyTrigger(KeyInput.KEY_MINUS));

        app.getInputManager().addMapping("camZOOM_Wheel_Minus",  new MouseAxisTrigger(MouseInput.AXIS_WHEEL, true));
        app.getInputManager().addMapping("camZOOM_Wheel_Plus",  new MouseAxisTrigger(MouseInput.AXIS_WHEEL, false));

        app.getInputManager().addListener(actionListener, "defaultCamPosition" , "Drag" );
        app.getInputManager().addListener(analogListener, "Left", "Right", "Up", "Down" , "camZOOM_Wheel_Minus" , "camZOOM_Wheel_Plus");
    }

    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("defaultCamPosition") && !keyPressed) {
                cameranode.setLocalTranslation(-4,3,3);
            }
            if (name.equals("Drag") && keyPressed) {
                drag = true;
            }
            if (name.equals("Drag") && !keyPressed) {
                drag = false;
            }
        }
    };

    private final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float value, float tpf) {
            int speed = 25;
            if (drag) {
                if (name.equals("Right")) {
                    mainCameraPosition = cameranode.getLocalTranslation();
                    cameranode.setLocalTranslation(mainCameraPosition.x , mainCameraPosition.y, mainCameraPosition.z - value * speed);
                }
                if (name.equals("Left")) {
                    mainCameraPosition = cameranode.getLocalTranslation();
                    cameranode.setLocalTranslation(mainCameraPosition.x , mainCameraPosition.y, mainCameraPosition.z + value * speed);
                }
                if (name.equals("Up")) {
                    mainCameraPosition = cameranode.getLocalTranslation();
                    cameranode.setLocalTranslation(mainCameraPosition.x + value * speed, mainCameraPosition.y , mainCameraPosition.z);
                }
                if (name.equals("Down")) {
                    mainCameraPosition = cameranode.getLocalTranslation();
                    cameranode.setLocalTranslation(mainCameraPosition.x - value * speed, mainCameraPosition.y , mainCameraPosition.z);
                }
            }
            if (name.equals("camZOOM_Wheel_Minus")) {
                mainCameraPosition = cameranode.getLocalTranslation();
                cameranode.setLocalTranslation(mainCameraPosition.x - 1 , mainCameraPosition.y + 1 , mainCameraPosition.z);
            }
            if (name.equals("camZOOM_Wheel_Plus")) {
                mainCameraPosition = cameranode.getLocalTranslation();
                cameranode.setLocalTranslation(mainCameraPosition.x + 1 , mainCameraPosition.y - 1, mainCameraPosition.z);
            }
        }
    };
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
