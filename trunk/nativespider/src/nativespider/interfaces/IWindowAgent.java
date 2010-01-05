package nativespider.interfaces;

import nativespider.Window;

/**
 * Window event 
 * @author junhui.he
 *
 */
public interface IWindowAgent {
	Window getActiveWindow();
	
	Window findWindowByID(int WndID);
	
	Window findWindowByTitle(String WndClass, String WndName);
	
	void activeWindow(int WndID);
	
	void minimizeWindow(int WndID);
	
	void maximizeWindow(int WndID);
	
	void restoreWindow(int WndID);

}
