/*
 * Array Based Implementation of a List
 * This List is ordered, containing Game objects 
 */
 
/**
 *
 * @author Daniel Nicolas
 */
public class GameStorage {
    private Game[] storage;
    private int count;
    private int length;
   
    /*
        default constructor with default values
    */
    public GameStorage()
    {
        count = 0;
        length = 10;
        storage = new Game[length];
        for(int i=0; i < storage.length; i++){
            storage[i] = null;
        }
    }
    /*
        constructor used when resizing array with default Game values
    */
    public GameStorage(int size)
    {
        count = 0;
        length = size;
        storage = new Game[length];
        for(int i=0; i < storage.length; i++){
            Game game = new Game();
            storage[i] = game;
        }
    }
   
    /*
        copy constructor
    */
    public GameStorage(GameStorage other)
    {
        this.storage = other.storage;
        this.count = other.count;
        this.length = other.length;
    }
    /*
        returns the length
    */
    public int getLength()
    {
        return length;
    }
    
    /*
       Sets a Game object at the desired index in the array
       If index is outside of the array or if the desired index is already
       taken then nothing will happen
    */
    public void set(int index, Game g)
    {  
        if(index < count || index > getLength())
        {
            System.out.println("Invalid entry. Nothing happened");
        }
        else
        {
            storage[index] = g; 
            count++;
        }
    }
   
    /*
        returns a Game object at the desired index, null if there isnt anything there
    */
    public Game get(int index)
    {
        return (storage[index] != null) ? storage[index] : null;
    }
    
    /*
        returns the size of the array
    */
    public int getSize()
    {
        return count;
    }
   
    // makes a (deep) copy of the current instance
    public GameStorage makeCopy()
    {
        GameStorage new_storage = new GameStorage();
        for(int i = 0; i < this.count; i++)
        {
            new_storage.set(i, this.storage[i]);
        }
        return new_storage;
    }
   
    /*
        resizes the List to the desired length
    */
    public void resize(int newSize)
    {
        GameStorage temp = new GameStorage(newSize);
        for(int i = 0; i < newSize; i++)
        {
            // if i becomes larger than current count ArrayIndexOutOfBounds will be thrown
            if(i >= this.count)
            {
                break;
            }
            temp.set(i, this.storage[i]);
        }
         this.storage = temp.storage;
         if(temp.count < this.count)
         {
             this.count = temp.count;
         }
         this.length = temp.length;
    }
   
    @Override
    public boolean equals(Object obj)
    {
        GameStorage other = (GameStorage) obj;
        // storages arent equal if they dont have the same # of elements
        if(count != other.count)
        {
            return false;
        }
 
        boolean[] arr = new boolean[count];
        for(int i = 0; i < arr.length;i++){
            arr[i] = false;
        }
 
        for(int otherStorage = 0; otherStorage < other.getSize(); otherStorage++)
        {
            for(int currentStorage = 0; currentStorage < other.getSize(); currentStorage++)
            {
                if(other.storage[otherStorage].equals(storage[currentStorage]))
                {
                    arr[otherStorage] = true;
                }
            }    
        }
        for(int i = 0; i < arr.length;i++){
            if(arr[i] == false)
            {   
                return false;
            }
        }
        return true;
    }
}