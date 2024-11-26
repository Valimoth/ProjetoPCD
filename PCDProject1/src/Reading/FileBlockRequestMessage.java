package Reading;

import java.security.MessageDigest;

public class FileBlockRequestMessage {
	
	private MessageDigest fileHash;
	private int offset;
	private int length;
	
	public FileBlockRequestMessage(MessageDigest fileHash, int offset, int length) {
		this.fileHash = fileHash;
		this.offset = offset;
		this.length = length;
	}

	public MessageDigest getFileHash() {
		return fileHash;
	}

	public int getOffset() {
		return offset;
	}

	public int getLength() {
		return length;
	}
	
}
