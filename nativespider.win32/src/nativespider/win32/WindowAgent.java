/**
 * 
 */
package nativespider.win32;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.widgets.Display;

import nativespider.Window;
import nativespider.interfaces.IWindowAgent;

/**
 * @author aabcce
 *
 */
public class WindowAgent implements IWindowAgent {

	/**
	 * 
	 */
	public WindowAgent() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see nativespider.interfaces.IWindowAgent#activeWindow(int)
	 */
	@Override
	public void activeWindow(int WndID) {
		OS.SetForegroundWindow(WndID);
	}

	/* (non-Javadoc)
	 * @see nativespider.interfaces.IWindowAgent#findWindow(java.lang.String)
	 */
	@Override
	public Window findWindowByTitle(String WndName) {
		TCHAR name = new TCHAR(OS.CP_UTF8,WndName,false);
		int wndID = OS.FindWindow(null, name);
		return findWindowByID(wndID);
	}
	
	public Window findWindowByID(int WndID)
	{
		RECT rect = new RECT ();
		OS.GetWindowRect (WndID, rect);
		
		Window win = new Window();
		win.WndID = WndID;
		win.x = rect.left;
		win.y = rect.top;
		win.width = rect.right - win.x;
		win.height = rect.bottom - win.y;
		
		return win;
	}

	/* (non-Javadoc)
	 * @see nativespider.interfaces.IWindowAgent#geActiveWindow()
	 */
	@Override
	public Window getActiveWindow() {
		int wndID = OS.GetActiveWindow();
		
		return findWindowByID(wndID);
	}

}
