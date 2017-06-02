package test1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TestHuffmanCode extends JApplet {

	private JTextField jtfText = new JTextField();
	  private JButton jbtShowHuffmanTree = new JButton("Show Huffman Tree");
	  private TreeView treeView = new TreeView();
	  private Tree tree;
	  
	  private JTextField jtfBits = new JTextField();
	  private JButton jbtShowText = new JButton("Decode Text");

	  public static void main(String[] args) {
	    JFrame frame = new JFrame("Exercise26_21: HuffmanCodingAnimation");
	    JApplet applet = new TestHuffmanCode();
	    frame.add(applet);
	    frame.setSize(500, 300);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	  }
	  
	  public TestHuffmanCode() {
	    add(new JScrollPane(treeView));
	    JPanel panel = new JPanel(new BorderLayout());
	    panel.add(new JLabel("Enter a text"), BorderLayout.WEST);
	    panel.add(jtfText, BorderLayout.CENTER);
	    panel.add(new JScrollPane(jbtShowHuffmanTree), BorderLayout.EAST);
	    add(panel, BorderLayout.NORTH);
	    
	    JPanel panel2 = new JPanel(new BorderLayout());
	    panel2.add(new JLabel("Enter a bit string"), BorderLayout.WEST);
	    panel2.add(jtfBits, BorderLayout.CENTER);
	    panel2.add(new JScrollPane(jbtShowText), BorderLayout.EAST);
	    add(panel2, BorderLayout.SOUTH);

	    jbtShowHuffmanTree.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        String text = jtfText.getText();
	        int[] counts = getCharacterFrequency(text);
	        tree = getHuffmanTree(counts);
	        treeView.setTree(tree);
	        
	        if (text.length() == 0) {
	          JOptionPane.showMessageDialog(null, "No text" );          
	        }
	        else {
	          String[] codes = getCode(tree.root);
	          JOptionPane.showMessageDialog(null, 
	            text + " is encoded to " + encode(text, codes),
	            "Encode Text to Bits", JOptionPane.INFORMATION_MESSAGE);
	        }
	      }
	     });
	    
	    jbtShowText.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        String bits = jtfBits.getText();

	        if (tree == null) {
	          JOptionPane.showMessageDialog(null, "No tree" );          
	        }
	        else if (bits.length() == 0) {
	          JOptionPane.showMessageDialog(null, "No codes" );
	        }
	        else {
	          String text = decode(bits);
	          if (text == null) {
	            JOptionPane.showMessageDialog(null, "Incorrect bits ");            
	          }
	          else
	            JOptionPane.showMessageDialog(null, 
	              bits + " is encoded to " + text,
	              "Decode Bits to Text", JOptionPane.INFORMATION_MESSAGE);
	        }
	      }
	    });
	  }
	  
	  /** Decode the bit string into a text */
	  public String decode(String bits) {
	    String result = "";
	    
	    Tree.Node p = tree.root; // Start from the root
	    for (int i = 0; i < bits.length(); i++) {
	      if (bits.charAt(i) == '0') 
	        p = p.left;
	      else if (bits.charAt(i) == '1')
	        p = p.right;
	      else
	        return null; // Wrong bits
	      
	      if (p.left == null) { // A leaf detected
	        result += p.element;
	        p = tree.root; // Restart from the root
	      }
	    }
	    
	    return result;
	  }
	  
	  /** Encode the text using the codes */
	  public static String encode(String text, String[] codes) {
	    String result = "";
	    for (int i = 0; i < text.length(); i++) 
	      result += codes[text.charAt(i)];    
	    return result;   
	  }
	  
	  /** Get Huffman codes for the characters 
	   * This method is called once after a Huffman tree is built
	   */
	  public static String[] getCode(Tree.Node root) {
	    if (root == null) return null;    
	    String[] codes = new String[2 * 128];
	    assignCode(root, codes);
	    return codes;
	  }
	  
	  /* Recursively get codes to the leaf node */
	  private static void assignCode(Tree.Node root, String[] codes) {
	    if (root.left != null) {
	      root.left.code = root.code + "0";
	      assignCode(root.left, codes);
	      
	      root.right.code = root.code + "1";
	      assignCode(root.right, codes);
	    }
	    else {
	      codes[(int)root.element] = root.code;
	    }
	  }
	  
	  /** Get a Huffman tree from the codes */  
	  public static Tree getHuffmanTree(int[] counts) {
	    // Create a heap to hold trees
	    Heap<Tree> heap = new Heap<Tree>();
	    for (int i = 0; i < counts.length; i++) {
	      if (counts[i] > 0)
	        heap.add(new Tree(counts[i], (char)i)); // A leaf node tree
	    }
	    
	    while (heap.getSize() > 1) { 
	      Tree t1 = heap.remove(); // Remove the smallest weight tree
	      Tree t2 = heap.remove(); // Remove the next smallest weight 
	      heap.add(new Tree(t1, t2)); // Combine two trees
	    }

	    return heap.remove(); // The final tree
	  }
	  
	  /** Get the frequency of the characters */
	  public static int[] getCharacterFrequency(String text) {
	    int[] counts = new int[256]; // 256 ASCII characters
	    
	    for (int i = 0; i < text.length(); i++)
	      counts[(int)text.charAt(i)]++; // Count the character in text
	    
	    return counts;
	  }
	  
	  // Inner class TreeView for displaying a tree on a panel
	  class TreeView extends JPanel {   
	    private int radius = 20; // Tree node radius
	    private int vGap = 50; // Gap between two levels in a tree
	    Tree tree;
	    
	    public TreeView() { 
	    }
	    
	    public TreeView(Tree tree) {
	      this.tree = tree;  
	    }
	    
	    public void setTree(Tree tree) {
	      this.tree = tree;
	      repaint();
	    }
	    
	    protected void paintComponent(Graphics g) {
	      super.paintComponent(g);

	      if (tree == null) return;
	      
	      if (tree.root != null) {
	        // Display tree recursively    
	        displayTree(g, tree.root, getWidth() / 2, 30, 
	          getWidth() / 4); 
	      }
	    }
	        
	    /** Display a subtree rooted at position (x, y) */
	    private void displayTree(Graphics g, Tree.Node root, 
	        int x, int y, int hGap) {
	      // Display the root
	      g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
	      g.drawString(root.weight + "", x - 6, y + 4);
	            
	      if (root.left == null) // Display the character for leaf node
	        g.drawString(root.element + "", x - 6, y + 34);
	        
	      if (root.left != null) {
	        // Draw a line to the left node
	        connectLeftChild(g, x - hGap, y + vGap, x, y);
	        // Draw the left subtree recursively
	        displayTree(g, root.left, x - hGap, y + vGap, hGap / 2);
	      }
	          
	      if (root.right != null) {
	        // Draw a line to the right node
	        connectRightChild(g, x + hGap, y + vGap, x, y);
	        // Draw the right subtree recursively
	        displayTree(g, root.right, x + hGap, y + vGap, hGap / 2);  
	      }
	    }
	        
	    /** Connect a parent at (x2, y2) with 
	     * its left child at (x1, y1) */
	    private void connectLeftChild(Graphics g, 
	        int x1, int y1, int x2, int y2) { 
	      double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
	      int x11 = (int)(x1 + radius * (x2 - x1) / d);
	      int y11 = (int)(y1 - radius * vGap / d);
	      int x21 = (int)(x2 - radius * (x2 - x1) / d);
	      int y21 = (int)(y2 + radius * vGap / d);
	      g.drawLine(x11, y11, x21, y21);
	      g.drawString("0", (x11 + x21) / 2 - 5, (y11 + y21) / 2);
	    }
	        
	    /** Connect a parent at (x2, y2) with 
	     * its right child at (x1, y1) */
	    private void connectRightChild(Graphics g, 
	        int x1, int y1, int x2, int y2) {
	      double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
	      int x11 = (int)(x1 - radius * (x1 - x2) / d);
	      int y11 = (int)(y1 - radius * vGap / d);
	      int x21 = (int)(x2 + radius * (x1 - x2) / d);
	      int y21 = (int)(y2 + radius * vGap / d);
	      g.drawLine(x11, y11, x21, y21);
	      g.drawString("1", (x11 + x21) / 2 + 5, (y11 + y21) / 2);
	    }
	    
	    public Dimension getPreferredSize() {
	      return new Dimension(300, 300);
	    }
	  }

	  /** Define a Huffman coding tree */
	  public static class Tree implements Comparable<Tree> {
	    Node root; // The root of the tree

	    /** Create a tree with two subtrees */
	    public Tree(Tree t1, Tree t2) {
	      root = new Node();
	      root.left = t1.root;
	      root.right = t2.root;
	      root.weight = t1.root.weight + t2.root.weight;
	    }
	    
	    /** Create a tree containing a leaf node */
	    public Tree(int weight, char element) {
	      root = new Node(weight, element);
	    }
	    
	    /** Compare trees based on their weights */
	    public int compareTo(Tree o) {
	      if (root.weight < o.root.weight) // Purposely reverse the order
	        return 1;
	      else if (root.weight == o.root.weight)
	        return 0;
	      else
	        return -1;
	    }

	    public class Node {
	      char element; // Stores the character for a leaf node
	      int weight; // weight of the subtree rooted at this node
	      Node left; // Reference to the left subtree
	      Node right; // Reference to the right subtree
	      String code = ""; // The code of this node from the root

	      /** Create an empty node */
	      public Node() {
	      }
	      
	      /** Create a node with the specified weight and character */
	      public Node(int weight, char element) {
	        this.weight = weight;
	        this.element = element;
	      }
	    }
	  }  
	  
	  public static class Heap<E extends Comparable<E>> {
	    private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

	    /** Create a default heap */
	    public Heap() {
	    }

	    /** Create a heap from an array of objects */
	    public Heap(E[] objects) {
	      for (int i = 0; i < objects.length; i++)
	        add(objects[i]);
	    }

	    /** Add a new object into the heap */
	    public void add(E newObject) {
	      list.add(newObject); // Append to the heap
	      int currentIndex = list.size() - 1; // The index of the last node

	      while (currentIndex > 0) {
	        int parentIndex = (currentIndex - 1) / 2;
	        // Swap if the current object is greater than its parent
	        if (list.get(currentIndex).compareTo(
	            list.get(parentIndex)) > 0) {
	          E temp = list.get(currentIndex);
	          list.set(currentIndex, list.get(parentIndex));
	          list.set(parentIndex, temp);
	        }
	        else
	          break; // the tree is a heap now

	        currentIndex = parentIndex;
	      }
	    }

	    /** Remove the root from the heap */
	    public E remove() {
	      if (list.size() == 0) return null;
	      
	      E removedObject = list.get(0);
	      list.set(0, list.get(list.size() - 1));
	      list.remove(list.size() - 1);

	      int currentIndex = 0;
	      while (currentIndex < list.size()) {
	        int leftChildIndex = 2 * currentIndex + 1;
	        int rightChildIndex = 2 * currentIndex + 2;
	        
	        // Find the maximum between two children
	        if (leftChildIndex >= list.size()) break; // The tree is a heap
	        int maxIndex = leftChildIndex;
	        if (rightChildIndex < list.size()) {
	          if (list.get(maxIndex).compareTo(
	              list.get(rightChildIndex)) < 0) {
	            maxIndex = rightChildIndex;
	          }
	        }      
	        
	        // Swap if the current node is less than the maximum 
	        if (list.get(currentIndex).compareTo(
	            list.get(maxIndex)) < 0) {
	          E temp = list.get(maxIndex);
	          list.set(maxIndex, list.get(currentIndex));
	          list.set(currentIndex, temp);
	          currentIndex = maxIndex;
	        }   
	        else 
	          break; // The tree is a heap
	      }

	      return removedObject;
	    }

	    /** Get the number of nodes in the tree */
	    public int getSize() {
	      return list.size();
	    }
	  }
	  
}
