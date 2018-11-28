package gui;

import java.io.File;
import java.util.Scanner;

import crypto.Wheel;
import crypto.tools;
import crypto.Reflector;

public class constructor {

	private static Wheel wheelOne;
	private static Wheel wheelTwo;
	private static Wheel wheelThree;
	private String input;
	private String output = "";
	
	
	public constructor(String input,
			int key1, int shift1,
			int key2, int shift2, 
			int key3, int shift3) {
		
		this.input = input;
		wheelOne = new Wheel(key1, shift1);
		wheelTwo = new Wheel(key2, shift2);
		wheelThree = new Wheel(key3, shift3);
				
		output = translate(input);		

		
		
	}
	
	public String translate(String input) {
		for (int i = 0; i < input.length(); i++) {
			Character temp = input.charAt(i);
			if (temp.toString().matches("[a-z]")) {
				output += translateDriver(input.charAt(i));
			}
			else {
				output += temp;
				wheelOne.increaseShift();
				wheelTwo.increaseShift();
				wheelThree.increaseShift();
			}
		}
		
	return output;
		
	}
		
	private static Character translateDriver(Character input) {
		
		int index = tools.getIndex(input);
				
		index = wheelOne.forward(index);
		index = wheelTwo.forward(index);
		index = wheelThree.forward(index);
		
		index = crypto.Reflector.reflect(index);
		
		index = wheelThree.backward(index);
		index = wheelTwo.backward(index);
		index = wheelOne.backward(index);
		
		return tools.getAbc()[index].charAt(0);
		
	}
	
	
	public String getOutput() {
		return output;		
	}
		
	public static void main(String[] args) {
	

		constructor test = new constructor("",
				1, 0, 
				2, 0, 
				3, 0);
		System.out.println(test.getOutput());
	}
}
