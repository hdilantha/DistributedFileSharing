import java.io.IOException;
import java.net.*;


public class BootstrapClient {
    private DatagramSocket socket;
    private InetAddress destinationAddress;
    private int serverPort = Constants.BS_SERVER_PORT;

    private byte[] buff;

    public BootstrapClient() {
        try {
            this.socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            this.destinationAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void register(String ipAddress, int port, String username) {
        String regMessage = String.format(Constants.REG_FORMAT, ipAddress, port, username);
        regMessage = String.format(Constants.MSG_FORMAT, regMessage.length() + 5, regMessage);
//        System.out.println(regMessage);
        this.buff = regMessage.getBytes();
        DatagramPacket packet = new DatagramPacket(buff, buff.length, destinationAddress, serverPort);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        packet = new DatagramPacket(buff, buff.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String received = new String(packet.getData(), 0, packet.getLength());

        System.out.println(received);
    }

    public InetAddress getDestinationAddress() {
        return destinationAddress;
    }

    public DatagramSocket getSocket() {
        return socket;
    }
}

//    public void sendEcho(String msg){
//        this.buff = msg.getBytes();
//        DatagramPacket packet = new DatagramPacket(buff, buff.length, destinationAddress, serverPort);
//        try {
//            socket.send(packet);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        packet = new DatagramPacket(buff, buff.length);
//        try {
//            socket.receive(packet);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String received = new String(packet.getData(), 0, packet.getLength());
//
//        System.out.println(received);
//
//    }