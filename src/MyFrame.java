import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MyFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	JPanel mainPanel,ButtonPanel,OpButtonPanel,DeleteAndClearPanel;
	JTextField textField;
	JButton[][] buttons; 
	JButton buttonDel,buttonClear,buttonAdd,buttonSub,
		    buttonMul,buttonDiv,buttonDec,buttonEqual;
	
	// Variable for the ActionEvent
	String buttonText,currentText,deleteOneText,resultDeleteOneDecimal;
	String operator = "";
	double currentNum = 0;
	double num2 = 0;
	double result = 0;
	boolean buttonDecHasBeenClicked = false;
	boolean buttonEqualHasBeenClicked = false;
	
	// this is a var not a JButton so we need to put these value to a 2D Array JButton 		
	public static final String[][] Button_Texts = { 
			{"1","2","3"},
			{"4","5","6"},
			{"7","8","9",},
			{"0"}
//			{"1","2","3","+"},
//			{"4","5","6","-"},
//			{"7","8","9","*"},
//			{".","0","=","/"},
	};
	
	
	MyFrame(){
		
		// Text Field
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(400,60));
		textField.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,24));
		textField.setEnabled(false);

		// Button Panel with Grid Layout
		ButtonPanel = new JPanel(new GridLayout(Button_Texts.length,Button_Texts[0].length));		
		
		// Operator Panel with Grid Layout
		OpButtonPanel = new JPanel(new GridLayout(6,1));
		
		// Clear & Delete Button Panel with Grid Layout
		DeleteAndClearPanel = new JPanel(new GridLayout(1,2,10,10));
		
		// Set the number of array ([][]) of JButton buttons 2D Array from Button_Texts.length
		buttons = new JButton[Button_Texts.length][Button_Texts[0].length];
		
		// create all button except Delete & Clear, using 2d ArrayList & Nested Loop 
		for(int i=0;i<Button_Texts.length;i++) {
			for(int j=0;j<Button_Texts[i].length;j++) {
				buttons[i][j] = new JButton(Button_Texts[i][j]); 
				buttons[i][j].setFont(new Font(Font.SANS_SERIF,Font.BOLD,24));			
				buttons[i][j].addActionListener(this);
				ButtonPanel.add(buttons[i][j]);
				
				// buttons[3][j].setPreferredSize(new Dimension(10,10));
				// if it checks the row 3 (the one with "0" value) then change to column value to "0" or 1 
				//if(Button_Texts[i].length == 3) {
					// ButtonPanel = new JPanel(new GridLayout(Button_Texts.length,0));	// THIS SHIT OVERRIDE THE BUTTONPANEL AAAAAAAARGGHGH
				//}
				
			}
		}
		
		
		
		// create operators Button
		buttonAdd = new JButton("+");
		buttonAdd.setFont(new Font(Font.SANS_SERIF, Font.BOLD,24));
		buttonAdd.addActionListener(this);
		OpButtonPanel.add(buttonAdd);
		
		buttonSub = new JButton("-");
		buttonSub.setFont(new Font(Font.SANS_SERIF, Font.BOLD,24));
		buttonSub.addActionListener(this);
		OpButtonPanel.add(buttonSub);
		
		buttonMul = new JButton("*");
		buttonMul.setFont(new Font(Font.SANS_SERIF, Font.BOLD,24));
		buttonMul.addActionListener(this);
		OpButtonPanel.add(buttonMul);
		
		buttonDiv = new JButton("/");
		buttonDiv.setFont(new Font(Font.SANS_SERIF, Font.BOLD,24));
		buttonDiv.addActionListener(this);
		OpButtonPanel.add(buttonDiv);
		
		buttonEqual = new JButton("=");
		buttonEqual.setFont(new Font(Font.SANS_SERIF, Font.BOLD,24));
		buttonEqual.addActionListener(this);
		OpButtonPanel.add(buttonEqual);
		
		buttonDec = new JButton(".");
		buttonDec.setFont(new Font(Font.SANS_SERIF, Font.BOLD,24));
		buttonDec.addActionListener(this);
		OpButtonPanel.add(buttonDec);
		
		// create Delete & Clear Button
		
		buttonDel = new JButton("Delete");
		buttonDel.setFont(new Font(Font.SANS_SERIF, Font.BOLD,24));
		buttonDel.addActionListener(this);
		DeleteAndClearPanel.add(buttonDel);
		
		buttonClear = new JButton("Clear");
		buttonClear.setFont(new Font(Font.SANS_SERIF, Font.BOLD,24));
		buttonClear.addActionListener(this);
		DeleteAndClearPanel.add(buttonClear);

		
		// Main Panel, add the text fields & buttons here and set BorderLayout
		mainPanel = new JPanel(new BorderLayout(20,20));
		mainPanel.add(textField, BorderLayout.PAGE_START);
		mainPanel.add(ButtonPanel, BorderLayout.CENTER);
		mainPanel.add(OpButtonPanel, BorderLayout.EAST);
		mainPanel.add(DeleteAndClearPanel,BorderLayout.PAGE_END);
		mainPanel.setBorder(new EmptyBorder(10,10,10,10)); // add padding around the panel

		
		// JFrame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(mainPanel);
		this.pack();
		this.setLocationRelativeTo(null);;
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			
		
		// Numbers Button Without Clear & Delete Function
		ButtonFunctionWithoutClearAndDelete(e);
		
		// Delete & Clear Function
		if(e.getSource() == buttonDel) {
	        currentText = textField.getText();
	        deleteOneText = currentText.substring(0, currentText.length() - 1);
	        
	        if (!currentText.isEmpty()) {
	        	try {
	            textField.setText(deleteOneText);
	            currentNum = Double.parseDouble(deleteOneText);
	            System.out.println(currentNum);
	            
	            
	        	} catch(StringIndexOutOfBoundsException e1){
	        		System.out.println(e1);
	        		currentNum = 0;
	        		System.out.println(currentNum);
	        	}
	        	
	        } 
//	        else { // if the string length is -1 then
//	        	try {
//	        		currentNum = 0;
//	        		System.out.println(currentNum);
//	        	} catch (StringIndexOutOfBoundsException e3) {
//	        		System.out.println("The String is below 0");
//	        	}
//
//	        }
	        
		} else if (e.getSource() == buttonClear) {
			textField.setText("");
			currentNum = 0;
			num2 = 0;
			result = 0;
			System.out.println(currentNum);
		}
		
	}
	
	
	// El's Code (WORKING YIPIIEEE)
	// Numbers Button Function
	private void ButtonFunctionWithoutClearAndDelete(ActionEvent e) {
		// Clears the text field if there is already a number there // Ini gk guna malah ngelimit input button numbernya jadi 1 angka doang
//		if (!textField.getText().isEmpty() && !textField.getText().equals("ERROR")) {
//			textField.setText("");
//		}
		
		// Number buttons
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				if (e.getSource() == buttons[i][j]) {
					// the problem is this syntax
					buttonText = buttons[i][j].getText();
					textField.setText(textField.getText() + buttonText);
					if (operator.isEmpty()) {
						currentNum = Double.parseDouble(textField.getText());
						System.out.println(currentNum + " this is currentNum");
					} else {
						num2 = Double.parseDouble(textField.getText());
						System.out.println(num2 + " this is num2");
					}
					// you don't need return; in java idk why LOL
					// return; // Exit the method after processing the button
					// the return is right there because its like once a button input has been processed, 
					// it'll immediately exit -- it prevents looping n shit
					
					// reset the textField,currentNum,num2,result, set buttonEqualHasBeenClicked to false,and immediately set the TextField to buttonText and set the currentNum from that textField
					// after pressing the result button/buttonEqual by clicking the numbers button
					if(buttonEqualHasBeenClicked) {
						buttonEqualHasBeenClicked = false;
						textField.setText("");
						buttonText = buttons[i][j].getText();
						textField.setText(textField.getText() + buttonText);
						currentNum = Double.parseDouble(textField.getText());
						System.out.println(currentNum + " this is currentNum");
						num2 = 0;
						result = 0;
						
					}
					
				}
			}
		}
		
		// Decimal button // Add ONLY one decimal, you can click the button as many times you want but it will not input any more than one decimal

		if(e.getSource() == buttonDec && buttonDecHasBeenClicked == false && operator.isEmpty()) {
			// if button has been clicked
			buttonDecHasBeenClicked = true;
			textField.setText(textField.getText()+ ".");
			currentNum = Double.parseDouble(textField.getText());
			System.out.println(currentNum +" this is currentNum after .0 is added");
		}else if(e.getSource() == buttonDec && buttonDecHasBeenClicked == false && !operator.isEmpty()) {
			buttonDecHasBeenClicked = true;
			textField.setText(textField.getText()+ ".");
			num2 = Double.parseDouble(textField.getText());
			System.out.println(num2 +" this is num2 after .0 is added");
		}else if(e.getSource() == buttonDec && buttonDecHasBeenClicked == true) {
			System.out.println("You cannot put more than one decimal!!");
		}
		// but after the operator button has been clicked reset the buttonDecHasBeenClicked to false


	
		// If an operator button is clicked : get that operator button "text" (+,-,*,/), clear the text field, and reset the buttonDecHasBeenClick to false
		if (e.getSource() == buttonAdd || e.getSource() == buttonSub || e.getSource() == buttonMul || e.getSource() == buttonDiv) {
			operator = ((JButton) e.getSource()).getText();
			textField.setText(""); // Clear the text field for the next number
			buttonDecHasBeenClicked = false;// reset the buttonDecHasBeenClick to false;
		}
	
		// Perform calculation when = button is pressed
		if (e.getSource() == buttonEqual) {
			// Check if both numbers and operator are set
			if (!operator.isEmpty() && num2 != 0) {
				result = calculation(currentNum, num2, operator);
				//resultDeleteOneDecimal = String.format("%.0f", result); // delete the .0 from the result in order for the delete button to function properly
				//System.out.println(resultDeleteOneDecimal + " This is the result without .0");
				//textField.setText(String.valueOf(resultDeleteOneDecimal));
				System.out.println(result + " This is the result");
				textField.setText(String.valueOf(result));
				// Reset variables for next calculation
				currentNum = result;
				num2 = 0;
				operator = "";
				buttonEqualHasBeenClicked = true;
				
			}
		}
	}
	
	// calculation for operators
	private double calculation(double num1, double num2, String operator) {
		double result = 0;
		try {
			
			switch(operator) {
				case "+":
					result = num1+num2;
					break;
				case "-":
					result = num1-num2;
					break;
				case "*":
					result = num1*num2;
					break;
				case "/":
					if (num2 !=0) {
						result = num1/num2;
					} else {
						System.out.println("Error: Division by zero");
					}
					break;
			}
			
		} catch (ArithmeticException e2) {
			// Handle division by zero
			textField.setText("ERROR");
			return 0; // Return a default value or handle as needed
		}
		
		return result;
	}
	
	
}

