package island.life.herbivores.animal;

import island.life.Herbivore;
import island.life.IslanderType;

import java.util.HashMap;

public class Horse extends Herbivore{
    private static HashMap<IslanderType, Integer> ableToEat = new HashMap<>();
    static {
        ableToEat.put(IslanderType.PLANT,100);
    }


    public Horse(int x, int y) {
        super(x,y,IslanderType.HORSE.normalWeight, IslanderType.HORSE);
    }


    @Override
    public Horse multiply(int x, int y) {
        Horse horse = new Horse(x,y);
        return horse;
    }

    @Override
    public  Integer getAbleToEat(IslanderType islanderType)
    {
        return ableToEat.get(islanderType) ;
    }

}
