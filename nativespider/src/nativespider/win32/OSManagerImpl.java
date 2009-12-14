package nativespider.win32;

import org.eclipse.swt.internal.win32.OS;

import nativespider.OSInfo;
import nativespider.Screen;
import nativespider.interfaces.IOSManager;

public class OSManagerImpl implements IOSManager {

	public byte[] captureFullScreen() {
		// TODO Auto-generated method stub
		return null;
	}

	public byte[] captureScreen(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return null;
	}

	public void executeCommand(String command) {
		// TODO Auto-generated method stub

	}

	public String getClipboard() {
		// TODO Auto-generated method stub
		return null;
	}

	public OSInfo getOSInfo() {
		OSInfo info = new OSInfo();
		
		info.Name = System.getProperty("os.name");
		info.isWin32 = true;
		
		int screencount = OS.GetSystemMetrics (OS.SM_CMONITORS);
		Screen[] screens = new Screen[screencount];
		for(int i=0;i<screencount;i++)
		{
			Screen screen = new Screen();
			screen.x = OS.GetSystemMetrics (OS.SM_XVIRTUALSCREEN);
			screen.y = OS.GetSystemMetrics (OS.SM_YVIRTUALSCREEN);	
			screen.width = OS.GetSystemMetrics (OS.SM_CXVIRTUALSCREEN);
			screen.height = OS.GetSystemMetrics (OS.SM_CYVIRTUALSCREEN);
			screens[i] = screen;
		}
		
		return info;
	}

	public String getOSName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String readCommand(String command) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setClipboard(String text) {
		// TODO Auto-generated method stub

	}

}
