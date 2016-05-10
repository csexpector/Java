/**
 * CS312 Assignment 6.
 *
 * On my honor, <NAME>, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to play Hangman.
 *
 *  email address:
 *  UTEID:
 *  Unique 5 digit course ID:
 *  Grader name:
 *  Number of slip days used on this assignment:
 */

import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {
        intro();
        PhraseBank phrases = buildPhraseBank(args);
        // CS312 Students -- Do not create any additional Scanners.
        Scanner keyboard = new Scanner(System.in);

        // CS12 students: add your code here

        keyboard.close();
    }

    // CS12 students: add your methods here.

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
        System.out.println();
        System.out.println("The computer will pick a random phrase.");
        System.out.println("Enter letters for your guess.");
        System.out.println("After 5 wrong guesses you lose.");
    }
}