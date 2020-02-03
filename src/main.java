import ds.core.Node;

public class main {
    public static void main (String args[]){
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
        Node node3 = new Node("1501963");
        node3.registerToBSServer();
        Node node4 = new Node("1501964");
        node4.registerToBSServer();
        Node node5 = new Node("1501965");
        node5.registerToBSServer();
        node0.echoBSServer();
        node0.unregisterFromBSServer();



    }
}
