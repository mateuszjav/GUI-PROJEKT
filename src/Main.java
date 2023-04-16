import logic.RailwaySystemManager;
import logic.TrainManager;
import model.Cars.Locomotive;
import model.Enums.Option;
import model.Exceptions.RailoradHazard;
import model.Railway.Station;
import model.Railway.TrainComposition;

import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {


        Station a = new Station("A");
        Station b = new Station("B");
        Station c = new Station("C");
        Station d = new Station("D");


        a.addConnection(b, 1);
        b.addConnection(c, 1);
        c.addConnection(d, 1);

        RailwaySystemManager.stations.put("A", a);
        RailwaySystemManager.stations.put("B", b);
        RailwaySystemManager.stations.put("C", c);
        RailwaySystemManager.stations.put("D", d);


        List<Station> path = RailwaySystemManager.getPath("A", "D");
        List<Station> path2 = RailwaySystemManager.getPath("D", "A");
        List<Station> path3 = RailwaySystemManager.getPath("A", "D");
//
        Locomotive locomotive = new Locomotive("INKA", 2, 2 ,1, 150);
        Locomotive locomotive2 = new Locomotive("INKA2", 2, 2 ,1, 150);
        Locomotive locomotive3 = new Locomotive("INKA3", 2, 2 ,1, 150);
//
        locomotive.addPath(path);
        locomotive2.addPath(path2);
        locomotive3.addPath(path3);
//
        TrainManager.trainCompositions.add(new TrainComposition(locomotive));
        TrainManager.trainCompositions.add(new TrainComposition(locomotive2));
        TrainManager.trainCompositions.add(new TrainComposition(locomotive3));

        new Thread(locomotive).start();
        new Thread(locomotive2).start();
        new Thread(locomotive3).start();





        mainloop();
        TrainManager.trainCompositions.forEach(System.out::println);
    }

    private static void mainloop() {
        Option option;

        do{
            printOptions();
            option = Option.getOption();
            executeOption(option);
        } while (option != Option.EXIT);
    }

    private static void printOptions() {
        for (Option value : Option.values()) {
            System.out.println(value);
        }
    }

    private static void executeOption(Option option){
        switch (option){

            case ADD_LOCOMOTIVE -> {
                TrainManager.addNewLocomotive();
            }
            case ADD_TRAIN_CAR -> {
                TrainManager.addNewTrainCar();
            }
            case ADD_RAILWAY_STATION -> {

            }
            case ADD_TRAIN_CAR_TO_LOCOMOTIVE -> {
                TrainManager.addTrainToLocomotive();
            }
            case ADD_PEOPLE -> {

            }
            case ADD_LOAD -> {

            }
            case SHOW_FREE_TRAIN_CAR -> {
                TrainManager.printFreeTrainCars();
            }
            case SHOW_FREE_LOCOMOTIVE -> {
                TrainManager.printFreeLocomotives();
            }
            case SHOW_RAPORT -> {
                TrainManager.printTrainCompositionRaport();
            }
            case EXIT -> {
                System.out.println("Koniec programu");
            }
        }
    }
}
