package slow;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class read implements Runnable{
	int port=10;
	String link,host,header;
	static int length=20;
	public read(String slink,String shost,String  sheader,int sport)
	{
		link =slink;
		host=shost;
		header=sheader;
		port=sport;
	}
	
	public static void main(String[] args) {
		new Thread(new read("127.0.0.1","","GET / HTTP/1.1",7777)).start();;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Socket s=new Socket(link,port);
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			send(out);
			InputStream in1 = s.getInputStream();
			byte[] buff = new byte[length + 3];
			int i;
			while (true) {
				i = in1.read(buff, 3, length);
				if (i > 0) {
					System.out.print(new String(buff, 3, i));
				}
				save(buff, i);
				if (!nend(buff, i))
					break;
				Thread.sleep(100);
			}
			s.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	static boolean nend(byte[] d, int i) {
		i = i + 3;
		if (i < 4)
			return false;
		if (d[i - 4] == '\r' && d[i - 3] == '\n' && d[i - 2] == '\r' && d[i - 1] == '\n')
			return false;
		return true;
	}

	static void save(byte[] buff, int i) {
		if (i > 0) {
			i = i + 3;
			buff[0] = buff[i - 3];
			buff[1] = buff[i - 2];
			buff[2] = buff[i - 1];
		}
	}
	public void send(PrintWriter out) throws IOException {
		out.println(header);
		out.println((new StringBuilder("Host: ")).append(host).toString());
		out.println("Accept: image/webp,image/apng,image/*,*/*;q=0.8");
		out.println("Accept-Encoding: gzip, deflate, br");
		out.println();
		out.flush();
	}
}
