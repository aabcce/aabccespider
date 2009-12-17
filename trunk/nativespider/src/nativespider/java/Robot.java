package nativespider.java;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.POINT;

public class Robot {
	public static int[] getMousePostion() {
		POINT pt = new POINT ();
		OS.GetCursorPos (pt);
		
		return new int[] {pt.x,pt.y};
	}
}
