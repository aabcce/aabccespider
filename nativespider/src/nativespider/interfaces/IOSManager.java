package nativespider.interfaces;

import org.eclipse.swt.graphics.Image;

import nativespider.*;

public interface IOSManager {
	/**
	 * execute command in console, and return the result
	 * @param command
	 * @param mode 0 return directly 1 wait for the command
	 * @return the result of the command
	 */
	String executeCommand(String command,int mode);
	
	/**
	 * execute command and read the output syncly
	 * @param command
	 * @return a handler for receive message from the spider
	 */
	String readCommand(String command);
	
	String getOSName();
	
	OSInfo getOSInfo();
	
	Image captureFullScreen();
	
	Image captureScreen(int x,int y,int width,int height);
	
	void setClipboard(String text);
	
	String getClipboard();
}
