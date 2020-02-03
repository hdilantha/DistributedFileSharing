package ds.core;

import ds.communication.BootstrapClient;
import ds.utils.PropertiesFile;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class Node {
    private String ip;
    private int port;
    private String username;
    private String[] fileList;
    private int fileCount;
    private ArrayList<Neighbour> neighbourList;
    private BootstrapClient bsClient;

    public Node(String username) {

        try {
            DatagramSocket socket = new DatagramSocket();
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            this.ip = InetAddress.getByName("localhost").getHostAddress();
//            System.out.println(ip);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Could not find the host address");
        }

        this.port = getFreeport();
        this.username = username;
        this.fileCount = randomInteger(3, 5);
        this.fileList = new String[this.fileCount];
        this.bsClient = new BootstrapClient();
        String[] allFiles = PropertiesFile.getNodeProperty("files").split(",");
        for (int i = 0; i < this.fileCount; i++) {
            while (true) {
                String temp = allFiles[randomInteger(0, allFiles.length - 1)];
                if (!Arrays.asList(this.fileList).contains(temp)) {
                    this.fileList[i] = temp;
                    break;
                }
            }

        }

    }

    //a socket for TCP/IP connection
    public int getFreeport() {
        try {
            ServerSocket socket = new ServerSocket(0);
            socket.setReuseAddress(true);
            int port = socket.getLocalPort();
            socket.close();
            return port;
        } catch (IOException e) {
            throw new RuntimeException("Could not find a free port. Process failed");
        }
    }

    public void registerToBSServer() {
        bsClient.register(this.ip, this.port, this.username);
    }

    public void unregisterFromBSServer(){
        bsClient.unregister(this.ip, this.port, this.username);
    }

    public void echoBSServer(){
        bsClient.echo(this.ip, this.port, this.username);
    }
    //creates a random number between min and max (including min and max)
    private int randomInteger(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String[] getFileList() {
        return fileList;
    }

    public int getFileCount() {
        return fileCount;
    }


}
