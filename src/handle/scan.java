package handle;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class scan {
	public static void main(String[]  a)
	{
		try {
			chosts("192.168.1");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static   void chosts(String subnet) throws UnknownHostException, IOException{
		   int timeout=100;
		   for (int i=1;i<255;i++){
		       String host=subnet + "." + i;
		       if (InetAddress.getByName(host).isReachable(timeout)){
		           System.out.println(host + " is reachable");
		       }
		   }
		}
}
