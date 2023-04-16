package model.Cars;

import logic.RailwaySystemManager;
import model.Exceptions.RailoradHazard;
import model.Railway.Station;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Locomotive implements Runnable {
    private int id;
    private String name;
    private int maxNumbersOfTrainCars;
    private double maxLoadWeight;
    private int maxNumberOfElectricTrainCars;
    private double speed;
    private static int locomotiveCount = 0;

    private Station homeStation;
    private Station currentStation;
    private Station destinationStation;

    private double distanceTraveledToNext;

    private List<Station> path;

    private static final Object block = new Object();
    private final Object monitor = new Object();

    public Locomotive(String name, int maxNumbersOfTrainCars, double maxLoadWeight, int maxNumberOfElectricTrainCars, double speed) {
        if (maxNumberOfElectricTrainCars > maxNumbersOfTrainCars) {
            throw new IllegalArgumentException("Maksymalna liczba wagonów elektrycznych nie może być większa od całkowitej możliwej liczby ciągniętych wagonów");
        }
        this.maxNumbersOfTrainCars = maxNumbersOfTrainCars;
        this.maxLoadWeight = maxLoadWeight;
        this.maxNumberOfElectricTrainCars = maxNumberOfElectricTrainCars;
        this.speed = speed;
        this.name = name;
        this.id = ++locomotiveCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxNumbersOfTrainCars() {
        return maxNumbersOfTrainCars;
    }

    public void setMaxNumbersOfTrainCars(int maxNumbersOfTrainCars) {
        this.maxNumbersOfTrainCars = maxNumbersOfTrainCars;
    }

    public double getMaxLoadWeight() {
        return maxLoadWeight;
    }

    public void setMaxLoadWeight(double maxLoadWeight) {
        this.maxLoadWeight = maxLoadWeight;
    }

    public int getMaxNumberOfElectricTrainCars() {
        return maxNumberOfElectricTrainCars;
    }

    public void setMaxNumberOfElectricTrainCars(int maxNumberOfElectricTrainCars) {
        this.maxNumberOfElectricTrainCars = maxNumberOfElectricTrainCars;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Station getHomeStation() {
        return homeStation;
    }

    public void setHomeStation(Station homeStation) {
        this.homeStation = homeStation;
    }

    public Station getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(Station currentStation) {
        this.currentStation = currentStation;
    }

    public Station getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(Station destinationStation) {
        this.destinationStation = destinationStation;
    }

    public double getDistanceTraveledToNext() {
        return distanceTraveledToNext;
    }

    public void setDistanceTraveledToNext(double distanceTraveledToNext) {
        this.distanceTraveledToNext = distanceTraveledToNext;
    }

    public String getLocationInfo() {
        return "Stacja macierzysta: " + homeStation +
                "\nStacja aktualna: " + currentStation +
                "\nStacja docelowa: " + destinationStation;
    }

    public List<Station> getPath() {
        return path;
    }

    public void setPath(List<Station> path) {
        this.path = path;
    }

    public void addPath(List<Station> path) {
        this.path = path;
        homeStation = path.get(0);
        destinationStation = path.get(path.size() - 1);
    }

    private void reversePath() {
        Collections.reverse(path);
        homeStation = path.get(0);
        currentStation = path.get(0);
        destinationStation = path.get(path.size() - 1);
    }

    @Override
    public String toString() {
        return "Lokomotywa " + name + " ID: " + id +
                "\nMaksymalna liczba wagonów: " + maxNumbersOfTrainCars +
                "\nMaksymalna waga ładunku: " + maxLoadWeight +
                "\nMaksymalna liczba wagonow elektrycznych" + maxNumberOfElectricTrainCars + "\n";
    }

    @Override
    public synchronized void run() {
        currentStation = path.get(0);

        while (true) {
            Station nextStation = null;
            nextStation = path.get(path.indexOf(currentStation) + 1);


            try {
                synchronized (block) {
                    while (RailwaySystemManager.isConnectionOccupated(currentStation.getName(), nextStation.getName(), name)) {
                        String result = "";
                        for (String[] occupatedConnection : RailwaySystemManager.occupatedConnections) {
                            result+= occupatedConnection[0] + occupatedConnection[1];
                            result+=" ";
                        }
//                        System.out.println(name + " mam zajete polaczenienia " + result + " to czekam");
                        block.wait();
                    }
                    RailwaySystemManager.addOccupatedConnection(currentStation.getName(), nextStation.getName());
                }

                double distanceToNext = currentStation.getConnections().get(nextStation);
                double distanceTraveled = 0;
                Random random = new Random();
                while (distanceTraveled < distanceToNext) {
                    boolean speedUp = random.nextBoolean();
                    if (speedUp) {
                        speed = speed + speed * 0.03;
                        if(speed > 300){
                            throw new RailoradHazard(this);
                        }
                    } else {
                        speed = speed - speed * 0.03;
                    }

                    if(distanceTraveled + (speed / 3600) > distanceToNext){
                        distanceTraveled += distanceToNext - distanceTraveled;
                    } else {
                        distanceTraveled += (speed / 3600);
                    }

                    distanceTraveledToNext = distanceTraveled;

                    synchronized (monitor) {
                        monitor.wait(1000);
                    }

//                    System.out.println(name + " Pokonana trasa " + String.format("%.2f", distanceTraveled) +
//                            " km do stacji " + nextStation + " Trasa: " + currentStation.getName() + nextStation.getName() + " Stacja poczatkowa: " + homeStation + "; Stacja koncowa: " + destinationStation);
                }

                synchronized (block) {
                    RailwaySystemManager.removeOccupatedFromList(currentStation.getName(), nextStation.getName());
                    block.notifyAll();
                    synchronized (monitor) {
                        monitor.wait(2000);
                    }
                }


                currentStation = nextStation;
                if (currentStation == destinationStation) {
                    reversePath();
                    distanceTraveledToNext = 0;
                    synchronized (block){
                        RailwaySystemManager.removeOccupatedFromList(currentStation.getName(), nextStation.getName());
                        block.notifyAll();
                        System.out.println("czekam bo skoncyzlem trase " + name);
                        synchronized (monitor) {
                            monitor.wait(30000);
                        }
                    }
                }

            } catch (InterruptedException | RailoradHazard e) {
                System.out.println(e);
                synchronized (block) {
                    RailwaySystemManager.removeOccupatedFromList(currentStation.getName(), nextStation.getName());
                    block.notifyAll();
                }
                break;

            }

            synchronized (block) {
                block.notifyAll();
            }


        }
    }

}
