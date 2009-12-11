package nativespider.interfaces;

import nativespider.*;

public interface IOSManager {
	void executeCommand(String command);
	
	String readCommand(String command);
	
	String getOSName();
	
	OSInfo getOSInfo();
	
	byte[] captureFullScreen();
	
	byte[] captureScreen(int x,int y,int width,int height);
	
	void setClipboard(String text);
	
	String getClipboard();
}
