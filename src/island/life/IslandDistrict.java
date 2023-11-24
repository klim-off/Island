package island.life;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IslandDistrict {

    public int x;
    public int y;

    ArrayList<Islander> predators;
    ArrayList<Islander> herbivores;
    ArrayList<Islander> plants;
    public HashMap<IslanderType, ArrayList<Islander>> districtPopulation = new HashMap<>();

    public IslandDistrict(int x, int y) {
        this.x = x;
        this.y = y;
        this.predators = new ArrayList<>();
        this.herbivores = new ArrayList<>();
        this.plants = new ArrayList<>();
    }

    public void districtPopulationCentus() {
        ArrayList<Islander> listIslanders = new ArrayList<>();
        for (IslanderType islanderType : IslanderType.values()) {
            for (Islander predator : predators) {
                if (predator.islanderType.equals(islanderType)) {
                    listIslanders.add(predator);
                }
            }
            for (Islander herbivore : herbivores) {
                if (herbivore.islanderType.equals(islanderType)) {
                    listIslanders.add(herbivore);
                }
            }
            for (Islander plant : plants) {
                if (plant.islanderType.equals(islanderType)) {
                    listIslanders.add(plant);
                }
            }
            districtPopulation.put(islanderType, new ArrayList<>(listIslanders));
            listIslanders.clear();
        }
    }

    public void printPopulationDistrict() {

        System.out.print("District (" + x + "," + y + "):  ");
        for (Map.Entry<IslanderType, ArrayList<Islander>> islanderEntry : districtPopulation.entrySet()) {
            System.out.print(islanderEntry.getKey().icon + ":" + islanderEntry.getValue().size() + "; ");
        }
        System.out.println();
    }


    public void adjustDistrictPopulation() {

        int typePopulationInDistrict;
        for (IslanderType islanderType : IslanderType.values()) {
            try {
                typePopulationInDistrict = districtPopulation.get(islanderType).size();
            } catch (NullPointerException e) {
                typePopulationInDistrict = 0;
            }
            if (typePopulationInDistrict > islanderType.maxCapacity) {

                if (islanderType.clazz.getSuperclass().equals(Predator.class)) {
                    for (int i = 0; i < typePopulationInDistrict - islanderType.maxCapacity; i++) {
                      //  System.out.print(predators.get(predators.indexOf(districtPopulation.get(islanderType).get(i))));
                       predators.remove(districtPopulation.get(islanderType).get(i));
                    }
                }
                if (islanderType.clazz.getSuperclass().equals(Herbivore.class)) {
                    for (int i = 0; i < typePopulationInDistrict - islanderType.maxCapacity; i++) {
                     //   System.out.print(herbivores.get(herbivores.indexOf(districtPopulation.get(islanderType).get(i))));
                        herbivores.remove(districtPopulation.get(islanderType).get(i));
                    }
                }

                if (islanderType.clazz.equals(Plant.class)) {
                    for (int i = 0; i < typePopulationInDistrict - islanderType.maxCapacity; i++) {
                        plants.remove(districtPopulation.get(islanderType).get(i));
                    }
                }
            }
        }
    }

}
