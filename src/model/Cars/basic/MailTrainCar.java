package model.Cars.basic;

import model.Cars.TrainCar;

public class MailTrainCar extends TrainCar {
    private String packagesType;
    private int numberOfPackages;

    public MailTrainCar(double weight, String packagesType, int numberOfPackages) {
        super(false, weight);
        this.packagesType = packagesType;
        this.numberOfPackages = numberOfPackages;
    }

    @Override
    public String toString() {
        return super.toString() + "\nRodzaj przewożonych paczek: " + packagesType + "\nLiczba przewożonych paczek: " + numberOfPackages;
    }


}

