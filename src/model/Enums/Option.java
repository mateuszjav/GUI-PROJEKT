package model.Enums;

import logic.io.ConsoleReader;

public enum Option {
    ADD_LOCOMOTIVE(0, "Stworz lokomotywe"),
    ADD_TRAIN_CAR(1, "Stworz wagon"),
    ADD_RAILWAY_STATION(2, "Dodaj nowa stacje kolejowa"),
    ADD_TRAIN_CAR_TO_LOCOMOTIVE(3, "Dolacz wagon do lokomotywy"),
    ADD_PEOPLE(4, "Dodaj osoby do wagonu"),
    ADD_LOAD(5,"Dodaj ladunek do wagonu"),
    SHOW_FREE_TRAIN_CAR(6, "Wyświetl wolne wagony"),
    SHOW_FREE_LOCOMOTIVE(7, "Wyświetl wolne lokomotywy"),
    SHOW_RAPORT(8, "Wyświetl raport pociągu"),
    EXIT(9,"Zakoncz program");

    private final int number;
    private final String description;

    Option(int number, String description) {
        this.number = number;
        this.description = description;
    }

    @Override
    public String toString() {
        return number + " - " + description;
    }

    private static Option createFromInt(int number){
        try{
            return values()[number];
        } catch (IndexOutOfBoundsException e){
            throw new IllegalArgumentException("Brak wagonu o numerze " + number);
        }
    }

    public static Option getOption(){
        int number;
        Option option = null;
        boolean isOptionCorrect = false;
        do {
            try {
                System.out.println("Podaj numer opcji: ");
                number = ConsoleReader.getInt();
                option = Option.createFromInt(number);
                isOptionCorrect = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Nie ma takiej opcji w menu");
            }
        } while (!isOptionCorrect);
        return option;
    }

}
