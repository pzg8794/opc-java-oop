/* a class for binary tree nodes
 * @author	Piter Garcia
 * @assignment	Lab5
 * @date	
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

public class BinarySearchTree<E extends java.lang.Comparable<E>> {

    /** 
    * A node in a binary tree 
    * @author         Piter Garcia
     * @param <E>
    * @date           
    */
    
    private static class BinaryNode<E> {
      private int item;
      private E item2;
      private static int size = 0;
      private BinaryNode left;
      private BinaryNode right;
    
    
      /** 
      * constructor to build a node with no subtrees
      * @param the value to be stored by this node
      */
      private BinaryNode(int value) {
        item = value;
        size++;
        left = null;
        right = null;
      }
      
      /** 
      * constructor to build a node with no subtrees
      * @param the value to be stored by this node
      */
      private BinaryNode(E value) {
        item2 = value;
        item = Integer.parseInt(value.toString());
        size++;
        left = null;
        right = null;
      }
      
  
    
      /** 
      * constructor to build a node with a specified (perhaps null) subtrees
      * @param the value to be stored by this node
      * @param the left subtree for this node
      * @param the right subtree for this node
      */
      private BinaryNode(E value, BinaryNode l, BinaryNode r) {
    	size++;
        item2 = value;
        item = Integer.parseInt(value.toString());
        left = l;
        right = r;
      }

    }

    /* the root of the tree is the only data field needed */
    protected BinaryNode root = null; // null when tree is empty

    	/* constructs an empty tree
    	*/
    	public BinarySearchTree() {
    		super();
    	}

    	/* constructs a tree with one element, as given
    	 * @param	value to be used for the one element in the tree
    	 */
    	public BinarySearchTree(E value) {
    		super();
    		root = new BinaryNode(value);
    	}

    	/* constructs a tree with the given node as root
    	 * @param	newRoot to be used as the root of the new tree
    	 */
    	public BinarySearchTree(BinaryNode newRoot) {
    		super();
    		root = newRoot;
    	}

    	
    	/* find a value in the tree
    	* @param	key identifies the node value desired
    	* @return	the node value found, or null if not found
    	*/
    	public boolean contains(int key) {
			
    		if( this.get(key) == 0 )
    			return false;
    		else
    			return true;
    	}
    	
    	
    	
    	/* find a value in the tree
    	* @param	key identifies the node value desired
    	* @return	the node value found, or null if not found
    	*/
    	public int get(int key) {
    		BinaryNode node = root;
    	
    		while (node != null) {
    			if ((key == node.item)) {
    				return node.item;
    			}
	    
    			if (key < node.item) {
    				node = node.left;
    			} else {
    				node = node.right;
    			}
    		}
    		return 0;
    	}
    	
    	
    	
    	/* find a value in the tree
    	* @param	key identifies the node value desired
    	* @return	the node value found, or null if not found
    	*/
    	public int size() {
    		
    		return root.size;
    	}
    	
    	
    	/* find a value in the tree
    	* @param	key identifies the node value desired
    	* @return	the node value found, or null if not found
    	*/
    	@SuppressWarnings("unchecked")
		public <E> E get1(int key) {
    		BinaryNode<E> node = root;
    	
    		while (node != null) {
    			if (key == node.item) {
    				return (E) node.item2;
    			}
	    
    			if (key < node.item) {
    				node = node.left;
    			} else {
    				node = node.right;
    			}
    		}
    		return null;
    	}
    	
    	
    	/* find a value in the tree
    	* @param	key identifies the node value desired
    	* @return	the node value found, or null if not found
    	*/
    	@SuppressWarnings("unchecked")
		public int get2(Collection<? extends E>	c) {
    		BinaryNode<E> node = root;
    	
    		while (node != null) {
    			if (((Comparable<E>) c).compareTo(node.item2) == 0) {
    				return node.item;
    			}
	    
    			if (((Comparable<E>) c).compareTo(node.item2) < 0) {
    				node = node.left;
    			} else {
    				node = node.right;
    			}
    		}
    		return -1;
    	}

    	/* add a value to the tree, replacing an existing value if necessary
    	 * @param	value to be inserted
    	 */
    	public boolean add(int value) {
    		
    		System.out.println(value);
    		System.out.println("contains: "+this.contains(value));
    		
    		if( this.contains(value)){
    			return false;
    		}
    		else{
    			root = add(value, root);
    			System.out.println(this.size());
    			return true;
    		}
    	}
    	
    	
    	/* add a value to the tree, replacing an existing value if necessary
    	 * @param	value to be inserted
    	 */
    	@SuppressWarnings("unchecked")
		public boolean add2(E value) {
    		
    		if( this.contains(this.get2((Collection<? extends E>) value))){
    			return false;
    		}
    		else{
    			root = add(Integer.parseInt(value.toString()),(Collection<? extends E>) value, root);
    			return true;
    		}
    	}
    	
    	
    	/* add a value to the tree, replacing an existing value if necessary
    	 * @param	value to be inserted
    	 */
    	@SuppressWarnings("unchecked")
		public boolean add( int key, Collection<? extends E> c) {
    		
    		if( this.contains(key)){
    			return false;
    		}
    		else{
    			root = add(key, c, root);
    			return true;
    		}
    	}

    	private BinaryNode add(int key, Collection<? extends E> value, BinaryNode node) {
    		if (node == null) {
    			return new BinaryNode(key);
    		}
    		if (key == node.item) {
    			// replace the value in this node with a new value
    			node.item = key;
    			node.item2 = value;
    			node.size++;
    			// alternative code creates new node, leaves old node unchanged:
    			//return new BinaryNode<T>(value, node.left, node.right);
    		} else {
    			if (key < node.item) {	// add to left subtree
    				node.left = add(key, value, node.left);
    			} else {		// add to right subtree
    				node.right = add(key, value, node.right);
    			}
    		}
    		return node;
		}

		private BinaryNode add(Collection<? extends E> value, BinaryNode node) {
    		if (node == null) {
    			return new BinaryNode(value);
    		}
    		if (this.get2(value) == node.item) {
    			// replace the value in this node with a new value
    			node.item2 = value;
    			node.item = Integer.parseInt(value.toString());
    			node.size++;
    			// alternative code creates new node, leaves old node unchanged:
    			//return new BinaryNode<T>(value, node.left, node.right);
    		} else {
    			if (this.get2(value) < node.item) {	// add to left subtree
    				node.left = add(value, node.left);
    			} else {		// add to right subtree
    				node.right = add(value, node.right);
    			}
    		}
    		return node;
		}

		/* add a value to the tree, replacing an existing value if necessary
    	 * @param	value to be inserted
    	 * @param	node that is the root of the subtree in which to insert
    	 * @return	the subtree with the node inserted
    	 */
    	@SuppressWarnings("unchecked")
		protected BinaryNode add(int value, BinaryNode node) {
    		if (node == null) {
    			return new BinaryNode(value);
    		}
    		if (value == node.item) {
    			// replace the value in this node with a new value
    			node.item = value;
    			// alternative code creates new node, leaves old node unchanged:
    			//return new BinaryNode<T>(value, node.left, node.right);
    		} else {
    			if (value < node.item) {	// add to left subtree
    				node.left = add(value, node.left);
    			} else {		// add to right subtree
    				node.right = add(value, node.right);
    			}
    		}
    		return node;
    	}

    	/* remove a value from the tree, if it exists
    	 * @param	key such that value.compareTo(key) == 0 for the node to remove
    	 */
    	public boolean remove(int key) {
    		    		
    		if( root != null){
    			root = remove(key, root);
    			root.size--;
    			return true;
    		}
    		else
    			return false;
    	}
    	
    	/* remove a value from the tree, if it exists
    	 * @param	key such that value.compareTo(key) == 0 for the node to remove
    	 */
    	public void clear() {
    		root.size = 0;
    		root = null;
    	}

    	/* remove a value from the tree, if it exists
    	 * @param	key such that value.compareTo(key) == 0 for the node to remove
    	 * @param	node the root of the subtree from which to remove the value
    	 * @return	the new tree with the value removed
    	 */
    	protected BinaryNode remove(int value, BinaryNode node) {
    		if (node == null) {	// key not in tree
    			return null;
    		}
    		if (value == node.item) { // remove this node
    			if (node.left == null) { // replace this node with right child
    				return node.right;
    			} else if (node.right == null) { // replace with left child
    				return node.left;
    			} else {
    				// replace the value in this node with the value in the
    				// rightmost node of the left subtree
    				node.item = getRightmost(node.left);
    				// now remove the rightmost node in the left subtree,
    				// by calling "remove" recursively
    				node.left = remove(node.item, node.left);
    				// return node;  -- done below
    			}
    			
    		} else {		// remove from left or right subtree
    			if (value < node.item) {
    				// remove from left subtree
    				node.left = remove(value, node.left);
    			} else {		// remove from right subtree
    				node.right = remove(value, node.right);
    			}
    		}
    		return node;
    	}

    	protected int getRightmost(BinaryNode node) {
    		assert(node != null);
    		BinaryNode<E> right = node.right;
    		if (right == null) {
    			return node.item;
    		} else {
    			return getRightmost(right);
    		}
    	}

    	/* iterator, traverses the tree in order */
    	public Iterator iterator() {
    		return new TreeIterator(root);
    	}

    	/* traverses the tree in pre-order */
    	public Iterator preIterator() {
    		return new TreeIterator(root, true);
    	}

    	/* traverses the tree in post-order */
    	public Iterator postIterator() {
    		return new TreeIterator(root, false);
    	}

    	/* toString
    	 * @returns	the string representation of the tree.
    	 */
    	public String toString() {
    		return toString(root);
    	}

    	protected String toString(BinaryNode node) {
    		if (node == null) {
    			return "";
    		}
    		return node.item + "(" + toString(node.left) + ", " +
    		toString(node.right) + ")";
    	}

    	/* unit test
    	 * @param	arguments, ignored
    	 */
    	public static void main(String[] arguments) {
    		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();

    		tree.add(5);
    		tree.add(7);
    		tree.add(9);
    		tree.add(3);
    		tree.add(1);
    		tree.add(2);
    		tree.add(4);
    		tree.add(6);
    		tree.add(8);
    		tree.add(10);

    		System.out.println(tree);

    		Iterator<Integer> it = tree.preIterator();
    		System.out.print("pre-order: ");
    		
    		while (it.hasNext()) {
    			System.out.print(it.next() + ", ");
    		}
    		System.out.println("");

    		it = tree.iterator();
    		System.out.print("in-order: ");
	
    		while (it.hasNext()) {
    			System.out.print(it.next() + ", ");
    		}
    		System.out.println("");

    		it = tree.postIterator();
    		System.out.print("post-order: ");
    		
    		while (it.hasNext()) {
    			System.out.print(it.next() + ", ");
    		}
    		System.out.println("");

    		if ( tree.get(5) != 5) {
    			System.out.println("error: tree does not have 5");
    		}
    		if (tree.get(13) != 0) {
    			System.out.println("error: tree has 13, should not");
    		}
    		if (tree.get(10) != 10) {
    			System.out.println("error: tree does not have 10");
    		}

    		tree.add(10);
    		System.out.println(tree);

    		tree.remove(10);
    		tree.remove(2);
    		tree.remove(5);
    		tree.remove(9);
    		tree.remove(10);
    		tree.remove(9);
    		System.out.println(tree);

    		if (tree.get(5) != 0) {
    			System.out.println("error: tree has 5, should not");
    		}
    		if (tree.get(13) != 0) {
    			System.out.println("error: tree has 13, should not");
    		}
    		if (tree.get(3) != 3) {
    			System.out.println("error: tree does not have 3");
    		}

    	}


    /* an iterator class to iterate over binary trees
    * @author	
    * @assignment	
    * @date	
    */
    
    private class TreeIterator implements Iterator {
        /* the class variables keep track of how much the iterator
         * has done so far, and what remains to be done.
         * root is null when the iterator has not been initialized,
         * or the entire tree has been visited.
         * the first stack keeps track of the last node to return
         * and all its ancestors
         * the second stack keeps track of whether the node visited
         * is to the left (false) or right (true) of its parent
         */
        protected BinaryNode root = null;
        protected Stack<BinaryNode> visiting = new Stack<BinaryNode>();
        protected Stack<Boolean> visitingRightChild = new Stack<Boolean>();
        /* only one of these booleans can be true */
        boolean preorder = false;
        boolean inorder = true;
        boolean postorder = false;
    
        /* constructor for in-order traversal
         * @param	root of the tree to traverse
         */
        public TreeIterator(BinaryNode<E> root) {
            this.root = root;
            visiting = new Stack<BinaryNode>();
            visitingRightChild = new Stack<Boolean>();
            preorder = false;
            inorder = true;
            postorder = false;
        }
    
        /* constructor for pre-order or post-order traversal
         * @param	root of the tree to traverse
         * @param	inPreorder true if pre-order, false if post-order
         */
        public TreeIterator(BinaryNode<E> root, boolean inPreorder) {
            this.root = root;
            visiting = new Stack<BinaryNode>();
            visitingRightChild = new Stack<Boolean>();
            preorder = inPreorder;
            inorder = false;
            postorder = ! preorder;
        }
    
        public boolean hasNext() {
            return (root != null);
        }
    
        public Object next() {
        	
            if (! hasNext()) {
                throw new java.util.NoSuchElementException("no more elements");
            }
            if (preorder) {
                return preorderNext();
            } else if (inorder) {
                return inorderNext();
            } else if (postorder) {
                return postorderNext();
            } else {
                assert(false);
                return 0;
            }
        }
    
        // return the node at the top of the stack, push the next node if any
        private int preorderNext() {
            if (visiting.empty()) {	// at beginning of iterator
                visiting.push(root);
            }
            BinaryNode node = visiting.pop();
            int result = node.item;
            // need to visit the left subtree first, then the right
            // since a stack is a LIFO, push the right subtree first, then
            // the left.  Only push non-null trees
            if (node.right != null) {
                visiting.push(node.right);
            }
            if (node.left != null) {
                visiting.push(node.left);
            }
            // may not have pushed anything.  If so, we are at the end
            if (visiting.empty()) { // no more nodes to visit
                root = null;
            }
            return node.item;
        }
    
        /* find the leftmost node from this root, pushing all the
        * intermediate nodes onto the visiting stack
        * @param	node the root of the subtree for which we
        *		are trying to reach the leftmost node
        * @changes	visiting takes all nodes between node and the leftmost
        */
        private void pushLeftmostNode(BinaryNode node) {
            // find the leftmost node
            if (node != null) {
                visiting.push(node); // push this node
                pushLeftmostNode(node.left); // recurse on next left node
            }
        }
    
        /* return the leftmost node that has not yet been visited
        * that node is normally on top of the stack
        * inorder traversal doesn't use the visitingRightChild stack
        */
        private int inorderNext() {
            if (visiting.empty()) {	// at beginning of iterator
                // find the leftmost node, pushing all the intermediate nodes
                // onto the visiting stack
                pushLeftmostNode(root);
            } // now the leftmost unvisited node is on top of the visiting stack
            BinaryNode<E> node = visiting.pop();
            int result = node.item; // this is the value to return
            // if the node has a right child, its leftmost node is next
            if (node.right != null) {
                BinaryNode<E> right = node.right;
                // find the leftmost node of the right child
                pushLeftmostNode (right);
                // note "node" has been replaced on the stack by its right child
            } // else: no right subtree, go back up the stack
              // next node on stack will be next returned
            if (visiting.empty()) { // no next node left
                root = null;
            }
            return result;
        }
    
        /* find the leftmost node from this root, pushing all the
         * intermediate nodes onto the visiting stack
         * and also stating that each is a left child of its parent
         * @param	node the root of the subtree for which we
         *		are trying to reach the leftmost node
         * @changes	visiting takes all nodes between node and the leftmost
         */
        private void pushLeftmostNodeRecord(BinaryNode node) {
            // find the leftmost node
            if (node != null) {
                visiting.push(node); // push this node
                visitingRightChild.push(false); // record that it is on the left
                pushLeftmostNodeRecord(node.left); // continue looping
            }
        }
    
        // 
        private int postorderNext() {
            if (visiting.empty()) {	// at beginning of iterator
                // find the leftmost node, pushing all the intermediate nodes
                // onto the visiting stack
                pushLeftmostNodeRecord(root);
            } // the node on top of the visiting stack is the next one to be
              // visited, unless it has a right subtree
            if ((visiting.peek().right == null) || // no right subtree, or
                (visitingRightChild.peek())) { // right subtree already visited
                // already visited right child, time to visit the node on top
                int result = visiting.pop().item;
                visitingRightChild.pop();
                if (visiting.empty()) {
            	root = null;
                }
                return result;
            } else { // now visit this node's right subtree
                // pop false and push true for visiting right child
                if (visitingRightChild.pop()) {
            	assert(false);
                }
                visitingRightChild.push(true);
                // now push everything down to the leftmost node
                // in the right subtree
                BinaryNode right = visiting.peek().right;
                assert(right != null);
                pushLeftmostNodeRecord(right);
                // use recursive call to visit that node
                return postorderNext();
            }
        }
    
        /* not implemented */
        public void remove() {
            throw new java.lang.UnsupportedOperationException("remove");
        }
    
        /* give the entire state of the iterator: the tree and the two stacks */
        public String toString() {
            if (preorder) {
                return "pre: " + toString(root) + "\n" + visiting + "\n";
            }
            if (inorder) {
                return "in: " + toString(root) + "\n" + visiting + "\n";
            }
            if (postorder) {
                return "post: " + toString(root) + "\n" + visiting + "\n" +
            	visitingRightChild;
            }
            return "none of pre-order, in-order, or post-order are true";
        }
    
        private String toString(BinaryNode node) {
            if (node == null) {
                return "";
            } else {
                return node.toString() + "(" + toString(node.left) + ", " +
            	   toString(node.right) + ")";
            }
        }

//        /* unit test
//         * @param	arguments, ignored
//         */
//        public static void main(String[] arguments) {
//            BinaryNode<String> x = new BinaryNode<String>("x");
//            BinaryNode<String> z = new BinaryNode<String>("z");
//            BinaryNode<String> y = new BinaryNode<String>("y", x, z);
//            testIterator(new TreeIterator<String>(y));
//    
//            testIterator(new TreeIterator<String>(y, true));
//            testIterator(new TreeIterator<String>(y, false));
//    
//            BinaryNode<String> a = new BinaryNode<String>("a");
//            BinaryNode<String> c = new BinaryNode<String>("c");
//            BinaryNode<String> b = new BinaryNode<String>("b", a, null);
//            BinaryNode<String> m = new BinaryNode<String>("m", b, y);
//            testIterator(new TreeIterator<String>(m));
//    
//            testIterator(new TreeIterator<String>(m, true));
//            testIterator(new TreeIterator<String>(m, false));
//    
//        }
//    
//        public static void testIterator(Iterator<String> it) {
//            System.out.println("it = " + it);
//            while (it.hasNext()) {
//                String result = it.next();
//                System.out.println("it.next gives " + result + "\n it = " + it);
//            }
//        }
    }
}