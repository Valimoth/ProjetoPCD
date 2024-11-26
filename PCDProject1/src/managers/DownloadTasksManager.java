package managers;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.ArrayList;

import Reading.FileBlockRequestMessage;

public class DownloadTasksManager {
	
	private static final int BLOCKLENGTH = 10240;
	private ArrayList<FileBlockRequestMessage> blocks = new ArrayList<FileBlockRequestMessage>();
	private MessageDigest hash;

	public DownloadTasksManager(File f) {
		try {
			hash = MessageDigest.getInstance("SHA-256");
			byte[] data = Files.readAllBytes(f.toPath());
			hash.update(data);
			//hash.digest();
			for (int i = 0; i < f.length(); i+=BLOCKLENGTH) {
				blocks.add(new FileBlockRequestMessage(hash, i, BLOCKLENGTH));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<FileBlockRequestMessage> getBlocks() {
		return blocks;
	}
	
	public int getNumBlocks() {
		return blocks.size();
	}
	
	public String getHash() {
		byte[] hashBytes = hash.digest();
		String result = new String();
		
		for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
            	result = result + "0";
            result = result + hex;
        }
		
		return result;
	}
}
