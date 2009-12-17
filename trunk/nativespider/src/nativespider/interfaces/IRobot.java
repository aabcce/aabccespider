package nativespider.interfaces;

public interface IRobot {
	/**
	 * Get current pointer position
	 * @return
	 */
	int[] getMousePostion();
	
	boolean keyPress(int keycode);
	
	boolean keyRelease(int keycode);
	
	boolean mouseMove(int x, int y);
	/**
	 * mousePress
	 * @param button 1 left 2 middle 3 right 4 XBUTTON1 5 XBUTTON2
	 * @return
	 */
	boolean mousePress(int button);
	/**
	 * mouseRelease
	 * @param button 1 left 2 middle 3 right 4 XBUTTON1 5 XBUTTON2
	 * @return
	 */
	boolean mouseRelease(int button);
	/**
	 * mouseWheel
	 * @param wheelAmt
	 * @param type default 1
	 * @return
	 */
	boolean mouseWheel(int wheelAmt,int type);
}
