/**
 * 
 */
package nativespider.java;

import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

import nativespider.OSInfo;
import nativespider.interfaces.IOSManager;

/**
 * @author aabcce
 *
 */
public class OSManagerImpl implements IOSManager {

	public Image captureFullScreen() {
		final Display desktop = Display.getDefault();
		GC gc = new GC(desktop);
	    final Image image = new Image(desktop, desktop.getBounds());
	    gc.copyArea(image, 0, 0);
	    gc.dispose();
	    
	    return image;
	}
	
	public Image captureScreen(int x, int y, int width, int height) {
		final Display desktop = Display.getDefault();
		GC gc = new GC(desktop);
		Rectangle rect = new Rectangle( x,  y,  width, height);
	    final Image image = new Image(desktop, rect);
	    gc.copyArea(image, x, y);
	    gc.dispose();
	    
	    return image;
	}

	public String executeCommand(String command,int mode) {
		String output = "";
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
			
			return e.getMessage();
		}
		
		if(mode == 0) return output;
		
		if(mode == 1)
		{
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				p.destroy();
				e.printStackTrace();
				return e.getMessage();
			}
			
			InputStreamReader outReader = new InputStreamReader(p.getInputStream());
			
			int len = 0;
			char ch[] = new char[1024];
			StringBuffer b = new StringBuffer();
			try {
				while((len = outReader.read(ch)) > 0)
				{
					b.append(ch, 0, len);
				}
			} catch (IOException e) {
				e.printStackTrace();
				p.destroy();
				return e.getMessage();
			}
			
			return b.toString();
		}
		
		return output;
	}

	public String getClipboard() {
		Display display = Display.getDefault();
		Clipboard clipboard = new Clipboard(display);
		TextTransfer textTransfer = TextTransfer.getInstance();
		String s = (String)clipboard.getContents(textTransfer);
		clipboard.dispose();
		
		return s;
	}

	public void setClipboard(String text) {
		Display display = Display.getDefault();
		Clipboard clipboard = new Clipboard(display);
		TextTransfer textTransfer = TextTransfer.getInstance();
		clipboard.setContents(new Object[]{text},  new Transfer[]{textTransfer});
		clipboard.dispose();
	}

	public OSInfo getOSInfo()
	{
		return null;
	}

	public String getOSName() {
		return System.getProperty("os.name");
	}

	public String readCommand(String command) {
		// TODO Auto-generated method stub
		return null;
	}
}
