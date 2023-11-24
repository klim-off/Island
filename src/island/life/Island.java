package island.life;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Island {
    private static final int LENGTH = 5;
    private static final int WIDTH = 5;
    private static IslandDistrict[][] islandDistricts = new IslandDistrict[LENGTH][WIDTH];
    private static ArrayList<Islander> movingHerbivoresOnIsland = new ArrayList<>();
    private static ArrayList<Islander> movingPredatorsOnIsland = new ArrayList<>();
    private static   int weightLossPerMultiply = 10;
    private static   int lifePeriod = 365;
    private static boolean gameOver=false;



    public static void main(String[] args) {
        createIsland();
        createIslanders();
        for (int i = 1; i <= lifePeriod; i++) {
            System.out.println("***************** " + i + " Day of the island.life.Island *******************");
            moveIslandersBetweenDistricts();
            eatingByIslanders();
            mulptiplyIslanders();
            if (gameOver) {break;}
        }
        System.out.println();
        System.out.println("********** GAME OVER ***********");
    }

    private static void moveIslandersBetweenDistricts() {
        System.out.println("Islanders are moving between Districts");
        moveOutDistrictsIsland();
        setGameStatus();
        moveIntoDistrictsIsland();
        setIslandPopulation();
    }

    private static void setGameStatus (){
        if (movingHerbivoresOnIsland.isEmpty()) {gameOver = true;}
        if (movingPredatorsOnIsland.isEmpty()) {gameOver = true;}
    }

    private static void eatingByIslanders() {
        System.out.println("Islanders have eaten");
        for (int x = 0; x < LENGTH; x++) {
            for (int y = 0; y < WIDTH; y++) {
               if (islandDistricts[x][y].herbivores.size() > 0) {
                    herbivoresEatingHerbivoresAtDistrict(islandDistricts[x][y].herbivores);
                }
                if (islandDistricts[x][y].herbivores.size() > 0) {
                    islandersEatingAtDistrict(islandDistricts[x][y].herbivores, islandDistricts[x][y].plants);
                }
                if (islandDistricts[x][y].predators.size() > 0) {
                    islandersEatingAtDistrict(islandDistricts[x][y].predators, islandDistricts[x][y].herbivores);
                }
            }
        }
       setIslandPopulation();
    }

    private static void islandersEatingAtDistrict(ArrayList<Islander> hunters, ArrayList<Islander> victims) {
        double foodVolume = 0;
        boolean canEat = true;
        int victimNumber;

        for (Islander hunter : hunters) {
            if (victims.isEmpty()) {
                break;
            }
            if (hunter.weight >= hunter.islanderType.normalWeight) {
                continue;
            }
            victimNumber = ThreadLocalRandom.current().nextInt(0, victims.size());
            foodVolume = hunter.islanderType.foodVolume;
            while (foodVolume > 0 && victimNumber < victims.size()) {
                if (hunter.getAbleToEat(victims.get(victimNumber).islanderType) == null) {
                    break;
                }
                canEat = ThreadLocalRandom.current().nextInt(0, 101) <=
                        hunter.getAbleToEat(victims.get(victimNumber).islanderType);
                if (!canEat) {
                    break;
                }
                foodVolume -= victims.get(victimNumber).weight;
                victims.remove(victimNumber);
                victimNumber = victims.isEmpty()? victimNumber: ThreadLocalRandom.current().nextInt(0, victims.size());
                if (foodVolume <= 0) {
                    hunter.weight = hunter.islanderType.normalWeight;
                }
            }
        }
       }


    private static void herbivoresEatingHerbivoresAtDistrict(ArrayList<Islander> herbivores) {
        ArrayList<Islander> canEatHerbivores = new ArrayList<>();
        ArrayList<Islander> eatableHerbivores = new ArrayList<>();
        for (Islander herbivore : herbivores) {
          if (checkHerbovoreDish(herbivore)) {
              canEatHerbivores.add(herbivore);
          }else {eatableHerbivores.add(herbivore);}
        }
        herbivores.clear();
        islandersEatingAtDistrict(canEatHerbivores, eatableHerbivores);

        for (Islander canEatHerbivore : canEatHerbivores) {
            herbivores.add(canEatHerbivore);
        }
        for (Islander eatableHerbivore : eatableHerbivores) {
            herbivores.add(eatableHerbivore);
        }
    }

    private static boolean checkHerbovoreDish(Islander herbivore) {
        if (herbivore.getAbleToEat(IslanderType.CATERPILLAR) == null){
            return false;
        } return true;
    }


    private static void mulptiplyIslanders() {
        System.out.println("The Islanders have multiplied");
        for (int x = 0; x < LENGTH; x++) {
            for (int y = 0; y < WIDTH; y++) {
                for (Map.Entry<IslanderType, ArrayList<Islander>> EntryIslander : islandDistricts[x][y].districtPopulation.entrySet()) {
                    if (EntryIslander.getValue().size() > 1) {
                        for (int i = 0; i < EntryIslander.getValue().size() / 2; i++) {
                            if (islandDistricts[x][y].predators.contains(EntryIslander.getValue().get(0))) {
                                islandDistricts[x][y].predators.add( EntryIslander.getValue().get(0).multiply(x, y));
                            } else if (islandDistricts[x][y].herbivores.contains(EntryIslander.getValue().get(0))) {
                                islandDistricts[x][y].herbivores.add( EntryIslander.getValue().get(0).multiply(x, y));
                            } else {
                                islandDistricts[x][y].plants.add( EntryIslander.getValue().get(0).multiply(x, y));
                            }
                        }//Multiply is Over. Subtract the weight from everyone except the plants who participated in reproduction
                        int countMiltplyIslander = (EntryIslander.getValue().size() % 2 == 0) ? EntryIslander.getValue().size()
                                : EntryIslander.getValue().size() - 1;
                        for (int i = 0; i < countMiltplyIslander; i++) {
                            if (EntryIslander.getValue().get(i).islanderType != IslanderType.PLANT) {
                                EntryIslander.getValue().get(i).weight -= EntryIslander.getValue().get(i).weight / 100 * weightLossPerMultiply;
                            }
                        }
                    }
                }
            }
        }
        setIslandPopulation();
    }
    private static void createIsland() {
        for (int x = 0; x < LENGTH; x++) {
            for (int y = 0; y < WIDTH; y++) {
                islandDistricts[x][y] = new IslandDistrict(x, y);
            }
        }
    }
    private static void createIslanders() {
        System.out.println(" Create Islanders on the island.life.Island");
        Runnable createPredators = new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < LENGTH; x++) {
                    for (int y = 0; y < WIDTH; y++) {
                        islandDistricts[x][y].predators = createPredators(x, y);
                    }
                }
            }
        };

        Runnable createHerbivores = new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < LENGTH; x++) {
                    for (int y = 0; y < WIDTH; y++) {
                        islandDistricts[x][y].herbivores = createHerbivores(x, y);
                    }
                }
            }
        };
        Runnable createPlants = new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < LENGTH; x++) {
                    for (int y = 0; y < WIDTH; y++) {
                        islandDistricts[x][y].plants = createPlants(x, y);
                    }
                }
            }
        };
        Thread createFirstGroup = new Thread(createPredators);
        Thread createSecondGroup = new Thread(createHerbivores);
        Thread createThirdGroup = new Thread(createPlants);
        createFirstGroup.start();
        createSecondGroup.start();
        createThirdGroup.start();
        try {
            createFirstGroup.join();
            createSecondGroup.join();
            createThirdGroup.join();
        } catch (InterruptedException e) {
            System.out.println(" Sorry my friend ...  we can't create island.life.Island for you... Try again..");
            throw new RuntimeException(e);
        }
       setIslandPopulation();
    }

    private static void moveOutDistrictsIsland() {

        Runnable moveOutPredatots = new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < LENGTH; x++) {
                    for (int y = 0; y < WIDTH; y++) {
                        for (Islander predator : islandDistricts[x][y].predators) {
                            predator.move(LENGTH, WIDTH);
                            movingPredatorsOnIsland.add(predator);
                        }
                        islandDistricts[x][y].predators.clear();
                    }
                }
            }
        };
        Runnable moveOutHerbivores = new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < LENGTH; x++) {
                    for (int y = 0; y < WIDTH; y++) {
                        for (Islander herbivore : islandDistricts[x][y].herbivores) {
                            herbivore.move(LENGTH, WIDTH);
                            movingHerbivoresOnIsland.add(herbivore);
                        }
                        islandDistricts[x][y].herbivores.clear();
                    }
                }

            }
        };
        Thread moveFirstGroup = new Thread(moveOutPredatots);
        Thread moveSecondGroup = new Thread(moveOutHerbivores);
        moveFirstGroup.start();
        moveSecondGroup.start();
        try {
            moveFirstGroup.join();
            moveSecondGroup.join();
        } catch (InterruptedException e) {
            System.out.println("Sorry my friend ... we can't move animals between district..");
            throw new RuntimeException(e);
        }
    }

    private static void moveIntoDistrictsIsland() {
       Runnable predatorMoveInDistrict = new Runnable() {
           @Override
           public void run() {
               for (Islander movingPredator : movingPredatorsOnIsland) {
                   int x = movingPredator.x;
                   int y = movingPredator.y;
                   if (movingPredator.weight > movingPredator.islanderType.normalWeight / 5) { //перевести в переменную %
                       islandDistricts[x][y].predators.add((Islander) movingPredator);
                   } else {// can print have died Islanders then they are moving between District
                    //   System.out.print(" " +movingPredator.islanderType + " " + movingPredator.islanderType.normalWeight + " " + movingPredator.weight);
                   }
               }
               movingPredatorsOnIsland.clear();
           }
       };
     Runnable herbivoresMoveInDistrict = new Runnable() {
           @Override
           public void run() {
               for (Islander movingHerbivore : movingHerbivoresOnIsland) {
                  int  x = movingHerbivore.x;
                   int y = movingHerbivore.y;
                   if (movingHerbivore.weight > movingHerbivore.islanderType.normalWeight / 5) { //перевести в переменную %
                       islandDistricts[x][y].herbivores.add((Herbivore) movingHerbivore);
                   } else {// can print have died Islanders
                     //  System.out.print(" " + movingHerbivore.islanderType + " " + movingHerbivore.islanderType.normalWeight + " " + movingHerbivore.weight);
                   }
               }
               movingHerbivoresOnIsland.clear();
           }
       };
        Thread moveFirstGroup = new Thread(predatorMoveInDistrict);
        Thread moveSecondGroup = new Thread(herbivoresMoveInDistrict);

        moveFirstGroup.start();
        moveSecondGroup.start();

        try {
            moveFirstGroup.join();
            moveSecondGroup.join();
        } catch (InterruptedException e) {
            System.out.println("Sorry my friend ... we can't move animals between district..");
            throw new RuntimeException(e);
        }

    }
    private static ArrayList<Islander> createPredators(int x, int y) {
        ArrayList<Islander> predators = new ArrayList<>();
        for (IslanderType value : IslanderType.values()) {
            int NumberSpecies = ThreadLocalRandom.current().nextInt(0, value.maxCapacity+1);
            Class[] param = {int.class, int.class};
            if (value.clazz.getSuperclass().equals(Predator.class)) {
                for (int i = 0; i < NumberSpecies; i++) {
                    try {
                        predators.add((Predator) value.clazz.getConstructor(param).newInstance(x, y));
                    } catch (Exception e) {
                        System.out.println("can't create Predators");
                    }
                }
            }
        }
        return predators;
    }
    private static ArrayList<Islander> createHerbivores(int x, int y) {
        ArrayList<Islander> herbivores = new ArrayList<>();
        for (IslanderType value : IslanderType.values()) {
            int NumberSpecies = ThreadLocalRandom.current().nextInt(0, value.maxCapacity+1);
            Class[] param = {int.class, int.class};
            if (value.clazz.getSuperclass().equals(Herbivore.class)) {
                for (int i = 0; i < NumberSpecies; i++) {
                    try {
                        herbivores.add((Herbivore) value.clazz.getConstructor(param).newInstance(x, y));
                    } catch (Exception e) {
                        System.out.println("can't create Herbivores");
                    }
                }
            }
        }
        return herbivores;
    }
    private static ArrayList<Islander> createPlants(int x, int y) {
        ArrayList<Islander> plants = new ArrayList<>();
        for (int i = 0; i < IslanderType.PLANT.maxCapacity; i++) {
            Plant plant = new Plant(x, y);
            plants.add(plant);
        }
        return plants;
    }
    private static void setIslandPopulation(){
        censusIslandPopulation();
        adjustIslandPopulation();
        censusIslandPopulation();
       // printIslandPopulationLiterally(); // you can print population at the Districts
         printIslandPopulation();
    }

    public static void censusIslandPopulation() {
        for (int x = 0; x < LENGTH; x++) {
            for (int y = 0; y < WIDTH; y++) {
                islandDistricts[x][y].districtPopulationCentus();
            }
        }
        for (IslanderType islanderType : IslanderType.values()) {
            islanderType.population = 0;
            for (int x = 0; x < LENGTH; x++) {
                for (int y = 0; y < WIDTH; y++) {
                    islanderType.population += islandDistricts[x][y].districtPopulation.get(islanderType).size();
                }
            }
        }
    }

    private static void adjustIslandPopulation() {
        for (int x = 0; x < LENGTH; x++) {
            for (int y = 0; y < WIDTH; y++) {
                islandDistricts[x][y].adjustDistrictPopulation ();
            }
        }
    }
    private static void printIslandPopulationLiterally() {
        for (int x = 0; x < LENGTH; x++) {
            for (int y = 0; y < WIDTH; y++) {
                islandDistricts[x][y].printPopulationDistrict();
            }
        }
    }
    private static void printIslandPopulation() {
        for (IslanderType value : IslanderType.values()) {
            System.out.print(value.icon + ": " + value.population + "; ");
        }
        System.out.println();
    }
}
