package ds.core;

import ds.core.Neighbour;

import java.util.ArrayList;

public class RoutingTable {
    private ArrayList<Neighbour> neighbours;
    private String localAddress;
    private int localPort;

    public RoutingTable(String localAddress, int localPort) {
        this.localAddress = localAddress;
        this.localPort = localPort;
        this.neighbours = new ArrayList<Neighbour>();
    }
}
