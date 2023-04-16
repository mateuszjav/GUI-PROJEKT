package logic;

import logic.io.ConsoleReader;
import model.Cars.Locomotive;
import model.Cars.TrainCar;
import model.Enums.TrainCarType;
import model.Railway.Station;
import model.Railway.TrainComposition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrainManager {
    public static List<TrainCar> freeTrainCars = new ArrayList<>();
    public static List<TrainComposition> trainCompositions = new ArrayList<>();

    public static <T extends TrainCar> List<T> filterTrainCarsByType(List<TrainCar> trainCars, Class<T> type){
        return trainCars.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }

    public static void printTrainCompositionRaport(){
        Optional<TrainComposition> searched = Optional.empty();
        int id = ConsoleReader.getPositiveInt();
        for (TrainComposition trainComposition : trainCompositions) {
            if(trainComposition.getId() == id){
                searched = Optional.of(trainComposition);
            }
        }
        if(searched.isPresent()){
            TrainComposition trainComposition = searched.get();
            Locomotive compositionLocomotive = trainComposition.getLocomotive();
            List<Station> compositionPath = compositionLocomotive.getPath();

            Station currentStation = compositionLocomotive.getCurrentStation();
            Station nextStation = compositionPath.get(compositionPath.indexOf(currentStation) + 1);
            double distanceTraveledToNext = compositionLocomotive.getDistanceTraveledToNext();

            double distanceFromStartToCurrent = 0;
            for (int i = 0; i < compositionPath.size() - 1; i++) {
                if(compositionPath.get(i) == currentStation){
                    break;
                }
                distanceFromStartToCurrent += compositionPath.get(i).getConnections().get(compositionPath.get(i + 1));
            }
            distanceFromStartToCurrent+= distanceTraveledToNext;

            double totalDistance = 0;
            for (int i = 0; i < compositionPath.size() - 1; i++) {
                totalDistance += compositionPath.get(i).getConnections().get(compositionPath.get(i + 1));
            }

            System.out.println("Skład ID: " + trainComposition.getId() + "Lokomotywa składu: " + compositionLocomotive.getName());
            System.out.println("Stacja domowa: " + compositionLocomotive.getHomeStation() + "; Stacja aktualna: " + compositionLocomotive.getCurrentStation() + "; Stacja końcowa: " + compositionLocomotive.getDestinationStation());
            System.out.println("Ukończona droga pomiędzy stacją startową i docelową: " + String.format("%.2f", (distanceFromStartToCurrent/totalDistance) * 100) + "%");
            Double distanceToNextStation = currentStation.getConnections().get(nextStation);
            System.out.println("Ukończona droga do najbliższej stacji: " + String.format("%.2f",(distanceTraveledToNext/distanceToNextStation) * 100) + "%");

        }
    }
    public static Optional<TrainCar> findFreeTrainCarById(int number){
        return freeTrainCars.stream().filter(t -> t.getId() == number).findFirst();
    }

    public static Optional<Locomotive> findFreeLocomotiveById(int number){
        Stream<Locomotive> locomotivesStream = getAvailableCompsitions().map(TrainComposition::getLocomotive);
        return locomotivesStream.filter(l -> l.getId() == number).findFirst();
    }

    public static void printFreeTrainCars() {
        if(freeTrainCars.isEmpty()){
            System.out.println("Brak wolnych wagonów");
        } else {
            printList(freeTrainCars);
        }
    }

    public static void printFreeLocomotives() {
        List<TrainComposition> availableCompsitions = getAvailableCompsitions().toList();
        if(!availableCompsitions.isEmpty()) {
            availableCompsitions.forEach(System.out::println);
        } else {
            System.out.println("Brak wolnych lokomotyw");
        }
    }

    public static void addNewTrainCar() {
        printList(Arrays.asList(TrainCarType.values()));
        TrainCarType type = TrainCarType.getTrainCarType();
        TrainCar trainCar = TrainFactory.createTrainCar(type);
        freeTrainCars.add(trainCar);
    }

    public static void addNewLocomotive(){
        Locomotive locomotive = TrainFactory.createLocomotive();
        TrainComposition trainComposition = new TrainComposition(locomotive);
        trainCompositions.add(trainComposition);
    }

    public static void addTrainToLocomotive() {
        List<TrainComposition> availableCompositions = getAvailableCompsitions().toList();

        if(availableCompositions.isEmpty()){
            System.out.println("Brak wolnych lokomotyw");
        } else if(freeTrainCars.isEmpty()){
            System.out.println("Brak wolnych wagonów");
        } else {
            System.out.println("Podaj ID wagonu:");
            int searchedTrainCarId = ConsoleReader.getPositiveInt();
            Optional<TrainCar> optionalTrainCar = findFreeTrainCarById(searchedTrainCarId);
            System.out.println("Podaj ID lokomotywy: ");
            int searchedLocomotiveId = ConsoleReader.getPositiveInt();
            Optional<Locomotive> optionalLocomotive = findFreeLocomotiveById(searchedLocomotiveId);

            if(optionalLocomotive.isEmpty()) {
                System.out.println("Brak wolnej lokomotywy o podanym ID");
            } else if (optionalTrainCar.isEmpty()) {
                System.out.println("Brak wolnego wagonu o podanym ID");
            } else {
                Locomotive locomotive = optionalLocomotive.get();
                TrainCar trainCar = optionalTrainCar.get();
                TrainComposition trainComposition = availableCompositions.stream()
                        .filter(c -> c.getLocomotive().getId() == locomotive.getId())
                        .findFirst()
                        .get();
                trainComposition.addTrain(trainCar);
            }
        }
    }

    public static void removeTrainCarFromFreeList(TrainCar trainCar){
        for (int i = 0; i < freeTrainCars.size(); i++) {
            if(freeTrainCars.get(i).getId() == trainCar.getId()){
                freeTrainCars.remove(i);
                break;
            }
        }
    }

    private static Stream<TrainComposition> getAvailableCompsitions(){
        return trainCompositions.stream().filter(t -> t.getTrainCars().size() < t.getLocomotive().getMaxNumbersOfTrainCars());
    }

    private static <T> void printList(List<T> list){
        for (T t : list) {
            System.out.println(t);
        }
    }

}
