package com.github.Scoobydoo181.Mortgage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MortgageInterface
{
	MortgageInterface()
	{
		JFrame frame = new JFrame("Mortgage Amortization Calculator");		//Initial properties
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(765,270);
		frame.setResizable(true);
		
		JPanel leftPanel = new JPanel();		//Organize into left for input
		JPanel rightPanel = new JPanel();		//and right for output
		JButton calculate = new JButton("Calculate");
		
		calculate.setFont(new Font("Arial",Font.PLAIN,16));
		
		leftPanel.setBorder(BorderFactory.createEmptyBorder());		//Add blank space around each main area
		rightPanel.setBorder(BorderFactory.createEmptyBorder());
		
		frame.setLayout(new FlowLayout());
		frame.add(leftPanel);
		frame.add(new JPanel());
		frame.add(calculate);
		frame.add(rightPanel);
		
		leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
		
		JPanel loanAmountPanel = new JPanel();		//within leftPanel, a panel for
		JPanel loanTermPanel = new JPanel();		//each input value
		JPanel APRPanel = new JPanel();			
		JPanel numCompoundsPanel = new JPanel();
		
		//Implement loanAmountPanel
		JLabel loanAmountLabel = new JLabel("Enter a loan amount (in dollars)");
		JTextField loanAmountField = new JTextField();
		
		loanAmountLabel.setFont(new Font("Arial",Font.PLAIN,16));		//Set font
		loanAmountField.setFont(new Font("Arial",Font.PLAIN,16));
		
		loanAmountPanel.setLayout(new BorderLayout());
		loanAmountPanel.add(loanAmountLabel, BorderLayout.NORTH);
		loanAmountPanel.add(loanAmountField, BorderLayout.CENTER);
		
		leftPanel.add(loanAmountPanel);
		
		//Implement loanTermPanel
		JLabel loanTermLabel = new JLabel("Enter a loan term (in years)");
		JTextField loanTermField = new JTextField();
		
		loanTermLabel.setFont(new Font("Arial",Font.PLAIN,16));		//Set font
		loanTermField.setFont(new Font("Arial",Font.PLAIN,16));
		
		loanTermPanel.setLayout(new BorderLayout());
		loanTermPanel.add(loanTermLabel,BorderLayout.NORTH);
		loanTermPanel.add(loanTermField, BorderLayout.CENTER);
		
		leftPanel.add(loanTermPanel);
		
		//Implement APRPanel
		JLabel APRLabel = new JLabel("Enter a loan APR (as a percentage)");
		JTextField APRField = new JTextField();
		
		APRLabel.setFont(new Font("Arial",Font.PLAIN,16));		//Set field
		APRField.setFont(new Font("Arial",Font.PLAIN,16));
		
		APRPanel.setLayout(new BorderLayout());
		APRPanel.add(APRLabel,BorderLayout.NORTH);
		APRPanel.add(APRField, BorderLayout.CENTER);
		
		leftPanel.add(APRPanel);
		
		//Implement numCompoundsPanel
		JLabel numCompoundsLabel = new JLabel("Enter the number of compounds per year (12 is most common)");
		JTextField numCompoundsField = new JTextField();
		
		numCompoundsLabel.setFont(new Font("Arial",Font.PLAIN,16));		//Set font
		numCompoundsField.setFont(new Font("Arial",Font.PLAIN,16));
		
		numCompoundsPanel.setLayout(new BorderLayout());
		numCompoundsPanel.add(numCompoundsLabel,BorderLayout.NORTH);
		numCompoundsPanel.add(numCompoundsField, BorderLayout.CENTER);
		
		leftPanel.add(numCompoundsPanel);
		
		//Output panel
		JLabel finalPaymentLabel = new JLabel("Payment: $00.00");
		JLabel totalInterestLabel = new JLabel("Total amount paid: $00.00");

		finalPaymentLabel.setFont(new Font("Arial",Font.PLAIN,16));
		totalInterestLabel.setFont(new Font("Arial",Font.PLAIN,16));
		
		rightPanel.add(finalPaymentLabel);
		rightPanel.add(new JPanel());
		rightPanel.add(totalInterestLabel);
		
		rightPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		
				
		//Show it
		frame.setVisible(true);
		
		//Check against null for blanks
		boolean loanAmountBlank = loanAmountField.getText() == null ? true : false;		//If it's null, it's blank.
		boolean loanTermBlank = loanTermField.getText() == null ? true : false;
		boolean APRBlank = APRField.getText()== null ? true: false;
		boolean numCompoundsBlank = numCompoundsField.getText() == null ? true : false;
		
		//boolean oneBlank = loanAmountBlank || loanTermBlank || APRBlank || numCompoundsBlank;
		
		
		//Implement the event listener on a new thread for performance
		calculate.addActionListener((ActionEvent e) -> {
			
			
			Thread thread = new Thread(() -> {
				try {
				
				//Get values from the fields
				double numCompounds = Double.parseDouble(numCompoundsField.getText().replaceAll("[^a-zA-Z0-9.]", ""));	//Use regex to find and replace all special characters
				double APR = Double.parseDouble(APRField.getText().replaceAll("[^a-zA-Z0-9.]", ""));
				double loanTerm = Double.parseDouble(loanTermField.getText().replaceAll("[^a-zA-Z0-9.]", ""));
				double loanAmount = Double.parseDouble(loanAmountField.getText().replaceAll("[^a-zA-Z0-9.]", ""));
				
				//Plug in to all the accounting formulas for amortization
				double rate = Math.pow((1 + ((1/numCompounds*APR)/100)),(1/(12* (1/numCompounds)) ));
				
				double numMonths = loanTerm *12;
				
				double amortizedPayment =  ((Math.pow(rate,numMonths)*(rate -1)) / (Math.pow(rate, numMonths) -1))*loanAmount;
				
				double totalCost = (amortizedPayment * 12 * loanTerm);
				
				
				finalPaymentLabel.setText("Your monthly payment is: " + String.format("$%,.2f",amortizedPayment));
				totalInterestLabel.setText("At the end of "+ loanTermField.getText()+" year" +((loanTerm != 1) ?"s, ":", ") + "$" + String.format("%,.2f",totalCost)+ " will have been paid in total");
				
				}
				catch(NumberFormatException ex)
				{
					if(ex.getMessage().contains("empty String"))
					{
						finalPaymentLabel.setText("Input error: all fields must be filled");
						totalInterestLabel.setText("");
					}
					else
					{
						finalPaymentLabel.setText("Input error: please enter only numbers");
						totalInterestLabel.setText("");
					}
				}
			} );
			thread.start();
		} );
		
	}
}
