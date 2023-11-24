package island.life;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Islander {

    private static HashMap<IslanderType, Integer> ableToEat;

    public int x;
    public int y;
    public double weight;
    public IslanderType islanderType;
    private int percentageOfLife = 8;
    private  int weightLossPerMove = 10;


    public Islander(int x, int y, double weight, IslanderType islanderType) {
        this.x = x;
        this.y = y;
        if (islanderType == IslanderType.PLANT) {
            this.weight = weight;
        } else {  this.weight = weight/2;} // every animal born this half of normal weight
        this.islanderType = islanderType;
    }

    public  Integer getAbleToEat(IslanderType islanderType)
    {
        return ableToEat.get(islanderType) ;
    }

    public Islander multiply(int x, int y) {
        return null;
    }

   synchronized public void move(int lenght, int width) {
        int newX = x;
        int newY = y;
        for (int i = 0; i < this.islanderType.travelSpeed; i++) {
            newX = newX + calcStep(newX, lenght - 1);
            newY = newY + calcStep(newY, width - 1);
            if (weight > (islanderType.normalWeight /100 * percentageOfLife)) { // if animal have lost more % of normal weight  . We think it is dead
                weight -= weight / 100 * weightLossPerMove; //we take % for moving
            } else weight = 0;
        }
        x = newX;
        y = newY;
    }


     private  int calcStep(int x, int limitField) {
        int min;
        int max;
        int randomNum;
        if (x == 0) {
            min = 0;
            max = 1;
        } else if (x == limitField) {
            min = -1;
            max = 0;
        } else {
            min = -1;
            max = 1;
        }
        return randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
    }


}
