package junit;

import junit.framework.Assert;
import nativespider.OSInfo;
import nativespider.interfaces.IOSManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
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
		ImageLoader loader = new ImageLoader();
		loader.data = new ImageData[]{img.getImageData()};
		loader.save("testCaptureFullScreen.png", SWT.IMAGE_PNG);
		Assert.assertNotNull(img);
		Assert.assertTrue(new java.io.File("testCaptureFullScreen.png").exists());
		new java.io.File("testCaptureFullScreen.png").delete();
	}

	@Test
	public void testCaptureScreen() {
		Image img = _osSManager.captureScreen(100, 100, 400, 400);
		
		ImageLoader loader = new ImageLoader();
		loader.data = new ImageData[]{img.getImageData()};
		loader.save("testCaptureScreen.png", SWT.IMAGE_PNG);
		Assert.assertNotNull(img);
		Assert.assertTrue(new java.io.File("testCaptureScreen.png").exists());
		new java.io.File("testCaptureScreen.png").delete();
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

	@Test
	public void testExecuteCommand() {
		String cmd = "cmd /?";
		
		String res = _osSManager.executeCommand(cmd, 0);
		
		Assert.assertTrue(res.length() == 0);
	}
	
	@Test
	public void testGetSetClipboard() {
		String text = "ÖÐÖ÷amyy";
		
		_osSManager.setClipboard(text);
		
		String expect = _osSManager.getClipboard();
		
		Assert.assertTrue(expect.equals(text));
	}
	
}
