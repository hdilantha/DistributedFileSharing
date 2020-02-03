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

    public Node(int port, String username) {
        this.ip = ;
        this.port = port;
        this.username = username;
        this.fileCount = randomInteger(3,5);
        this.fileList = new String[this.fileCount];
        this.bsClient = new BootstrapClient();
        String[] allFiles = PropertiesFile.getNodeProperty("files").split(",");
        for (int i = 0; i < this.fileCount; i++){
            while (true){
                String temp = allFiles[randomInteger(0,allFiles.length-1)];
                if (!Arrays.asList(this.fileList).contains(temp)){
                    this.fileList[i] = temp;
                    break;
                }
            }

        }

    }

    public void registerToBSServer(){
        bsClient.register(this.ip, this.port, this.username);
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
