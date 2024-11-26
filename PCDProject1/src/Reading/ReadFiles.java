package Reading;

import java.io.File;
import java.io.FileFilter;

public class ReadFiles {
	
	private static final String path = "pasta"; // pasta onde estarao os ficheiros mp3
	private File[] files;
	
	public ReadFiles() {
		
		this.files = new File(path).listFiles(new FileFilter() {
			public boolean accept(File f) {
				return true;    
			}
		});
		if(files.length == 0)
			files = null;
	}
	
	public File[] getFiles() {
		return files;
	}
	

}
