package nativespider.java;
import java.io.*;

import nativespider.interfaces.IFileManager;

public class FileManager implements IFileManager {

	@Override
	public int isDirectory(String file) {
		return new File(file).isDirectory()?1:0;
	}

	@Override
	public int isFile(String file) {
		return new File(file).isFile()?1:0;
	}

	@Override
	public String[] listFolder(String folder) {
		File folderFile = new File(folder);
		if(!folderFile.exists() || !folderFile.isDirectory())
		{
			return new String[0];
		}
		return folderFile.list();
	}

	@Override
	public byte[] readFile(String file) {
		try {
			FileInputStream stream = new java.io.FileInputStream(file);
			byte[] buf = new byte[stream.available()];
			stream.read(buf);
			return buf;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new byte[0];
	}

	@Override
	public String readTextFile(String file) {
		StringBuffer buf = new StringBuffer();
		
		try {
			String s = null;
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while((s = reader.readLine()) != null)
			{
				buf.append(s);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return buf.toString();
	}

}
