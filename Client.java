import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	Socket socket;
	
	public Client() {
		try {
			socket = new Socket("192.168.10.240", 10004);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setConnect(){
		// ���� �����忡�� ���������� ���� Ű���� �Է°��� ������ �����Ѵ�
		new Thread(){
			public void run(){
				Scanner scanner = new Scanner(System.in);
				try {
					while(true){
						String word = scanner.nextLine() + "\r\n"; // �Է��� ����ϰ� �ִٰ� enter Ű�� �ԷµǸ�
						OutputStream os = socket.getOutputStream();
						os.write(word.getBytes());	// stream �� ����
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

}
