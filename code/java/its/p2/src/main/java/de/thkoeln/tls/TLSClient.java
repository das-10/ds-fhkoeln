/**
 * @author sereni Dongmo
 */

package de.thkoeln.tls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class TLSClient {
	
	public static void main(String[] args) 
			throws KeyManagementException, NoSuchAlgorithmException, UnknownHostException, IOException
	{
		
		 String host = "localhost";
		 int port = 443;
		 boolean chat = false;
		 String cipher = null;
		 String keystore = null;
		
		for (int i = 0; i < args.length; i++){
			if ("-host".equals(args[i])) host = args[++i];
			else if ("-port".equals(args[i])) port = Integer.parseInt(args[++i]);
			else if ("-cipher".equals(args[i])) cipher = args[++i];
			else if ("-keystore".equals(args[i])) keystore = args[++i];
			else if ("-chat".equals(args[i])) chat =Boolean.parseBoolean(args[++i]) ;
		}
		
		//assert keystore != null: "Keystore may not be null";
		if (keystore != null){
			System.setProperty("javax.net.ssl.trustStore",keystore);
			System.setProperty("javax.net.ssl.trustStorePassword","P03380rd");
			System.out.println("Keystore set");
		}
		
		SSLSocket sslSocket = createSSlSocket(port, host,cipher);
		startHandshake(sslSocket);
		printSubjectNamesOfServerCertificate(sslSocket);
		if (chat){
			MyTLSReceiver myTLSReceiver= new MyTLSReceiver(sslSocket);
			new Thread(myTLSReceiver).start();
			secureChat(sslSocket);
		}
		sslSocket.close();
		
	} // end main methode
	
	
	public static SSLSocket createSSlSocket(int port, String host,String cipher) 
			throws NoSuchAlgorithmException, KeyManagementException, UnknownHostException, IOException
	{
		String[] suites = {cipher};
		SSLContext sc = SSLContext.getInstance("TLSv1.2");
		sc.init(null,null,null);
		SSLSocketFactory factory = (SSLSocketFactory)sc.getSocketFactory();
		SSLSocket sslSocket = (SSLSocket)factory.createSocket(host, port);
		
		if (cipher != null) sslSocket.setEnabledCipherSuites(suites);
		return sslSocket;
	}
	
	public static void startHandshake(SSLSocket sslSocket) throws IOException{
		
		try {
			sslSocket.startHandshake();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			sslSocket.close();
			}
//		
//		finally{
//			sslSocket.close();
//		}
		
	}
	
	
	public static void printSubjectNamesOfServerCertificate(SSLSocket sslSocket) throws SSLPeerUnverifiedException{
		SSLSession sslSession = sslSocket.getSession();
		X509Certificate[] x509Certificates = (X509Certificate[]) sslSession.getPeerCertificates();
		System.out.println("Protocol:"+sslSession.getProtocol());
		
		for (X509Certificate x509Certificate : x509Certificates) {
			System.out.println(x509Certificate.getSubjectX500Principal().getName());
		}
	}
	
	
	public static void secureChat(SSLSocket sslSocket) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
		
		while (true) {
			String s = in.readLine();
			if (!s.equals("")) {
			out.write(s);
			out.write("\r\n");
			out.flush();
			if (s.equals(".")) break;
			}
		}
		
		in.close();
		out.close();
		//sslSocket.close();
		
	}
	
		

}
