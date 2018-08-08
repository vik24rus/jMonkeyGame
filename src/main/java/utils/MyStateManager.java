package utils;

import AppStates.*;
import com.jme3.app.SimpleApplication;


public class MyStateManager {

    static GridAppState gridAppState;
    static SimpleApplication application;

    static SkyAppState skyAppState ;
    static UIAppState uiAppState;
    static CameraAppState cameraAppState;
    static ForTestAppState forTestAppState;
    static LightAppState lightAppState;


    public MyStateManager(SimpleApplication application){
        this.application = application;
        gridAppState = new GridAppState();
        uiAppState = new UIAppState();
        skyAppState = new SkyAppState();
        cameraAppState = new CameraAppState();
        forTestAppState = new ForTestAppState();
        lightAppState = new LightAppState();
    }


    public static void addGrid(){
        application.getStateManager().attach(gridAppState);
    }


    public static void delGrid(){
        application.getStateManager().detach(gridAppState);
    }


    public static void addUImain(){
        application.getStateManager().attach(uiAppState);
    }


    public static void addSkybox(){
        application.getStateManager().attach(skyAppState);
    }

    public static void addCamera() { application.getStateManager().attach(cameraAppState); }

    public static void forTest() { application.getStateManager().attach(forTestAppState); }

    public static void addLight() { application.getStateManager().attach(lightAppState); }
}
