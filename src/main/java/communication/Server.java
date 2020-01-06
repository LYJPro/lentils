package communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
	private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);
	
	public static void main(String[] args) {
		final int DEFAULT_PORT = 8888;
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(DEFAULT_PORT);
			LOGGER.info("启动服务，监听端口：" + DEFAULT_PORT);
			
			while (true) {
				Socket socket = serverSocket.accept();
				LOGGER.info("客户端：[" + socket.getPort() + "]已连接");
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				String msg = null;
				while ((msg = reader.readLine()) != null) {
					LOGGER.info("客户端：" + msg);
					
					writer.write(msg + "\n");
					writer.flush();
					
					if ("quit".equals(msg)) {
						LOGGER.info("客户端：[" + socket.getPort() + "]断开连接");
						break;
					}
				}
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}
			}
		}
	}
}
