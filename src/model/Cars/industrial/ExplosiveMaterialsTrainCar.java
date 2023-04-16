package model.Cars.industrial;

import model.Cars.TrainCar;

public class ExplosiveMaterialsTrainCar extends HeavyFreightTrainCar {
    private String typeOfExplosive;
    private String flammabilityClass;

    public ExplosiveMaterialsTrainCar(double weight, String securityMethod, String type, String typeOfExplosive, String flammabilityClass) {
        super(weight, "Materiały wybuchowe", securityMethod, type);
        this.typeOfExplosive = typeOfExplosive;
        this.flammabilityClass = flammabilityClass;
    }

    @Override
    public String toString() {
        return super.toString() + "\nRodzaj przewożonego materiału wybuchowego: " + typeOfExplosive + "\nKlasa palności: " + flammabilityClass;
    }
}
