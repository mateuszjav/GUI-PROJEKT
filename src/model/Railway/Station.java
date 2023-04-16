package model.Railway;

import java.util.HashMap;

public class Station {
    private String name;
    private HashMap<Station, Double> connections = new HashMap<>();

    public void addConnection(Station station, double distance){
        connections.put(station, distance);
        station.connections.put(this, distance);
    }

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public HashMap<Station, Double> getConnections() {
        return connections;
    }

    @Override
    public String toString() {
        return name;
    }
}
