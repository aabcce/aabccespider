package nativespider.interfaces;

import org.eclipse.swt.graphics.Image;

import nativespider.*;

public interface IOSManager {
	void executeCommand(String command);
	
	String readCommand(String command);
	
	String getOSName();
	
	OSInfo getOSInfo();
	
	Image captureFullScreen();
	
	Image captureScreen(int x,int y,int width,int height);
	
	void setClipboard(String text);
	
	String getClipboard();
}
