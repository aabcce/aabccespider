package nativespider.win32;

import nativespider.interfaces.IRobot;
import nativespider.java.POINT;

import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.*;

public class RobotImpl implements IRobot {

	public int[] getMousePostion()
	{
		POINT pt = new POINT ();
		OS.GetCursorPos (pt);
		
		return new int[] {pt.x,pt.y};
	}
	
	public boolean mouseMove(int posX, int posY) {
		MOUSEINPUT inputs = new MOUSEINPUT ();
		
		inputs.dwFlags = OS.MOUSEEVENTF_MOVE | OS.MOUSEEVENTF_ABSOLUTE;
		int x= 0, y = 0, width = 0, height = 0;
		if (OS.WIN32_VERSION >= OS.VERSION (5, 0)) {
			inputs.dwFlags |= OS.MOUSEEVENTF_VIRTUALDESK;
			x = OS.GetSystemMetrics (OS.SM_XVIRTUALSCREEN);
			y = OS.GetSystemMetrics (OS.SM_YVIRTUALSCREEN);	
			width = OS.GetSystemMetrics (OS.SM_CXVIRTUALSCREEN);
			height = OS.GetSystemMetrics (OS.SM_CYVIRTUALSCREEN);
		} else {
			width = OS.GetSystemMetrics (OS.SM_CXSCREEN);
			height = OS.GetSystemMetrics (OS.SM_CYSCREEN);
		}
		inputs.dx = ((posX - x) * 65535 + width - 2) / (width - 1);
		inputs.dy = ((posY - y) * 65535 + height - 2) / (height - 1);
		
		int /*long*/ hHeap = OS.GetProcessHeap ();
		int /*long*/ pInputs = OS.HeapAlloc (hHeap, OS.HEAP_ZERO_MEMORY, INPUT.sizeof);
		OS.MoveMemory(pInputs, new int[] {OS.INPUT_MOUSE}, 4);
		//TODO - DWORD type of INPUT structure aligned to 8 bytes on 64 bit
		OS.MoveMemory (pInputs + OS.PTR_SIZEOF, inputs, MOUSEINPUT.sizeof);
		boolean result = OS.SendInput (1, pInputs, INPUT.sizeof) != 0;
		OS.HeapFree (hHeap, 0, pInputs);
		return result;
	}

	public boolean mousePress(int button) {
		return mouseSend(SWT.MouseDown,button);
	}

	public boolean mouseRelease(int button) {
		return mouseSend(SWT.MouseUp,button);
	}
	
	private boolean mouseSend(int type,int button)
	{
		MOUSEINPUT inputs = new MOUSEINPUT ();

		switch (button) {
			case 1: inputs.dwFlags = type == SWT.MouseDown ? OS.MOUSEEVENTF_LEFTDOWN : OS.MOUSEEVENTF_LEFTUP; break;
			case 2: inputs.dwFlags = type == SWT.MouseDown ? OS.MOUSEEVENTF_MIDDLEDOWN : OS.MOUSEEVENTF_MIDDLEUP; break;
			case 3: inputs.dwFlags = type == SWT.MouseDown ? OS.MOUSEEVENTF_RIGHTDOWN : OS.MOUSEEVENTF_RIGHTUP; break;
			case 4: {
				if (OS.WIN32_VERSION < OS.VERSION (5, 0)) return false;
				inputs.dwFlags = type == SWT.MouseDown ? OS.MOUSEEVENTF_XDOWN : OS.MOUSEEVENTF_XUP;
				inputs.mouseData = OS.XBUTTON1;
				break;
			}
			case 5: {
				if (OS.WIN32_VERSION < OS.VERSION (5, 0)) return false;
				inputs.dwFlags = type == SWT.MouseDown ? OS.MOUSEEVENTF_XDOWN : OS.MOUSEEVENTF_XUP;
				inputs.mouseData = OS.XBUTTON2;
				break;
			}
			default: return false;
		}
		
		int /*long*/ hHeap = OS.GetProcessHeap ();
		int /*long*/ pInputs = OS.HeapAlloc (hHeap, OS.HEAP_ZERO_MEMORY, INPUT.sizeof);
		OS.MoveMemory(pInputs, new int[] {OS.INPUT_MOUSE}, 4);
		//TODO - DWORD type of INPUT structure aligned to 8 bytes on 64 bit
		OS.MoveMemory (pInputs + OS.PTR_SIZEOF, inputs, MOUSEINPUT.sizeof);
		boolean result = OS.SendInput (1, pInputs, INPUT.sizeof) != 0;
		OS.HeapFree (hHeap, 0, pInputs);
		return result;
	}

	public boolean mouseWheel(int wheelAmt,int type) {
		MOUSEINPUT inputs = new MOUSEINPUT ();

		if (OS.WIN32_VERSION < OS.VERSION (5, 0)) return false;
		inputs.dwFlags = OS.MOUSEEVENTF_WHEEL;
		switch (type) {
			case SWT.SCROLL_PAGE:
				inputs.mouseData = wheelAmt * OS.WHEEL_DELTA;
				break;
			case SWT.SCROLL_LINE:
				int [] value = new int [1];
				OS.SystemParametersInfo (OS.SPI_GETWHEELSCROLLLINES, 0, value, 0);
				inputs.mouseData = wheelAmt * OS.WHEEL_DELTA / value [0];
				break;
			default: return false;
		}
			
		int /*long*/ hHeap = OS.GetProcessHeap ();
		int /*long*/ pInputs = OS.HeapAlloc (hHeap, OS.HEAP_ZERO_MEMORY, INPUT.sizeof);
		OS.MoveMemory(pInputs, new int[] {OS.INPUT_MOUSE}, 4);
		//TODO - DWORD type of INPUT structure aligned to 8 bytes on 64 bit
		OS.MoveMemory (pInputs + OS.PTR_SIZEOF, inputs, MOUSEINPUT.sizeof);
		boolean result = OS.SendInput (1, pInputs, INPUT.sizeof) != 0;
		OS.HeapFree (hHeap, 0, pInputs);
		return result;
	}

	public boolean keyPress(int keycode) {
		return keySend(SWT.KeyUp,keycode);
	}

	public boolean keyRelease(int keycode) {
		return keySend(SWT.KeyDown,keycode);
	}
	
	private boolean keySend(int type, int keycode)
	{
		KEYBDINPUT inputs = new KEYBDINPUT ();
		inputs.wVk = (short) untranslateKey (keycode);
		if (inputs.wVk == 0) {
			char key = (char) keycode;
			switch (key) {
				case SWT.BS: inputs.wVk = (short) OS.VK_BACK; break;
				case SWT.CR: inputs.wVk = (short) OS.VK_RETURN; break;
				case SWT.DEL: inputs.wVk = (short) OS.VK_DELETE; break;
				case SWT.ESC: inputs.wVk = (short) OS.VK_ESCAPE; break;
				case SWT.TAB: inputs.wVk = (short) OS.VK_TAB; break;
				/*
				* Since there is no LF key on the keyboard, do not attempt
				* to map LF to CR or attempt to post an LF key.
				*/
//				case SWT.LF: inputs.wVk = (short) OS.VK_RETURN; break;
				case SWT.LF: return false;
				default: {
					if (OS.IsWinCE) {
						inputs.wVk = (short)/*64*/OS.CharUpper ((short) key);
					} else {
						inputs.wVk = OS.VkKeyScan ((short) wcsToMbcs (key, 0));
						if (inputs.wVk == -1) return false;
						inputs.wVk &= 0xFF;
					}
				}
			}
		}
		inputs.dwFlags = type == SWT.KeyUp ? OS.KEYEVENTF_KEYUP : 0;
		int /*long*/ hHeap = OS.GetProcessHeap ();
		int /*long*/ pInputs = OS.HeapAlloc (hHeap, OS.HEAP_ZERO_MEMORY, INPUT.sizeof);
		OS.MoveMemory(pInputs, new int[] {OS.INPUT_KEYBOARD}, 4);
		//TODO - DWORD type of INPUT structure aligned to 8 bytes on 64 bit
		OS.MoveMemory (pInputs + OS.PTR_SIZEOF, inputs, KEYBDINPUT.sizeof);
		boolean result = OS.SendInput (1, pInputs, INPUT.sizeof) != 0;
		OS.HeapFree (hHeap, 0, pInputs);
		return result;
	}
	
	/* Key Mappings */
	private static final int [] [] KeyTable = {
		
		/* Keyboard and Mouse Masks */
		{OS.VK_MENU,	SWT.ALT},
		{OS.VK_SHIFT,	SWT.SHIFT},
		{OS.VK_CONTROL,	SWT.CONTROL},
//		{OS.VK_????,	SWT.COMMAND},

		/* NOT CURRENTLY USED */		
//		{OS.VK_LBUTTON, SWT.BUTTON1},
//		{OS.VK_MBUTTON, SWT.BUTTON3},
//		{OS.VK_RBUTTON, SWT.BUTTON2},
		
		/* Non-Numeric Keypad Keys */
		{OS.VK_UP,		SWT.ARROW_UP},
		{OS.VK_DOWN,	SWT.ARROW_DOWN},
		{OS.VK_LEFT,	SWT.ARROW_LEFT},
		{OS.VK_RIGHT,	SWT.ARROW_RIGHT},
		{OS.VK_PRIOR,	SWT.PAGE_UP},
		{OS.VK_NEXT,	SWT.PAGE_DOWN},
		{OS.VK_HOME,	SWT.HOME},
		{OS.VK_END,		SWT.END},
		{OS.VK_INSERT,	SWT.INSERT},

		/* Virtual and Ascii Keys */
		{OS.VK_BACK,	SWT.BS},
		{OS.VK_RETURN,	SWT.CR},
		{OS.VK_DELETE,	SWT.DEL},
		{OS.VK_ESCAPE,	SWT.ESC},
		{OS.VK_RETURN,	SWT.LF},
		{OS.VK_TAB,		SWT.TAB},
	
		/* Functions Keys */
		{OS.VK_F1,	SWT.F1},
		{OS.VK_F2,	SWT.F2},
		{OS.VK_F3,	SWT.F3},
		{OS.VK_F4,	SWT.F4},
		{OS.VK_F5,	SWT.F5},
		{OS.VK_F6,	SWT.F6},
		{OS.VK_F7,	SWT.F7},
		{OS.VK_F8,	SWT.F8},
		{OS.VK_F9,	SWT.F9},
		{OS.VK_F10,	SWT.F10},
		{OS.VK_F11,	SWT.F11},
		{OS.VK_F12,	SWT.F12},
		{OS.VK_F13,	SWT.F13},
		{OS.VK_F14,	SWT.F14},
		{OS.VK_F15,	SWT.F15},
		
		/* Numeric Keypad Keys */
		{OS.VK_MULTIPLY,	SWT.KEYPAD_MULTIPLY},
		{OS.VK_ADD,			SWT.KEYPAD_ADD},
		{OS.VK_RETURN,		SWT.KEYPAD_CR},
		{OS.VK_SUBTRACT,	SWT.KEYPAD_SUBTRACT},
		{OS.VK_DECIMAL,		SWT.KEYPAD_DECIMAL},
		{OS.VK_DIVIDE,		SWT.KEYPAD_DIVIDE},
		{OS.VK_NUMPAD0,		SWT.KEYPAD_0},
		{OS.VK_NUMPAD1,		SWT.KEYPAD_1},
		{OS.VK_NUMPAD2,		SWT.KEYPAD_2},
		{OS.VK_NUMPAD3,		SWT.KEYPAD_3},
		{OS.VK_NUMPAD4,		SWT.KEYPAD_4},
		{OS.VK_NUMPAD5,		SWT.KEYPAD_5},
		{OS.VK_NUMPAD6,		SWT.KEYPAD_6},
		{OS.VK_NUMPAD7,		SWT.KEYPAD_7},
		{OS.VK_NUMPAD8,		SWT.KEYPAD_8},
		{OS.VK_NUMPAD9,		SWT.KEYPAD_9},
//		{OS.VK_????,		SWT.KEYPAD_EQUAL},

		/* Other keys */
		{OS.VK_CAPITAL,		SWT.CAPS_LOCK},
		{OS.VK_NUMLOCK,		SWT.NUM_LOCK},
		{OS.VK_SCROLL,		SWT.SCROLL_LOCK},
		{OS.VK_PAUSE,		SWT.PAUSE},
		{OS.VK_CANCEL,		SWT.BREAK},
		{OS.VK_SNAPSHOT,	SWT.PRINT_SCREEN},
//		{OS.VK_????,		SWT.HELP},
		
	};
	
	private static int untranslateKey (int key) {
		for (int i=0; i<KeyTable.length; i++) {
			if (KeyTable [i] [1] == key) return KeyTable [i] [0];
		}
		return 0;
	}
	
	/*
	 * Returns a single character, converted from the wide
	 * character set (WCS) used by Java to the specified
	 * multi-byte character set used by the operating system
	 * widgets.
	 *
	 * @param ch the WCS character
	 * @param codePage the code page used to convert the character
	 * @return the MBCS character
	 */
	static int wcsToMbcs (char ch, int codePage) {
		if (OS.IsUnicode) return ch;
		if (ch <= 0x7F) return ch;
		TCHAR buffer = new TCHAR (codePage, ch, false);
		return buffer.tcharAt (0);
	}

}
