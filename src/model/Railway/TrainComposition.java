package model.Railway;

import logic.TrainManager;
import model.Cars.TrainCar;
import model.Cars.Locomotive;
import model.Exceptions.TrainCarConnectionException;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class TrainComposition {
    private int id;
    private static int countOfCompositions = 0;
    private Locomotive locomotive;
    private LinkedList<TrainCar> trainCars = new LinkedList<>();
    private double currentPull = 0;

    public void addTrain(TrainCar trainCar){
        try {
            if(isPossibleToConnect(trainCar)) {
                trainCars.add(trainCar);
                TrainManager.removeTrainCarFromFreeList(trainCar);
                System.out.println("Dodano pociąg o id " + trainCar.getId() + " do lokomotywy " + locomotive.getName() + " o id " + locomotive.getId());
            }
        } catch (TrainCarConnectionException e){
            System.out.println("Nie można dodać pociągu do lokomotywy ponieważ " + e);
        }
    }

    private boolean isPossibleToConnect(TrainCar trainCar) throws TrainCarConnectionException{
        int electricalsAlreadyConnected = countElectricalTrainCars();
        int alreadyConnected = trainCars.size();

        if(alreadyConnected == locomotive.getMaxNumbersOfTrainCars()){
            throw new TrainCarConnectionException("osiągnieto maksymalną liczbe wagonów");
        } else if(electricalsAlreadyConnected == locomotive.getMaxNumberOfElectricTrainCars() && trainCar.isNeededElectricalConnection()) {
            throw new TrainCarConnectionException("osiągnięto maksymalną liczbe wagonów elektrycznych");
        } else {
            return true;
        }
    }

    private int countElectricalTrainCars(){
        return (int) trainCars.stream()
                .filter(TrainCar::isNeededElectricalConnection)
                .count();
    }

    public TrainComposition(Locomotive locomotive) {
        this.locomotive = locomotive;
        this.id = ++countOfCompositions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public void setLocomotive(Locomotive locomotive) {
        this.locomotive = locomotive;
    }

    public LinkedList<TrainCar> getTrainCars() {
        return trainCars;
    }

    public void setTrainCars(LinkedList<TrainCar> trainCars) {
        this.trainCars = trainCars;
    }

    @Override
    public String toString() {
        return "Lokomotywa: " + locomotive.getName() + " o ID " + locomotive.getId() +
                "\nID całego składu: " + id;
    }
}
