package test2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import test5.GenericStack;

public class TwentyFourPokerGame extends JApplet {
	 private JLabel jlblCard1 = new JLabel();
	  private JLabel jlblCard2 = new JLabel();
	  private JLabel jlblCard3 = new JLabel();
	  private JLabel jlblCard4 = new JLabel();
	  private JTextField jtfExpression = new JTextField(8);
	  private JButton jbtVerify = new JButton("Verify");
	  private JButton jbtRefresh = new JButton("Refresh");
	  private ImageIcon[] cardIcons = new ImageIcon[52];
	  private JPanel panel1 = new JPanel();

	  private ArrayList<Integer> list = new ArrayList<Integer>();

	  public TwentyFourPokerGame() {
	    for (int i = 0; i < 52; i++)
	      list.add(i);

	    // Load the image icons
	    for (int i = 0; i < 52; i++)
	      cardIcons[i] = new ImageIcon("/currentdesign2/image/card/" + (i + 1) + ".png");
	    panel1.add(jlblCard1);
	    panel1.add(jlblCard2);
	    panel1.add(jlblCard3);
	    panel1.add(jlblCard4);

	    JPanel panel2 = new JPanel(new BorderLayout());
	    panel2.add(new JLabel("Enter an expression: "), BorderLayout.WEST);
	    panel2.add(jtfExpression, BorderLayout.CENTER);
	    panel2.add(jbtVerify, BorderLayout.EAST);

	    JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    panel3.add(jbtRefresh);

	    add(panel1, BorderLayout.CENTER);
	    add(panel2, BorderLayout.SOUTH);
	    add(panel3, BorderLayout.NORTH);

	    refresh();

	    jbtRefresh.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        refresh();
	      }
	    });

	    jbtVerify.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        // Check whether all numbers in the expression are currently selected
	        if (!correctNumbers()) {
	          JOptionPane
	              .showMessageDialog(null,
	                  "The numbers in the expression don't \nmatch the numbers in the set ");
	        } else {
	          // Check whether the expression evaluates to 24.
	          if (evaluate()) {
	            JOptionPane.showMessageDialog(null, "Correct");
	          } else {
	            JOptionPane.showMessageDialog(null, "Incorrect result");
	          }
	        }
	      }
	    });
	  }

	  private boolean correctNumbers() {
	    // Get the card values from the expression
	    String[] values = jtfExpression.getText().trim().split("[()+-/* ]");
	    //String[] values = jtfExpression.getText().trim().split("[+|-|\\*|/| |(|)]");
	    ArrayList<Integer> valueList = new ArrayList<Integer>();

	    for (int i = 0; i < values.length; i++)
	      valueList.add(Integer.parseInt(values[i]));

	    Collections.sort(valueList);
	    Collections.sort(currentCardValues);

	    if (valueList.equals(currentCardValues))
	      return true;
	    else
	      return false;
	  }

	  private boolean evaluate() {
	    return EvaluateExpression
	        .evaluateExpression(jtfExpression.getText().trim()) == 24;
	  }

	  ArrayList<Integer> currentCardValues = new ArrayList<Integer>();

	  private void refresh() {
	    Collections.shuffle(list);

	    int index1 = list.get(0);
	    int index2 = list.get(1);
	    int index3 = list.get(2);
	    int index4 = list.get(3);

	    currentCardValues.clear();
	    currentCardValues.add(index1 % 13 + 1);
	    currentCardValues.add(index2 % 13 + 1);
	    currentCardValues.add(index3 % 13 + 1);
	    currentCardValues.add(index4 % 13 + 1);

	    jlblCard1.setIcon(cardIcons[index1]);
	    jlblCard2.setIcon(cardIcons[index2]);
	    jlblCard3.setIcon(cardIcons[index3]);
	    jlblCard4.setIcon(cardIcons[index4]);
	  }

	  public static void main(String[] args) {
		 TwentyFourPokerGame  applet = new TwentyFourPokerGame ();
	    JFrame frame = new JFrame();
	    // EXIT_ON_CLOSE == 3
	    frame.setDefaultCloseOperation(3);
	    frame.setTitle("Exercise25_7: 24-Point Card Game");
	    frame.add(applet, BorderLayout.CENTER);
	    applet.init();
	    applet.start();
	    frame.pack();
	    frame.setLocationRelativeTo(null); // Center the frame
	    frame.setVisible(true);
	  }

	  public static class EvaluateExpression {
	    /** Evaluate an expression */
	    public static int evaluateExpression(String expression) {
	      // Create operandStack to store operands
	      GenericStack<Integer> operandStack = new GenericStack<Integer>();

	      // Create operatorStack to store operators
	      GenericStack<Character> operatorStack = new GenericStack<Character>();

	      // Extract operands and operators
	      java.util.StringTokenizer tokens = new java.util.StringTokenizer(
	          expression, "()+-/*", true);

	      // Phase 1: Scan tokens
	      while (tokens.hasMoreTokens()) {
	        String token = tokens.nextToken().trim(); // Extract a token
	        if (token.length() == 0) // Blank space
	          continue; // Back to the while loop to extract the next token
	        else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
	          // Process all +, -, *, / in the top of the operator stack
	          while (!operatorStack.isEmpty()
	              && (operatorStack.peek() == '+' || operatorStack.peek() == '-'
	                  || operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
	            processAnOperator(operandStack, operatorStack);
	          }

	          // Push the + or - operator into the operator stack
	          operatorStack.push(token.charAt(0));
	        } else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
	          // Process all *, / in the top of the operator stack
	          while (!operatorStack.isEmpty()
	              && (operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
	            processAnOperator(operandStack, operatorStack);
	          }

	          // Push the * or / operator into the operator stack
	          operatorStack.push(token.charAt(0));
	        } else if (token.trim().charAt(0) == '(') {
	          operatorStack.push('('); // Push '(' to stack
	        } else if (token.trim().charAt(0) == ')') {
	          // Process all the operators in the stack until seeing '('
	          while (operatorStack.peek() != '(') {
	            processAnOperator(operandStack, operatorStack);
	          }

	          operatorStack.pop(); // Pop the '(' symbol from the stack
	        } else { // An operand scanned
	          // Push an operand to the stack
	          operandStack.push(new Integer(token));
	        }
	      }

	      // Phase 2: process all the remaining operators in the stack
	      while (!operatorStack.isEmpty()) {
	        processAnOperator(operandStack, operatorStack);
	      }

	      // Return the result
	      return operandStack.pop();
	    }

	    /**
	     * Process one opeator: Take an operator from operatorStack and apply it on
	     * the operands in the operandStack
	     */
	    public static void processAnOperator(GenericStack<Integer> operandStack,
	        GenericStack<Character> operatorStack) {
	      char op = operatorStack.pop();
	      int op1 = operandStack.pop();
	      int op2 = operandStack.pop();
	      if (op == '+')
	        operandStack.push(op2 + op1);
	      else if (op == '-')
	        operandStack.push(op2 - op1);
	      else if (op == '*')
	        operandStack.push(op2 * op1);
	      else if (op == '/')
	        operandStack.push(op2 / op1);
	    }
	  }
}
