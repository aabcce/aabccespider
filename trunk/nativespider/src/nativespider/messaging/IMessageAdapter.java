package nativespider.messaging;

public interface IMessageAdapter {
	
	void sendMessge(int type,byte[] data);
	
	void sendMessge(int type,String data);
}
