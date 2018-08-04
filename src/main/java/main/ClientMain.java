package main;

import AppStates.GridAppState;
import AppStates.SkyAppState;
import AppStates.UIAppState;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ClasspathLocator;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.*;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.scene.Mesh;
import com.jme3.scene.control.CameraControl;
import com.jme3.scene.debug.Arrow;
import com.jme3.system.AppSettings;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.controls.windows.Window;
import tonegod.gui.core.Screen;
import utils.*;
import utils.UtNetworking.NetworkMessage;
import utils.UtNetworking.PositionMessage;



public class ClientMain extends SimpleApplication {

    private Client client;
    private ConcurrentLinkedQueue<String> messageQueue;
    private Geometry geom;

    CameraNode cameranode;
    Camera camera;
    Vector3f ve;
    //Tonegod
    //Screen screen;
    //public int winCount = 0;
    public static void main(String[] args){
        UtNetworking.initialiseSerializables();
        ClientMain app = new ClientMain();
        AppSettings settings = new AppSettings(true);
        settings.setTitle("jMonkey 3.2");
        //settings.setSettingsDialogImage("Interface/logic-excavator.png");
        settings.setHeight(600);
        settings.setWidth(800);

        app.setSettings(settings);
        app.setShowSettings(false);
        app.start();
    }

    public ClientMain(){
        // super (new StatsAppState());
    }


    @Override
    public void simpleInitApp() {

        assetManager.registerLocator("/Assets", ClasspathLocator.class);
        //setDisplayFps(false);
        //setDisplayStatView(false);
        flyCam.setEnabled(true);

        inputManager.setCursorVisible(true);
        //JmeCursor jc = (JmeCursor) assetManager.loadAsset("Interface/Nifty/resources/cursorPointing.cur");
        // inputManager.setMouseCursor(jc);
        this.setPauseOnLostFocus(false);
//        cam.setLocation(new Vector3f(-4,3,3));  //y - Vertikal


        //????
        //cameraNode = new CameraNode("Main Camera", getCamera());
        //cameraNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        cameranode = new CameraNode("Main Camera", getCamera());
        //cameranode.setControlDir(CameraControl.ControlDirection.SpatialToCamera); //??? WTF
        rootNode.attachChild(cameranode);
        cameranode.setLocalTranslation(-4,3,3);
        Quaternion q2 = new Quaternion();
//
        float angle1 = 45*FastMath.DEG_TO_RAD; //наклон
        float angle2 = 90*FastMath.DEG_TO_RAD; //разворот
        cameranode.setLocalRotation(q2.fromAngles(angle1,angle2,0));

        //Tonegod
//        screen = new Screen(this, "tonegod/gui/style/def/style_map.gui.xml");
//        guiNode.addControl(screen);
//        // Add window
//        Window win = new Window(screen, "win", new Vector2f(15, 15));
//        // create button and add to window
//        ButtonAdapter makeWindow = new ButtonAdapter( screen, "Btn1", new Vector2f(15, 55) ) {
//            @Override
//            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
//                createNewWindow("New Window " + winCount);
//            }
//        };
//        makeWindow.setText("NEW WIN");
//        win.setUseCloseButton(true);
//        // Add it to our initial window
//        win.addChild(makeWindow);
//        // Add window to the screen
//        screen.addElement(win);


//        SkyAppState skyAppState = new SkyAppState();
//        UIAppState uiAppState = new UIAppState();
//        GridAppState gridAppState = new GridAppState();
//        stateManager.attach(skyAppState);
//        stateManager.attach(uiAppState);
//        stateManager.attach(gridAppState);


//        try{
//            client = Network.connectToServer("127.0.0.1", UtNetworking.PORT);
//            client.start();
//        } catch (IOException ex){
//            Logger.getLogger(ClientMain.class.getClass()).log(Level.SEVERE,null,ex);
//        }

        geom = new CreateGeoms(this).createBox();
        rootNode.attachChild(geom);
        MyStateManager myStateManager = new MyStateManager(this);
        MyStateManager.addGrid();
        MyStateManager.addSkybox();
        MyStateManager.addUImain();
        messageQueue = new ConcurrentLinkedQueue<String>() ;
        //client.addMessageListener(new NetworkMessageListener());
        attachCoordinateAxes(new Vector3f(0f,0f,0f));

        initKeys();
        startEventSystem();
// given

    }



    private void startEventSystem() {
        EventBus eventBus = new EventBus("test");
        //EventStateManager eventStateManager = new EventStateManager(this); //EventListener
        //eventBus.register(eventStateManager);

        //eventBus.post(new OurTestEvent(200));

        //System.out.println(listener.getLastMessage());

    }

//    //Tonegod
//    public final void createNewWindow(String someWindowTitle) {
//        Window nWin = new Window(
//                screen,
//                "Window" + winCount,
//        new Vector2f( (screen.getWidth()/2)-175, (screen.getHeight()/2)-100 )
//    );
//        nWin.setWindowTitle(someWindowTitle);
//        screen.addElement(nWin);
//        winCount++;
//    }

    private void initKeys() {
        // You can map one or several inputs to one named action
        inputManager.addMapping("camUP",  new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("camDOWN",  new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping("camLEFT",  new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("camRIGHT",  new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("camZOOM_Plus",  new KeyTrigger(KeyInput.KEY_1));
        inputManager.addMapping("camZOOM_Minus",  new KeyTrigger(KeyInput.KEY_2));
        inputManager.addMapping("Pause",  new KeyTrigger(KeyInput.KEY_P));

        inputManager.addListener(actionListener, "Pause" , "camZOOM_Plus" , "camZOOM_Minus");
        inputManager.addListener(analogListener, "camUP", "camDOWN", "camLEFT" , "camRIGHT" );
    }

    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("Pause") && !keyPressed) {
                System.out.println("SPACE");
            }
            if (name.equals("camZOOM_Plus") && !keyPressed ) {
                ve = cameranode.getLocalTranslation();

                cameranode.setLocalTranslation(ve.x + 1 , ve.y - 1, ve.z);

            }
            if (name.equals("camZOOM_Minus)") && !keyPressed) {
                ve = cameranode.getLocalTranslation();
                cameranode.setLocalTranslation(ve.x + 1 , ve.y + 1, ve.z + 1);
            }
        }
    };

    private final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float value, float tpf) {
                if (name.equals("camUP")) {
                    System.out.println("test");
                }
                if (name.equals("camDOWN")) {
                    System.out.println("test");
                }
                if (name.equals("camLEFT")) {
                    System.out.println("test");
                }
                if (name.equals("camRIGHT")) {
                    System.out.println("test");
                }

        }
    };

    private void attachCoordinateAxes(Vector3f pos){
        Arrow arrow = new Arrow(Vector3f.UNIT_X);
        putShape(arrow, ColorRGBA.Red).setLocalTranslation(pos);

        arrow = new Arrow(Vector3f.UNIT_Y);
        putShape(arrow, ColorRGBA.Green).setLocalTranslation(pos);

        arrow = new Arrow(Vector3f.UNIT_Z);
        putShape(arrow, ColorRGBA.Blue).setLocalTranslation(pos);

    }

    private Geometry putShape(Mesh shape, ColorRGBA color) {
        Geometry g = new Geometry("coordinate axis", shape);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.getAdditionalRenderState().setWireframe(true);
        mat.getAdditionalRenderState().setLineWidth(5);
        mat.setColor("Color", color);
        g.setMaterial(mat);
        g.setLocalScale(50f);
        rootNode.attachChild(g);
        return g;
    }

    @Override
    public void simpleUpdate(float tpf){
//        String message = messageQueue.poll();
//        
//        if (message != null){
//            fpsText.setText(message);
//        }else{
//            fpsText.setText("NO MESSAGE!!!!!!!!!!!!");
//        }
        // VSNK TURN ON!!!
        //super.simpleUpdate(tpf);
        //if (container.isNeedWriteToJme()) {
        //        container.writeToJme();
        //}


    }

    private class NetworkMessageListener implements MessageListener <Client>{

        @Override
        public void messageReceived(Client s, Message m)
        {
            if (m instanceof NetworkMessage )
            { // instanceof проверяет принадлежит ли объект к классу
                NetworkMessage message = (NetworkMessage) m;
                // messageQueue.add(message.getMessage());
                String text = " ";
                if (message.getMessage().equals("won"))
                {
                    text = " YOU WIN!!! ";

                }else
                {
                    text = "LOOOSE";
                }
                //helloText.setText(text);
                ClientMain.this.enqueue(new Callable()
                {
                    @Override
                    public Object call() throws Exception
                    {
                        //guiNode.attachChild(helloText);
                        return null;
                    }
                });

                //}


            }
            else if (m instanceof PositionMessage)
            {
                final PositionMessage positionMessage = (PositionMessage) m;

                ClientMain.this.enqueue(new Callable()
                {
                    public Object call() throws Exception
                    {
                        //geom.setLocalTranslation(positionMessage.GetPosition());
                        return null;
                    }

                });
            }
        }

    }

    @Override
    public void destroy(){
        //client.close();
        super.destroy();
    }
}