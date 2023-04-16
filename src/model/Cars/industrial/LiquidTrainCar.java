package model.Cars.industrial;

public class LiquidTrainCar extends BasicFreightTrainCar implements LiquidCargo{
    private String liquidType;
    private double amountOfLiquid = 0;

    public LiquidTrainCar(double weight, boolean isAntiTheftProtected, String liquidType, double amountOfLiquid) {
        super(weight, false, "Ciecz", isAntiTheftProtected);
        this.liquidType = liquidType;
        this.amountOfLiquid = amountOfLiquid;
    }

    @Override
    public String toString() {
        return super.toString() + "\nRodzaj cieczy: " + liquidType + "\nIlosc cieczy: " + amountOfLiquid + " l";
    }

    @Override
    public void loadLiquidToTank() {

    }

    @Override
    public void unloadLiquidFromTank() {

    }
}
