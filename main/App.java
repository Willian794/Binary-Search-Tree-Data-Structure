package main;

import entities.*;
import structures.*;

public class App {
	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();
		tree.add(new Item('s'));
		tree.add(new Item('u'));
		tree.add(new Item('n'));
		tree.add(new Item('d'));
		tree.add(new Item('a'));
		tree.add(new Item('y'));
		System.out.println(tree);
		tree.remove('s');
		System.out.println(tree);
		char[] array;
		array = tree.makePreOrderArray();
		printlnArray(array);
		array= tree.makeInOrderArray();
		printlnArray(array);
		array= tree.makePosOrderArray();
		printlnArray(array);
	}
	
	public static void printlnArray(char[] array) {
		System.out.print("| ");
		for (char c : array) 
			System.out.print(c + " | ");
		System.out.println();
	}
}
