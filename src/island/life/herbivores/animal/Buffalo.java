package island.life.herbivores.animal;
import island.life.Herbivore;
import island.life.IslanderType;

import java.util.HashMap;




public class Buffalo extends Herbivore {
    private static HashMap<IslanderType, Integer> ableToEat = new HashMap<>();
    static {
        ableToEat.put(IslanderType.PLANT,100);
    }


    public Buffalo(int x, int y) {
        super(x,y,IslanderType.BUFFALO.normalWeight, IslanderType.BUFFALO);
    }


    @Override
    public Buffalo multiply(int x, int y) {
        Buffalo buffalo = new Buffalo(x,y);
        return buffalo;
    }

    @Override
    public  Integer getAbleToEat(IslanderType islanderType)
    {
        return ableToEat.get(islanderType) ;
    }
}
