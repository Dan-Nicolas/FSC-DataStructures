/**
 * A Binary Search Tree Implementation of containing Game objects
 */

/**
 *
 * @author Daniel Nicolas
 */
public class GameBinarySearchTree {
    /**
     * Node Class use to contain Game objects and those objects pointers
     */
    public class Node
    {
        public Game data;
        public Node left;
        public Node right;
        public Node(Game g)
        {
            data = g;
            left = null;
            right = null;
        }
    }
    
    public Node root;
    public int length;
    
    /**
     * Constructor for the class
     */
    public GameBinarySearchTree()
    {
        this.root = null;
        this.length = 0;
    }
        
    /**
     * Copy constructor for deep copy
     * @param other 
     */
    public GameBinarySearchTree(GameBinarySearchTree other)
    {
        GameBinarySearchTree copy = other.makeCopy();
        root = copy.root;
        length = copy.length;    
    }
    
    /**
     *  Empties the tree
     */
    public void clear()
    {
        this.root = null;
        this.length = 0;
    }
    
    /**
     * Returns the number of Games in the tree 
     * @return 
     */
    public int getLength()
    {
        return this.length;
    }
    
    /**
     * Helper method to add a Game by using recursion
     * @param root
     * @param g
     * @return Node
     */
    private Node add(Node root, Game g)
    {
        if(root == null)// if we go beyond a leaf then we found a spot to place the game
        {
            return new Node(g);
        }
        String key = g.getTitle();
        int compare = root.data.getTitle().compareToIgnoreCase(key);// know whether to search in the left or right sub trees
        if(compare > 0)
        {
            root.left = add(root.left, g);
        }else if(compare < 0)
        {
            root.right = add(root.right, g);
        }
        return root;
    }
    
    /**
     * Adds a game to the tree and increments the length
     * @param g 
     */
    public void add(Game g)
    {
        this.root = add(this.root, g);
        this.length++;
    }
    
    /**
     * Helper method used to help search a Game by its Title using recursion
     * @param root
     * @param s
     * @return 
     */
    private Node getHelper(Node root, String s)
    {
        if(root == null)// if we go beyond a leaf then the game isn't in the tree
        {
            return new Node(null);
        }
        // compare the 2 strings
        int compare = root.data.getTitle().compareToIgnoreCase(s); // know whether to search in the left or right sub trees
        Node game = new Node(root.data);
        if(compare == 0)// once we find the game return it
        {
            return game;
        }
        if(compare > 0)// search the left child
        {
            game = getHelper(root.left, s);
        }else if(compare < 0)// search the right child
        {
            game = getHelper(root.right, s);
        }
        return game;
    }
    
    /**
     * Returns a Game by searching for it by using its title, returns null if there is no Game with that title
     * @param title
     * @return 
     */
    public Game getGame(String title)
    {
        Game retrieved = getHelper(this.root, title).data;
        return retrieved;
    }
        
    /**
     * Helper method that helps prints titles in a post-order fashion using recursion
     * @param root 
     */
    private void postorderRecursion(Node root)
    {
        if(root != null)
        {
            postorderRecursion(root.left);
            postorderRecursion(root.right);
            System.out.println(root.data.getTitle());
        }
    }
    
    /**
     * Prints all the the Games by Title in a post-order fashion 
     */
    public void postorder()
    {
        postorderRecursion(this.root);
    }
    
    /**
     * Helper method that helps prints titles in a in-order fashion using recursion
     * @param root 
     */
    private void inorderRecursion(Node root)
    {
        if(root != null)
        {
            inorderRecursion(root.left);
            System.out.println(root.data.getTitle());
            inorderRecursion(root.right);
        }
    }
    
    /**
     * Prints all the the Games by Title in a in-order fashion 
     */
    public void inorder()
    {
        inorderRecursion(this.root);
    }
    
    /**
     * Helper method that helps prints titles in a pre-order fashion using recursion
     * @param root 
     */
    private void preorderRecursion(Node root)
    {
        if(root != null)
        {
            System.out.println(root.data.getTitle());
            preorderRecursion(root.left);
            preorderRecursion(root.right);
        }
    }
    
    /**
     * Prints all the the Games by Title in a pre-order fashion 
     */
    public void preorder()
    {
        preorderRecursion(this.root);
    }
    
    /**
     * Using recursion, gets the total of all the prices in the tree
     * @param root
     * @return 
     */
    private double totalHelper(Node root)
    {
        double total = 0;
        if(root != null)
        {
            total += root.data.getPrice() + totalHelper(root.left) + totalHelper(root.right);
        }
        return total;
    }
    
    /**
     * Gets the total of all the prices in the tree
     * @return 
     */
    public double totalPriceUsingRecursion()
    {
        return totalHelper(this.root);
    }
    
    /**
     * Helper method, makes a deep copy of the current instance using recursion
     * @param root
     * @param copy
     * @return 
     */
    private GameBinarySearchTree makeCopyHelper(Node root, GameBinarySearchTree copy)
    {
        if(root != null)
        {
            copy.add(root.data.makeCopy());
            makeCopyHelper(root.left, copy);
            makeCopyHelper(root.right, copy);
        }
        return copy;
    }
    
    /**
     * Makes a deep copy of the current instance
     * @return 
     */
    public GameBinarySearchTree makeCopy()
    {
        GameBinarySearchTree copy = new GameBinarySearchTree();
        makeCopyHelper(this.root, copy);
        return copy;
    }
    /**
     * Helper method -  using recursion, compares Nodes in two different trees to see if they have the same value
     * @param root1
     * @param root2
     * @return 
     */
    private boolean equalsHelper(Node root1, Node root2)
    {
        // if one node is a leaf and the other is not then they are not equal
        if((root1 == null && root2 != null) || (root2 == null && root1 != null))
        {
            return false;
        }
        // if both nodes are the children of leafs then they have to be equal
        if(root1 == null && root2 == null)
        {
            return true;
        }
        //boolean equal;
        //equal = root1.data.equals(root2.data) && equalsHelper(root1.left, root2.left) && equalsHelper(root1.right, root2.right);
        return root1.data.equals(root2.data) && equalsHelper(root1.left, root2.left) && equalsHelper(root1.right, root2.right);
    }
    
    /**
     * Compares two trees to see if they both have all the same games
     * @param obj
     * @return true/false
     */
    @Override
    public boolean equals(Object obj)
    {
        GameBinarySearchTree other = (GameBinarySearchTree) obj;
        if(this.getLength() != other.getLength())
        {
            return false;
        }        
        return equalsHelper(this.root, other.root);
    }
    
}
