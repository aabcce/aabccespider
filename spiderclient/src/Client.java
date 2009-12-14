import nativespider.interfaces.*;
import nativespider.NativeFactory;

public class Client {
	public static void main(String args[])
	{
		System.out.print(System.getProperty("os.name"));
		
		IRobot robot = NativeFactory.getInstance().getRobot();
		
		robot.mouseMove(1, 1);
	}
}
