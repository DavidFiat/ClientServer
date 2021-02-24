import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;

public class Tests {

	public static void main(String[] args) {

		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
				NetworkInterface netN = interfaces.nextElement();
				if (netN.isUp()) {
					List<InterfaceAddress> list = netN.getInterfaceAddresses();
					for (int i = 0; i < list.size(); i++) {
						// System.out.println(list.get(i));
					}

				}

			}

			InetAddress myAddress = InetAddress.getLocalHost();
			System.out.println(myAddress.getHostName());
			System.out.println(myAddress.getHostAddress());
			NetworkInterface net = NetworkInterface.getByInetAddress(myAddress);
			System.out.println(net.getName());

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
