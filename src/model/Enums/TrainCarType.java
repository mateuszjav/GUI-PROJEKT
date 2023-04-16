package model.Enums;

import logic.io.ConsoleReader;

public enum TrainCarType {
    PASSENGER(0,"Pasażerski"),
    MAIL(1,"Pocztowy"),
    BAGGAGE(2,"Bagażowo-poczyowy"),
    DINING(3,"Restauracyjny"),
    BASIC_FREIGHT(4,"Towarowy podstawowy"),
    HEAVY_FREIGHT(5,"Towarowy cięzki"),
    REFRIGERATED(6,"Chłodniczy"),
    LIQUID(7,"Na materiały ciekłe"),
    GAS(8,"Na materiały gazowe"),
    EXPLOSIVE(9,"Na materiały wybuchowe"),
    TOXIC(10,"Na materiał toksyczne"),
    TOXIC_LIQUID(11,"Na ciekłe materiały toksyczne");

    private final int number;
    private final String name;

    TrainCarType(int number, String name) {
        this.number = number;
        this.name = name;
    }

    @Override
    public String toString() {
        return number + " - " + name;
    }

    public static TrainCarType createFromInt(int number){
        try{
            return values()[number];
        } catch (IndexOutOfBoundsException e){
            throw new IllegalArgumentException("Brak opcji o numerze " + number);
        }
    }

    public static TrainCarType getTrainCarType(){
        int number;
        TrainCarType type = null;
        boolean isCarCorrect = false;
        do {
            try {
                System.out.println("Podaj numer wagonu do swtorzenia: ");
                number = ConsoleReader.getInt();
                type = TrainCarType.createFromInt(number);
                isCarCorrect = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Nie ma takiego numeru wagonu");
            }
        } while (!isCarCorrect);
        return type;
    }






}
