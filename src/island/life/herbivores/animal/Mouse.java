package island.life.herbivores.animal;

import island.life.Herbivore;
import island.life.IslanderType;

import java.util.HashMap;

public class Mouse extends Herbivore {
    private static HashMap<IslanderType, Integer> ableToEat = new HashMap<>();
    static {
        ableToEat.put(IslanderType.PLANT,100);
        ableToEat.put(IslanderType.CATERPILLAR,90);
    }


    public Mouse(int x, int y) {
        super(x,y,IslanderType.MOUSE.normalWeight, IslanderType.MOUSE);
    }


    @Override
    public Mouse multiply(int x, int y) {
        Mouse mouse = new Mouse(x,y);
        return mouse;
    }

    @Override
    public  Integer getAbleToEat(IslanderType islanderType)
    {
        return ableToEat.get(islanderType) ;
    }
}


