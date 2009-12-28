package nativespider.messaging;

public class MessageAdapter implements IMessageAdapter {
	public void sendMessge(int messageType,byte[] data)
	{
		;
	}
	
	public void sendMessge(int messageType,String data)
	{
		sendMessge(messageType,data.getBytes());
	}
}
