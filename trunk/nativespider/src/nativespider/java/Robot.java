package nativespider.java;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

public class Robot {
	public static Point getMousePoint() {
		PointerInfo p = null;
		p = MouseInfo.getPointerInfo();
		if (p == null)
			return null;

		return p.getLocation();
	}
}
