package cn.william.mytomcatv2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BBServlet implements Servlet {

	@Override
	public void init() {
		System.out.println("bb...init.....");

	}

	@Override
	public void service(InputStream inputStream, OutputStream outputStream) throws IOException {
		System.out.println("BB service...");
		outputStream.write("I am from Server...BB".getBytes());
		outputStream.flush();
	}

	@Override
	public void destory() {
		System.out.println("bb...destory.....");
	}

}
