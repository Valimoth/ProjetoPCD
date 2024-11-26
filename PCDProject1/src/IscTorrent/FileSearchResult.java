package IscTorrent;

public class FileSearchResult {
	 private String fileName;
	    private long fileSize;
	    private String fileHash;
	    private String nodeAddress;
	    private int nodePort;

	    public FileSearchResult(String fileName, long fileSize, String fileHash, String nodeAddress, int nodePort) {
	        this.fileName = fileName;
	        this.fileSize = fileSize;
	        this.fileHash = fileHash;
	        this.nodeAddress = nodeAddress;
	        this.nodePort = nodePort;
	    }

	    public String getFileName() {
	        return fileName;
	    }

	    public long getFileSize() {
	        return fileSize;
	    }

	    public String getFileHash() {
	        return fileHash;
	    }

	    public String getNodeAddress() {
	        return nodeAddress;
	    }

	    public int getNodePort() {
	        return nodePort;
	    }

	    @Override
	    public String toString() {
	        return "FileSearchResult{" +
	                "fileName='" + fileName + '\'' +
	                ", fileSize=" + fileSize +
	                ", fileHash='" + fileHash + '\'' +
	                ", nodeAddress='" + nodeAddress + '\'' +
	                ", nodePort=" + nodePort +
	                '}';
	    }
}
