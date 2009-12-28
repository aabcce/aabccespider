package nativespider.win32;

import org.eclipse.swt.internal.win32.OS;

import nativespider.OSInfo;
import nativespider.Screen;

public class OSManagerImpl extends nativespider.java.OSManagerImpl {

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
		info.Screens = screens;
		
		return info;
	}
}
