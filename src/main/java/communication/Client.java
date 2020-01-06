package communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
	private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);
	
	public static void main(String[] args) {
		final String DEFAULT_SERVER_HOST = "127.0.0.1";
		final int DEFAULT_SERVER_PORT = 8888;
		Socket socket = null;
		
		BufferedWriter writer = null;
		
		try {
			socket = new Socket(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
			// 控制台输入内容
			while (true) {
				String sendMsg = consoleReader.readLine();
				
				// 发送内容
				writer.write(sendMsg + "\n");
				writer.flush();
				
				String receiveMsg = reader.readLine();
				LOGGER.info("服务器：" + receiveMsg);
				
				if ("quit".equals(sendMsg)) {
					break;
				}
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} finally {
			if (socket != null) {
				try {
					writer.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}
			}
		}
	}
}
