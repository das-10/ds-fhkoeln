/**
 * @author sereni Dongmo
 */

package de.thkoeln.tls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.net.ssl.SSLSocket;

public class MyTLSReceiver implements Runnable {
	
	private SSLSocket sslSocket = null;
	private BufferedReader sslin = null;
	
	public MyTLSReceiver(SSLSocket sslSocket) throws IOException{
		this.sslSocket = sslSocket;
		this.sslin = new BufferedReader(new InputStreamReader(this.sslSocket.getInputStream()));
	}

	public void run() {
		while (true) {
			try {
				String s = this.sslin.readLine();
				System.out.println();
			} catch (IOException soe) {
				// TODO Auto-generated catch block
				System.out.println(soe.getMessage());
				System.exit(0);
			}
			
			
		}

	}

}
