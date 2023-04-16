package model.Cars.industrial;

public class LiquidToxicMaterialsTrainCar extends HeavyFreightTrainCar implements LiquidCargo{
    private String toxicLiquid;
    private double amountOfLiquid = 0;

    @Override
    public void loadLiquidToTank() {

    }

    @Override
    public void unloadLiquidFromTank() {

    }

    public LiquidToxicMaterialsTrainCar(double weight, String securityMethod, String type, String toxicLiquid, double amountOfLiquid) {
        super(weight, "Ciecz", securityMethod, type);
        this.toxicLiquid = toxicLiquid;
        this.amountOfLiquid = amountOfLiquid;
    }

    @Override
    public String toString() {
        return super.toString() + "\nToksyczna ciecz: " + toxicLiquid + "\nIlosc cieczy: " + amountOfLiquid + " l";
    }
}
