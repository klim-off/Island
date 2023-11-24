package island.life.predators.animal;

import island.life.IslanderType;
import island.life.Predator;
import java.util.HashMap;

public class Wolf extends Predator {
    private static HashMap<IslanderType, Integer> ableToEat = new HashMap<>();
static {
    ableToEat.put(IslanderType.SHEEP, 70);
    ableToEat.put(IslanderType.BUFFALO, 10);
    ableToEat.put(IslanderType.HORSE, 10);
   ableToEat.put(IslanderType.DUCK, 30);
    ableToEat.put(IslanderType.HOG, 25);
    ableToEat.put(IslanderType.DEER, 50);
    ableToEat.put(IslanderType.GOAT, 60);
}
    public Wolf(int x,int y) {
        super(x,y,IslanderType.WOLF.normalWeight,IslanderType.WOLF);
    }

    @Override
    public Wolf multiply(int x, int y) {
       Wolf wolf = new Wolf(x,y);
       return wolf;
    }

    @Override
    public  Integer getAbleToEat(IslanderType islanderType)
    {
        return ableToEat.get(islanderType) ;
    }
}
