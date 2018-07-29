package main;

import AppStates.SkyAppState;
import AppStates.UIAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetLocator;
import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.ClasspathLocator;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.math.ColorRGBA;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.scene.Mesh;
import com.jme3.scene.debug.Arrow;
import com.jme3.system.AppSettings;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;


import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.controls.windows.Window;
import tonegod.gui.core.Screen;
import utils.UtNetworking;
import utils.UtNetworking.NetworkMessage;
import utils.UtNetworking.PositionMessage;



public class ClientMain extends SimpleApplication {

    private Client client;
    private ConcurrentLinkedQueue<String> messageQueue;
    private Geometry geom;
    SkyAppState skyAppState;
    UIAppState uiAppState;

    Screen screen;
    public int winCount = 0;
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

        assetManager.registerLocator("/main", ClasspathLocator.class);
        //setDisplayFps(false);
        //setDisplayStatView(false);
        flyCam.setEnabled(false);
        inputManager.setCursorVisible(true);
        //JmeCursor jc = (JmeCursor) assetManager.loadAsset("Interface/Nifty/resources/cursorPointing.cur");
        // inputManager.setMouseCursor(jc);
        this.setPauseOnLostFocus(false);

        screen = new Screen(this, "tonegod/gui/style/def/style_map.gui.xml");
        screen.initialize();
        guiNode.addControl(screen);

        // Add window
        Window win = new Window(screen, "win", new Vector2f(15, 15));

        // create button and add to window
        ButtonAdapter makeWindow = new ButtonAdapter( screen, "Btn1", new Vector2f(15, 55) ) {
            @Override
            public void onButtonMouseLeftUp(MouseButtonEvent evt, boolean toggled) {
                createNewWindow("New Window " + winCount);
            }
        };
        makeWindow.setText("SUKA");
        win.setUseCloseButton(true);
        
        // Add it to our initial window
        win.addChild(makeWindow);

        // Add window to the screen
        screen.addElement(win);

        skyAppState = new SkyAppState();
        uiAppState = new UIAppState();
        stateManager.attach(skyAppState);
        stateManager.attach(uiAppState);


//        try{
//            client = Network.connectToServer("127.0.0.1", UtNetworking.PORT);
//            client.start();
//        } catch (IOException ex){
//            Logger.getLogger(ClientMain.class.getClass()).log(Level.SEVERE,null,ex);
//        }

        //geom = new CreateGeoms(this).createBox();
        //rootNode.attachChild(geom);

        messageQueue = new ConcurrentLinkedQueue<String>() ;
        //client.addMessageListener(new NetworkMessageListener());
        attachCoordinateAxes(new Vector3f(0f,0f,0f));

        initKeys();


    }

    public final void createNewWindow(String someWindowTitle) {
        Window nWin = new Window(
                screen,
                "Window" + winCount,
        new Vector2f( (screen.getWidth()/2)-175, (screen.getHeight()/2)-100 )
    );
        nWin.setWindowTitle(someWindowTitle);
        screen.addElement(nWin);
        winCount++;
    }

    private void initKeys() {
        // You can map one or several inputs to one named action
        inputManager.addMapping("Pause",  new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(actionListener, "Pause");
    }

    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("Pause") && !keyPressed) {

                //gridAppState.setEnabled(false);
                System.out.println("SPACE");
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
        g.setLocalScale(15f);
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