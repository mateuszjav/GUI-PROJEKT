package logic.io;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleReader {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getPositiveInt(){
        int number;

        do {
            number = getInt();
            if(number <= 0){
                System.out.println("Podana wartosc nie jest dodatnia");
            } else {
                return number;
            }
        } while (true);
    }

    public static int getInt() {
        while(true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Podana wartosc nie jest liczba calkowita");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public static boolean getBoolean(){
        while(true) {
            try {
                return scanner.nextBoolean();
            } catch (InputMismatchException e) {
                System.out.println("Podana wartosc nie prawda ani falszem");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public static double getDouble(){
        while(true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Podana wartosc nie jest liczba");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public static double getPositiveDouble(){
        double number;
        do {
            number = getDouble();
            if(number <= 0){
                System.out.println("Podana liczba nie jest dodatnia");
            } else {
                return number;
            }
        } while (true);
    }

    public static String getString(){
        return scanner.nextLine();
    }

}
