package rstntSimObjects;
import rstntSimObjects.utilities.Constants;
import rstntSimObjects.utilities.OrderUtility;

import java.util.List;

public class Party {
    private List <Person> members;
    private Order order;

    private int minutesInQueue;
    private int totalMinutesSeated;
    private int timeLeftBeforeLeaving = Integer.MAX_VALUE;

    public Party(List<Person> members) {
        this.members = members;
    }

    public void markStillInQueue () {
        this.minutesInQueue++;
    }
    public void decrementTimeLeftBeforeLeaving(){
        this.timeLeftBeforeLeaving--;
    }
    public boolean isDone(){
        return this.timeLeftBeforeLeaving == 0;
    }
    public List<Person> getMembers() {
        return members;
    }

    public int getMinutesInQueue() {
        return minutesInQueue;
    }

    public int getTotalMinutesSeated() {
        return totalMinutesSeated;
    }

    public int getTimeLeftBeforeLeaving() {
        return timeLeftBeforeLeaving;
    }

    public boolean gotSeated() {
        return order!=null;
    }

    public Order getOrder() {
        return order;
    }

    private boolean partyOrdersAppetizer(){
        return OrderUtility.weightedCoinFlip(Constants.PROBABILITY_PARTY_ORDERS_APPETIZER);
    }
    private boolean partyOrdersDessert(){
        return OrderUtility.weightedCoinFlip(Constants.PROBABILITY_PARTY_ORDERS_DESSERT);
    }
    public Order generateOrder(model.menu.Menu menu){
        this.order = new Order();
        if(this.partyOrdersAppetizer()){
            this.order.setAppetizer(OrderUtility.pickItemFromMap(menu.getAppetizers()));
        }

        for(Person person : this.members){
            this.order.addMainCourses(person.pickMainCourse(menu));
        }

        if(this.partyOrdersDessert()){
            this.order.setDessert(OrderUtility.pickItemFromMap(menu.getDesserts()));
        }
        this.totalMinutesSeated = this.order.totalTimeToCompleteOrder();
        this.timeLeftBeforeLeaving = this.totalMinutesSeated;

        return order;
    }
}
