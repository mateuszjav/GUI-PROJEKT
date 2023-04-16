package model.Cars.industrial;

public class HeavyFreightTrainCar extends FreightTrainCar {
    private String securityMethod;
    private String type;
    public static final double maxCargoWeight = 22000;

    public HeavyFreightTrainCar(double weight, String cargoType, String securityMethod, String type) {
        super(weight, false, cargoType);
        this.securityMethod = securityMethod;
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + "\nRodzaj zabezpieczenia: " +  securityMethod + "\nTyp wagonu: " + type;
    }
}
