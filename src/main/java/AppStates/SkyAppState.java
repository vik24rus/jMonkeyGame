package AppStates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;

public class SkyAppState extends BaseAppState {

    private SimpleApplication app;

    @Override
    protected void initialize(Application application) {
        this.app = (SimpleApplication) application;
    }

    @Override
    protected void cleanup(Application application) {

    }

    @Override
    protected void onEnable() {
        Texture westTex = app.getAssetManager().loadTexture("Textures/Sky/right.png");
        Texture eastTex = app.getAssetManager().loadTexture("Textures/Sky/left.png");
        Texture northTex = app.getAssetManager().loadTexture("Textures/Sky/back.png");
        Texture southTex = app.getAssetManager().loadTexture("Textures/Sky/front.png");
        Texture upTex = app.getAssetManager().loadTexture("Textures/Sky/top.png");
        Texture downTex = app.getAssetManager().loadTexture("Textures/Sky/bot.png");

        final Vector3f normalScale = new Vector3f(-1, 1, 1);
        Spatial skySpatial = SkyFactory.createSky(
                app.getAssetManager(),
                westTex,
                eastTex,
                northTex,
                southTex,
                upTex,
                downTex,
                normalScale );
        app.getRootNode().attachChild(skySpatial);
    }

    @Override
    protected void onDisable() {

    }
}
