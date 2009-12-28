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
	
	Window findWindowByTitle(String WndName);
	
	void activeWindow(int WID);

}
