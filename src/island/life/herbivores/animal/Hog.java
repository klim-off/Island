package island.life.herbivores.animal;

import island.life.Herbivore;
import island.life.IslanderType;

import java.util.HashMap;

public class Hog extends Herbivore  {
    private static HashMap<IslanderType, Integer> ableToEat = new HashMap<>();
    static {
        ableToEat.put(IslanderType.PLANT,100);
        ableToEat.put(IslanderType.MOUSE,50);
       // ableToEat.put(island.life.IslanderType.CATERPILLAR,90);
    }


    public Hog(int x, int y) {
        super(x,y,IslanderType.HOG.normalWeight, IslanderType.HOG);
    }


    @Override
    public Hog multiply(int x, int y) {
        Hog hog = new Hog(x,y);
        return hog;
    }

    @Override
    public  Integer getAbleToEat(IslanderType islanderType)
    {
        return ableToEat.get(islanderType) ;
    }
}
