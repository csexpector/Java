/**
 * CS312 Assignment 6.
 *
 * On my honor, Mark Castaneda, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to play Hangman.
 *
 *  email address: markcastaneda@utexas.edu
 *  UTEID: mac8397
 *  Unique 5 digit course ID: 50590
 *  Grader name: Sonika
 *  Number of slip days used on this assignment:
 */

import java.util.Scanner;

public class Hangman1 {
    public static final int MAX_WRONG_GUESSES = 5;
    public static int wrongGuesses = 0;
    public static String currentPhrase = "";
    public static String alphabet = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z ";
    public static String alphabet_fix = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z ";
    public static void main(String[] args) {
        intro();
        PhraseBank phrases = buildPhraseBank(args);
        // CS312 Students -- Do not create any additional Scanners.
        Scanner keyboard = new Scanner(System.in);

        // CS12 students: add your code here
        while (true) {
            playOneGame(keyboard, phrases);
            if ( !playAgain(keyboard) ) break;
        }
        keyboard.close();
    }
    // CS12 students: add your methods here.
    
    //get the topic for the phrases
    public static String topicName(PhraseBank phrases) {
        String topic = phrases.getTopic();
        System.out.println("I am thinking of a " + topic + " ...");
        return topic;
    }
    
    //get the next phrase from the phrase bank
    public static String getPhrase(PhraseBank phrases) {
        currentPhrase = "";
        String phrase = phrases.getNextPhrase();
        System.out.print("The current phrase is ");
        //turns phrase into series of atserisks and underscores
        for(int i = 0; i < phrase.length(); i++) {
            if(phrase.charAt(i) == '_') {           //convert spaces to underscores
                System.out.print("_");
                currentPhrase += "_";
            } else {                                //convert letters to atserisks
                System.out.print("*");
                currentPhrase += "*";
            }
        }
        System.out.println();
        return phrase;
    }
    
    //show letters that have not been guessed
    public static void displayLetters(String phrase) {
        System.out.println("The letters you have not guessed yet are: ");
        char character;
        for(int i = 0; i < alphabet.length(); i++) {
           character = alphabet.charAt(i);
           if(character != '*') {
            System.out.print(character);
           }
        }
        System.out.println();
    }
    
    //get the user's guess
    public static String getGuess(Scanner keyboard, PhraseBank phrases, String phrase) {
        System.out.print("Enter your next guess: ");
        String guess = (keyboard.next()).toUpperCase();
        //System.out.println("Alphabet: " + alphabet);
        if(alphabet.contains(guess) && !guess.equals("*")) {
            System.out.println("You guessed " + guess + ".");
            guessResults(guess, phrase);
            storeGuesses(guess, phrase);
            displayCurrentPhrase(guess, phrase);
        } else {
            System.out.println(guess + " is not a valid guess.");            
        }
        return guess;
    }
    
    //shows results of valid guess
    public static void guessResults(String guess, String phrase) {
        if(phrase.contains(guess)) {        //correct guess
             System.out.println("That is present in the secret phrase.");
             System.out.println("Number of wrong guesses so far: " + wrongGuesses);
            } else {                        //incorrect guess
             System.out.println("That is not present in the secret phrase.");
             wrongGuesses++;
             System.out.println("Number of wrong guesses so far: " + wrongGuesses);
            }
    }
    
    //reveals correctly guessed letters
    public static void displayCurrentPhrase(String guess, String phrase) {
        String letters = "";
        //System.out.println("Check: " + currentPhrase + " vs " + phrase);
        if (wrongGuesses != MAX_WRONG_GUESSES && !currentPhrase.equals(phrase)) {
            
            for(int i = 0; i < phrase.length(); i++) {
                if(phrase.charAt(i) == '_' || phrase.charAt(i) == guess.charAt(0)) {
                    letters = letters + phrase.charAt(i);
                } else {
                    letters = letters + currentPhrase.charAt(i);
               }
            }
            currentPhrase = letters;
            if (wrongGuesses != MAX_WRONG_GUESSES && !currentPhrase.equals(phrase)) 
            {
                System.out.print("The current phrase is ");
                System.out.println(currentPhrase);
            }
        }
    }
    
    //stores valid guesses to avoid repeated guesses
    public static void storeGuesses(String guess, String phrase) {
        String letters2 = "";
        for(int i = 0; i < alphabet.length(); i++) {
            if(alphabet.charAt(i) == guess.charAt(0)) {
                    letters2 = letters2 + "*";
                    i++;
            } else {
                    letters2 = letters2 + alphabet.charAt(i);
            }
        }
        alphabet = letters2;
    }
    
    //plays one game of hangman
    public static void playOneGame(Scanner keyboard, PhraseBank phrases) {
        topicName(phrases);
        wrongGuesses = 0;
        alphabet = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z ";
        String phrase = getPhrase(phrases);
        while(wrongGuesses < MAX_WRONG_GUESSES && currentPhrase.contains("*")) {
        //while (true) {
            displayLetters(phrase);
            String g = getGuess(keyboard, phrases, phrase);
            if(wrongGuesses == MAX_WRONG_GUESSES) {     //user loses
                System.out.println("You lose. The secret phrase was " + phrase);
                //break;
            }
            if (!currentPhrase.contains("*")) {         //user wins
                System.out.println("The phrase is " + phrase + ".");
                System.out.println("You win!!");
                //break;
            }
            //displayCurrentPhrase(g, phrase);
        }
    }
    
    //asks the user if they would like to play again
    public static boolean playAgain(Scanner keyboard) {
        System.out.println("Do you want to play again?");
        System.out.print("Enter 'Y' or 'y' to play again: ");
        String response = keyboard.next();
        return (response.equals("y") || response.equals("Y")); //{
            //playOneGame(keyboard, phrases);                  //begins new game
        //}
    }
    

    // Build the PhraseBank.
    // If args is empty or null, build the default phrase bank.
    // If args is not null and has a length greater than 0
    // then the first elements is assumed to be the name of the
    // file to build the PhraseBank from.
    public static PhraseBank buildPhraseBank(String[] args) {
        PhraseBank result;
        if(args == null || args.length == 0
                || args[0] == null || args[0].length() == 0)
            result =  new PhraseBank();
        else
            result = new PhraseBank(args[0]);
        return result;
    }

    // show the intro to the program
    public static void intro() {
        System.out.println("This program plays the game of hangman.");
        System.out.println("The computer will pick a random phrase.");
        System.out.println("Enter letters for your guess.");
        System.out.println("After 5 wrong guesses you lose.");
    }
}