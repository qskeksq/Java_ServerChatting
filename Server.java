import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	
	ServerSocket serverSocket;
	List<Socket> clients;		// 1 : �� �̱� ������ �迭�� ��Ƶд�
	
	// 1. ���� ����
	public Server(int port){
		try {
			serverSocket = new ServerSocket(port);
			clients = new ArrayList<>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 2. ���� ����
	public void precess(){
		
		while(true){
			// 3. ���� thread ���� socket �� ���� ���
			// ��ġ ��ĳ��ó�� ���⼭ ������ �� ������ �����ִ´�.
			Socket socket;
			try {
				socket = serverSocket.accept();
				subProcess(socket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			// 4. ���Ͽ� ���� ��û�� ������ ���������� ����ҿ� ��Ƶд�.
			
		}
		// 5. ������ ���� �����Ͱ� ������ ȭ�鿡 ���
		

	}
	
	private void subProcess(Socket socket){
		new Thread(){
			public void run(){
				InputStream os = null;
				try {
					os = socket.getInputStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // ��Ʈ���� ���� ���� ����� �غ�
				BufferedReader br = new BufferedReader(new InputStreamReader(os)); // ���ڸ� ����� �� �ִ� 
				// �� ������ ȭ�鿡 ���
				while(true){
					String message = "";
					try {
						message = br.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(socket.getInetAddress() + " �޽��� : " +message);
				}
			}
		}.start();
	}
	
	
	
	
	
	
	
}
