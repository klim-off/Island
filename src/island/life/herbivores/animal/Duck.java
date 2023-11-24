package island.life.herbivores.animal;

import island.life.Herbivore;
import island.life.IslanderType;


import java.util.HashMap;

public class Duck extends Herbivore   {
    private static HashMap<IslanderType, Integer> ableToEat = new HashMap<>();
    static {
       ableToEat.put(IslanderType.PLANT,100);
       ableToEat.put(IslanderType.CATERPILLAR,80);
    }
    public Duck(int x, int y) {

        super(x, y, IslanderType.DUCK.normalWeight, IslanderType.DUCK);
    }

    @Override
    public Duck multiply(int x, int y) {
        Duck duck = new Duck(x,y);
        return duck;
    }

    @Override
    public  Integer getAbleToEat(IslanderType islanderType)
    {
        return ableToEat.get(islanderType) ;
    }
}
