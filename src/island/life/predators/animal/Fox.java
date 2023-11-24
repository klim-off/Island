package island.life.predators.animal;

import island.life.IslanderType;
import island.life.Predator;
import java.util.HashMap;

public class Fox extends Predator {
    private static HashMap<IslanderType, Integer> ableToEat = new HashMap<>();
    static {
        ableToEat.put(IslanderType.MOUSE, 90);
        ableToEat.put(IslanderType.RABBIT, 50);
        ableToEat.put(IslanderType.DUCK, 60);
    }
    public Fox(int x,int y) {
        super(x,y,IslanderType.FOX.normalWeight,IslanderType.FOX );
    }

    @Override
    public Fox multiply(int x, int y) {
        Fox fox = new Fox(x,y);
        return fox;
    }

    @Override
    public  Integer getAbleToEat(IslanderType islanderType)
    {
        return ableToEat.get(islanderType) ;
    }
}
