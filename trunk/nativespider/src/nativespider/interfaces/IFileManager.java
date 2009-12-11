package nativespider.interfaces;

public interface IFileManager {
	String[] readTextFile(String file);
	
	byte[] readFile(String file);
	
	int isFile(String file);
	
	int isDirectory(String file);
	
	String[] listFolder(String folder);
}
