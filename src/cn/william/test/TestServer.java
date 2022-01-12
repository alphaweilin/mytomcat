package cn.william.test;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = null;
		Socket socket = null;
		OutputStream outputStream = null;

		try {
			serverSocket = new ServerSocket(8080);
			while (true) {

				socket = serverSocket.accept();

				outputStream = socket.getOutputStream();

				outputStream.write("HTTP/1.1 200 OK\n".getBytes());
				outputStream.write("Content-Type:text/html;charset=utf-8\n".getBytes());
				outputStream.write("Server:Apache-Coyote/1.1\n".getBytes());
				outputStream.write("\n\n".getBytes());

				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append("<html>");
				stringBuffer.append("<head><title>this is title</title></head>");
				stringBuffer.append("<body>");
				stringBuffer.append("<h1>I am header</h1>");
				stringBuffer.append("<a href='https://www.baidu.com'>baidu</a>");
				stringBuffer.append("</body>");
				stringBuffer.append("</html>");

				outputStream.write(stringBuffer.toString().getBytes());

				outputStream.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				outputStream.close();
				outputStream = null;
			}
			if (socket != null) {
				socket.close();
				socket = null;
			}
		}

	}

}
