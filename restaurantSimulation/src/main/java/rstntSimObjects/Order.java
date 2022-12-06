package rstntSimObjects;
import model.menu.Appetizer;
import model.menu.Dessert;
import model.menu.Entree;
import rstntSimObjects.utilities.OrderUtility;

import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

public class Order {
    private Optional <Appetizer> appetizer;
    private Set<Entree> entrees;
    private Optional<Dessert> dessert;

    public Order(){
        this.appetizer = Optional.empty();
        this.dessert = Optional.empty();
    }

    public void setAppetizer(Appetizer appetizer) {
        this.appetizer = Optional.ofNullable(appetizer);
    }

    public void addMainCourses(Entree entree) {
        if (this.entrees == null){
            this.entrees= new HashSet<>();
        }
        this.entrees.add(entree);
    }

    public void setDessert(Dessert dessert) {
        this.dessert = Optional.ofNullable(dessert);
    }

    public Optional<Appetizer> getAppetizer() {
        return appetizer;
    }

    public Set<Entree> getMainCourses() {
        return entrees;
    }

    public Optional<Dessert> getDessert() {
        return dessert;
    }
    public int totalTimeToCompleteOrder(){
        int totalTime=0;
        if(this.appetizer.isPresent()){
            totalTime+= OrderUtility.getTotalTimeToPrepareItem(this.appetizer.get());
            totalTime+=this.appetizer.get().getTimeToEat();
        }
        totalTime+= OrderUtility.getMaxTimeToPrepare(this.entrees);
        Set<Entree>itemsSentBack = OrderUtility.getItemsSentBack(this.entrees);
        totalTime += OrderUtility.getMaxTimeToPrepare(itemsSentBack);
        totalTime+=OrderUtility.getMaxTimeToEat(this.entrees);
        if (this.dessert.isPresent()) {
            totalTime+=OrderUtility.getTotalTimeToPrepareItem(this.dessert.get());
            totalTime+=this.dessert.get().getTimeToEat();
        }

        return totalTime;
    }

}
