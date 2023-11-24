package island.life.predators.animal;

import island.life.IslanderType;
import island.life.Predator;
import java.util.HashMap;

public class Boa extends Predator {
    private static HashMap<IslanderType, Integer> ableToEat = new HashMap<>();
    static {
        ableToEat.put(IslanderType.DUCK, 20);
        ableToEat.put(IslanderType.RABBIT, 50);
        ableToEat.put(IslanderType.MOUSE, 40);

    }
    public Boa(int x,int y) {
        super(x,y,IslanderType.BOA.normalWeight,IslanderType.BOA);
    }

    @Override
    public Boa multiply(int x, int y) {
        Boa boa = new Boa(x,y);
        return boa;
    }

    @Override
    public  Integer getAbleToEat(IslanderType islanderType)
    {
        return ableToEat.get(islanderType) ;
    }
}
