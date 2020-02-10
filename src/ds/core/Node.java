package ds.core;

import ds.communication.BootstrapClient;
import ds.utils.PropertiesFile;
import ds.communication.ftp.FTPClient;
import ds.communication.ftp.FTPServer;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class Node {
    private String ip;
    private int port;
    private int ftpServerPort;
    private String username;
    private String[] fileList;
    private int fileCount;
    private ArrayList<Neighbour> neighbourList;
    private BootstrapClient bsClient;
    private FTPServer ftpServer;

    public Node(String username) throws Exception {

        try {
            DatagramSocket socket = new DatagramSocket();
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            this.ip = InetAddress.getByName("localhost").getHostAddress();
//            System.out.println(this.ip);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Could not find the host address");
        }

        this.port = getFreeport();
        this.ftpServerPort = getFreeport();
        this.username = username;
        this.fileCount = randomInteger(3, 5);
        this.fileList = new String[this.fileCount];
        this.bsClient = new BootstrapClient();
        FileManager fileManager = new FileManager(this.username);
        this.ftpServer = new FTPServer(this.ftpServerPort, this.username);
        Thread t = new Thread(ftpServer);
        t.start();

        fileManager.initializeFolder();
        String[] allFiles = PropertiesFile.getNodeProperty("files").split(",");
        for (int i = 0; i < this.fileCount; i++) {
            while (true) {
                String temp = allFiles[randomInteger(0, allFiles.length - 1)];
                if (!Arrays.asList(this.fileList).contains(temp)) {
                    this.fileList[i] = temp;
                    fileManager.createFile(temp);
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

    public int getFtpServerPort() {
        return ftpServerPort;
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

    public void getFile(String fileName, String address, int port) {
        try {
            System.out.println("The file you requested is " + fileName);
            FTPClient ftpClient = new FTPClient(address, port, fileName);

            System.out.println("Waiting for file download...");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
