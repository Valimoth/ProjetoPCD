package IscTorrent;

import java.io.Serializable;

public class NewConnectionRequest implements Serializable{
	private String nodeName;
    private String message;

    public NewConnectionRequest(String nodeName) {
        this.nodeName = nodeName;
        this.message = "Em espera";
    }

    public String getNodeName() {
        return nodeName;
    }

    public String getMessage() {
        return message;
    }
}
