package logic;

import logic.io.ConsoleReader;
import model.Cars.Locomotive;
import model.Cars.TrainCar;
import model.Cars.basic.BaggageTrainCar;
import model.Cars.basic.DiningTrainCar;
import model.Cars.basic.MailTrainCar;
import model.Cars.basic.PassengerTrainCar;
import model.Cars.industrial.*;
import model.Enums.TrainCarType;

public class TrainFactory {
    public static Locomotive createLocomotive(){
        System.out.println("Podaj nazwe lokomotywy: ");
        String name = ConsoleReader.getString();
        System.out.println("Podaj maksymalną liczbe ciągniętych wagonów: ");
        int maxNumberOfTrainCars = ConsoleReader.getPositiveInt();
        System.out.println("Podaj maksymalny uciąg (w kg): ");
        double maxLoadWeight = ConsoleReader.getPositiveDouble();
        System.out.println("Podaj maksymalna liczbe ciągniętych wagonów z siecią elektryczną: ");
        int numberOfElectricTrainCars = ConsoleReader.getPositiveInt();
        System.out.println("Podaj średnią prędkość lokomotywy: ");
        double speed = ConsoleReader.getPositiveDouble();
        return new Locomotive(name, maxNumberOfTrainCars, maxLoadWeight, maxNumberOfTrainCars, speed);
    }
    public static TrainCar createTrainCar(TrainCarType type){
        switch (type){
            case PASSENGER -> {
                System.out.println("Podaj liczbe miejsc siedzacych w wagonie:");
                int numberOfSeats = ConsoleReader.getPositiveInt();
                System.out.println("Podaj liczbe przedziałów w wagonie:");
                int numberOfCompartment = ConsoleReader.getPositiveInt();
                System.out.println("Czy wagon posiada ma posiadać toalete ? (true/false)");
                boolean hasToilet = ConsoleReader.getBoolean();
                System.out.println("Podaj wage wagonu (w kg):");
                double weight = ConsoleReader.getDouble();
                return new PassengerTrainCar(weight, numberOfSeats, numberOfCompartment, hasToilet);
            }
            case MAIL -> {
                System.out.println("Podaj rodzaj przewożonych paczek: ");
                String packagesType = ConsoleReader.getString();
                System.out.println("Podaj liczbe miejsc na paczki:");
                int numberOfPackages = ConsoleReader.getPositiveInt();
                System.out.println("Podaj wage wagonu (w kg):");
                double weight = ConsoleReader.getDouble();
                return new MailTrainCar(weight, packagesType, numberOfPackages);
            }
            case BAGGAGE -> {
                System.out.println("Podaj liczbe miejsc na bagaże:");
                int numberOfSpaces = ConsoleReader.getPositiveInt();
                System.out.println("Podaj maksymalny cieżar bagażu pasażera:");
                double maxBaggageWeight = ConsoleReader.getDouble();
                System.out.println("Podaj wage wagonu (w kg):");
                double weight = ConsoleReader.getPositiveDouble();
                return new BaggageTrainCar(weight, numberOfSpaces, maxBaggageWeight);
            }
            case DINING -> {
                System.out.println("Podaj liczbe stolików w wagonie:");
                int numberOfTables = ConsoleReader.getPositiveInt();
                System.out.println("Podaj rodzaj serwowanej kuchnii:");
                String cuisine = ConsoleReader.getString();
                System.out.println("Podaj liczbe pracujących kelnerów:");
                int numberOfWaiters = ConsoleReader.getPositiveInt();
                System.out.println("Podaj wage wagonu (w kg):");
                double weight = ConsoleReader.getPositiveDouble();
                return new DiningTrainCar(weight, numberOfTables, cuisine, numberOfWaiters);
            }
            case BASIC_FREIGHT -> {
                System.out.println("Podaj przewożony ładunek:");
                String cargoType = ConsoleReader.getString();
                System.out.println("Czy wagon ma zabezpieczenie towaru przed kradzieżą? (true/false):");
                boolean isAntiTheftProtected = ConsoleReader.getBoolean();
                System.out.println("Podaj wage wagonu (w kg):");
                double weight = ConsoleReader.getPositiveDouble();
                return new BasicFreightTrainCar(weight, false, cargoType, isAntiTheftProtected);
            }
            case HEAVY_FREIGHT -> {
                System.out.println("Podaj przewożony ładunek:");
                String cargoType = ConsoleReader.getString();
                System.out.println("Podaj sposób zabezpieczenia ładunku:");
                String securityMethod = ConsoleReader.getString();
                System.out.println("Podaj typ wagonu(np. cysterna/samowyładowczy/specjalny...):");
                String trainCarType = ConsoleReader.getString();
                System.out.println("Podaj wage wagonu (w kg): ");
                double weight = ConsoleReader.getPositiveDouble();
                return new HeavyFreightTrainCar(weight, cargoType, securityMethod, trainCarType);
            }
            case REFRIGERATED -> {
                System.out.println("Podaj przewożony ładunek:");
                String cargoType = ConsoleReader.getString();
                System.out.println("Podaj godzinowe zapotrzebowanie elektryczne (w kWh):");
                double powerConsumption = ConsoleReader.getPositiveDouble();
                System.out.println("Podaj temperature do chłodzenia:");
                double coolingTemperature = ConsoleReader.getDouble();
                System.out.println("Podaj wage wagonu (w kg):");
                double weight = ConsoleReader.getPositiveDouble();
                return new RefrigeratedTrainCar(weight, cargoType, powerConsumption, coolingTemperature);
            }
            case LIQUID -> {
                System.out.println("Podaj rodzaj przewożonej cieczy: ");
                String liquidType = ConsoleReader.getString();
                System.out.println("Podaj ilosc przewożonej cieczy: ");
                double liquidAmount = ConsoleReader.getPositiveDouble();
                System.out.println("Czy wagon ma zabezpieczenie towaru przed kradzieżą? (true/false):");
                boolean isAntiTheftProtected = ConsoleReader.getBoolean();
                System.out.println("Podaj wage wagonu (w kg): ");
                double weight = ConsoleReader.getPositiveDouble();
                return new LiquidTrainCar(weight, isAntiTheftProtected, liquidType, liquidAmount);
            }
            case TOXIC_LIQUID -> {
                System.out.println("Podaj rodzaj przewożonej toksycznej cieczy: ");
                String toxicLiquid = ConsoleReader.getString();
                System.out.println("Podaj ilosc przewożonej cieczy: ");
                double liquidAmount = ConsoleReader.getPositiveDouble();
                System.out.println("Podaj sposób zabezpieczenia ładunku:");
                String securityMethod = ConsoleReader.getString();
                System.out.println("Podaj typ wagonu(np. cysterna/samowyładowczy/specjalny...):");
                String trainCarType = ConsoleReader.getString();
                System.out.println("Podaj wage wagonu (w kg): ");
                double weight = ConsoleReader.getPositiveDouble();
                return new LiquidToxicMaterialsTrainCar(weight, securityMethod, trainCarType, toxicLiquid, liquidAmount);
            }
            case TOXIC -> {
                System.out.println("Podaj rodzaj przewożonego toksycznego materiały: ");
                String toxicMatterial = ConsoleReader.getString();
                System.out.println("Podaj stopien toksyczności przewożonego materiału: ");
                String toxicityLevel = ConsoleReader.getString();
                System.out.println("Czu jest radioaktywny? (true/false? :");
                boolean isRadioactive = ConsoleReader.getBoolean();
                System.out.println("Podaj sposób zabezpieczenia ładunku:");
                String securityMethod = ConsoleReader.getString();
                System.out.println("Podaj typ wagonu(np. cysterna/samowyładowczy/specjalny...):");
                String trainCarType = ConsoleReader.getString();
                System.out.println("Podaj wage wagonu (w kg): ");
                double weight = ConsoleReader.getPositiveDouble();

                return new ToxicMaterialsTrainCar(weight, toxicMatterial, securityMethod, trainCarType, toxicityLevel, isRadioactive);
            }
            case GAS -> {
                System.out.println("Podaj rodzaj przewożonego gazu: ");
                String gasType = ConsoleReader.getString();
                System.out.println("Podaj ilosc przewożonego gazu: ");
                double amountOfGas = ConsoleReader.getPositiveDouble();
                System.out.println("Czy wagon ma zabezpieczenie towaru przed kradzieżą? (true/false):");
                boolean isAntiTheftProtected = ConsoleReader.getBoolean();
                System.out.println("Podaj wage wagonu (w kg): ");
                double weight = ConsoleReader.getPositiveDouble();
                return new GasTrainCar(weight, isAntiTheftProtected, gasType, amountOfGas);
            }
            case EXPLOSIVE -> {
                System.out.println("Podaj rodzaj przewożonego materiału wybuchowego: ");
                String typeOfExplosive = ConsoleReader.getString();
                System.out.println("Podaj klase łatwopalności materiału: ");
                String flammabilityClass = ConsoleReader.getString();
                System.out.println("Podaj sposób zabezpieczenia ładunku:");
                String securityMethod = ConsoleReader.getString();
                System.out.println("Podaj typ wagonu(np. cysterna/samowyładowczy/specjalny...):");
                String trainCarType = ConsoleReader.getString();
                System.out.println("Podaj wage wagonu (w kg): ");
                double weight = ConsoleReader.getPositiveDouble();

                return new ExplosiveMaterialsTrainCar(weight, securityMethod, trainCarType, typeOfExplosive, flammabilityClass);
            }
            default -> throw new IllegalArgumentException("Nieznany typ pociągu");
        }
    }
}
