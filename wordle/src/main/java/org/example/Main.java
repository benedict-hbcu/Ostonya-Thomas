package org.example;
import java.util.Scanner;
import static org.example.Util.Result.HIT;
import static org.example.Util.Result.SEMI_HIT;
import static org.example.Util.Result.MISS;



public class Main {
    public static void main(String[] args) {
        String randomWord = Util.getRandomWord(); //get random word
        char[] randomWordchars = randomWord.toCharArray(); //make random word string into array of characters

        //Intro Message
        String banner = "*=+=+=+=+=+=+=+=+=+=+=+=*";
        String wordleWelcome = "*=+ WELCOME TO WORDLE +=*";
        //Print Intro Message
        System.out.println(banner);
        System.out.println(wordleWelcome);
        System.out.println(banner+"\n");
//        System.out.println(randomWord); // testing casing setting

        Scanner myInput = new Scanner(System.in);  //making input object

        int i = 1; //initializing i at 1 to print our word attempts
        while (i < 7) { //loops for six five-letter-word attempts
            System.out.println("\nEnter your word (attempt " + String.valueOf(i) + " of 6):"); //Prompt to enter word
            String word = myInput.nextLine(); //reading input from user and storing it in string
            int wordLength = word.length(); //retrieving length of entered word
            if (wordLength != 5) { //If the word isn't exactly five letters end don't count attempt and warn user
                System.out.println("Your word must have exactly 5 (five) letters.");
            } else {
                i++; //count attempt
                char[] wordChars = word.toCharArray(); //converts word into array of characters
                for (int j = 0; j < 5; j++) {//looping through five letters
                    //if someone types in an uppercase letter Character.toLowerCase() converts it to lowercase when comparing
                    if (randomWord.indexOf(Character.toLowerCase(wordChars[j])) != -1) { //if the letter is in the word; this returns -1 if it's not present
                        if (randomWordchars[j] == Character.toLowerCase(wordChars[j])) { // check if the letter is in the right place
                            System.out.print(Util.getFormattedLetter(wordChars[j], HIT)); //if it is, it's a HIT!
                        } else {
                            System.out.print(Util.getFormattedLetter(wordChars[j], SEMI_HIT)); //if it is not in the same place, it's a SEMI_HIT.
                        }
                    } else {
                        System.out.print(Util.getFormattedLetter(wordChars[j], MISS)); //if it is not in the word at all, it's a MISS.
                    }
                }
                if (word.equalsIgnoreCase(randomWord)) {//if someone types in the correct word even if it's with capital letters
                    System.out.println("\nHmmm... What an exquisite vocabulary."); //congratulatory messages
                    System.out.println("*=+ CONGRATULATIONS!!! +=*");
                    i = 7; //ends program
                }

            }
        }
        System.out.println("\nThe word was: "+randomWord); //revelation of word
    }
}