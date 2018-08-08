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
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.scene.Spatial;


public class ForTestAppState extends BaseAppState {
    SimpleApplication app;
    Spatial planet1;
    Material m;
    Spatial planet2;
    Material m2;
    Spatial planet3;
    Material m3;
    @Override
    protected void initialize(Application app ) {
        this.app = (SimpleApplication) app;

        //1
        planet1 = this.app.getAssetManager().loadModel("Models/planet1.j3o" );
        planet1.setLocalTranslation(10,0,-1);
        //m = new Material (this.app.getAssetManager(), "Models/Material.001.material");
        m = new Material (this.app.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        m.setBoolean("UseMaterialColors",true);  // Установим некоторые параметры, например. Синий.
        m.setColor("Ambient", ColorRGBA.Blue);   // ... цвет этого объекта
        m.setColor("Diffuse", ColorRGBA.Blue);   // ... цвет отраженного света
        planet1.setMaterial(m);
        this.app.getRootNode().attachChild(planet1);

        //5
        FilterPostProcessor fpp=new FilterPostProcessor(this.app.getAssetManager());
        BloomFilter bloom = new BloomFilter(BloomFilter.GlowMode.Objects);
        fpp.addFilter(bloom);
        this.app.getViewPort().addProcessor(fpp);
        planet2 = this.app.getAssetManager().loadModel("Models/planet2.j3o" );
        planet2.setLocalTranslation(0,0,0);
        planet2.setLocalScale(0.5f);
        //m = new Material (this.app.getAssetManager(), "Models/Material.001.material");
        m2 = new Material (this.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        m2.setColor("GlowColor",ColorRGBA.White);
        //m2.setColor("Color", ColorRGBA.Orange);
        planet2.setMaterial(m2);
        this.app.getRootNode().attachChild(planet2);

        //3
        planet3 = this.app.getAssetManager().loadModel("Models/planet3.j3o" );
        planet3.setLocalTranslation(6,0,7);
        //m = new Material (this.app.getAssetManager(), "Models/Material.001.material");
        m3 = new Material (this.app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        m3.setColor("Color", ColorRGBA.Green);
        planet3.setMaterial(m3);
        this.app.getRootNode().attachChild(planet3);

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
