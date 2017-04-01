package game.java.se.yahtzee;
import java.util.Random;

import game.java.se.yahtzee.util.InputReader;

/**
 * Simplified Yahtzee Game.
 * 
 * @author Siamak Pourian
 * @Ver. Jan 12, 2016
 */
public class Yahtzee 
{
    private Dice[] dice;
    private Score[] score;
    private String[] CATEGORYDESCRIPTION = {"ONES", "TWOS", "THREES", "FOURS", "FIVES", "SIXES", "YAHTZEE", "CHANCE"};
    private String[] CATEGORY = {"a", "b", "c", "d", "e", "f", "g", "h"};
      
    private InputReader reader;
    private int totalScore;
    private int roundNumber;
    private int rollNumber;
    
    public static final int ROUNDS_TO_PLAY = 8;
    public static final int DICE_NUMBERS = 6;
    public static final int FIRST_ROLL = 1;
    public static final int LAST_ROLL = 3;
    public static final int YAHTZEE_POINT = 50;
    
    /**
     * Overoaded constructor for Yahtzee class
     */
    public Yahtzee() 
    {
        dice = new Dice[5];
        score = new Score[8];
        
        reader = new InputReader();
    
        for(int i = 0 ; i < dice.length; i++)
        {
            dice[i] = new Dice(i + 1, false);
        }
        
        for(int i = 0; i < score.length; i++)
        {
            if(i > 5)
            {
                score[i] = new Score(0, 0, CATEGORYDESCRIPTION[i], CATEGORY[i], false, false);
            }
            else {
                score[i] = new Score(i + 1, 0, CATEGORYDESCRIPTION[i], CATEGORY[i], false, false);
            }
         }
        
        requirements();
    }
    
    /**
     * Main method is the point of entry into the
     * program.
     * @param args Command line arguments
     */
  public static void main(String[] args) {
		   Yahtzee firstObject = new Yahtzee();
    	   firstObject.startTheGame();    
	}

    /**
     * Gives you an idea about the game.
     */
    public void requirements() 
    {
        System.out.println("The game consists of 8 rounds. In each round the player\n" + 
                           "rolls 5 dice and then scores the roll in one of 8 categories.\n" +
                           "Choose startTheGame() from the object bench to start playing.");
    }
    
    /**
     * Starts the game. Keeps track of 8 rounds and the 3 rolls within each round.
     */
    public void startTheGame()
    {
         boolean userAnswer;
         
         System.out.println("Welcome to the simplified version of YAHTZEE game!\n" +
                               "Let the game begin by rolling 5 dice. Good Luck!");
        for(roundNumber = 0; roundNumber < ROUNDS_TO_PLAY; roundNumber++)
        {
            for(rollNumber = FIRST_ROLL; rollNumber <= LAST_ROLL; rollNumber++)
            {
                System.out.println("\n*** ROLL DICE " + rollNumber + " of ROUND " + (roundNumber + 1) + " ***\n");
                rollDice();
                System.out.println("Your combination is:\n");
                for(int j = 0; j < dice.length; j++)
                {
                    if(dice[j] == null)
                    {
                        System.out.println("Error!");
                    }
                    else {
                        System.out.print("  " + dice[j].getValue());
                    }
                }
                
                if(rollNumber == LAST_ROLL)
                {
                    userAnswer = true;
                    scoreRoll();
                }
                else {
                    do {
                        System.out.print("\n\nDo you want to score the current roll? (yes/no): ");
                        String userInput = reader.getInput();
                        if(userInput.trim().toLowerCase().equals("yes"))
                        {
                            userAnswer = true;
                            rollNumber = LAST_ROLL + 1;
                            scoreRoll();
                        }
                        else if(userInput.trim().toLowerCase().equals("no"))
                        {
                            userAnswer = true;
                            chooseDice();
                        }
                        else {
                            System.out.println("Must choose yes or no.");
                            userAnswer = false;
                        }
                    }
                    while(!userAnswer);
                }
            }
        }
        totalScore();     
    }
    
    /**
     * Rolls the dice which the player does not want to keep.
     */
    public void rollDice()
    {
        Random randomGenerator = new Random();
        
        for(int i = 0; i < dice.length; i++)
        {
            if(dice[i] == null)
            {
                System.out.println("Error!");
            }
            else {
                if(dice[i].getKeepDice() == false)
                {    
                    dice[i].setValue(randomGenerator.nextInt(DICE_NUMBERS) + 1);
                }
            }
        }
    }
    
    /**
     * Asks the player which dice to keep and which ones to re-roll.
     */
    public void chooseDice()
    {
        boolean userAnswer = true;
        String userInput;
                
        for(int i = 0; i < dice.length; i++)
        {
            do {
                if(dice[i] == null)
                {
                    System.out.println("Error!");
                }
                else {
                    System.out.print("\nKeep die with the value of " +
                                       dice[i].getValue() + " ? (yes/no) : ");
                    userInput = reader.getInput();
                
                    if(userInput.trim().toLowerCase().equals("yes"))
                    {
                        userAnswer = true;
                        dice[i].setKeepDice(true);
                    }
                    else if(userInput.trim().toLowerCase().equals("no"))
                    {
                        userAnswer = true;
                        dice[i].setKeepDice(false);
                    }
                    else {
                        System.out.println("\nEnter yes or no.");
                        userAnswer = false;
                    }
                }
            }
            while(!userAnswer);
        }
    }
    
    /**
     * Scors the round in the category the player has chosen.
     */
    public void scoreRoll()
    {
            String userInput = getValidUserInput();
            switch(userInput) 
            {
                case "a":
                case "b":
                case "c":
                case "d":
                case "e":
                case "f":
                    calculateScore(userInput);
                    break;
                case "g":
                    calculateYahtzee();
                    break;
                case "h":
                    calculateChance();
                    break;
                case "help" :
                    availableCategories();
                    break;
            }
    }

    /**
     * Prompts the player to enter a valid category between a-h. It also checks to verify
     * that the chosen valid category has not been closed-out in the previous rounds. If so,
     * it prompts the player to choose another category which is still open for scoring.
     * 
     * @return the verified category, entered by user.
     */
    public String getValidUserInput()
    {
        boolean wrongInput = true;
        boolean searching;
        String userInput;
       
        do{
            System.out.print("\nChoose a category(a-h) to score. (Type Help for available categories) : ");
            userInput = reader.getInput().trim().toLowerCase();
            if(userInput.equals("a") || userInput.equals("b") || userInput.equals("c") || userInput.equals("d") || userInput.equals("e") || userInput.equals("f") || userInput.equals("g") || userInput.equals("h"))
            {
                searching = true;
                for(Score tempScore : score)
                {
                    if(tempScore.getCategory().equals(userInput) && searching)
                    {
                        searching = false;
                        if(tempScore.getClosedOut() == false)
                        {
                            wrongInput = false;
                        }
                        else {
                            System.out.println("You have scored this category in the previous rounds and it's closed out " +
                                               "Choose another category.\n");
                            availableCategories();
                        } 
                    }
                }
            }
            else if(userInput.equals("help")) {
                availableCategories();
            }
            else { 
                System.out.println("Enter a valid category: ");
            }
         } while(wrongInput);
        return userInput;
    }
    
    /**
     * Calculates the socre for a category and then closes out that category for the rest 
     * of the game.
     * 
     * @param userInput the chosen category by user to be scored. 
     */
    public void calculateScore(String userInput)
    {
        boolean searching = true;
        
        for(Score tempScore : score)
        {
            if(tempScore.getCategory().equals(userInput) && searching)
            {
                for(int i = 0; i < dice.length; i++)
                {          
                    if(dice[i] == null)
                    {
                        System.out.println("Error!");
                    }
                    else {
                        if(dice[i].getValue() == tempScore.getDiceNumber())
                        {
                            tempScore.setCategoryScore(tempScore.getCategoryScore() + dice[i].getValue());
                        }
                    }
                }
                System.out.println("\nYour score in category " + tempScore.getCategory() + " ," + tempScore.getCategoryDescription() +
                                   ", is: " + tempScore.getCategoryScore() + "\nCategory " + tempScore.getCategory() + " is closed out for the rest of the rounds.");
                tempScore.setClosedOut(true);
                searching = false;
            }
        }
    }
    
    /**
     * Calculates the score for the chance category.
     */
    public void calculateChance()
    {
        for(Dice tempDice : dice)
        {
            score[7].setCategoryScore(score[7].getCategoryScore() + tempDice.getValue());
        }
        System.out.println("\nYour score in category h is: " + score[7].getCategoryScore() + 
                           "\nCategory h is closed out for the rest of the rounds.");
        score[7].setClosedOut(true);
    }
    
    /**
     * Calculates the score for the Yahtzee category. First it validates the chosen category 
     * to be eligible for a Yahtzee point.
     */
    public void calculateYahtzee()
    {
        boolean validYahtzee = true;
        
        for(int i = 1; i < dice.length; i++)
        {
            if(dice[i - 1].getValue() != dice[i].getValue())
            {
                validYahtzee = false;
                i = dice.length;
                System.out.println("You don't have 5 of a kind. This category is not eligible for a Yahtzee. ");
                if(roundNumber == ROUNDS_TO_PLAY - 1)
                {
                    System.out.println("This is your round 8 of game. Your score in category h is 0.");
                    score[6].setCategoryScore(0);
                }
                else {
                    System.out.println("Choose another category.");
                    scoreRoll();      
                }
            }
        }
        
        if(validYahtzee)
        {
            score[6].setCategoryScore(YAHTZEE_POINT);
            score[6].setClosedOut(true);
            System.out.println("\nYour score in category g is: 50" +
                               "\nCategory g is closed out for the rest of the rounds.");
        }
    }
    
    /**
     * Calculates the total score the player has achieved in each round.
     */
    public void totalScore()
    {
        for(Score tempScore : score)
            {
                totalScore += tempScore.getCategoryScore();
            }
        System.out.println("End of game.\n Your 8 rounds total score in this game is: " + totalScore);
    }
    
    /**
     * Prints the available categories.
     */
    public void availableCategories()
    {
        System.out.println("Youe options are: ");
        for(int i = 0; i < score.length; i++)
        {
            if(score[i].getClosedOut() == false)
            {
               System.out.print(" " + score[i].getCategory());
            }
        }
    }
}
