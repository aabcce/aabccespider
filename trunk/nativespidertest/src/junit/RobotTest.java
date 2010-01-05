package junit;

import junit.framework.Assert;
import nativespider.*;
import nativespider.interfaces.*;
import nativespider.win32.RobotImpl;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class RobotTest {
	public static IRobot _robot = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		_robot = new RobotImpl();
	}

	@Test
	public void testGetPointer() {
		int[] p = _robot.getMousePostion();
		
		Assert.assertTrue(p.length == 2 && p[0] > 0 && p[1] > 0);
	}

	@Test
	public void testMouseMove() {
		int[] p1 = _robot.getMousePostion();
		_robot.mouseMove(p1[0] + 1, p1[1] + 1);
		int[] p2 = _robot.getMousePostion();
		
		Assert.assertTrue(p1[0]+1 == p2[0] && p1[1]+1 == p2[1]);
	}

	@Test
	public void testMousePress() {
		boolean res = _robot.pressMouse(1);
		_robot.releaseMouse(1);
		Assert.assertTrue(res);
	}

	@Test
	public void testMouseRelease() {
		_robot.pressMouse(1);
		boolean res = _robot.releaseMouse(1);
		Assert.assertTrue(res);
	}

	@Test
	public void testMouseWheel() {
		boolean res = _robot.scrollMouse(200,1);
		Assert.assertTrue(res);
	}

	@Test
	public void testKeyPress() {
		boolean res = _robot.pressKey(0x41);
		_robot.releaseKey(0x41);
		Assert.assertTrue(res);
	}

	@Test
	public void testKeyRelease() {
		_robot.pressKey(0x41);
		boolean res = _robot.releaseKey(0x41);
		Assert.assertTrue(res);
	}

	@Test
	public void testTypeText() {
		boolean res = _robot.typeText("aabcce");
		Assert.assertTrue(res);
	}

	@Test
	public void testTypeText1() {
		boolean res = _robot.typeText("aabcce@sohu.com");
		Assert.assertTrue(res);
	}

}
