import ds.core.Node;

public class main {
    public static void main (String args[]) throws Exception {
//        ds.core.Node node1 = new ds.core.Node("23", 43, "guna");
//        int fileCount = node1.getFileCount();
//        System.out.println(fileCount);
//
//        for(int i = 0; i< fileCount; i++){
//            System.out.println(node1.getFileList()[i]);
//        }

        Node node0 = new Node("1501960");
        node0.registerToBSServer();
        Node node1 = new Node("1501961");
        node1.registerToBSServer();
        Node node2 = new Node("1501962");
        node2.registerToBSServer();

        Thread.sleep(2000);                       // remove this if you are sure that all the files are created before the download

        String testFile = node2.getFileList()[0];       // for testing - remove
        node1.getFile(testFile, node2.getIp(), node2.getFtpServerPort());
    }
}
