package nativespider.win32;

import nativespider.interfaces.IRobot;

import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public class RobotImpl implements IRobot {

	public native String getHelloworld(String name);

	public void keyPress(int keycode) {
		RobotInput input = new RobotInput();
		input.type = RobotInput.INPUT_KEYBOARD;
		RobotKeyboardInput ki = new RobotKeyboardInput();
		ki.wVk = keycode;
		input.ki = ki;
	}

	public void keyRelease(int keycode) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMove(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void mousePress(int buttons) {
		// TODO Auto-generated method stub
		
	}

	public void mouseRelease(int buttons) {
		// TODO Auto-generated method stub
		
	}

	public void mouseWheel(int wheelAmt) {
		// TODO Auto-generated method stub
		
	}

	public void set(int i) {
		// TODO Auto-generated method stub
		
	}

}
