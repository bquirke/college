/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 26/11/14 16:49:42
 *
 *  @author Bryan Quirke
 *
 *************************************************************************/

 

 
public class BST<Key extends Comparable<Key>, Value>
{
    private Node root; // root of BST
 
    /**
     * Private node class.
     */
    
    private class Node
    {
        private Key key; // sorted by key
        private Value val; // associated data
        private Node left, right; // left and right subtrees
        private int N; // number of nodes in subtree
 
        public Node(Key key, Value val, int N)
        {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }
 
    // is the symbol table empty?
    public boolean isEmpty()
    {
        return size() == 0;
    }
 
    // return number of key-value pairs in BST
    public int size()
    {
        return size(root);
    }
 
    // return number of key-value pairs in BST rooted at x
    private int size(Node x)
    {
        if(x == null)
        {
        	return 0;
        }
        else
    	{
    	return x.N;
    	}
    }
 
//////////////////////________GET___________//////////////////////////////////////////  
 
    /**
     * Search BST for given key. What is the value associated with given key?
     *
     * @param key
     *            the search key
     * @return value associated with the given key if found, or null if no such
     *         key exists.
     */
    public Value get(Key key)
    {
        return get(root, key);
    }
 
    private Value get(Node x, Key key)
    {
        if(x == null)
        {
        	return null;
        }
        
        int cmp = key.compareTo(x.key);
        
        if(cmp < 0)
        {
        	return get(x.left, key);
        }
        else if(cmp > 0)
        {
        	return get(x.right, key);
        }
        else
        {
        	return x.val;
        }
    }
/////////////////////////____________PUT_____________//////////////////////////////////////
    /**
     * Insert key-value pair into BST. If key already exists, update with new
     * value.
     *
     * @param key
     *            the key to insert
     * @param val
     *            the value associated with key
     */
    public void put(Key key, Value val)
    {
        if (val == null)
        {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }
 
    private Node put(Node x, Key key, Value val)
    {
        if(x == null)
        {
        	return new Node(key, val, 1);
        }
        
        int cmp = key.compareTo(x.key);
        
        if(cmp < 0)
        {
        	x.left = put(x.left, key, val);
        }
        
        else if(cmp > 0)
        {
        	x.right = put(x.right, key, val);
        }
        
        else
        {
        	x.val = val;
        }
        
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }
 ///////////////////////___________HEIGHT________________//////////////////////////
    /**
     * Tree height.
     *
     * Asymptotic worst-case running time: Theta(N)
     * Explanation: as the runtime is directly proportional to the 
     * number of nodes in the binary search tree.
     * 	
     *
     * @return the number of links from the root to the deepest leaf.
     *
     *         Example 1: for an empty tree this should return -1. Example 2:
     *         for a tree with only one node it should return 0. Example 3: for
     *         the following tree it should return 2. B / \ A C \ D
     */
    public int height()
    {
        if (isEmpty())
        {
        	return -1;
        }
        
        else if (size() == 1)
        {
        	return 0;
        }
        
        else
    	{
        	return findHeight(root);
    	}
    }
 
    private int findHeight(Node n)
    {
        if (n == null){return -1;}
        
        int leftNode = 0;
        int rightNode = 0;
        
        if (n.left != null)
        {
        	leftNode = findHeight(n.left);
        }
        
        if (n.right != null)
        {
        	rightNode = findHeight(n.right);
        }
        
        return (leftNode > rightNode) ? leftNode +1 : rightNode + 1;
    }
//////////////////////////////////____________________MEDIAN___________//////////////////    
    /**
     * Median key. If the tree has N keys k1 < k2 < k3 < ... < kN, then their
     * median key is the element at position (N+1)/2 (where "/" here is integer
     * division)
     *
     * @return the median key, or null if the tree is empty.
     */
    public Key median()
    {
        if (isEmpty()){return null;}
        
        
        if (size() == 1){return root.key;}
        
        
        int med = (root.N + 1) / 2;
        return get(root, med);
    }
 
    private Key get(Node n, int num)
    {
        int leftCount = 0;
        
        if (n.left != null){leftCount = n.left.N;}
       
        
        if (num == leftCount + 1){return n.key;}
        
        
        else if (leftCount >= num){return get(n.left, num);}
        
        
        else
        {
            num -= (leftCount + 1);
            return get(n.right, num);
        }
    }
 /////////////////////_____________PRINTING_____________///////////////////////////////////
    /**
     * Print all keys of the tree in a sequence, in-order. That is, for each
     * node, the keys in the left subtree should appear before the key in the
     * node. Also, for each node, the keys in the right subtree should appear
     * before the key in the node. For each subtree, its keys should appear
     * within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()" Example 2: Tree containing only "A"
     * -- output: "(()A())" Example 3: Tree: B / \ A C \ D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment:
     * (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    public String printKeysInOrder()
    {
        if (isEmpty())
        {
        	return "()";
        }
        
        if (size() == 1)
        {
        	return "(()" + root.val + "())";
        }
        
        Node current = root;
        String returnString = buildString(current);
        return "(" + returnString + ")";
    }
 
    private String buildString(Node n)
    {
        String left = "";
        
        if (n.left != null)
        {
        	left = buildString(n.left);
        }
        
        String right = "";
        
        if (n.right != null)
        {
        	right = buildString(n.right);
        }
        
        return "(" + left + ")" + n.val + "(" + right + ")";
    }
 
    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for
     * details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    
    public String prettyPrintKeys()
    {
    	if (isEmpty())
    	{
    		return "-null";
    	}
    	
    	String prefix = "";
    	return buildPrettyString(root, prefix) + "\n";
    }
    
    private String buildPrettyString(Node n, String start)
    {
    	if (n == null)
    	{
    		return start + "-null";
    	}
    	
    	else
    	{
    		return start + "-" + n.val + "\n" +
    				buildPrettyString(n.left, start + " |") + "\n" +
    				buildPrettyString(n.right, start + "  ");
    	}
    }
 
    /**
     * Deteles a key from a tree (if the key is in the tree). Note that this
     * method works symmetrically from the Hibbard deletion: If the node to be
     * deleted has two child nodes, then it needs to be replaced with its
     * predecessor (not its successor) node.
     *
     * @param key
     *            the key to delete
     */
////////////////////______________DELETE___________//////////////////////////////////////////////    
    public void delete(Key key)
    {
        root = delete(root, key);
    }
 
    private Node delete(Node x, Key key)
    {
        if (x == null)
        {
        	return null;
        }
        
        int cmp = key.compareTo(x.key);
        
        if (cmp < 0)
        {
        	x.left = delete(x.left, key);
        }
        
        else if (cmp > 0)
        {
        	x.right = delete(x.right, key);
        }
        
        else
        {
            if (x.right == null)
            {
            	return x.left;
            }
            
            else if (x.left == null)
            {
            	return x.right;
            }
            
            Node t = x;
            Node temp = x.left; 
            
            if (temp.right == null)
            {
            	x = temp.left;
            }
            
            while (temp.right != null)
            {
                x = temp.right;
                temp = temp.right;
            }
            
            x.left = deleteMax(t.left);
            x.right = t.right;
        }
        
        x.N = size(x.right) + size(x.left) + 1;
        return x;
    }
 
    public void deleteMax()
    {
        root = deleteMax(root);
    }
 
    private Node deleteMax(Node x)
    {
        if (x.right == null)
        {
        	return x.left;
        }
        
        x.right = deleteMax(x.right);
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }
    
}
