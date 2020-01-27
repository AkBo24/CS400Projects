/**
 * PracticeIO.java created by akshaybodla on MacBook Pro in CS400Project0
 * 
 * Author: Akshay Bodla (bodla@wisc.edu)
 * Date: @date
 * 
 * Course: CS400
 * Semester: Spring 2020
 * Lecture: 001
 * 
 * IDE: Eclipse IDE for Java Developers
 * 
 * Version: 2019-06 (4.12.0)
 * Build id: 20190614-1200
 * 
 * Device:  Akshay's Macbook Pro
 * OS    :  macOS High Sierra
 * Version: Version 10.13.6
 * 
 * List Collaborators: N/A
 * 
 * Other Credits: N/A
 * 
 * Known Bugs: Scanner may not read current line
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author akshaybodla
 * Class that gives user two games to play around on: a guessing game and a short story
 */
public class PracticeIO {
    private static final Scanner num = new Scanner(System.in);
    private static final File log = new File("Log.txt");

    public static void main(String[] args) {
        
        String kPlay = "";
        int uNum = 0;
        
        /**
         * Prints my information
         */
        System.out.print("Welcome to Akshay Bodla's Project0\n");
        System.out.println("===== email: bodla@wisc.edu ======");
        System.out.println("=====  Lecture Number: 001  =====");
        
        /**
         * Loops through the core algorithm
         */
        do {
            System.out.println("\n\nWhat would you like to do(enter a number between 1-2)");
            System.out.println("Option 1: Play a guessing game!");
            System.out.println("Option 2: Write a story?");
            
            try {
                PrintWriter logger = new PrintWriter(log);
                
                uNum = num.nextInt();
                while(!validate(uNum, 1, 2)) { // ensures the user passed in 1 or 2
                    System.out.println("enter a number between 1-2");
                    uNum = num.nextInt();
                }
                System.out.println();
                
                /**
                 * if uNum is 1, play the guessing game
                 * if uNum is 2, write a story
                 */
                if(uNum == 1)
                    playGuessingGame();
                else if(uNum == 2)
                    writeStory();
                
                logger.println("Finished one cycle");
                logger.close();
                
            } catch (java.util.InputMismatchException e) {
                System.out.println("Input mismatch");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            //gets user input if he/she wants to keep playing
            kPlay = keepPlaying(num, kPlay);            
            
        } while(!kPlay.equals("exit"));
        
        num.close();
        
    }

    /**
     * @throws IOException 
     * @param none
     * @throws void
     * This prints the story for the project
     */
    private static void writeStory() throws IOException {
        PrintWriter logger = new PrintWriter(log);
        
        readStory(new File("Output.txt"));
        
        File file1 = new File("Input.txt");
        if (!file1.exists()) {  //if Output does not exist (written in case file is run multiple times)
            file1.createNewFile(); 
        }
        num.nextLine(); //move token to next line
        String in = num.nextLine();
        File file2 = new File(in);
        
        if(file1.renameTo(file2)) //renames the Output.txt to user's choice
          logger.println("Rename Successful");
        else
          logger.println("Rename Not Successful");
        
        PrintWriter pw = new PrintWriter(file2); // to add the story to
        
        System.out.println("\nGreat! Now lets finish this");
        System.out.println("What is your sentence?");
        String story = num.nextLine();
        
        //code to add both stentences to the new file
        pw.println("Here's your 2-sentence story, titled: " + in);
        pw.println("We had done it!  The AI successfully created human emotions.");
        pw.println(story);
        pw.close();
        readStory(file2);
        
        logger.println("Story written successfully");
        logger.close();
    }

    
    /**
     * @param File file
     * @throws void
     * Helper method to writeStory(), read a passed in gameFile
     */
    private static void readStory(File gameFile) {
        //scanner to read the story
        Scanner readStory = null;
        
        try {
            readStory = new Scanner(gameFile);
            
            //Print the lines as long as scanner has a line to read
            while(readStory.hasNext())
                System.out.println(readStory.nextLine());
            
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        System.out.println();
    }
    
    /**
     * @param none
     * @throws void
     * @throws FileNotFoundException
     * Plays the guessing game
     */
    private static void playGuessingGame() throws FileNotFoundException {
        
        //generate a randum number between 1 and 10
        int randNum = (int)(Math.random()*10)+1,
            uGuess  = 0;
        int guess = 5;
        
        System.out.println("Guess a random number between 1 and 10");
        System.out.println("You have " + guess + " guess's");
        
        //User only has 5 chances to guess the number
        for(int i = 0; i < 5; i++) {
            //get hte user's guess
             uGuess = num.nextInt(); 
             while(!validate(uGuess, 1, 10)) { //validates if number is between 1 and 10
                 System.out.println("Guess a random number between 1 and 10");
                 uGuess = num.nextInt(); 
             }
             
             //Prints congratulations if the user guesses the write number
             //Writes to log if user guessed the right number
             if(uGuess == randNum) {
                 System.out.println("Congrats! You guessed the correct number");
                 
                 PrintWriter logger = new PrintWriter(log);
                 logger.println("User did guess the right number!");
                 logger.close();
                 return;
             }
             
             else {
                 System.out.println("You guessed incorrectly, try again");
                 System.out.println("You have " + --guess + " guess's left");
             }
        }
        
        //Writes to log that the user did not guess the number
        PrintWriter logger = new PrintWriter(log);
        logger.println("User did not guess the write number!");
        logger.close();
        
        System.out.println();
    }

    /**
     * 
     * @param Scanner in
     * @param String kPlay
     * @return String
     * Helper to main method to read user input
     */
    private static String keepPlaying(Scanner in, String kPlay) {
        System.out.println("Would you like to keep exploring? Exit to stop");

        //gets input and makes it all lowercase
        kPlay = in.nextLine();
        kPlay = kPlay.toLowerCase();
        return kPlay;
    }
    
    /**
     * 
     * @param num
     * @param min
     * @param max
     * @return boolean
     * 
     * Makes sure the number is between min and max
     */
    private static boolean validate(int num, int min, int max) {
        return  (min <= num) && (max >= num);
     }
}
