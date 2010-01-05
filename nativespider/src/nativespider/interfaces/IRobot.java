package nativespider.interfaces;

public interface IRobot {
	/**
	 * Get current pointer position
	 * @return
	 */
	int[] getMousePostion();
	
	boolean mouseMoveTo(int posX, int posY);
	
	boolean mouseMove(int posX, int posY);

	boolean click();
	
	boolean rightClick();
	
	boolean middleClick();
	
	boolean pressMouse(int button);
	
	boolean releaseMouse(int button);
	
	boolean scrollMouse(int wheelAmt,int type);
	
	boolean typeKey(int keycode);
	
	boolean typeText(String text);
	
	boolean pressKey(int keycode);
	
	boolean releaseKey(int keycode);
	
	
}
