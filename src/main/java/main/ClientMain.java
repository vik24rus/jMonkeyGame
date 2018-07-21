package main;

import AppStates.GridAppState;
import AppStates.UIAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetKey;
import com.jme3.asset.plugins.ClasspathLocator;
import com.jme3.font.BitmapText;
import com.jme3.input.event.MouseButtonEvent;
import com.jme3.input.event.MouseMotionEvent;
import com.jme3.material.Material;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.math.ColorRGBA;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Network;
import com.jme3.scene.Mesh;
import com.jme3.scene.Spatial;
import com.jme3.scene.debug.Arrow;

import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import com.sun.istack.internal.logging.Logger;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;


import tonegod.gui.controls.buttons.Button;
import tonegod.gui.controls.buttons.ButtonAdapter;
import tonegod.gui.controls.windows.Window;
import tonegod.gui.core.Screen;
import utils.CreateGeoms;
import utils.Grid;
import utils.UI;
import utils.UtNetworking;
import utils.UtNetworking.NetworkMessage;
import utils.UtNetworking.PositionMessage;

//import java.util.logging.Logger;



public class ClientMain extends SimpleApplication {

    private Client client;
    private final Logger log = Logger.getLogger(ClientMain.class.getClass());
    private ConcurrentLinkedQueue<String> messageQueue;
    private Geometry geom;
    private BitmapText helloText;

    private int gridY , gridX;
    private Grid grid;
    private Spatial[][] cells;
    private boolean gridON;
    private UI userUI;

    public int winCount = 0;
    private Screen screen;
    public static void main(String[] args){
        UtNetworking.initialiseSerializables();
        ClientMain app = new ClientMain();
        AppSettings settings = new AppSettings(true);
        settings.setTitle("jMonkey 3.2");
        //settings.setSettingsDialogImage("Interface/logic-excavator.png");
        app.setSettings(settings);
        app.setShowSettings(false);
        app.start();
    }

    public ClientMain(){
        // super (new StatsAppState());
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

    @Override
    public void simpleInitApp() {
        //setDisplayFps(false);
        //setDisplayStatView(false);
        //flyCam.setEnabled(true);
        //inputManager.setCursorVisible(false);
        //JmeCursor jc = (JmeCursor) assetManager.loadAsset("Interface/Nifty/resources/cursorPointing.cur");
        // inputManager.setMouseCursor(jc);

        GridAppState gridAppState = new GridAppState();
        UIAppState uiAppState = new UIAppState();
        stateManager.attach(gridAppState);
        stateManager.attach(uiAppState);
        //stateManager.detach(uiAppState);


        try{
            client = Network.connectToServer("127.0.0.1", UtNetworking.PORT);
            client.start();
        } catch (IOException ex){
            Logger.getLogger(ClientMain.class.getClass()).log(Level.SEVERE,null,ex);
        }

        //geom = new CreateGeoms(this).createBox();
        //rootNode.attachChild(geom);

        messageQueue = new ConcurrentLinkedQueue<String>() ;
        client.addMessageListener(new NetworkMessageListener());

        this.setPauseOnLostFocus(false);
//        inputManager.addMapping("left", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
//        inputManager.addListener (new ActionListener() {
//            @Override
//            public void onAction(String name, boolean isPressed, float tpf) {
//               
//                Vector2f click2d = inputManager.getCursorPosition();
//                Vector3f click3d = cam.getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f).clone();
//                Vector3f dir = cam.getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d);
// 
//                if(isPressed){
//                    client.send(new LocAndDirMessage(click3d , dir));
//                }
//            }
//        }, "left");


//        guiNode.detachAllChildren();
//        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
//        helloText = new BitmapText(guiFont, false);
//        helloText.setSize(guiFont.getCharSet().getRenderedSize());
//        helloText.setText(" f");
//        helloText.setLocalTranslation(300,300 +  helloText.getLineHeight(), 0);

        //if (gridON) {
            //renderGrid();
        //}
        renderSky();
        attachCoordinateAxes(new Vector3f(0f,0f,0f));

        //UI userUI = new UI(this);
        //guiNode.addControl(userUI.getScreen());
       // guiNode.attachChild(helloText);

    }
    public void renderGrid(){
            gridY = 10;
            gridX = 10;
            grid = new Grid(gridY , gridX, this);
            cells = new Spatial[gridY][gridX];
            cells = grid.getGrid();
            for (int i = 0; i < 10; i++) //_columnCount
            {
                for (int j = 0; j < 10; j++) //_rowCount
                {
                    rootNode.attachChild(cells[i][j]);
                }
            }
    }

    private void attachCoordinateAxes(Vector3f pos)
    {
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

    }

    private void renderSky()
    {
        Texture westTex = assetManager.loadTexture("Textures/Sky/right.png");
        Texture eastTex = assetManager.loadTexture("Textures/Sky/left.png");
        Texture northTex = assetManager.loadTexture("Textures/Sky/back.png");
        Texture southTex = assetManager.loadTexture("Textures/Sky/front.png");
        Texture upTex = assetManager.loadTexture("Textures/Sky/top.png");
        Texture downTex = assetManager.loadTexture("Textures/Sky/bot.png");

        final Vector3f normalScale = new Vector3f(-1, 1, 1);
        Spatial skySpatial = SkyFactory.createSky(
                            assetManager,
                            westTex,
                            eastTex,
                            northTex,
                            southTex,
                            upTex,
                            downTex,
                            normalScale );
        rootNode.attachChild(skySpatial);
        //getRootNode().attachChild(SkyFactory.createSky(getAssetManager(), "Textures/Sky/Bright/BrightSky.dds", SkyFactory.EnvMapType.CubeMap));
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
                helloText.setText(text);
                ClientMain.this.enqueue(new Callable()
                {
                    @Override
                    public Object call() throws Exception
                    {
                        guiNode.attachChild(helloText);
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
        client.close();
        super.destroy();
    }
}