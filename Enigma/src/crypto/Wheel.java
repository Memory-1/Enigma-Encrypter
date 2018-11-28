package crypto;

import java.util.LinkedList;
import java.util.Random;

public class Wheel {

	private int key;
	private String[] wheel;
	private int shift;
	private Random rand;
	private boolean visited = false;
	
	public Wheel(int key, int shift) {
		this.key = key;
		this.shift = shift;
		rand = new Random();
		rand.setSeed(this.key);
		constructArray();	
	
	}
	
	/**
	 * Constructs the wheel. 
	 * This will remove nodes from the Linked list at random and will 
	 * put it into the array. 
	 * 
	 * This results in an array which will be randomized a-z
	 */
	private void constructArray() {
		String[] arr = new String[26];
		LinkedList<String> list = alphabet();
		
		//This will take a random number and mod it by 26
		//and then add it to the array
		for (int i = 26; i > 0; i--) {
			arr[i-1] = list.remove( Math.abs(rand.nextInt())%i  );
		}
		wheel = arr;
	}
	
	/**
	 * Constructs a linked list from a-z
	 * 
	 * This is for the wheel so that we can remove a node
	 * at a time.
	 * @return
	 */
	private LinkedList<String> alphabet(){
		LinkedList<String> list = new LinkedList<String>();
		for (int i = 0; i < 26; i++) {
			list.add(Character.toString((char) ('a' + i)));
		}
		return list;
	}
	
	/**
	 * Prints the wheel to a string
	 * 
	 * @return
	 */
	private String wheelToArray() {
		String str = "[";
			for (int i = 0; i < wheel.length; i++) {
				str += wheel[(i+shift)%26] + ", ";
			}
		str = str.substring(0, str.length()-2);
		str += "]";
		return str;
	}
		
	public boolean setVisited() {
		if (this.visited == false) {
			visited = true;
		}
		else if (this.visited == true) {
			visited = false;
			shift++;
		}
		return visited;
	}
		
	public int getShift() {
		return shift;
	}
	
	public int forward(int index) {
		Character temp = wheel[Math.abs((index+shift)%26)].charAt(0);
		int output = tools.getIndex(temp);
		setVisited();
		return output;
	}
	
	public int backward(int index) {
		Character temp = tools.getAbc()
				[index].charAt(0);
		int output = tools.findCharacter(wheel, temp, shift);
		setVisited();
		return output;
				
		
	}
	public String[] getWheel() {
		return wheel;
	}
	public void increaseShift() {
		shift++;
	}
}
