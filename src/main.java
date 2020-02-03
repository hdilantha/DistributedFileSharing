public class main {
    public static void main (String args[]){
//        Node node1 = new Node("23", 43, "guna");
//        int fileCount = node1.getFileCount();
//        System.out.println(fileCount);
//
//        for(int i = 0; i< fileCount; i++){
//            System.out.println(node1.getFileList()[i]);
//        }

        BootstrapClient client = new BootstrapClient();
        client.register(client.getDestinationAddress().getHostAddress(), client.getSocket().getLocalPort(), "150196B");
//        System.out.println(client.getSocket().getLocalAddress());
//        System.out.println(client.getDestinationAddress().toString());



    }
}
