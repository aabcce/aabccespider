package junit;

import static org.junit.Assert.*;
import junit.framework.Assert;

import nativespider.NativeFactory;
import nativespider.Window;
import nativespider.interfaces.IWindowAgent;
import nativespider.win32.WindowAgent;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WindowAgentTest {
	
	private static IWindowAgent _windowAgent = NativeFactory.getInstance().getWindowAgent();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testActiveWindow() {
		Window wnd = _windowAgent.findWindowByTitle("notpad");
		
		(new WindowAgent()).activeWindow(wnd.WndID);
	}

	@Test
	public void testFindWindowByTitle() {
		Window wnd = _windowAgent.findWindowByTitle("notpad");
		Assert.assertNotNull(wnd);
	}

	@Test
	public void testFindWindowByID() {
		Window wnd = _windowAgent.findWindowByID(5444);
		Assert.assertNotNull(wnd);
	}

	@Test
	public void testGetActiveWindow() {
		Window wnd = _windowAgent.getActiveWindow();
		Assert.assertNotNull(wnd);
	}

}
