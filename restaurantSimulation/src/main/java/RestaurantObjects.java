import java.util.List;
import java.util.Queue;
import java.util.Map;

public class RestaurantObjects {
    public class Restaurant{
        List<Table> tables;
        Queue<Party> line;
        Menu menu;
        void addPartyToQueue(Party party);
        Party getNextParty();
    }
    public class Table{
        int numChairs;
        boolean isOccupied;
    }
    public class Party{
        List <Person> members;
        Order orderPlaced;
        boolean ordersAppetizer;
        MenuItem generateAppetizer(Menu menu);
        boolean ordersDessert;
        MenuItem generateDessert(Menu menu);
        Order generateOrder (Menu menu);
        int timeLeftBeforeLeaving(); //time after being seated
        int getTimeWaitedInQueue(); //how long they have been waiting for a table
    }
    public class Person{
        String name;
        MenuItem generateMainCourse(Menu menu);
    }
    public class Menu{
        List<MenuItem>appetizers;
        List<MenuItem>mainCourses;
        List<MenuItem>desserts;
    }
    public class MenuItem{
        String itemName;
        int preparationTime;
        int eatingTime;
        double chanceOfBeingSentBack;
        Map<Ingredient,Integer>ingredients;
    }
    public class Ingredient{
        String name;
        double price;
    }
    public class Order{
        MenuItem appetizer;
        List<MenuItem>mainCourses;
        MenuItem dessert;
        int timeToCook(); //take time of item that takes the longest time to cook
        int timeToEat(); //take time of item that takes the longest time to eat
        int timeToComplete(); //time to cook + time to eat
    }

}
