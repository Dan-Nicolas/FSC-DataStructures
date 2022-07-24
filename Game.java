/**
 * Game Class, contains Title (string), Esrb rating (string) and Price(int) variables with appropriate methods
 */
 
/**
 * @author Daniel Nicolas
 */
public class Game
{
    private double price;
    private String title;
    private String esrb;
 
    /**
     *  default constructor
     */
    public Game()
    {
        price = 0.00;
        title = "Title";
        esrb = "N/A";
    }
   /**
    * constructor with parameters
    * @param title
    * @param price
    * @param esrb 
    */
    public Game(String title, double price, String esrb)
    {
        this.title = title;
        this.price = price;
        this.esrb = esrb.toUpperCase();    
    }
 
    /**
     * deep copy constructor
     * @param other 
     */
    public Game(Game other)
    {
        esrb = other.esrb;
        price = other.price;
        title = other.title;
    }
   
    /**
     * makes a copy of the current instance
     * @return 
     */
    public Game makeCopy()
    {
        Game newGame = new Game(this.title, this.price, this.esrb);
        return newGame;
    }
    /**
     * gets the price of the current game
     * @return 
     */
    public double getPrice()
    {
        return price;
    }
    /**
     * sets the price for the current game
     * @param price 
     */
    public void setPrice(double price)
    {
        // Any negative value will not be accepted and price will be set to 0
       
        if (price >= 0){
            this.price = price;
        }
        else
        {
            this.price = 0;
        }
    }
    /**
     * gets the title of the current game
     * @return string
     */
    public String getTitle()
    {
        if(this.title == null)
        {
            return null;
        }
        return this.title;
    }
    /**
     * sets the title of the current game
     * @param title 
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
 
    /**
     * gets the Esrb of the current game
     * @return Esrb (String)
     */
    public String getEsrb()
    {
        if(this.esrb == null)
        {
            return null;
        }
        return this.esrb;
    }
 
    /**
       ESRB ratings are E for Everyone, T for Teen, and M Mature.
       If a string is entered that isn't any of those three than the Esrb rating for this game
       will be set to N/A.
    */
    public void setEsrb(String esrb)
    {
        String[] validESRB = {"Everyone", "Teen", "Mature"};// arr used to validate esrb rating
        this.esrb = "N/A";// N/A is set by default
        for (String rating : validESRB) {
            if (esrb.equalsIgnoreCase(rating)) {
                this.esrb = esrb;// If ESRB is in validESRB arr then it is valid and will be set to this.esrb
            }
        }
    }
   /**
    * toString method for Game
    * @return string
    */
    @Override
    public String toString()
    {  
        char rating = getEsrb().charAt(0);
        return "This game is called " + getTitle() + " , it costs $" +
                String.format("%.2f",getPrice()) + " and it is rated \'"
                + rating + "\' for " + getEsrb() ;
    }
   /**
    * Compares to see if two Game objects are equal to each other
    * @param obj (Game)
    * @return Boolean
    */
    @Override
    public boolean equals(Object obj)
    {
        Game other = (Game) obj;
       
        return !(title.equals(other.title) == false || price == (other.price) == false
                || esrb.equals(other.esrb) == false);
    }

}
