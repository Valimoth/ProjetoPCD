package IscTorrent;


public class NodeStarter {
	public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java NodeStarter <nomeDoNo> <porta>");
            return;
        }

        String nodeName = args[0];
        int port = Integer.parseInt(args[1]);
        String filePath = args[2];

        Nodes node = new Nodes(nodeName, port);
        node.addFile(filePath);
        node.startServer();

        System.out.println("No " + nodeName + " iniciado na porta " + port + " e aguardando conexoes...");
        IscTorrentGUI window = new IscTorrentGUI(node);
		window.open();
    }
}
