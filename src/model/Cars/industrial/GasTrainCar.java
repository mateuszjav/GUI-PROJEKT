package model.Cars.industrial;

public class GasTrainCar extends BasicFreightTrainCar {
    private String gasType;
    private double amountOfGas;

    public GasTrainCar(double weight, boolean isAntiTheftProtected, String gasType, double amountOfGas) {
        super(weight, false, "Gaz", isAntiTheftProtected);
        this.gasType = gasType;
        this.amountOfGas = amountOfGas;
    }

    @Override
    public String toString() {
        return super.toString() + "\nPrzewozony gaz: " + gasType + "\nIlosc gazu " + amountOfGas + "m3";
    }
}
