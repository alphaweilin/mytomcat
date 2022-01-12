package cn.william.mytomcatv1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {

	public static String WEB_ROOT = System.getProperty("user.dir") + "\\" + "WebContent";
	private static String url = "";

	public static void main(String[] args) throws Exception {
//		System.out.println(WEB_ROOT);
		ServerSocket serverSocket = null;
		Socket socket = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			serverSocket = new ServerSocket(8080);
			while (true) {
				// get client socket
				socket = serverSocket.accept();
				inputStream = socket.getInputStream();
				outputStream = socket.getOutputStream();

				parse(inputStream);

				sendStaticResource(outputStream);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				outputStream.close();
				outputStream = null;
			}
			if (inputStream != null) {
				inputStream.close();
				inputStream = null;
			}
			if (socket != null) {
				socket.close();
				socket = null;
			}
		}
	}

	private static void parse(InputStream inputStream) throws IOException {
		StringBuffer content = new StringBuffer();
		byte[] buffer = new byte[2048];
		int i = -1;
		i = inputStream.read(buffer);
		for (int j = 0; j < i; j++) {
			content.append((char) buffer[j]);
		}
		System.out.println(content);
		parseUrl(content.toString());
	}

	private static void parseUrl(String content) {
		int index1, index2;
		index1 = content.indexOf(" ");
		if (index1 != -1) {
			index2 = content.indexOf(" ", index1 + 1);
			if (index2 > index1) {
				url = content.substring(index1 + 2, index2);
			}

		}
		System.out.println(url);

	}

	private static void sendStaticResource(OutputStream outputStream) throws Exception {
		byte[] bytes = new byte[2048];
		FileInputStream fileInputStream = null;

		try {
			File file = new File(WEB_ROOT, url);
			if (file.exists()) {
				outputStream.write("HTTP/1.1 200 OK\n".getBytes());
				outputStream.write("Content-Type:text/html;charset=utf-8\n".getBytes());
				outputStream.write("Server:Apache-Coyote/1.1\n".getBytes());
				outputStream.write("\n".getBytes());

				fileInputStream = new FileInputStream(file);
				int ch = fileInputStream.read(bytes);
				while (ch != -1) {
					outputStream.write(bytes, 0, ch);
					ch = fileInputStream.read(bytes);
				}
			} else {
				outputStream.write("HTTP/1.1 404 Not found\n".getBytes());
				outputStream.write("Content-Type:text/html;charset=utf-8\n".getBytes());
				outputStream.write("Server:Apache-Coyote/1.1\n".getBytes());
				outputStream.write("\n".getBytes());

				String errorMsg = "file not found";
				outputStream.write(errorMsg.getBytes());
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (null != fileInputStream) {
				fileInputStream.close();
				fileInputStream = null;
			}
		}

	}

}
