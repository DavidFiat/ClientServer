import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Calendar;

public class Server {

	public static void main(String[] args) {

		try {
			while (true) {
				@SuppressWarnings("resource")
				ServerSocket server = new ServerSocket(5003);
				System.out.println("Waiting conection");
				Socket socket = server.accept();
				System.out.println("Connected.");
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

				String option = br.readLine();

				if (option.equals("remoteIpconfig")) {
					InetAddress localHost = InetAddress.getLocalHost();
					String local = localHost.getHostAddress();
					bw.write(local);
				} else if (option.equals("interface")) {

					InetAddress address = socket.getInetAddress();
					NetworkInterface net = NetworkInterface.getByInetAddress(address);
					bw.write(net.getName());

				} else if (option.equals("whatTimeIsIt")) {
					Calendar c = Calendar.getInstance();
					bw.write(c.getTime().toString());

				} else if (option.length() == 1024) {
					bw.write(option);

				} else if (option.length() == 8192) {
					bw.write(option);
				}

				bw.flush();
				socket.close();
			}

		} catch (

		IOException e) {
			e.printStackTrace();
		}

	}

}
