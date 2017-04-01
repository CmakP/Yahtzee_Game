package game.java.se.yahtzee.util;
import java.util.Scanner;

/**
 * InputReader Class - reads user input.
 * 
 * @author Siamak Pourian
 * @Ver. Jan 12, 2016
 */
public class InputReader
{
    private Scanner scanner;
    
    /**
     * Create a new InputReader to read user input.
     */
    public InputReader()
    {
        scanner = new Scanner(System.in);   //For keyboard input.
        
    }
    
    /**
     * @return the user's input as a String.
     */
    public String getInput()
    {
        return scanner.nextLine();
    }
}