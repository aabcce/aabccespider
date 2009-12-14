package nativespider.util;

public class PlatformUtil {
	public static boolean isWindows()
	{
		return (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS")!=-1);
	}
	public static boolean isMac()
	{
		return (System.getProperty("os.name").toUpperCase().indexOf("MAC")!=-1);
	}
	public static boolean isLinux()
	{
		return (System.getProperty("os.name").toUpperCase().indexOf("LINUX")!=-1);
	}
}
