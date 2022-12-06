
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import Generators.TableGenerator;
import Generators.MenuGenerator;
import Generators.PartyGenerator;

import rstntSimObjects.*;
import rstntSimObjects.utilities.MetricsUtility;


import static rstntSimObjects.utilities.Constants.MINUTES_TO_RUN_SIMULATION;

public class Main {
    private static List<Party> allPartiesInSimulation = new ArrayList<>();
    public static void printStats(){
        MetricsUtility utility = new MetricsUtility(allPartiesInSimulation);
        newSection();
        System.out.println("Total Parties That Entered Restaurant: " + utility.totalPartiesThatEnteredRestaurant());
        System.out.println();
        newSection();
        System.out.println("Total Parties Seated: " + utility.totalPartiesThatGotSeated());
        newSection();
        System.out.println("Average Wait Time for Parties that Got Seated (In Minutes): " + utility.averageWaitTimeForSeatedParties());
        newSection();
        System.out.println("Average Wait Time for Parties that Did Not Get Seated (In Minutes): " + utility.averageWaitTimeForNeverSeatedParties());
        newSection();
        System.out.println("Distribution of Parties that Did Not Get Seated (Party Size: Number of Parties): ");
        Map<Integer,Integer> partiesNotSeated = utility.getDistributionOfNeverSeatedParties();
        printMap(partiesNotSeated);
        newSection();
        System.out.println("Average Sit Down Time of Parties (In Minutes): " + utility.averageSitDownTimeOfPartiesWhoCompletedMeals());
        newSection();
        System.out.println("Average Total Time of Full Journey (In Minutes): " + utility.averageTotalFullJourneyTime());
        newSection();
        System.out.println("Total Menu Items Ordered: ");
        printMap(utility.numberOfMenuItemsOrdered());
        newSection();
        System.out.println("Total Ingredients Used: ");
        printMap(utility.numberOfMenuIngredientsUsed());
        System.out.println();
        System.out.println("You have reached the end of the statistics for the most recent two-hour shift.");
    }

    private static <T> void printMap(Map<T, Integer> partiesNotSeated) {
        partiesNotSeated.forEach((key, value)-> System.out.println(key + ": " + value));
    }

    private static void newSection() {
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    public static void main(String... args) throws IOException, InterruptedException {

        Restaurant restaurant = new Restaurant(
                TableGenerator.generateTables(),
                MenuGenerator.generateMenu()

        );

        for (int i =0; i< MINUTES_TO_RUN_SIMULATION;i++){
            restaurant.incrementTimeWaitedInQueue();
            Optional<Party> newParty = PartyGenerator.generateParty();
            if(newParty.isPresent()){
                System.out.println("We got a new party of size: " + newParty.get().getMembers().size());
                allPartiesInSimulation.add(newParty.get());
                restaurant.addPartyToQueue(newParty.get());
            }
            List<Table> occupiedTables = restaurant.getOccupiedTables();
            for(Table table : occupiedTables) {
                table.getSeatedParty().decrementTimeLeftBeforeLeaving();
                if(table.getSeatedParty().isDone()) {
                    System.out.println("Buh-bye party! Unseating party from table.");
                    table.unseatParty();
                }
            }
            List<Table> unoccupiedTables = restaurant.getUnoccupiedTables();
            for(Table table : unoccupiedTables){
                TableType tableType = table.getTableType();
                Optional<Party> nextParty = restaurant.getNextParty(tableType);
                if(nextParty.isPresent()){
                    System.out.println("A party can be seated. Your table is this way.");
                    table.seatParty(nextParty.get());
                    System.out.println("Have you decided what you would like to order? The party is generating an order");
                    nextParty.get().generateOrder(restaurant.getMenu());
                }
            }
            //Thread.sleep(1500);
        }
        System.out.println("2 hour shift complete!");

        printStats();
    }





}

