package nativespider.interfaces;

import nativespider.Window;

/**
 * Window event 
 * @author junhui.he
 *
 */
public interface IWindowAgent {
	Window geActiveWindow();
	
	Window findWindow(String name);
	
	void activeWindow(int WID);

}
