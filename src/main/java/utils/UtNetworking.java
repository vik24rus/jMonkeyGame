package utils;

import com.jme3.math.Vector3f;
import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;
import com.jme3.network.serializing.Serializer;


/**
 *
 * @author vik24rus
 */
public class UtNetworking {

    public static final int PORT = 6069;
    public static void initialiseSerializables(){
        Serializer.registerClass(NetworkMessage.class);
        Serializer.registerClass(LocAndDirMessage.class);
        Serializer.registerClass(PositionMessage.class);
    }

    @Serializable
    public static class LocAndDirMessage extends AbstractMessage {
        private Vector3f position;
        private Vector3f direction;

        public LocAndDirMessage(){
        }

        public LocAndDirMessage(Vector3f position, Vector3f direction){  //дербаним для сервера для получения 2х значений
            this.position = position;
            this.direction = direction;
        }
        public Vector3f GetLocation(){
            return position;
        }
        public Vector3f GetDirection(){
            return direction;
        }
    }

    @Serializable
    public static class PositionMessage extends AbstractMessage {
        private Vector3f position;


        public PositionMessage(){
        }

        public PositionMessage(Vector3f position){
            this.position = position;
        }
        public Vector3f GetPosition(){
            return position;
        }

    }


    @Serializable
    public static class NetworkMessage extends AbstractMessage {

        private String message;

        public NetworkMessage(){

        }

        public NetworkMessage(String message){
            this.message = message;
        }

        public String getMessage(){
            return message;
        }

    }

}
