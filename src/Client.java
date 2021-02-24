import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {

		while (true) {
			System.out.println("Command List:");
			System.out.println("remoteIpconfig");
			System.out.println("interface");
			System.out.println("whatTimeIsIt");
			System.out.println("RTT");
			System.out.println("speed");
			Scanner reader = new Scanner(System.in);
			String op = reader.nextLine();

			if (op.equals("RTT")) {
				String mess = new String(new byte[1023]);
				mess += "\n";
				long t1 = System.currentTimeMillis();
				connect(mess);
				long t2 = System.currentTimeMillis();
				double rtt = (t2 - t1) / 1000;
				System.out.println(rtt);
			} else if (op.equals("speed")) {
				String mess = new String(new byte[8191]);
				mess += "\n";
				long t1 = System.currentTimeMillis();
				connect(mess);
				long t2 = System.currentTimeMillis();
				double rtt = (t2 - t1) / 1000;
				double kBytes = mess.length();
				System.out.println(kBytes / rtt);

			} else {
				connect(op);

			}
		}
	}

	private static void connect(String message) {

		try {
			System.out.println("Sending request...");
			Socket socket = new Socket("127.0.0.1", 5003);
			System.out.println("Connected...");
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw.write(message);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw.flush();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
