package junit;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import nativespider.java.*;

public class FileManagerTest {
	
	private FileManager _fm = new FileManager();

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
	public void testIsDirectory() {
		String folder = Double.toString(Math.random());
		
		int res = _fm.isDirectory(folder);
		
		Assert.assertEquals(res, 0);
		
		folder = System.getenv("JAVA_HOME");
		
		res =  _fm.isDirectory(folder);
		
		Assert.assertEquals(res, 1);
	}

	@Test
	public void testIsFile() {
		String file = Double.toString(Math.random());
		
		int res = _fm.isFile(file);
		
		Assert.assertEquals(res, 0);
		
		file = System.getenv("JAVA_HOME") + java.io.File.separator + "bin" + 
		java.io.File.separator + "java.exe";
		
		res =  _fm.isFile(file);
		
		Assert.assertEquals(res, 1);
	}

	@Test
	public void testListFolder() {
		String folder = System.getenv("JAVA_HOME");
		
		String[] files = _fm.listFolder(folder + java.io.File.separator + "bin");

		Assert.assertTrue(files.length > 0);
	}

	@Test
	public void testReadFile() {
		String folder = System.getenv("JAVA_HOME");
		
		byte[] fileCon = _fm.readFile(folder + java.io.File.separator + "bin" + 
				java.io.File.separator + "java.exe");
		

		Assert.assertTrue(fileCon.length > 0);
	}

	@Test
	public void testReadTextFile() {
String folder = System.getenv("JAVA_HOME");
		
		String fileCon = _fm.readTextFile(folder + java.io.File.separator + "COPYRIGHT");
		
		Assert.assertTrue(fileCon.length() > 0);
	}

}
