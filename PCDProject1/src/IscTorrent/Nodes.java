package IscTorrent;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Nodes {
	private String nodeName;
    private int port;
    private List<Socket> connections = new ArrayList<>();
    private List<String> localFiles;

    
    public Nodes(String nodeName, int port) {
        this.nodeName = nodeName;
        this.port = port;
        this.localFiles = new ArrayList<>();
    }
    
    public void addFile(String filePath) {
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            localFiles.add(filePath);
        } else {
            System.out.println("Arquivo inválido: " + filePath);
        }
    }
    
    public List<String> getLocalFileNames() {
        List<String> fileNames = new ArrayList<>();
        for (String filePath : localFiles) {
            File file = new File(filePath);
            fileNames.add(file.getName());
        }
        return fileNames;
    }
    
    public int getPort() {
        return this.port;
    }
  
    public List<FileSearchResult> searchFiles(WordSearchMessage message) {
        List<FileSearchResult> results = new ArrayList<>();
        for (String file : localFiles) {
            if (file.toLowerCase().contains(message.getKeyword().toLowerCase())) {
                // Simulando informações do arquivo
                results.add(new FileSearchResult(
                    file,
                    1024L, // Exemplo de tamanho
                    Integer.toHexString(file.hashCode()), // Exemplo de hash
                    "localhost",
                    this.port
                ));
            }
        }
        return results;
    }
  
    public void startServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println(nodeName + " esperando conexoes...");
                while (true) {
                    Socket socket = serverSocket.accept();
                    connections.add(socket);
                    System.out.println(nodeName + " conectado a: " + socket.getLocalAddress());
                    new Thread(new NodeHandler(socket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    public  void connectToNode(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            connections.add(socket);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(new NewConnectionRequest(nodeName));
            System.out.println(nodeName + " conectou-se " + host + ":" + port);
            new Thread(new NodeHandler(socket)).start();
        } catch (IOException e) {
        	 System.out.println("Conexao nao encontrada");
        }
    }
}
