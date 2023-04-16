package model.Cars.industrial;

import model.Cars.TrainCar;

abstract public class FreightTrainCar extends TrainCar {
    private double cargoWeight;
    private String cargoType;

    public FreightTrainCar(double weight, boolean isNeededElectricalConnection, String cargoType) {
        super(isNeededElectricalConnection, weight);
        this.cargoType = cargoType;
        this.cargoWeight = 0;
    }

    @Override
    public String toString() {
        return super.toString() + "\nRodzaj przewożonego ładunku: " + cargoType + "\nWaga ładunku: " + cargoWeight + " kg";
    }
}
