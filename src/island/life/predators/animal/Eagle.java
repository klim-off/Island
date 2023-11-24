package island.life.predators.animal;

import island.life.IslanderType;
import island.life.Predator;
import java.util.HashMap;

public class Eagle extends Predator  {
    private static HashMap<IslanderType, Integer> ableToEat = new HashMap<>();
    static {
        ableToEat.put(IslanderType.DUCK, 80);
        ableToEat.put(IslanderType.RABBIT, 90);
        ableToEat.put(IslanderType.MOUSE, 90);

    }
    public Eagle(int x,int y) {
        super(x,y,IslanderType.EAGLE.normalWeight,IslanderType.EAGLE);
    }

    @Override
    public Eagle multiply(int x, int y) {
        Eagle eagle = new Eagle(x,y);
        return eagle;
    }

    @Override
    public  Integer getAbleToEat(IslanderType islanderType)
    {
        return ableToEat.get(islanderType) ;
    }
}
