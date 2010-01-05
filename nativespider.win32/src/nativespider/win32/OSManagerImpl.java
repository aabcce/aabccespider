package nativespider.win32;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.SHELLEXECUTEINFO;
import org.eclipse.swt.internal.win32.TCHAR;

import nativespider.OSInfo;
import nativespider.Screen;

public class OSManagerImpl extends nativespider.java.OSManagerImpl {

	protected OSInfo _osInfo = null;

	public OSInfo getOSInfo() {
		if (_osInfo == null) {
			OSInfo info = new OSInfo();

			info.Name = System.getProperty("os.name");
			info.isWin32 = true;

			int screencount = OS.GetSystemMetrics(OS.SM_CMONITORS);
			Screen[] screens = new Screen[screencount];
			for (int i = 0; i < screencount; i++) {
				Screen screen = new Screen();
				if (OS.WIN32_VERSION >= OS.VERSION(5, 0)) {
					screen.x = OS.GetSystemMetrics(OS.SM_XVIRTUALSCREEN);
					screen.y = OS.GetSystemMetrics(OS.SM_YVIRTUALSCREEN);
					screen.width = OS.GetSystemMetrics(OS.SM_CXVIRTUALSCREEN);
					screen.height = OS.GetSystemMetrics(OS.SM_CYVIRTUALSCREEN);
				} else {
					screen.x = 0;
					screen.y = 0;
					screen.width = OS.GetSystemMetrics(OS.SM_CXSCREEN);
					screen.height = OS.GetSystemMetrics(OS.SM_CYSCREEN);
				}

				screens[i] = screen;
			}
			info.Screens = screens;

			_osInfo = info;
		}
		return _osInfo;
	}

	public void executeProg(String fileName) throws Exception {
		int hHeap = OS.GetProcessHeap();
		TCHAR buffer = new TCHAR(0, fileName, true);
		int byteCount = buffer.length() * TCHAR.sizeof;
		int lpFile = OS.HeapAlloc(hHeap, OS.HEAP_ZERO_MEMORY, byteCount);
		OS.MoveMemory(lpFile, buffer, byteCount);
		SHELLEXECUTEINFO info = new SHELLEXECUTEINFO();
		info.cbSize = SHELLEXECUTEINFO.sizeof;
		info.lpFile = lpFile;
		// Òþ²ØÆô¶¯
		info.nShow = OS.SW_HIDE;
		boolean result = OS.ShellExecuteEx(info);
		if (lpFile != 0)
			OS.HeapFree(hHeap, 0, lpFile);
		if (result == false)
			throw new Exception("Æô¶¯Ê§°Ü!");
	}
}
