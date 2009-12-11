package nativespider.win32;

public class RobotInput {
	public static final int INPUT_MOUSE = 0;
	public static final int INPUT_KEYBOARD = 1;
	public static final int INPUT_HARDWARE = 2;
	
	public int type;
	
	public RobotMouseInput mi;
	public RobotKeyboardInput ki;
	public RobotHardwareInput hi;
	
	public long[] toCType()
	{
		return new long[0];
	}
}
