package island.life.herbivores.animal;

import island.life.Herbivore;
import island.life.IslanderType;


import java.util.HashMap;

public class Deer  extends Herbivore {
    private static HashMap<IslanderType, Integer> ableToEat = new HashMap<>();
    static {
        ableToEat.put(IslanderType.PLANT,100);
    }


    public Deer(int x, int y) {
        super(x,y,IslanderType.DEER.normalWeight, IslanderType.DEER);
    }


    @Override
    public Deer multiply(int x, int y) {
        Deer deer = new Deer(x,y);
        return deer;
    }

    @Override
    public  Integer getAbleToEat(IslanderType islanderType)
    {
        return ableToEat.get(islanderType) ;
    }
}
