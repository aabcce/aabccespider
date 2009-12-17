package nativespider.win32;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.win32.OS;

import nativespider.OSInfo;
import nativespider.Screen;
import nativespider.interfaces.IOSManager;

public class OSManagerImpl implements IOSManager {

	public Image captureFullScreen() {
		Screen screen = getOSInfo().Screens[0];
		
		return captureScreen(0,0,screen.width,screen.height);
	}
	
	public Image captureScreen(int x, int y, int width, int height) {
		int hDC = OS.GetDC(0);
		
		int memHdc = OS.CreateCompatibleDC(hDC);
		int hBitmap = OS.SelectObject(memHdc, hDC);
		OS.BitBlt(memHdc, x, y, width, height, hDC, 0, 0, OS.SRCCOPY);
		OS.SelectObject(memHdc, hBitmap);
		OS.DeleteDC(memHdc);
		
		Image img = Image.win32_new(null, SWT.BITMAP, hBitmap);
		
		return img;
	}

	public void executeCommand(String command) {
		// TODO Auto-generated method stub

	}

	public String getClipboard() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setClipboard(String text) {
		// TODO Auto-generated method stub

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
		info.Screens = screens;
		
		return info;
	}

	public String getOSName() {
		return System.getProperty("os.name");
	}

	public String readCommand(String command) {
		// TODO Auto-generated method stub
		return null;
	}

}
