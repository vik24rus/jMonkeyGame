package network.server;

import com.jme3.network.HostedConnection;
import com.jme3.network.Server;

public class ConnectionListener implements com.jme3.network.ConnectionListener {
    @Override
    public void connectionAdded(Server server, HostedConnection conn) {
        System.out.println(server.getGameName() + " has a new connection:" + conn.getId());

    }

    @Override
    public void connectionRemoved(Server server, HostedConnection conn) {
        System.out.println(server.getGameName() + " lost a connection:" + conn.getId());

    }

}