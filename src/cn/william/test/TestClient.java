package cn.william.test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TestClient {

	public static void main(String[] args) throws Exception {
		Socket socket = null;
		InputStream is = null;
		OutputStream ops = null;

		try {
			socket = new Socket("www.baidu.com", 80);
			is = socket.getInputStream();
			ops = socket.getOutputStream();

			ops.write("GET /index.html HTTP/1.1\n".getBytes());
			ops.write("HOST:www.baidu.com\n".getBytes());
			ops.write("\n".getBytes());

			int i = is.read();
			while (i != -1) {
				System.out.print((char) i);
				i = is.read();
			}
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				is.close();
				is = null;
			}
			if (ops != null) {
				ops.close();
				ops = null;
			}
			if (socket != null) {
				socket.close();
				socket = null;
			}
		}

	}

}
