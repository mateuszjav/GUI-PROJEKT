package model.Cars.basic;

import model.Cars.TrainCar;

public class BaggageTrainCar extends TrainCar {
    private int numberOfSpacesForBaggage;
    private double maxBaggageWeight;

    public BaggageTrainCar(double weight, int numberOfSpacesForBaggage, double maxBaggageWeight) {
        super(false, weight);
        this.numberOfSpacesForBaggage = numberOfSpacesForBaggage;
        this.maxBaggageWeight = maxBaggageWeight;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLiczba miejsc na bagaże: " + numberOfSpacesForBaggage +
                "\nMaksymalny ciężar pojedynczego bagażu: " + maxBaggageWeight;
    }
}
