package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Desktop;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Gui {

private JTextArea inputPane;
private JTextArea outputPane;
private String input;
private String fileName;
private String output;
private static String[][] wheelConf = { {"0","0"} , {"0","0"} , {"0","0"} };


	public Gui(JFrame frame) {
		GridLayout layout = new GridLayout(1,0);
		frame.setLayout(layout);
		JPanel bottom = new JPanel();
	
		//Constructs the Text Pane
		inputPane = new JTextArea();
		inputPane.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {	}

			@Override
			public void keyReleased(KeyEvent arg0) {
				input = inputPane.getText();
			}

			@Override
			public void keyTyped(KeyEvent arg0) {	}	
		});
		
		//Has the output
		outputPane = new JTextArea();	
		
		//Converts the given input
		JButton displayInput = new JButton("Encrypt");
		displayInput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				String pattern = "[0-9]+";
				if (wheelConf[0][0].matches(pattern) &&
					wheelConf[1][0].matches(pattern) &&
					wheelConf[2][0].matches(pattern) &&
					wheelConf[0][1].matches(pattern) &&
					wheelConf[1][1].matches(pattern) &&
					wheelConf[2][1].matches(pattern)) {
				
				int wheel1 = Integer.parseInt(wheelConf[0][0]);
				int wheel2 = Integer.parseInt(wheelConf[1][0]);
				int wheel3 = Integer.parseInt(wheelConf[2][0]);
				int shift1 = Integer.parseInt(wheelConf[0][1]);
				int shift2 = Integer.parseInt(wheelConf[1][1]);
				int shift3 = Integer.parseInt(wheelConf[2][1]);
				input = input.toLowerCase();
				constructor enigma = new constructor(input, wheel1, shift1, wheel2, shift2, wheel3, shift3);	
				output = enigma.getOutput();
				outputPane.setText(output);
			}
				else {
					outputPane.setText("Please input valid wheel settings \n No characters are allowed, only numbers");
				}
			}
			
			
		});
		
		//Select a text file
		JButton openFile = new JButton("Select a File");
		openFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
			    Desktop desktop = null;
			    
		   // on Windows, retrieve the path of the "Program Files" folder
		      File file = new File(System.getenv("programfiles"));
			  Scanner s;
			  String temp = "";
			    try {
					s = new Scanner(file);
					while (s.hasNext());
					temp += s.next();
					
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
			    
	
// This is on the side burner for now 
//			    try {
//				      if (Desktop.isDesktopSupported()) {
//				         desktop = Desktop.getDesktop();
//				       
//				         
//				      }
//				      else {
//				         System.out.println("desktop is not supported");
//				      }
//				    }
//				    catch (IOException e){  }
			}});	

		//Debugging button
				JButton debug = new JButton("Debug");
				debug.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						
						System.out.println(
								 "Wheel One:" + wheelConf[0][0] + " " + wheelConf[0][1] + "\n" +
								"Wheel Two:" + wheelConf[1][0] + " " + wheelConf[1][1] + "\n" +
								"Wheel Three:" + wheelConf[2][0] + " " + wheelConf[1][1] + "\n");
						
					}});
				
		//Borders and stuff
		TitledBorder input;
		input = BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Input");
		TitledBorder output;
		output = BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Output");
//This is for adding the whole of it together.
		//Adds the inputOutput together
		JPanel inputOutputPanel = new JPanel(new GridLayout(0, 1));
		JPanel inputPanel = new JPanel(new GridLayout(1,0));
		inputPanel.setBorder(input);
		JPanel outputPanel = new JPanel(new GridLayout(1,0));
		outputPanel.setBorder(output);
		inputOutputPanel.add(inputPanel);
		inputOutputPanel.add(outputPanel);
		inputPanel.add(inputPane);
		outputPanel.add(outputPane);		
		//Adds the buttons to the option panels
		JPanel optionsPanel = new JPanel(new GridLayout(4,0));
		optionsPanel.add(openFile);
		optionsPanel.add(displayInput);
		optionsPanel.add(debug);
		JPanel wheelConfig = new JPanel(new GridLayout(3,2));
		JTextArea wheel1 = wheelConfig("Wheel One", 0, 0);
		JTextArea wheel2 = wheelConfig("Wheel Two", 1, 0);
		JTextArea wheel3 = wheelConfig("Wheel Three", 2, 0);
		JTextArea shift1 = wheelConfig("Shift One", 0, 1);
		JTextArea shift2 = wheelConfig("Shift Two", 1, 1);
		JTextArea shift3 = wheelConfig("Shift One", 2, 1);
		wheelConfig.add(wheel1);
		wheelConfig.add(shift1);
		wheelConfig.add(wheel2);
		wheelConfig.add(shift2);
		wheelConfig.add(wheel3);
		wheelConfig.add(shift3);
		
		optionsPanel.add(wheelConfig);
		
		
		
		
		
		
		//Adds final stuff to the frame
		frame.add(inputOutputPanel);
		frame.add(optionsPanel);
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Gui gui = new Gui(frame);
		
		frame.pack();
		frame.setVisible(true);
		
	}
	
	
	private JTextArea wheelConfig(String titleOfText, int row, int column) {
	JTextArea temp = new JTextArea();
	TitledBorder title;
	title = BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), titleOfText);
	temp.setBorder(title);
	
	temp.addKeyListener(new KeyListener() {

		@Override
		public void keyPressed(KeyEvent arg0) {
			
			
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			wheelConf[row][column] = temp.getText();
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
			
		}
});

	return temp;
}
}
	/*
	   public static JComboBox comboBoxConstructor(String titleOfBox) {
		   	ArrayList<Integer> shift = new ArrayList<Integer>();
		   	for(int i =0; i<26; i++)
		   		shift.add(i);
	    	@SuppressWarnings({ "rawtypes", "unchecked" })
			JComboBox box = new JComboBox(shift);
	    	
			TitledBorder title;
			title = BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), titleOfBox);
			box.setBorder(title);
	    	return box;
	   }
	   */
