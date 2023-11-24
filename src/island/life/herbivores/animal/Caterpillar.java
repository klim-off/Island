package island.life.herbivores.animal;

import island.life.Herbivore;
import island.life.IslanderType;


import java.util.HashMap;

public class Caterpillar extends Herbivore  {
    private static HashMap<IslanderType, Integer> ableToEat = new HashMap<>();

    static {
        ableToEat.put(IslanderType.PLANT,100);
    }

    public Caterpillar(int x, int y) {
        super(x, y, IslanderType.CATERPILLAR.normalWeight, IslanderType.CATERPILLAR);
    }
    @Override
    public Caterpillar multiply(int x, int y) {
        Caterpillar caterpillar = new Caterpillar(x,y);
        return caterpillar;
    }

    @Override
    public  Integer getAbleToEat(IslanderType islanderType)
    {
        return ableToEat.get(islanderType) ;
    }

}
