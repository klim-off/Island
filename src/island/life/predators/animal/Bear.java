package island.life.predators.animal;

import island.life.IslanderType;
import island.life.Predator;

import java.util.HashMap;

public class Bear extends Predator {
    private static HashMap<IslanderType, Integer> ableToEat = new HashMap<>();
    static {
        ableToEat.put(IslanderType.SHEEP, 60);
        ableToEat.put(IslanderType.RABBIT, 20);
        ableToEat.put(IslanderType.HOG, 50);
        ableToEat.put(IslanderType.HORSE, 40);
        ableToEat.put(IslanderType.GOAT, 50);
        ableToEat.put(IslanderType.DEER, 50);
        ableToEat.put(IslanderType.BUFFALO, 20);


    }
    public Bear(int x,int y) {
        super(x,y,IslanderType.BEAR.normalWeight,IslanderType.BEAR);
    }

    @Override
    public Bear multiply(int x, int y) {
        Bear bear = new Bear(x,y);
        return bear;
    }

    @Override
    public  Integer getAbleToEat(IslanderType islanderType)
    {
        return ableToEat.get(islanderType) ;
    }
}


