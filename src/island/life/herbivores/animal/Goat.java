package island.life.herbivores.animal;

import island.life.Herbivore;
import island.life.IslanderType;

import java.util.HashMap;

public class Goat extends Herbivore  {
    private static HashMap<IslanderType, Integer> ableToEat = new HashMap<>();
    static {
        ableToEat.put(IslanderType.PLANT,100);
    }


    public Goat(int x, int y) {
        super(x,y,IslanderType.GOAT.normalWeight, IslanderType.GOAT);
    }


    @Override
    public Goat multiply(int x, int y) {
        Goat goat = new Goat(x,y);
        return goat;
    }

    @Override
    public  Integer getAbleToEat(IslanderType islanderType)
    {
        return ableToEat.get(islanderType) ;
    }
}
