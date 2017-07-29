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
	List<Socket> clients;		// 1 : 다 이기 때문에 배열에 담아둔다
	
	// 1. 서버 생성
	public Server(int port){
		try {
			serverSocket = new ServerSocket(port);
			clients = new ArrayList<>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 2. 서버 동작
	public void precess(){
		
		while(true){
			// 3. 서브 thread 에서 socket 을 열고 대기
			// 마치 스캐너처럼 여기서 반응이 올 때까지 멈춰있는다.
			Socket socket;
			try {
				socket = serverSocket.accept();
				subProcess(socket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			// 4. 소켓에 연결 요청이 들어오면 소켓을열고 저장소에 담아둔다.
			
		}
		// 5. 소켓을 통해 데이터가 들어오면 화면에 출력
		

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
				} // 스트림을 열고 뎅터 통신을 준비
				BufferedReader br = new BufferedReader(new InputStreamReader(os)); // 글자를 출력할 수 있는 
				// 줄 단위로 화면에 출력
				while(true){
					String message = "";
					try {
						message = br.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(socket.getInetAddress() + " 메시지 : " +message);
				}
			}
		}.start();
	}
	
	
	
	
	
	
	
}
