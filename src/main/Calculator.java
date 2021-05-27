/*
 * 
 * Calculator
 * Parser XMLa
 * Version 1.0
 * Date: 25-05-2021
 * Autor: Jacek Raczyñski
 * Copyright CC0
 * 
 * Pozwala wyœwietliæ okno kalkulatora i wykonuje obliczenia przeliczania walut.
 */
package main;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Calculator extends JFrame {
	/* Object the class which parse currency data from xml file.*/
	private static Currency currency;
	private JPanel contentPane;
	private JTextField textField;				//display
	private JTextField textField_1; 			//insert amount in euros


	public Calculator() {
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			
			JComboBox<String> comboBox = new JComboBox<String>
								(new Vector<String>(Currency.currencyList));
		
		    textField  = new JTextField();
		    textField.setColumns (10);
		    textField_1  = new JTextField();
		    textField_1.setColumns (10); 			
		 
		    JLabel lblNewLabel = new JLabel("Select currency:");
		    JLabel lblNewLabel_1 = new JLabel("Currency | Have");
		    JLabel lblNewLabel_2 = new JLabel("Currency | Want");
			JLabel lblNewLabel_3 = new JLabel("EUR amount:");
			JLabel lblNewLabel_5 = new JLabel(" amount:");
			JLabel lblNewLabel_6 = new JLabel("Date of data:");
			JButton btnNewButton = new JButton("Convert");
			//Error label
		    JLabel lblNewLabel_4 = new JLabel("");
		    lblNewLabel_4.setVerticalAlignment (SwingConstants.BOTTOM);
		    lblNewLabel_4.setForeground(Color.RED);
		    lblNewLabel_4.setFont (new Font("Tahoma", Font.PLAIN, 11));
		
			lblNewLabel_6.setText("Date of data: " + currency.getDate());
			
			
			//TextField_1 has change
			textField_1.addActionListener(new java.awt.event.ActionListener() {
			    public void actionPerformed(java.awt.event.ActionEvent e) {
			    double amount;
				if(lblNewLabel_4.getText() != null)
	    			lblNewLabel_4.setText(null);
					try {
						if(	Double.parseDouble(textField_1.getText()) < 0){
				    		textField_1.setText("0.0");
				    		lblNewLabel_4.setText(
				    		"Amount of money can't be negative!" );
							throw new IllegalAccessError("Amount of money can't be negative!");
						}
						amount = (Double.parseDouble(textField_1.getText())
									*Double.parseDouble(
									 currency.getCurrency().get(
									 comboBox.getSelectedItem()).toString()));
	
						amount *= 10000;
						amount = Math.round(amount);
						amount /= 10000;
						
						lblNewLabel_5.setText(comboBox.getSelectedItem().toString() + " amount: ");
						textField.setText(Double.toString(amount));
					} catch (NumberFormatException ex) {
						textField_1.setText("0.0");
						lblNewLabel_4.setText(
						"To calculate value of the selected currency you have to specify quantity" );
						ex.printStackTrace();
					}
			           /* JOptionPane.showMessageDialog(null,
			                    "Error: Please enter number bigger than 0", "Error Message",
			                    JOptionPane.ERROR_MESSAGE); */
			            
			    }
			});
			

			//Listener button calculation currency
		    btnNewButton.addActionListener ( new ActionListener() {
		    	public void actionPerformed(ActionEvent e) { 				
		    		double amount;
		    		if(lblNewLabel_4.getText() != null)
		    			lblNewLabel_4.setText(null);
					try {
							if(	Double.parseDouble(textField_1.getText()) < 0){
								textField_1.setText("0.0");
				    		    lblNewLabel_4.setText(
				    				"Amount of money can't be negative!" );
								throw new IllegalAccessError(
									"Amount of money can't be negative!");
							}
							amount = (Double.parseDouble(textField_1.getText())
								* Double.parseDouble(
								  currency.getCurrency().get(
										  comboBox.getSelectedItem()).toString()));
					
							amount *= 10000;
							amount = Math.round(amount);
							amount /= 10000;
							
							lblNewLabel_5.setText(
								comboBox.getSelectedItem().toString() + " amount: ");
							textField.setText(Double.toString(amount));
					} catch (NumberFormatException ex) {
							textField_1.setText("0.0");
							lblNewLabel_4.setText(
								"To calculate value of the selected currency you have to specify quantity" );
							ex.printStackTrace();
					}
			}});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8, 8, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_4)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 
									99, GroupLayout.PREFERRED_SIZE)
							.addGap(210)
							.addComponent(lblNewLabel_6))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(
									Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 
											115, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblNewLabel_2))
								.addComponent(lblNewLabel_1))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(
									Alignment.TRAILING)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_5))
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(
									Alignment.TRAILING)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 
										GroupLayout.DEFAULT_SIZE, 
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))))
					.addGap(196))
				.addGroup(gl_contentPane.createParallelGroup()
					.addGap(309)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 112, 
							GroupLayout.PREFERRED_SIZE)
					)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.BASELINE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 
									27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(
									Alignment.BASELINE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, 
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3)))
						.addComponent(lblNewLabel_6))
					.addGap(69)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 
							GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addComponent(lblNewLabel_4)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	public Currency getCurrency() {
	    	return currency;
	}
	public void setCurrency(Currency currency) {
	    	this.currency = currency;
	}

	/**
	 * Create the frame.
	 */
	public static void main(String[] args) throws VerifyError, Exception {
			currency = new Currency();
			currency.ReadRecordsFromFile();
			EventQueue.invokeLater(new Runnable() {
			public void run() {				// Method try to make a window.+
				try {
						Calculator frame = new Calculator();
						frame.setTitle(ReadPropertyFile.getProperty("name"));
						int resolutionWidth =
								Toolkit.getDefaultToolkit().getScreenSize().width;
						int resolutionHeight = 
								Toolkit.getDefaultToolkit().getScreenSize().height;
						frame.setSize(resolutionWidth/4, resolutionHeight/4);
						frame.setLocation((resolutionWidth- frame.getSize().width)/2, 
								(resolutionHeight - frame.getSize().height)/2);
						frame.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame.setVisible(true);
				} catch (Exception e) {
						e.printStackTrace();
				}
			}
		});
	
		}

	
}
