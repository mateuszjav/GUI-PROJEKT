package logic;

import model.Railway.Station;

import java.util.*;

public class RailwaySystemManager {
    public static Map<String, Station> stations = new HashMap<>();

    public static Set<String[]> occupatedConnections = new HashSet<>();

    public void addStation(String stationName){
        stations.put(stationName, new Station(stationName));
    }

    public void addConnection(String station1, String station2, double distance){
        Station s1 = stations.get(station1);
        Station s2 = stations.get(station2);
        s1.addConnection(s2, distance);

    }

    static public List<Station> getPath(String start, String end){

        Map<Station, Double> distances = new HashMap<>();
        Map<Station, Station> previousStations = new HashMap<>();
        PriorityQueue<Station> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        Set<Station> visited = new HashSet<>();

        for (Station station : stations.values()) {
            distances.put(station, Double.POSITIVE_INFINITY);
        }
        distances.put(stations.get(start), 0.0);
        queue.offer(stations.get(start));

        while (!queue.isEmpty()) {
            Station current = queue.poll();
            visited.add(current);

            if (current.getName().equals(end)) {
                break;
            }

            for (Map.Entry<Station, Double> entry : current.getConnections().entrySet()) {
                Station neighbor = entry.getKey();
                double distanceToNeighbor = entry.getValue();

                if (visited.contains(neighbor)) {
                    continue;
                }

                double tentativeDistance = distances.get(current) + distanceToNeighbor;

                if (tentativeDistance < distances.get(neighbor)) {
                    distances.put(neighbor, tentativeDistance);
                    previousStations.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }

        if (distances.get(stations.get(end)) == Double.POSITIVE_INFINITY) {
            return null;
        }

        List<Station> path = new ArrayList<>();
        Station current = stations.get(end);
        while (!current.equals(stations.get(start))) {
            path.add(0, current);
            current = previousStations.get(current);
        }
        path.add(0, stations.get(start));

        return path;

    }
    public synchronized static void addOccupatedConnection(String station1, String station2){
        String[] connection = {station1, station2};
        occupatedConnections.add(connection);
    }

    public synchronized static boolean isConnectionOccupated(String station1, String station2, String name){
        Random random = new Random();
        for (String[] occupatedConnection : occupatedConnections) {
            if(occupatedConnection[0].equals(station1)){
                if(occupatedConnection[1].equals(station2)){
                    return true;
                }
            } else {
                if(occupatedConnection[0].equals(station2)){
                    if(occupatedConnection[1].equals(station1)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public synchronized static void removeOccupatedFromList(String station1, String station2) {
        String[] toRemove = null;
        for (String[] occupatedConnection : occupatedConnections) {
            if (occupatedConnection[0].equals(station1)) {
                if (occupatedConnection[1].equals(station2)) {
                    toRemove = occupatedConnection;
                }
            } else {
                if (occupatedConnection[0].equals(station2)) {
                    if (occupatedConnection[1].equals(station1)) {
                        toRemove = occupatedConnection;
                    }
                }
            }
        }
        occupatedConnections.remove(toRemove);
    }
}
