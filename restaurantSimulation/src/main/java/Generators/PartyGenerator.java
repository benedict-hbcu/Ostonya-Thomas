package Generators;

import rstntSimObjects.utilities.Constants;
import rstntSimObjects.utilities.OrderUtility;
import rstntSimObjects.Party;
import rstntSimObjects.Person;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PartyGenerator {
    private PartyGenerator() {
    }
    private static List<String> names;
    public static  String namesFile = ("/Users/bcinternship2/git/Ostonya-Thomas/restaurantSimulation/src/main/resources/names.txt");

    public static Optional<Party> generateParty(){
        if (!OrderUtility.weightedCoinFlip(Constants.PROBABILITY_PARTY_ENTERS_RESTAURANT)){
            return Optional.empty();
        }
        int numMembers = OrderUtility.pickItemFromMap(Constants.PROBABILITY_DISTRIBUTION_OF_PARTY_SIZE);
        return Optional.of(generatePartyOfSize(numMembers));
    }

    private static Party generatePartyOfSize(int size) {
        List <Person> members = new ArrayList<>();
        for (int i = 0; i<size; i++){
            String name = generateRandomName();
            members.add(new Person(name));
        }
        return new Party(members);
    }

    private static List<String> getNames(){
        if (names==null){
            try {
                names= Files.readAllLines(new File(namesFile).toPath());
            } catch (IOException e) {
                System.out.println("There was an issue reading the names file.");
                throw new RuntimeException(e);
            }

        }
        return names;
    }
    private static String generateRandomName(){
        //OrderUtility.pickRandomElementFromList(getNames())
        return OrderUtility.pickRandomElementFromList(getNames());
    }
}
