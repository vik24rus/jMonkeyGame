package main;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.math.Ray;
import com.jme3.network.Filters;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;

import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.system.JmeContext;
import java.io.IOException;
import java.util.Random;

import event.Event;
import network.server.ConnectionListener;
import network.server.MessageListener;
import utils.CreateGeoms;
import utils.UtNetworking;
import utils.UtNetworking.LocAndDirMessage;
import utils.UtNetworking.NetworkMessage;
import utils.UtNetworking.PositionMessage;


/** Пример 1 - как начать с самого простого приложения JME3.
 * На экране отображается 3D куб с просмотром со всех сторон
 * и перемещением с помощью мыши и кнопок WASD. */

public class ServerMain extends SimpleApplication {

    private Server server;
    private float counter = 0;        //для рандомного движения коробки 
    private Random random = new Random();
    private Geometry geom;

    public static void main(String[] args){
        UtNetworking.initialiseSerializables(); //серилизовали класс сообщений
        ServerMain app = new ServerMain();
        app.start(JmeContext.Type.Headless); //off render
    }

    @Override
    public void simpleInitApp() {
        try{
            server = Network.createServer(UtNetworking.PORT);
            //server.addMessageListener(new MessageHandler());  //слушаем ответы от клиентов
            server.addMessageListener(new MessageListener(), Event.class);  // IN OpenRTS
            server.addConnectionListener(new ConnectionListener());
            server.start();
        } catch (IOException ex){
            System.out.println("Error start server");
        }
        geom = new CreateGeoms(this).createBox(); //создали объект взаимодействия
        rootNode.attachChild(geom);
        System.out.println("Server startup!");

    }

//    private class MessageHandler implements MessageListener <HostedConnection>{
//
//        @Override
//        public void messageReceived(HostedConnection source, Message m) {
//            if (m instanceof LocAndDirMessage){        //если объект сообщения относится к координатам коробки
//                LocAndDirMessage message  = (LocAndDirMessage) m;
//                Vector3f location = message.GetLocation();
//                Vector3f direction = message.GetDirection();
//
//                Ray ray = new Ray(location , direction);   //проверям попадание
//                CollisionResults results = new CollisionResults();
//                rootNode.collideWith(ray,results);
//                if (results.size() > 0){ //если луч попал в коробку
//                    server.broadcast(Filters.equalTo(source) , new NetworkMessage ("won")); //тому от кого пришло сообщение - отправка ВЫИГРАЛ
//                    server.broadcast(Filters.notEqualTo(source) , new NetworkMessage ("lost")); //отправка всем кто подключен кроме того кто отправил сообщение
//                }
//
//            }
//        }
//
//    }

    @Override
    public void simpleUpdate(float tpf){
        server.broadcast(new PositionMessage(new Vector3f(1,1,1)));
//        counter += tpf;
//        if (counter > 1f){
//            float f = random.nextFloat() * 3;
//            server.broadcast(new PositionMessage(new Vector3f(f,f,f))); //отправка всем позиции
//            geom.setLocalTranslation(new Vector3f(f,f,f));
//            counter = 0;
//        }

    }

    @Override
    public void destroy(){
        server.close();
        super.destroy();
    }
}