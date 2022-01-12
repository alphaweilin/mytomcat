package cn.william.mytomcatv2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AAServlet implements Servlet {

	@Override
	public void init() {
		System.out.println("aa...init.....");

	}

	@Override
	public void service(InputStream inputStream, OutputStream outputStream) throws IOException {
		System.out.println("AA service...");
		outputStream.write("I am from Server...AA".getBytes());
		outputStream.flush();
	}

	@Override
	public void destory() {
		System.out.println("aa...destory.....");
	}

}
