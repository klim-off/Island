package island.life;

import java.util.ArrayList;

public class Plant extends Islander {

    public static ArrayList<IslanderType> plantsTypeList = new ArrayList<>();

    public Plant(int x, int y) {
        super(x, y, IslanderType.PLANT.normalWeight, IslanderType.PLANT);
    }

    @Override
    public Plant multiply(int x, int y) {
        Plant plant = new Plant(x, y);
        return plant;
    }
}

