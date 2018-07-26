package AppStates;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.simsilica.lemur.*;

public class UIAppStatePlayerInfo extends BaseAppState {
    private Container containerPlayerInfo;
    private float containerPlayerInfo_X , containerPlayerInfo_Y;
    private SimpleApplication app;

    @Override
    protected void initialize(Application application) {
        this.app = (SimpleApplication) application;
        containerPlayerInfo_X = 250;
        containerPlayerInfo_Y = 400;
        containerPlayerInfo = new Container();
        containerPlayerInfo.setLocalTranslation(containerPlayerInfo_X,containerPlayerInfo_Y,0);
        app.getGuiNode().attachChild(containerPlayerInfo);

    }

    @Override
    protected void cleanup(Application application) {

    }

    @Override
    protected void onEnable() {
        containerPlayerInfo.addChild(new Label("Hello, World."));
        //containerPlayerInfo.addChild(new ActionButton(new CallMethodAction("Ok", this, "close")));


    }

    @Override
    protected void onDisable() {
        containerPlayerInfo.clearChildren();
    }
}
