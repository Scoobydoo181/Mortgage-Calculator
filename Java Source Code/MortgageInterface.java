package com.github.Scoobydoo181.Mortgage;

import javax.swing.*;
import java.awt.*;

public class MortgageInterface
{
	MortgageInterface()
	{
		JFrame frame = new JFrame("Mortgage Amortization Calculator");		//Initial properties
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,500);
		frame.setResizable(false);
		
		JPanel leftPanel = new JPanel();		//Organize into left for input
		JPanel rightPanel = new JPanel();		//and right for output
		JButton calculate = new JButton("Calculate");
		
		frame.add(leftPanel,BorderLayout.WEST);
		frame.add(calculate);
		frame.add(rightPanel,BorderLayout.EAST);
		
		leftPanel.setLayout(new FlowLayout());
		
		JPanel loanAmountPanel = new JPanel();		//within leftPanel, a panel for
		JPanel loanTermPanel = new JPanel();		//each input value
		JPanel APRPanel = new JPanel();			
		JPanel numCompoundsPanel = new JPanel();
		
		//Implement loanAmount
		JTextField amount = new JTextField("Enter a loan amount");
		
		loanAmountPanel.add(amount);
		leftPanel.add(loanAmountPanel);
		
		frame.setVisible(true);
	}
}
