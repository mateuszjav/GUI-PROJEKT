package model.Cars.industrial;

public class BasicFreightTrainCar extends FreightTrainCar {
    private boolean isAntiTheftProtected;
    private static final double maxCargoWeight = 15000;

    public BasicFreightTrainCar(double weight, boolean isNeededElectricalConnection, String cargoType, boolean isAntiTheftProtected) {
        super(weight, isNeededElectricalConnection, cargoType);
        this.isAntiTheftProtected = isAntiTheftProtected;
    }

    @Override
    public String toString() {
        return super.toString() + "\nMa zabezpieczenie towaru przed kradieżą: " + isAntiTheftProtected;
    }
}
