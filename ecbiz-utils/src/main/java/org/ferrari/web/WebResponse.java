package org.ferrari.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class WebResponse extends HttpServletResponseWrapper {

	private PrintWriter writer = new PrintWriter(new WebOutputStream());
	private OutputStream outputStream = null;

	public WebResponse(HttpServletResponse response) {
		super(response);
	}

	public WebResponse(HttpServletResponse response, OutputStream outputStream) {
		super(response);
		this.outputStream = outputStream;
	}

	public PrintWriter getWriter() throws IOException {
		return writer;
	}

	private ServletOutputStream getSuperStream() throws IOException {
		return super.getOutputStream();
	}

	class WebOutputStream extends ServletOutputStream {

		public void close() throws IOException {
			getSuperStream().close();
			if (outputStream != null)
				outputStream.close();
		}

		public void flush() throws IOException {
			getSuperStream().flush();
			if (outputStream != null)
				outputStream.flush();
		}

		public void write(int b) throws IOException {
			getSuperStream().write(b);
			if (outputStream != null)
				outputStream.write((byte)b);
		}

	}

}
