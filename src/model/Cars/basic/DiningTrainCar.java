package model.Cars.basic;

import model.Cars.TrainCar;

public class DiningTrainCar extends TrainCar {
    private int numberOfTables;
    private String cuisine;
    private int numberOfWaiters;

    public DiningTrainCar(double weight, int numberOfTables, String cuisine, int numberOfWaiters) {
        super(true, weight);
        this.numberOfTables = numberOfTables;
        this.cuisine = cuisine;
        this.numberOfWaiters = numberOfWaiters;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLiczba stolikow: " + numberOfTables + "\nKuchnia: " + cuisine + "\nLiczba kelnerow: " + numberOfWaiters;
    }
}
