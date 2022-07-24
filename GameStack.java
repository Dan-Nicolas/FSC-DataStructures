
/**
 *
 * @author Daniel
 */
public class GameStack{
    private Game[] stack;
    private int length;
    private int size;
    
    /**
     * default constructor with default values
     */
    public GameStack()
    {
        size = 10;
        stack = new Game[size];
        length = 0;
        
    }
    /**
     * Deep copy constructor
     * @param other 
     */
    public GameStack(GameStack other)
    {
        size = other.size;
        length = other.length;
        stack = other.makeCopy().stack;
    }
    /**
     * Constructor use for resizing the array when needed
     * @param new_size 
     */
    public GameStack(int new_size)
    {
        size = new_size;
        stack = new Game[size];
        length = 0;
    }
    /**
     * Adds a Game object to the end of the array (New Top of Stack)
     * @param g 
     */
    public void push(Game g)
    {
        this.stack[this.length] = g;
        this.length++;
    }
    /**
     * Removes the last Game in the array 
     * (not really just decrements the length so logically current Top will be overwritten by a new Game obj)
     */
    public void pop()
    {
        if(this.length == 0)
        {
            System.out.println("Error. Nothing to pop, stack is empty");
        }else
        {
            this.length--;
        }
    }
    /**
     * Returns Game at the top of the Stack
     * @return Game
     */
    public Game top()
    {
        try {
            return this.stack[length - 1];
        } catch (Exception e) {
            System.out.print("Error. Stack is empty, ");
        }
        return null;
    }
    /**
     * Returns the current length of the array
     * @return length
     */
    public int getLength()
    {
        return this.length;
    }
    /**
     * Sets the length to 0 so logically speaking any new Game objects will overwrite any Games in the array
     */
    public void makeEmpty()
    {
        this.length = 0;
    }
    
    /**
     * Prints the Stack starting from the Top to the bottom
     */
    public void show()
    {
        for(int i = this.length - 1; i >= 0; i--)
        {
            System.out.println(this.stack[i]);
        }
    }
    
    /**
     * Makes a deep copy of the current stack
     * @return Deep copy of stack (GameStack)
     */
    public GameStack makeCopy()
    {
        // if parent stack has a larger size of 10 (default size) then this will accomedate for that
        GameStack copy = new GameStack(this.size); 
        for(int i = 0; i < this.getLength(); i++)
        {
            Game copy_game;
            copy_game = this.stack[i].makeCopy();        
            copy.push(copy_game);
        }
        return copy;
    }
    /**
     * Doubles the size of the array and keeps existing Game objects
     */
    public void resize()
    {
        GameStack new_size = new GameStack(this.size*2); // double the size 
        for(int i = 0; i < this.getLength(); i++) // deep copy all the Games
        {
            Game g = this.stack[i].makeCopy();
            new_size.push(g);
        }
        this.stack = new_size.stack;
        this.size = new_size.size;
    }
    
    public boolean check(Game g1, GameStack g2)
    {
        for(int i = 0; i < g2.length; i++)
        {
            if(g1.equals(g2.stack[i]))
            {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        GameStack other = (GameStack) obj;
        if(this.length != other.length)
        {
            return false;
        }

        boolean found;
        for(int i = 0; i < this.length; i++)
        {
            found = check(this.stack[i], other);
            if(found == false)
            {
                return false;
            }
        }
        return true;
    }
}