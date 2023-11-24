package island.life;

import island.life.Plant;
import island.life.herbivores.animal.*;
import island.life.predators.animal.*;



public enum IslanderType {

    SHEEP(0, "\uD83D\uDC11", 70, 15, 140, 3, Sheep.class),
    DUCK(0, "\uD83E\uDD86", 1, 0.4, 200, 4, Duck.class),
    CATERPILLAR(0,"\uD83D\uDC1B",0.3,0.3,1000,1, Caterpillar.class),
    RABBIT(0,"\uD83D\uDC07",2,0.5,150,2, Rabbit.class),
    MOUSE (0,"\uD83D\uDC01",0.3,0.3,500,1, Mouse.class),
    GOAT(0,"\uD83D\uDC10",60,10,140,3, Goat.class),
    DEER(0,"\uD83E\uDD8C",300,20,20,4, Deer.class),
    BUFFALO(0,"\uD83D\uDC03",700,100,10,3, Buffalo.class),
    HOG(0,"\uD83D\uDC17",400,50,50,2, Hog.class),
    HORSE (0,"\uD83D\uDC0E", 400,60,20,4, Horse.class),

    PLANT(0, "\uD83C\uDF31", 2, 0, 10000, 0, Plant.class),
    WOLF(0, "\uD83D\uDC3A", 50, 8, 30, 3, Wolf.class),
    BEAR(0,"\uD83D\uDC3B",500, 80,5,2, Bear.class),
    BOA (0,"\uD83D\uDC0D", 15,4,20,2, Boa.class),
    EAGLE(0,"\uD83E\uDD85", 6,1,20,3, Eagle.class),
    FOX(0,"\uD83E\uDD8A",8,2,30,3, Fox.class);


    int population;
    String icon;
    public double normalWeight;
    double foodVolume;
    int maxCapacity;
    int travelSpeed;

    Class clazz;


    IslanderType(int population, String icon, double normalWeight,
                 double foodVolume, int maxCapacity, int travelSpeed, Class clazz) {
        this.population = population;
        this.icon = icon;
        this.normalWeight = normalWeight;
        this.foodVolume = foodVolume;
        this.maxCapacity = maxCapacity;
        this.travelSpeed = travelSpeed;
        this.clazz = clazz;

    }
}
