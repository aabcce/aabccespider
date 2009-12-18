package junit;

import static org.junit.Assert.*;
import junit.framework.Assert;

import nativespider.OSInfo;
import nativespider.interfaces.IOSManager;
import org.eclipse.swt.graphics.Image;

import org.junit.BeforeClass;
import org.junit.Test;

public class OSManagerTest {
	public static IOSManager _osSManager = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		_osSManager = nativespider.NativeFactory.getInstance().getOSManager();
	}

	@Test
	public void testCaptureFullScreen() {
		Image img = _osSManager.captureFullScreen();
		
		Assert.assertNotNull(img);
	}

	@Test
	public void testCaptureScreen() {
		Image img = _osSManager.captureScreen(100, 100, 100, 100);
		
		Assert.assertNotNull(img);
	}

	@Test
	public void testGetOSInfo() {
		OSInfo info = _osSManager.getOSInfo();
		
		Assert.assertNotNull(info);
	}

	@Test
	public void testGetOSName() {
		String osName = _osSManager.getOSName();
		
		Assert.assertEquals("Windows Vista", osName);
	}

}
