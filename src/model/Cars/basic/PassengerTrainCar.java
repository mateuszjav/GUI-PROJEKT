package model.Cars.basic;

import model.Cars.TrainCar;

public class PassengerTrainCar extends TrainCar {
    private int numberOfSeats;
    private int numberOfCompartment;
    private boolean hasToilet;
    private int numberOfPeople;

    public PassengerTrainCar(double weight, int numberOfSeats, int numberOfCompartment, boolean hasToilet) {
        super(true, weight);
        this.numberOfSeats = numberOfSeats;
        this.numberOfCompartment = numberOfCompartment;
        this.hasToilet = hasToilet;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLiczba miejsc siedzących: " + numberOfSeats +
                "\nLiczba przedziałow w wagonie: " + numberOfCompartment +
                "\nPosiada toalete: " + hasToilet;
    }


}
