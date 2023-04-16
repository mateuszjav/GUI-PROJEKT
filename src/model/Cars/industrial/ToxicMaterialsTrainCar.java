package model.Cars.industrial;

public class ToxicMaterialsTrainCar extends HeavyFreightTrainCar {
    private String toxicityLevel;
    private boolean isRadioactive;

    public ToxicMaterialsTrainCar(double weight, String cargoType, String securityMethod, String type, String toxicityLevel, boolean isRadioactive) {
        super(weight, cargoType, securityMethod, type);
        this.toxicityLevel = toxicityLevel;
        this.isRadioactive = isRadioactive;
    }

    @Override
    public String toString() {
        return super.toString() + "\nStopien toksycznosci: " + toxicityLevel + "\nJest radioaktywny: " + isRadioactive;
    }
}
