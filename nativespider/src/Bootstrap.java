import nativespider.win32.RobotImpl;
public class Bootstrap {

	static 
	{
		System.loadLibrary("nativespider");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.print(new nativespider.win32.RobotImpl().getHelloworld("Aabcce")); 
	}

}
