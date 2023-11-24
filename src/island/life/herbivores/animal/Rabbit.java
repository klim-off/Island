package island.life.herbivores.animal;

import island.life.Herbivore;
import island.life.IslanderType;

import java.util.HashMap;

public class Rabbit extends Herbivore  {
    private static HashMap<IslanderType, Integer> ableToEat = new HashMap<>();
    static {
        ableToEat.put(IslanderType.PLANT,100);
    }


    public Rabbit(int x, int y) {
        super(x,y,IslanderType.RABBIT.normalWeight, IslanderType.RABBIT);
    }


    @Override
    public Rabbit multiply(int x, int y) {
        Rabbit rabbit = new Rabbit(x,y);
        return rabbit;
    }

    @Override
    public  Integer getAbleToEat(IslanderType islanderType)
    {
        return ableToEat.get(islanderType) ;
    }
}

