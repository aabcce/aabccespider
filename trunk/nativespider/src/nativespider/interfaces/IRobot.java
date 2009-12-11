package nativespider.interfaces;

public interface IRobot {
	void mouseMove(int x, int y);
	void mousePress(int buttons);
	void mouseRelease(int buttons);
	void mouseWheel(int wheelAmt);
	void keyPress(int keycode);
	void keyRelease(int keycode);
}
