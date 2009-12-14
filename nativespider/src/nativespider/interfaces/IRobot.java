package nativespider.interfaces;

public interface IRobot {
	int[] getPointer();
	boolean keyPress(int keycode);
	boolean keyRelease(int keycode);
	boolean mouseMove(int x, int y);
	boolean mousePress(int button);
	boolean mouseRelease(int button);
	boolean mouseWheel(int type,int wheelAmt);
}
