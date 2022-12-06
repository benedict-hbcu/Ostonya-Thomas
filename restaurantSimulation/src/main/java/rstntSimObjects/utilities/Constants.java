package rstntSimObjects.utilities;

import rstntSimObjects.TableType;

import java.util.Map;
public class Constants {
    private Constants(){

    }

    public static final int MINUTES_TO_RUN_SIMULATION = 120;

    public static final double PROBABILITY_PARTY_ORDERS_APPETIZER=0.3;
    public static final double PROBABILITY_PARTY_ORDERS_DESSERT=0.4;

    public static final double PROBABILITY_PARTY_ENTERS_RESTAURANT = 0.20;

    public static  Map<Integer,Double> PROBABILITY_DISTRIBUTION_OF_PARTY_SIZE= Map.of(
            1, 0.10,
            2, 0.15,
            3, 0.15,
            4, 0.30,
            5, 0.15,
            6, 0.15
    );

    public static Map<TableType, Integer> TABLE_TYPE_DISTRIBUTION = Map.of(
            TableType.SMALL, 2,
            TableType.MEDIUM, 4,
            TableType.LARGE, 6
    );
}
