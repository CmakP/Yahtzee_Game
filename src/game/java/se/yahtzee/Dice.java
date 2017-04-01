package game.java.se.yahtzee;
/**
 * Dice class - a typical playing dice.
 * 
 * @author Siamak Pourian
 * @Ver. Jan 12, 2016
 */
public class Dice
{
    private int value;
    private boolean keepDice;
    
    /**
     * Constructor for Dice Class.
     */
    public Dice() {}
    
    /**
     * Overloaded Constructor for Dice class.
     */
    public Dice(int value, boolean keepDice)
    {
        this.value = value;
        this.keepDice = keepDice;
    }
   
    /**
     * 
     * @return the dice value.
     */
    public int getValue() 
    {
        return value;    
    }
   
    /**
     * @param sets the value of the dice. 
     */
    public void setValue(int value) 
    {
       this.value = value;
    }
   
    /**
     * @return if the dice is available for roll or not.
     */
    public boolean getKeepDice() 
    {
       return keepDice;
    }
    
    /**
     * @param sets the availability of the dice for rolling. 
     */
    public void setKeepDice(boolean keepDice)
    {
        this.keepDice = keepDice;
    }
}
