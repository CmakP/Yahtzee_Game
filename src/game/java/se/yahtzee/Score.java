package game.java.se.yahtzee;
/**
 * Score class - 8 Categories for scoring.
 * 
 * @author Siamak Pourian
 * @Ver. Jan 12, 2016
 */
public class Score
{
    private int diceNumber;
    private int categoryScore;
    private String categoryDescription;
    private String category;
    private boolean closedOut;
    private boolean yahtzee;
    
    /**
     * Constructor for Score Class.
     */
    public Score() {}
    
    /**
     * Overloaded constructor for Score class.
     */
    public Score(int diceNumber, int categoryScore, String categoryDescription, String category, boolean closedOut, boolean yahtzee)
    {
       setDiceNumber(diceNumber);
       setCategoryScore(categoryScore);
       setCategoryDescription(categoryDescription);
       setCategory(category);
       setClosedOut(closedOut);
       setYahtzee(yahtzee);
    }
   
    /**
     * @return the dice number associated with this category.
     */
    public int getDiceNumber() 
    {
        return diceNumber;    
    }
  
    /**
     * @param diceNumber sets the dice number associated with this category. 
     */
    public void setDiceNumber(int diceNumber) 
    {
       this.diceNumber = diceNumber;
    }
    
    /**
     * @return the category's score.
     */
    public int getCategoryScore() 
    {
        return categoryScore;    
    }
   
    /**
     * @param categoryScore sets the category's score. 
     */
    public void setCategoryScore(int categoryScore) 
    {
       this.categoryScore = categoryScore;
    }
    
    /**
     * @return the category's description.
     */
    public String getCategoryDescription() 
    {
        return categoryDescription;    
    }
   
    /**
     * @param categoryDescription sets the category's description. 
     */
    public void setCategoryDescription(String categoryDescription) 
    {
       this.categoryDescription = categoryDescription;
    }
    
    /**
     * @return the category.
     */
    public String getCategory() 
    {
        return category;    
    }
   
    /**
     * @param category sets the category. 
     */
    public void setCategory(String category) 
    {
       this.category = category;
    }
    
    /**
     * @return the category's state, if it's open or closed out.
     */
    public boolean getClosedOut()
    {
        return closedOut;
    }
    
    /**
     * @param closedOut to be set as category's state.
     */
    public void setClosedOut(boolean closedOut)
    {
        this.closedOut = closedOut;
    }
    
    /**
     * @return if this category has 5 of a kind or not.
     */
    public boolean getYahtzee()
    {
        return yahtzee;
    }
    
    /**
     * @param yahtzee sets if this category has 5 of a kind or not.
     */
    public void setYahtzee(boolean yahtzee)
    {
        this.yahtzee = yahtzee;
    }
}
