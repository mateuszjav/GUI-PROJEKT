package model.Cars.industrial;

public class RefrigeratedTrainCar extends BasicFreightTrainCar{
    private double powerConsumptionPerHour;
    private double coolingTemperature;

    public RefrigeratedTrainCar(double weight, String cargoType, double powerConsumptionPerHour, double coolingTemperature) {
        super(weight, true, cargoType, true);
        this.powerConsumptionPerHour = powerConsumptionPerHour;
        this.coolingTemperature = coolingTemperature;
    }

    @Override
    public String toString() {
        return super.toString() + "\nPotrzebna moc: " + powerConsumptionPerHour + " kWh" + "\n";
    }
}
