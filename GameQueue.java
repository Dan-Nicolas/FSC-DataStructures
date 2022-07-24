/**
 * A Linked List implementation of a Queue containing Game Objects
 */

/**
 *
 * @author Daniel Nicolas
 */
public class GameQueue {
    /**
     * Node class with appropriate member variables and constructor 
     */
    public class Node
    {
        public Game data;
        public Node next;
        public Node()
        {
            data = null;
            next = null;
        }
    }
    
    private Node front;
    private Node rear;
    private int length;
    
    /**
     * default constructor
     */
    public GameQueue()
    {
        front = null;
        rear = null;
        length = 0;
    }
    /**
     * Copy constructor performing a deep copy 
     * @param other 
     */
    public GameQueue(GameQueue other)
    {
        front = other.makeCopy().front;
        rear = other.makeCopy().rear;
        length = other.length;
    }
    /**
     * Adds a Game Object to the end of the Queue
     * @param g 
     */
    public void enqueue(Game g)
    {
        Node temp;
        temp = new Node();
        temp.data = g;
        temp.next = null;
        if(this.length == 0) // set both front and rear to the new node if the list has no prior nodes
        {
            this.front = temp;
            this.rear = temp;
        }
        else // set the new node to the back
        {
            Node end_temp = this.rear;
            end_temp.next = temp;
            this.rear = temp;
        }
        this.length++;
    }
    /**
     * Removes the front of the Queue and assigns the Front to the following Game
     * @return 
     */
    public Game dequeue()
    {
        Node removed = this.front;
        this.front = this.front.next;
        this.length--;
        return removed.data;
    }
    /**
     * Prints out the Queue
     */
    public void show()
    {
        Node curr = this.front;
        while(curr != null)
        {
            System.out.println(curr.data);
            curr = curr.next;
        }
    }
    /**
     * Returns the last Game in the Queue
     * @return Game
     */
    public Game getRear()
    {
        return this.rear.data;
    }
    /**
     * Returns the first Game in the Queue
     * @return Game
     */
    public Game getFront()
    {
        return this.front.data;
    }
    /**
     * Returns the current length of the Queue
     * @return 
     */
    public int getLength()
    {
        return this.length;
    }
    /**
     * Creates a deep copy of the Queue
     * @return 
     */
    public GameQueue makeCopy()
    {
        GameQueue copy = new GameQueue();
        Node curr = this.front;
        while(curr != null)
        {
            Game game_copy = curr.data.makeCopy();
            copy.enqueue(game_copy);
            curr = curr.next;
        }
        return copy;
    }
    /**
     * Empties the Queue
     */
    public void makeEmpty()
    {
        this.front = null;
        this.length = 0;
    }
    /**
        Helper method, check takes in two parameters, a Node value and a Queue value
        The Node value contains the game we're searching for to see if it is in the other Queue
        The q value is the Queue in which we are searching in
    
        @param node Current Node/Game we are looking for
        @param q Queue we are searching in to look for the current Node/Game
        @return true/false whether the Node/Game is in the queue we are searching in
    */
    private boolean check(Node node, GameQueue q)
    {
        Node node2 = q.front;
        while(node2 != null) // goes thru the queue until it reaches the end
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
     * Method compares to Queues to see if their values are exactly equal to each other
     * If all elements are found in both then method will return true otherwise false
     * @param obj
     * @return true/false whether the two lists are exact same
     */
    @Override
    public boolean equals(Object obj)
    {        
        GameQueue other = (GameQueue) obj;
       
        if(this.length != other.length){ // if the two queues aren't the same size then they can't be equal to each other
            return false;
        }
       
        Node this_node = this.front;
       
        boolean found = true; // condition to know whether or not to keep searching
        /*
            while loop will only run under 2 conditions
            (1) found is true (determined in the check() method)
                                AND
            (2) while this_node isn't rear.next
            If the loop reaches the dummy node then all previous Nodes/Games/data values are equal in both GameQueues
        */
        while(found && this_node != null)
        {
            found = check(this_node, other); // this will check to see if the current Game is in the other queue, true if it is false if not
            if(found == false)
            {
                return false;
            }
            this_node = this_node.next; // move on to the next node
        }
        return true; // if we exit the while loop then that means that both queues are equal
    }

}


