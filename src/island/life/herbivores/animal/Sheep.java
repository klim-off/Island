package island.life.herbivores.animal;

import island.life.Herbivore;
import island.life.IslanderType;

import java.util.HashMap;

public class Sheep extends Herbivore {
    private static HashMap<IslanderType, Integer> ableToEat = new HashMap<>();
    static {
        ableToEat.put(IslanderType.PLANT,100);
    }


    public Sheep(int x, int y) {
        super(x,y,IslanderType.SHEEP.normalWeight, IslanderType.SHEEP);
    }


    @Override
    public Sheep multiply(int x, int y) {
        Sheep sheep = new Sheep(x,y);
        return sheep;
    }

    @Override
    public  Integer getAbleToEat(IslanderType islanderType)
    {
        return ableToEat.get(islanderType) ;
    }
}
