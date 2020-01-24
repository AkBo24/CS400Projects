import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PracticeIO {
    private static final Scanner num = new Scanner(System.in);
    private static final File logger = new File("Log.txt");

    public static void main(String[] args) {
        String kPlay = "";
        
        int uNum = 0;
        
        System.out.print("Welcome to Akshay Bodla's Project0\n");
        System.out.println("===== email: bodla@wisc.edu ======");
        System.out.println("======= Lecture Number: 001 ======");
        
        do {
            System.out.println("\n\nWhat would you like to do(enter a number between 1-2)");
            System.out.println("Option 1: Play a guessing game!");
            System.out.println("Option 2: Write a story?");
            
            try {
                uNum = num.nextInt();
                while(!validate(uNum, 1, 2)) {
                    System.out.println("enter a number between 1-2");
                    uNum = num.nextInt();
                }
                System.out.println();
                
                if(uNum == 1)
                    playGuessingGame();
                else if(uNum == 2)
                    writeStory();
                
                
            } catch (java.util.InputMismatchException e) {
                System.out.println("Input mismatch");
            }
            
            kPlay = keepPlaying(num, kPlay);            
            
        } while(!kPlay.equals("exit"));
        
        num.close();
    }

    private static void writeStory() {
        
        readStory(new File("Input"));
        
        num.nextLine();
        String story = num.nextLine();
        
        try {
            File gameFile = new File(story);
            gameFile.createNewFile();
            
            PrintWriter pw = new PrintWriter(gameFile);            

            pw.println(story + ":");
            pw.println("We had done it!  The AI successfully created human emotions.");
            
            System.out.println("Now, how will you end our sentence?");
            String end = num.nextLine();
            pw.println(end);
            pw.close();
            
            readStory(gameFile);
            
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void readStory(File gameFile) {
        Scanner readStory = null;
        
        try {
            readStory = new Scanner(gameFile);
            
            while(readStory.hasNext())
                System.out.println(readStory.nextLine());
            
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        System.out.println();
    }

    private static void playGuessingGame() {
        int randNum = (int)(Math.random()*10)+1,
            uGuess  = 0;
        int guess = 5;
        
        System.out.println("Guess a random number between 1 and 10");
        System.out.println("You have " + guess + " guess's");
        
        for(int i = 0; i < 5; i++) {
             uGuess = num.nextInt(); 
             while(!validate(uGuess, 1, 3)) {
                 System.out.println("Guess a random number between 1 and 10");
                 uGuess = num.nextInt(); 
             }
             
             if(uGuess == randNum) {
                 System.out.println("Congrats! You guessed the correct number");
                 return;
             }
             
             else {
                 System.out.println("You guessed incorrectly, try again");
                 System.out.println("You have " + --guess + " guess's left");
             }
        }
        System.out.println();
    }

    private static String keepPlaying(Scanner in, String kPlay) {
        System.out.println("Would you like to keep exploring? Exit to stop");
        
        if(in.hasNext())
            in.nextLine();

        kPlay = in.nextLine();
        kPlay = kPlay.toLowerCase();
        return kPlay;
    }
    
    private static boolean validate(int num, int min, int max) {
       return  (1 <= min) && (3 >= max);
    }
}
