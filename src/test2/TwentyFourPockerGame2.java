package test2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TwentyFourPockerGame2 extends JApplet {

	private ArrayList<JCard> cards = new ArrayList<JCard>();
	  private JCard jcCard1 = new JCard();
	  private JCard jcCard2 = new JCard();
	  private JCard jcCard3 = new JCard();
	  private JCard jcCard4 = new JCard();
	  private JTextField jtfExpression = new JTextField();
	  private JTextField jtfSolution = new JTextField();

	  public TwentyFourPockerGame2() {
	    // Creates user interface items
	    JButton jbtRefresh = new JButton("Refresh");
	    JButton jbtVerify = new JButton("Verify");
	    JButton jbtFindSolution = new JButton("Find a Solution");
	    JLabel jlbText = new JLabel("Enter an experssion:");

	    // Creates Grouping Panels
	    JPanel topPanel = new JPanel();
	    JPanel cardPanel = new JPanel();
	    JPanel bottomPanel = new JPanel();

	    // Creates all the cards
	 /*   for (int i = 0; i < 4; i++)
	      for (int j = 0; j < 13; j++) {
	        int k = i * 13 + j + 1;
	        cards.add(new JCard(new ImageIcon("image/card/" + k
	            + ".png"), j + 1));
	      } */
	    
	    for (int i = 0; i < 52; i++)
	        cards.add(new JCard(new ImageIcon("/currentdesign2/image/card/" + (i + 1)
	              + ".png"), i % 13 + 1));
	        

	    // Sets interface variables
	    jtfExpression.setHorizontalAlignment(SwingConstants.LEFT);
	    jtfExpression.setColumns(10);
	    jtfSolution.setHorizontalAlignment(SwingConstants.LEFT);
	    jtfSolution.setColumns(9);

	    // Adds all interface items to panels
	    topPanel.add(jbtFindSolution);
	    topPanel.add(jtfSolution);
	    topPanel.add(jbtRefresh);
	    cardPanel.add(jcCard1);
	    cardPanel.add(jcCard2);
	    cardPanel.add(jcCard3);
	    cardPanel.add(jcCard4);
	    bottomPanel.add(jlbText);
	    bottomPanel.add(jtfExpression);
	    bottomPanel.add(jbtVerify);

	    // Adds panels to applet
	    add(topPanel, BorderLayout.NORTH);
	    add(cardPanel, BorderLayout.CENTER);
	    add(bottomPanel, BorderLayout.SOUTH);

	    // Register action Listeners
	    jbtRefresh.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        cardRefresh();
	      }
	    });

	    jbtVerify.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        cardExpression();
	      }
	    });

	    jbtFindSolution.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        jtfSolution.setText(findSolution());
	      }
	    });
	  }

	  /** main method */
	  public static void main(String[] args) {
	    JFrame frame = new JFrame();
	    TwentyFourPockerGame2 applet = new TwentyFourPockerGame2();

	    frame.add(applet);
	    frame.setLocationRelativeTo(null);
	    frame.setTitle("Exercise25_9: 24-Point Card Game");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(350, 210);
	    frame.setVisible(true);
	  }

	  /*
	   * Shuffles the cards array list and assigns the first 4 cards to be the cards
	   * displayed
	   */
	  private void cardRefresh() {
	    // Clears all text fields
	    jtfExpression.setText(null);
	    jtfSolution.setText(null);

	    Collections.shuffle(cards);

	    jcCard1.setIcon(cards.get(0).getIcon());
	    jcCard1.faceValue = cards.get(0).faceValue;

	    jcCard2.setIcon(cards.get(1).getIcon());
	    jcCard2.faceValue = cards.get(1).faceValue;

	    jcCard3.setIcon(cards.get(2).getIcon());
	    jcCard3.faceValue = cards.get(2).faceValue;

	    jcCard4.setIcon(cards.get(3).getIcon());
	    jcCard4.faceValue = cards.get(3).faceValue;
	  }

	  /* Checks if the entered expression is correct */
	  private void cardExpression() {
	    // Adds the numbers into the expressionValues array list
	    ArrayList<Integer> expressionValues = new ArrayList<Integer>();
	    String expression = jtfExpression.getText();
	    String[] numbers = expression.split("[()+-/* ]");
	    for (String s : numbers) {
	      if (s.length() > 0) {
	        expressionValues.add(new Integer(s));
	      }
	    }

	    // Checks if the entered expression is valid and correct
	    if (!expressionValues.isEmpty()) {
	      if (expressionValues.contains(jcCard1.faceValue))
	        expressionValues.remove(expressionValues
	            .indexOf(jcCard1.faceValue));
	      if (expressionValues.contains(jcCard2.faceValue))
	        expressionValues.remove(expressionValues
	            .indexOf(jcCard2.faceValue));
	      if (expressionValues.contains(jcCard3.faceValue))
	        expressionValues.remove(expressionValues
	            .indexOf(jcCard3.faceValue));
	      if (expressionValues.contains(jcCard4.faceValue))
	        expressionValues.remove(expressionValues
	            .indexOf(jcCard4.faceValue));
	      if (expressionValues.isEmpty()) {
	        if (EvaluateExpression.evaluateExpression(expression) == 24)
	          JOptionPane.showMessageDialog(null, "Correct Solution");
	        else
	          JOptionPane.showMessageDialog(null, "Invalid Expression", "Message", JOptionPane.ERROR_MESSAGE);
	      } else
	        JOptionPane.showMessageDialog(null, "Invalid Expression", "Message", JOptionPane.ERROR_MESSAGE);
	    } else
	      JOptionPane.showMessageDialog(null, "Enter an Expression", "Message", JOptionPane.ERROR_MESSAGE);
	  }

	  /* Finds a solution for the cards shown */
	  public String findSolution() {
	    int a = jcCard1.faceValue;
	    int b = jcCard2.faceValue;
	    int c = jcCard3.faceValue;
	    int d = jcCard4.faceValue;
	    String noSolution = "No solution";
	    String solution;
	    String[] operators = {"+", "-", "*", "/"};
	    int[][] allCombinations = {     		
	    	{ a, b, c, d }, { d, a, b, c },
	        { c, d, a, b }, { b, c, d, a }, { a, b, d, c }, { c, a, b, d },
	        { d, c, a, b }, { b, d, c, a }, { a, d, c, b }, { b, a, d, c },
	        { c, b, a, d }, { d, c, b, a }, { a, c, b, d }, { d, a, c, b },
	        { b, d, a, c }, { c, b, d, a }, { b, a, c, d }, { d, b, a, c },
	        { c, d, b, a }, { a, c, d, b }, { a, d, b, c }, { c, a, d, b },
	        { b, c, a, d }, { d, b, c, a } };
	    
	    for (String firstOp : operators)
	      for (String secondOp : operators)
	        for (String thirdOp : operators)
	          for (int[] cardNums : allCombinations)
	            for (int i = 0; i < 3; i++)
	              for (int j = 0; j < 5; j++) {
	                if (i == 0) {
	                  if (j == 0) {
	                    solution = cardNums[0] + firstOp
	                        + cardNums[1] + secondOp
	                        + cardNums[2] + thirdOp
	                        + cardNums[3];
	                    if (EvaluateExpression
	                        .evaluateExpression(solution) == 24)
	                      return solution;
	                  } else if (j == 1) {
	                    solution = "(" + cardNums[0] + firstOp
	                        + cardNums[1] + ")" + secondOp
	                        + cardNums[2] + thirdOp
	                        + cardNums[3];
	                    if (EvaluateExpression
	                        .evaluateExpression(solution) == 24)
	                      return solution;
	                  } else if (j == 2) {
	                    solution = cardNums[0] + firstOp + "("
	                        + cardNums[1] + secondOp
	                        + cardNums[2] + ")" + thirdOp
	                        + cardNums[3];
	                    if (EvaluateExpression
	                        .evaluateExpression(solution) == 24)
	                      return solution;
	                  } else if (j == 3) {
	                    solution = cardNums[0] + firstOp
	                        + cardNums[1] + secondOp + "("
	                        + cardNums[2] + thirdOp
	                        + cardNums[3] + ")";
	                    if (EvaluateExpression
	                        .evaluateExpression(solution) == 24)
	                      return solution;
	                  } else if (j == 4) {
	                    solution = "(" + cardNums[0] + firstOp
	                        + cardNums[1] + ")" + secondOp
	                        + "(" + cardNums[2] + thirdOp
	                        + cardNums[3] + ")";
	                    if (EvaluateExpression
	                        .evaluateExpression(solution) == 24)
	                      return solution;
	                  }
	                } else if (i == 1) {
	                  if (j == 0) {
	                    solution = "(" + cardNums[0] + firstOp
	                        + cardNums[1] + secondOp
	                        + cardNums[2] + ")" + thirdOp
	                        + cardNums[3];
	                    if (EvaluateExpression
	                        .evaluateExpression(solution) == 24)
	                      return solution;
	                  } else if (j == 1) {
	                    solution = "((" + cardNums[0] + firstOp
	                        + cardNums[1] + ")" + secondOp
	                        + cardNums[2] + ")" + thirdOp
	                        + cardNums[3];
	                    if (EvaluateExpression
	                        .evaluateExpression(solution) == 24)
	                      return solution;
	                  } else if (j == 2) {
	                    solution = "(" + cardNums[0] + firstOp
	                        + "(" + cardNums[1] + secondOp
	                        + cardNums[2] + "))" + thirdOp
	                        + cardNums[3];
	                    if (EvaluateExpression
	                        .evaluateExpression(solution) == 24)
	                      return solution;
	                  }
	                } else if (i == 2) {
	                  if (j == 0) {
	                    solution = cardNums[0] + firstOp + "("
	                        + cardNums[1] + secondOp
	                        + cardNums[2] + thirdOp
	                        + cardNums[3] + ")";
	                    if (EvaluateExpression
	                        .evaluateExpression(solution) == 24)
	                      return solution;
	                  } else if (j == 1) {
	                    solution = cardNums[0] + firstOp + "(("
	                        + cardNums[1] + secondOp
	                        + cardNums[2] + ")" + thirdOp
	                        + cardNums[3] + ")";
	                    if (EvaluateExpression
	                        .evaluateExpression(solution) == 24)
	                      return solution;
	                  } else if (j == 2) {
	                    solution = cardNums[0] + firstOp + "("
	                        + cardNums[1] + secondOp + "("
	                        + cardNums[2] + thirdOp
	                        + cardNums[3] + "))";
	                    if (EvaluateExpression
	                        .evaluateExpression(solution) == 24)
	                      return solution;
	                  }
	                }
	              }
	    return noSolution;
	  }

	  /* Card class */
	  public class JCard extends JLabel {
	    int faceValue;

	    public JCard() {
	    }

	    public JCard(javax.swing.Icon icon, int faceValue) {
	      this.setIcon(icon);
	      this.faceValue = faceValue;
	    }
	  }

	  /* Class to evaluate card Expression */
	  public static class EvaluateExpression {
	    /** Evaluate an expression */
	    public static double evaluateExpression(String expression) {
	      // Create operandStack to store operands
	      Stack<Double> operandStack = new Stack<Double>();

	      // Create operatorStack to store operators
	      Stack<Character> operatorStack = new Stack<Character>();

	      // Extract operands and operators
	      java.util.StringTokenizer tokens = new java.util.StringTokenizer(
	          expression, "()+-/*", true);

	      // Phase 1: Scan tokens
	      while (tokens.hasMoreTokens()) {
	        String token = tokens.nextToken().trim(); // Extract a token
	        if (token.length() == 0) // Blank space
	          continue; // Back to the while loop to extract the next
	        // token
	        else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
	          // Process all +, -, *, / in the top of the operator stack
	          while (!operatorStack.isEmpty()
	              && (operatorStack.peek().equals('+')
	                  || operatorStack.peek().equals('-')
	                  || operatorStack.peek().equals('*') || operatorStack
	                  .peek().equals('/'))) {
	            processAnOperator(operandStack, operatorStack);
	          }

	          // Push the + or - operator into the operator stack
	          operatorStack.push(new Character(token.charAt(0)));
	        } else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
	          // Process all *, / in the top of the operator stack
	          while (!operatorStack.isEmpty()
	              && (operatorStack.peek().equals('*') || operatorStack
	                  .peek().equals('/'))) {
	            processAnOperator(operandStack, operatorStack);
	          }

	          // Push the * or / operator into the operator stack
	          operatorStack.push(new Character(token.charAt(0)));
	        } else if (token.trim().charAt(0) == '(') {
	          operatorStack.push(new Character('(')); // Push '(' to stack
	        } else if (token.trim().charAt(0) == ')') {
	          // Process all the operators in the stack until seeing '('
	          while (!operatorStack.peek().equals('(')) {
	            processAnOperator(operandStack, operatorStack);
	          }

	          operatorStack.pop(); // Pop the '(' symbol from the stack
	        } else { // An operand scanned
	          // Push an operand to the stack
	          operandStack.push(new Double(token));
	        }
	      }

	      // Phase 2: process all the remaining operators in the stack
	      while (!operatorStack.isEmpty()) {
	        processAnOperator(operandStack, operatorStack);
	      }

	      // Return the result
	      return ((Double) (operandStack.pop())).doubleValue();
	    }

	    /**
	     * Process one opeator: Take an operator from operatorStack and apply it
	     * on the operands in the operandStack
	     */
	    public static void processAnOperator(Stack<Double> operandStack,
	        Stack<Character> operatorStack) {
	      if (operatorStack.peek().equals('+')) {
	        operatorStack.pop();
	        double op1 = ((Double) (operandStack.pop())).doubleValue();
	        double op2 = ((Double) (operandStack.pop())).doubleValue();
	        operandStack.push(new Double(op2 + op1));
	      } else if (operatorStack.peek().equals('-')) {
	        operatorStack.pop();
	        double op1 = ((Double) (operandStack.pop())).doubleValue();
	        double op2 = ((Double) (operandStack.pop())).doubleValue();
	        operandStack.push(new Double(op2 - op1));
	      } else if (operatorStack.peek().equals('*')) {
	        operatorStack.pop();
	        double op1 = ((Double) (operandStack.pop())).doubleValue();
	        double op2 = ((Double) (operandStack.pop())).doubleValue();
	        operandStack.push(new Double(op2 * op1));
	      } else if (operatorStack.peek().equals('/')) {
	        operatorStack.pop();
	        double op1 = ((Double) (operandStack.pop())).doubleValue();
	        double op2 = ((Double) (operandStack.pop())).doubleValue();
	        operandStack.push(new Double(op2 / op1));
	      }
	    }
	  }
	  
}
