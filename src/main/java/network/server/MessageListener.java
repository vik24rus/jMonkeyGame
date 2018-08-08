package network.server;


import com.jme3.network.Filters;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import event.InputEvent;
//import event.InputEvent;

public class MessageListener implements com.jme3.network.MessageListener<HostedConnection> {


    @Override
    public void messageReceived(HostedConnection source, Message message) {
        if (message instanceof InputEvent) {
            //do something with the message
            InputEvent helloMessage = (InputEvent) message;
            System.out.println("Client #" + source.getId() + " received: '" + helloMessage.getActionCommand() + "'");
            source.getServer().broadcast(Filters.notEqualTo(source), message);
        }
    }
}