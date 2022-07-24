/**
    CircularGameList - Circular Doubly Linked List Implementation of storing Game objects
 */
 
/**
 *
 * @author Daniel Nicolas
 */
public class CircularGameList {
    /**
     * Node class
     */
    public class Node
    {
        public Game data; // contains a Game instance and all of its values
        public Node prev; // points to the previous node
        public Node next; // points to the next node
       /**
        * default constructor for Node objects
        */
        public Node()
        {
            data = null;
            prev = next;
            next = prev;
        }
    }
   
    public Node dummy;
    public int count;
   
    /**
        default constructor with default values
    */
    public CircularGameList()
    {
        dummy = new Node();
        dummy.prev = new Node();
        dummy.next = new Node();
        count = 0;
    }
   
    /**
        Copy constructor
     * @param other
    */
    
    public CircularGameList(CircularGameList other)
    {
        CircularGameList copy;
        copy = other.makeCopy();
        this.dummy = copy.dummy;
        this.dummy.next = copy.dummy.next;
        this.dummy.prev = copy.dummy.prev;
        this.count = other.count;
    }
 
    /**
        returns the size of the List
        Includes all Game instance, excludes the dummy Node
        
        @returns int 
    */
    public int getLength()
    {
        return this.count;
    }
 
    /**
        Adds a new Game obj to the front of the List (new head)
        Increments count by one
        
        @param g Game we want to insert, specifically in the front of the list
    */
    public void addFront(Game g)
    {
        Node node = new Node(); // sets up the node
        node.data = g; // sets the values to be added to the list
       // sets up appropriate pointers
        if(count == 0){
            dummy.next = node;
            dummy.prev = node;
            node.next = dummy;
            node.prev = dummy;
        }else{
            node.next = dummy.next;
            node.prev = dummy;
            node.next.prev = node;
            dummy.next = node;
        }
        count++;
    }
 
    /**
        Adds a new Game obj to the end of the List (new tail)
        Increments count by one
        
        @param g Game we want to insert, specifically in the back of the list
    */
    public void addBack(Game g)
    {
        Node node = new Node(); // sets up the node
        node.data = g; // sets the values to be added to the list
       // sets up appropriate pointers
        if(count == 0){
            dummy.next = node;
            dummy.prev = node;
            node.next = dummy;
            node.prev = dummy;
        }else{
            node.prev = dummy.prev;
            dummy.prev.next = node;
            node.next = dummy;
            dummy.prev = node;
        }
        count++;
    }
 
    /**
        Removes the first Game(Head) obj in the List, next Game obj is now the head
        Decrements List by one and returns the removed game
        Checks if list is empty before attempting to remove anything
    
        @return Game
    */
    public Game removeFront()
    {
        try
        {
            Game removed = dummy.next.data;
            dummy.next = dummy.next.next;
            dummy.next.prev = dummy;
            count--;
            return removed;
        } catch (Exception e){
            System.out.println("Error. List is Empty");
            return dummy.data;
        }
    }
 
    /**
        Removes the last Game (Tail) obj in the List, next Game obj is now the head
        Decrements List by one, and returns the removed game
           
        @return Game
    */
    public Game removeBack()
    {
        try
        {
            Game removed = dummy.prev.data;
            dummy.prev = dummy.prev.prev;
            dummy.prev.next = dummy;
            count--;
            return removed;
        } catch (Exception e){
            System.out.println("Error. List is Empty");
            return dummy.data;
        }
    }
    /**
        Returns the first Game in the List and its values
        
        @returns Game
    */
    public Game getHeadData()
    {
        return dummy.next.data;
    }
 
    /**
        Returns the last Game in the List and its values

        @return Game
    */
    public Game getTailData()
    {
        return dummy.prev.data;
    }
 
    /**
        Returns true if a Game with the given title is in the list
        Returns false if otherwise
        
        @param name The Title of the Game we want to find
        @return true/false whether the contains the Node/Game we are searching for
    */
    public boolean hasItem(String name)
    {
        Node location = dummy.next;
        String game_name = location.data.getTitle().toUpperCase();
        while(location != null)
        {
            if(game_name.equals(name.toUpperCase()))
            {
                return true;
            }
            location = location.next;
             if(location == dummy)
            {
                break;
            }
            game_name = location.data.getTitle().toUpperCase();
        }
        return false;
    }
    
    /**
        Clears the list, sets count back to zero
    */
    public void makeEmpty()
    {
        dummy.next = dummy;
        dummy.prev = dummy;
        count = 0;
    }
    /**
     * Prints the list in its entirety excluding dummy 
     */
    public void show()
    {
        Node n = dummy.next;
        while(n.data != null)
        {  
            System.out.println(n.data);
            n = n.next;
        }
    }
    /**
        makes a (deep) copy of the current instance
        
        @return a deepcopy CircularGameList of the current instance
    */
    public CircularGameList makeCopy()
    {
        
        Node n = this.dummy.next;
        CircularGameList new_list = new CircularGameList();

        while(n.data != null)
        {  
            Game copyGame = new Game();
            copyGame.setEsrb(n.data.getEsrb());
            copyGame.setPrice(n.data.getPrice());
            copyGame.setTitle(n.data.getTitle());
            new_list.addBack(copyGame);
            n = n.next;
        }
        return new_list;
    }
    
    /**
        Helper method, check takes in two parameters, a Node value and a List value
        The Node value contains the game we're searching for to see if it is in the other List
        The List value is the list in which we are searching in
    
        @param node Current Node/Game we are looking for
        @param list List we are searching in to look for the current Node/Game
        @return true/false whether the Node/Game is in the list we are searching in
    */
    public boolean check(Node node, CircularGameList list)
    {
        Node node2 = list.dummy.next;
        while(node2.data != null) // goes thru the list until it reaches dummy
        {
            if(node.data.equals(node2.data)) // if the game we're searching for is in the list we're looking in then return true
            {
                return true;
            }
            node2 = node2.next;
        }
        return false; // if we are out of the while loop and never return true then the game is not in this list
    }
    
    /**
     * Method compares to lists to see if their values are exactly equal to each other
     * If all elements are found in both then method will return true otherwise false
     * @param obj
     * @return true/false whether the two lists are exact same
     */

    @Override
    public boolean equals(Object obj)
    {        
        CircularGameList other = (CircularGameList) obj;
       
        if(this.count != other.count){ // if the two lists aren't the same size then they can't be equal to each other
            return false;
        }
       
        Node this_node = this.dummy.next;
       
        boolean found = true; // condition to know whether or not to keep searching
        /*
            while loop will only run under 2 contions
            (1) found is true (determined in the check() method
                                AND
            (2) while this_node isn't the dummy node
            If the loop reaches the dummy node then all previous Nodes/Games/data values are equal in both CircularGameList lists
        */
        while(found && this_node.data != null)
        {
            found = check(this_node, other); // this will check to see if the current Game is in the other list, true if it is false if not
            if(found == false)
            {
                return false;
            }
            this_node = this_node.next; // move on to the next node
        }
        return true; // if we exit the while loop then that means that both lists are equal
    }
    
}