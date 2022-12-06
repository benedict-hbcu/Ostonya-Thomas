package rstntSimObjects.utilities;

import model.menu.AMenuItem;
import model.menu.Entree;

import java.util.*;


public class OrderUtility {
    public static Set<Entree> getItemsSentBack(Set<Entree>mainCourses){
        Set<Entree> mainCoursesSentBack = new HashSet<>();
        for(Entree mainCourse : mainCourses) {
            boolean isSentBack = weightedCoinFlip(mainCourse.getProbabilityOfBeingSentBack());
            if (isSentBack){
                mainCoursesSentBack.add(mainCourse);
            }
        }
        return mainCoursesSentBack;
    }

    public static int getMaxTimeToPrepare(Set<Entree> mainCourses){
        OptionalInt maxTimeToPrepare= mainCourses.stream().mapToInt(Entree::getTimeToMake).max();
        return maxTimeToPrepare.orElse(0);
    }
    public static int getMaxTimeToEat(Set<Entree> mainCourses){
        OptionalInt maxTimeToEat = mainCourses.stream().mapToInt(Entree::getTimeToEat).max();
        return maxTimeToEat.orElse(0);
    }
    public static int getTotalTimeToPrepareItem(AMenuItem dish){
        int timeToPrepare = dish.getTimeToMake();
        double chanceOfBeingSentBack = dish.getProbabilityOfBeingSentBack();
        boolean isSentBack = weightedCoinFlip(chanceOfBeingSentBack);
        if (isSentBack) {
            timeToPrepare+=dish.getTimeToMake();
        }
        return timeToPrepare;
    }
    public static boolean weightedCoinFlip(double weight){
        double random = Math.random();
        return random<weight;
    }
    public static <T> T pickItemFromMap(Map<T,Double> optionsWithProbability) {
        double totalSum = optionsWithProbability.values().stream().mapToDouble(x->x).sum();
        if(Double.compare(totalSum, 1)!=0) {
            throw new IllegalArgumentException("Probabilities must add to 1");
        }
        List<T> tempList = getListOfWeightedElements(optionsWithProbability);
        return pickRandomElementFromList(tempList);
    }

    private static <T> List <T> getListOfWeightedElements (Map <T, Double> optionsWithProbability){
        List <T> tempList = new ArrayList<>();
        for(Map.Entry<T, Double> entry: optionsWithProbability.entrySet()) {
            double probability = entry.getValue();
            int numCount = (int) (probability*100);
            for (int i = 0; i< numCount; i++){
                tempList.add(entry.getKey());
            }
        }
        return tempList;
    }
    public static <T> T pickRandomElementFromList(List<T> list){
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}

