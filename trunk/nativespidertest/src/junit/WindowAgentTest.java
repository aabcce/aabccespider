package junit;

import static org.junit.Assert.*;
import junit.framework.Assert;

import nativespider.Window;
import nativespider.interfaces.IWindowAgent;
import nativespider.win32.WindowAgentImpl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WindowAgentTest {
	
	private static IWindowAgent _windowAgent = new WindowAgentImpl();

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
		String cls="CalcFrame";
		String title="Calculator";
		Window wnd = _windowAgent.findWindowByTitle(cls,title);
		
		Assert.assertNotNull(wnd);
		
		_windowAgent.activeWindow(wnd.WndID);
	}

	@Test
	public void testFindWindowByTitle() {
		String cls="CalcFrame";
		String title="Calculator";
		Window wnd = _windowAgent.findWindowByTitle(cls,title);
		Assert.assertNotNull(wnd);
	}

	@Test
	public void testFindWindowByID() {
		String cls="CalcFrame";
		String title="Calculator";
		Window wnd = _windowAgent.findWindowByTitle(cls,title);
		Assert.assertNotNull(wnd);
	}

	@Test
	public void testGetActiveWindow() {
		Window wnd = _windowAgent.getActiveWindow();
		Assert.assertNotNull(wnd);
	}
	
	@Test
	public void testMinimizeWindow() {
		String cls="CalcFrame";
		String title="Calculator";
		Window wnd = _windowAgent.findWindowByTitle(cls,title);
		
		Assert.assertNotNull(wnd);
		
		_windowAgent.minimizeWindow(wnd.WndID);
		Assert.assertNotNull(wnd);
	}	

	@Test
	public void testMaximizeWindow() {
		String cls="CalcFrame";
		String title="Calculator";
		Window wnd = _windowAgent.findWindowByTitle(cls,title);

		Assert.assertNotNull(wnd);
		
		_windowAgent.maximizeWindow(wnd.WndID);
		Assert.assertNotNull(wnd);
	}	

	@Test
	public void testRestoreWindow() {
		String cls="CalcFrame";
		String title="Calculator";
		Window wnd = _windowAgent.findWindowByTitle(cls,title);

		Assert.assertNotNull(wnd);
		
		_windowAgent.restoreWindow(wnd.WndID);
		Assert.assertNotNull(wnd);
	}
}
