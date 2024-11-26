package IscTorrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class NodeHandler implements Runnable {
	private Socket socket;

	    public NodeHandler(Socket socket) {
	        this.socket = socket;
	    }
	@Override
	   public void run() {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            Object obj;
            while ((obj = in.readObject()) != null) {
                if (obj instanceof NewConnectionRequest) {
                    NewConnectionRequest request = (NewConnectionRequest) obj;
                    System.out.println("Pedido de conexão recebido de: " + request.getNodeName() + " " + socket.getPort());
                } else if (obj instanceof String) {
                    System.out.println("Mensagem recebida: " + obj);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
