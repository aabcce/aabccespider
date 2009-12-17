package nativespider;

import java.io.*;
import java.util.StringTokenizer;

import nativespider.interfaces.*;
import nativespider.util.*;

public class NativeFactory {

	private static NativeFactory _factory = new NativeFactory();

	private IFileManager _fileManager;
	private IOSManager _osManager;
	private IRobot _robot;
	private IWindowAgent _windowAgent;

	public static NativeFactory getInstance() {
		return _factory;
	}

	private NativeFactory() {

	}
	
	public IFileManager getFileManager() {
		return this._fileManager;
	}
	
	public IOSManager getOSManager() {
		if (_osManager == null) {
			if (PlatformUtil.isWindows()) {

				_osManager = new nativespider.win32.OSManagerImpl();
			} else if (PlatformUtil.isMac()) {
				_osManager = null;
			} else if (PlatformUtil.isLinux()) {
				_osManager = null;
			}
		}
		return this._osManager;
	}

	public IRobot getRobot() {
		if (_robot == null) {
			if (PlatformUtil.isWindows()) {

				_robot = new nativespider.win32.RobotImpl();
			} else if (PlatformUtil.isMac()) {
				_robot = null;
			} else if (PlatformUtil.isLinux()) {
				_robot = null;
			}
		}

		return _robot;
	}
	
	public IWindowAgent getWindowAgent() {
		return this._windowAgent;
	}

	static {
		try {
			String libpath = System.getProperty("java.library.path");
			String path = null;
			StringTokenizer st = new StringTokenizer(libpath, System
					.getProperty("path.separator"));
			if (st.hasMoreElements()) {
				path = st.nextToken();
			}

			File dllFile = null;
			InputStream inputStream = null;
			FileOutputStream outputStream = null;
			byte[] array = null;
			
			String nativeCode = "";
			if(nativespider.util.PlatformUtil.isWindows())
			{
				nativeCode = "swt-win32.dll";
			}
			else if(nativespider.util.PlatformUtil.isMac())
			{
				nativeCode = "libswt-cocoa.jnilib";
			}
			else if(nativespider.util.PlatformUtil.isLinux())
			{
				nativeCode = "libswt-gtk.so";
			}
			
			dllFile = new File(new File(path), nativeCode);
			if (!dllFile.exists()) {
				inputStream = NativeFactory.class.getResource("/nativespider/libs/" + nativeCode)
						.openStream();
				outputStream = new FileOutputStream(dllFile);
				array = new byte[1024];
				for (int i = inputStream.read(array); i != -1; i = inputStream
						.read(array)) {
					outputStream.write(array, 0, i);
				}
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
