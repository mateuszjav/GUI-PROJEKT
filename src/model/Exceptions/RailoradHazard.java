package model.Exceptions;

import model.Cars.Locomotive;

public class RailoradHazard extends Exception {
    public RailoradHazard(Locomotive locomotive) {
        super("Pociag o ID " + locomotive.getId() + " " + locomotive.getName() + " Osiągnął zbyt dużą prędkość");
    }
}
