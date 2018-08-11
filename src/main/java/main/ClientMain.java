package main;


import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ClasspathLocator;
import com.jme3.cursors.plugins.JmeCursor;
import com.jme3.material.Material;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.network.Network;
import com.jme3.scene.Geometry;
import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.debug.Arrow;
import com.jme3.scene.shape.Line;
import com.jme3.system.AppSettings;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.lwjgl.glfw.GLFW;
import com.jme3.system.lwjgl.LwjglWindow;
import utils.*;
import utils.UtNetworking.NetworkMessage;
import utils.UtNetworking.PositionMessage;


public class ClientMain extends SimpleApplication {

    private Client client;
    private ConcurrentLinkedQueue<String> messageQueue;
    private Geometry geom;
    //Tonegod
    //Screen screen;
    //public int winCount = 0;
    public static void main(String[] args){
        UtNetworking.initialiseSerializables();
        ClientMain app = new ClientMain();
        AppSettings settings = new AppSettings(true);
        settings.setTitle("jMonkey 3.2");
        //settings.setSettingsDialogImage("Interface/logic-excavator.png");
        settings.setHeight(720);
        settings.setWidth(1280);


        app.setSettings(settings);
        app.setShowSettings(false);
        app.start();
    }

    public ClientMain(){

    }


    @Override
    public void simpleInitApp() {
        //////Windows Position
        final LwjglWindow lwjglContext = (LwjglWindow) this.getContext();
        final long windowHandle = lwjglContext.getWindowHandle();
        GLFW.glfwSetWindowPos(windowHandle, 0 ,10 );
        //////Windows Position
        assetManager.registerLocator("/Assets", ClasspathLocator.class);
        //setDisplayFps(false);
        //setDisplayStatView(false);
        flyCam.setEnabled(false);
        inputManager.setCursorVisible(true);
        JmeCursor jc = (JmeCursor) assetManager.loadAsset("tonegod/gui/style/def/Common/Cursors/Pointer.cur");
        inputManager.setMouseCursor(jc);
        this.setPauseOnLostFocus(false);

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

        geom = new CreateGeoms(this).createBox();
        rootNode.attachChild(geom);

        MyStateManager myStateManager = new MyStateManager(this);
        MyStateManager.addGrid();;
        MyStateManager.addUImain();
        MyStateManager.addCamera();
        //TODO объединить в один AppState
        MyStateManager.addSkybox();
        MyStateManager.addPlanet();
        MyStateManager.addLight();
        attachCoordinateAxes(new Vector3f(0f,0f,0f));


        try{
            client = Network.connectToServer("127.0.0.1", UtNetworking.PORT);
            client.start();
        } catch (IOException ex){
            System.out.println("Error connect to server");
        }
        messageQueue = new ConcurrentLinkedQueue<String>() ;
        client.addMessageListener(new NetworkMessageListener());
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
        client.close();
        super.destroy();
    }
}