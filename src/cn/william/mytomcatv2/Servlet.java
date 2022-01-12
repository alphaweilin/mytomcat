package cn.william.mytomcatv2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Servlet {
	void init();

	void service(InputStream inputStream, OutputStream outputStream) throws IOException;

	void destory();
}
