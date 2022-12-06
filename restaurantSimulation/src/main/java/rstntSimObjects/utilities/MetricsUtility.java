package rstntSimObjects.utilities;

import model.menu.Appetizer;
import model.menu.Dessert;
import model.menu.Entree;
import rstntSimObjects.Ingredient;
import rstntSimObjects.Party;

import java.util.*;

public class MetricsUtility {

    private List<Party> totalPartiesFromSimulation;

    public MetricsUtility(List<Party> totalPartiesFromSimulation){
        this.totalPartiesFromSimulation = totalPartiesFromSimulation;
    }

    public int totalPartiesThatEnteredRestaurant(){
        return this.totalPartiesFromSimulation.size();
    }
    public int totalPartiesThatGotSeated(){
        return (int) this.totalPartiesFromSimulation.stream().filter(Party::gotSeated).count();
    }
    public double averageWaitTimeForSeatedParties() {
        List<Integer> allWaitTimes = this.totalPartiesFromSimulation.stream()
                .filter(Party::gotSeated)
                .map(Party::getMinutesInQueue).toList();
        return getAverage(allWaitTimes);
    }
    public double averageWaitTimeForNeverSeatedParties() {
        List<Integer> allWaitTimes = this.totalPartiesFromSimulation.stream()
                .filter(x->!x.gotSeated())
                .map(Party::getMinutesInQueue).toList();
        return getAverage(allWaitTimes);
    }
    public Map<Integer,Integer> getDistributionOfNeverSeatedParties(){
        List<Party> neverSeatedParties = this.totalPartiesFromSimulation.stream()
                .filter(x->!x.gotSeated()).toList();
        Map<Integer,Integer> distribution = new HashMap<>();
        for (Party party : neverSeatedParties){
            addToMap(distribution, party.getMembers().size());
        }

        return distribution;
    }
    public double averageSitDownTimeOfPartiesWhoCompletedMeals(){
        List<Integer> sitDownTimes = this.totalPartiesFromSimulation.stream()
                .filter(Party::isDone)
                .map(Party::getTotalMinutesSeated)
                .toList();
        return getAverage(sitDownTimes);
    }

    public double averageTotalFullJourneyTime(){
        List<Integer> totalTimes = this.totalPartiesFromSimulation.stream()
                .filter(Party::isDone)
                .map(x->x.getMinutesInQueue()+x.getTotalMinutesSeated())
                .toList();
        return getAverage(totalTimes);
    }

    public Map<String,Integer> numberOfMenuItemsOrdered(){
        Map<String,Integer> itemsOrdered = new HashMap<>();
        for (Party party : this.totalPartiesFromSimulation){
            if (party.gotSeated()){
                Optional<Appetizer> app = party.getOrder().getAppetizer();
                app.ifPresent(appetizer -> addToMap(itemsOrdered, appetizer.getName()));
                Set<Entree> mains = party.getOrder().getMainCourses();
                mains.forEach(entree->addToMap(itemsOrdered,entree.getName()));
                Optional<Dessert> dsrt = party.getOrder().getDessert();
                dsrt.ifPresent(dessert -> addToMap(itemsOrdered, dessert.getName()));
            }
        }
        return itemsOrdered;
    }

    public Map<String,Integer> numberOfMenuIngredientsUsed() {
        Map<String, Integer> ingredientsUsed = new HashMap<>();
        for (Party party : this.totalPartiesFromSimulation) {
            if (party.gotSeated()) {
                Optional<Appetizer> app = party.getOrder().getAppetizer();
                app.ifPresent(appetizer -> {
                    Map<String, Integer> ingredients = appetizer.getIngredients();
                    for (Map.Entry<String, Integer> e : ingredients.entrySet()){
                        String ingredientName = e.getKey();
                        int amountOfIngredient = e.getValue();
                        for (int i = 0; i < amountOfIngredient;i++){
                            addToMap(ingredientsUsed,ingredientName);
                        }
                    }
                });
                Set<Entree> mains = party.getOrder().getMainCourses();
                mains.forEach(entree->{
                    Map<String, Integer> ingredients = entree.getIngredients();
                    for (Map.Entry<String, Integer> e : ingredients.entrySet()){
                        String ingredientName = e.getKey();
                        int amountOfIngredient = e.getValue();
                        for (int i = 0; i < amountOfIngredient;i++){
                            addToMap(ingredientsUsed,ingredientName);
                        }
                    }
                });
                Optional<Dessert> dsrt = party.getOrder().getDessert();
                dsrt.ifPresent(dessert -> {
                    Map<String, Integer> ingredients = dessert.getIngredients();
                    for (Map.Entry<String, Integer> e : ingredients.entrySet()){
                        String ingredientName = e.getKey();
                        int amountOfIngredient = e.getValue();
                        for (int i = 0; i < amountOfIngredient;i++){
                            addToMap(ingredientsUsed,ingredientName);
                        }
                    }
                });


            }
        }
        return ingredientsUsed;
    }

    private <T> void addToMap(Map<T, Integer> distribution, T itemToAdd) {
        if (!distribution.containsKey(itemToAdd)){
            distribution.put(itemToAdd,0);
        }
        int oldCount = distribution.get(itemToAdd);
        distribution.put(itemToAdd,oldCount+1);
    }

    private static double getAverage(List<Integer> list) {
        double sum = 0.0;
        for (int i : list){
            sum+=i;
        }
        return sum / list.size();
    }

}
