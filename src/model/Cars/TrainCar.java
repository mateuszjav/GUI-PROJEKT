package model.Cars;

public abstract class TrainCar {
    private int id;
    private final boolean isNeededElectricalConnection;
    private static int trainCarCount = 0;
    private double weight;

    public TrainCar(boolean isNeededElectricalConnection, double weight) {
        this.id = ++trainCarCount;
        this.isNeededElectricalConnection = isNeededElectricalConnection;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isNeededElectricalConnection() {
        return isNeededElectricalConnection;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Wagon ID: " + id + " m" +"\nWymaga podlaczenia do sieci elektrycznj: " + (isNeededElectricalConnection ? "Tak" : "Nie") + "\nWaga wagonu: " + weight + " kg";
    }
}
