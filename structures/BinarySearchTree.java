package structures;

import entities.*;

/*
 * This class is an implementation of the 
 * binary search tree data structure.
 */

public class BinarySearchTree {
	private enum ORDER_ARRAY {PRE_ORDER, IN_ORDER, POS_ORDER};
	
	private Node root;
	private int count;
	
	private Node add(Node root, Item item) {
		if (root == null) {
			this.count += 1;
			root = new Node(item);
		} else if (item.getElem() > root.getItem().getElem())
			root.setRight(add(root.getRight(), item));
		else if (item.getElem() < root.getItem().getElem())
			root.setLeft(add(root.getLeft(), item));
		else
			return null;
		return root;
	}
	
	public void add(Item item) {
		this.root = add(this.root, item);
	}
	
	private Node search(Node root, char elem) {
		if (root == null)
			return null;
		else if (elem == root.getItem().getElem())
			return root;
		else if (elem > root.getItem().getElem()) 
			return search(root.getRight(), elem);
		else
			return search(root.getLeft(), elem);
	}
	
	public Item search(char elem) {
		Node result = search(this.root, elem); 
		return result == null ? null : result.getItem();
	}
	
	private int numberOfNos(char elem) {
		Node no = search(this.root, elem); 
		if (no == null) 
			return -1;
		else if (no.getLeft() != null && no.getRight() != null)
			return 2;
		else if (no.getLeft() != null || no.getRight() != null)
			return 1;
		else
			return 0;
	}
	
	private Node searchFatherNo(Node root, char elem) {
		if (root == null)
			return null;
		else if (elem < root.getItem().getElem() && root.getLeft() != null) { 
			if (root.getLeft().getItem().getElem() == elem) 
				return root;
			return searchFatherNo(root.getLeft(), elem);
		} else if (elem > root.getItem().getElem() && root.getRight() != null) {
			if (root.getRight().getItem().getElem() == elem) 
				return root;
			return searchFatherNo(root.getRight(), elem);
		}
		return null;
	}
	
	private Node moreRight(Node raiz) {
		if (raiz != null && raiz.getRight() != null)
			return moreRight(raiz.getRight());
		return raiz;
	}
	
	public Item remove(char elem) {
		Node father = searchFatherNo(this.root, elem);
		Item itemDeleted = null;
		int numberOfNos = numberOfNos(elem);
		
		switch (numberOfNos) {
			case -1 -> {
				return itemDeleted;
			}
			case 0 -> {   
				if (elem > father.getItem().getElem()) {
					itemDeleted = father.getRight().getItem();
					father.setRight(null);
				} else {
					itemDeleted = father.getLeft().getItem();
					father.setLeft(null);
				}
				return itemDeleted;
			}
			case 1 -> {
				if (elem > father.getItem().getElem()) {
					itemDeleted = father.getRight().getItem();
					if (father.getRight().getRight() != null)
						father.setRight(father.getRight().getRight());
					else
						father.setRight(father.getRight().getLeft());
				} else {
					itemDeleted = father.getLeft().getItem();
					if (father.getLeft().getLeft() != null)
						father.setLeft(father.getLeft().getLeft());
					else
						father.setLeft(father.getLeft().getRight());
				}
			}
			case 2 -> {
				Node moreRight;
				if (elem == this.root.getItem().getElem()) {
					itemDeleted = this.root.getItem();
					moreRight = moreRight(this.root.getLeft());
					remove(moreRight.getItem().getElem());
					moreRight.setLeft(this.root.getLeft());
					moreRight.setRight(this.root.getRight());
					this.root = moreRight;
				} else if (elem > father.getItem().getElem()) {
					itemDeleted = father.getRight().getItem();
					moreRight = moreRight(father.getRight().getLeft());
					remove(moreRight.getItem().getElem());
					moreRight.setLeft(father.getRight().getLeft());
					moreRight.setRight(father.getRight().getRight());
					father.setRight(moreRight);
				} else {
					itemDeleted = father.getLeft().getItem();
					moreRight = moreRight(father.getLeft().getLeft());
					remove(moreRight.getItem().getElem());
					moreRight.setLeft(father.getLeft().getLeft());
					moreRight.setRight(father.getLeft().getRight());
					father.setLeft(moreRight);
				}
			}
		}
		this.count -= 1;
		return itemDeleted;
	}
	
	private String structureToString(Node root) {
		String aux = new String();
		if (root != null) {
			aux += root.getItem().getElem() + "(";
			aux += structureToString(root.getLeft());
			aux += structureToString(root.getRight());
			aux += ")";
		}
		return aux;
	}
	
	private void makeArray(Node root, ORDER_ARRAY order, ArrayList array) {
		if (root != null) {
			switch (order) {
				case PRE_ORDER -> {
					array.add(root.getItem().getElem());
					makeArray(root.getLeft(), ORDER_ARRAY.PRE_ORDER, array);
					makeArray(root.getRight(), ORDER_ARRAY.PRE_ORDER, array);
				}
				case IN_ORDER -> {
					makeArray(root.getLeft(), ORDER_ARRAY.IN_ORDER, array);
					array.add(root.getItem().getElem());
					makeArray(root.getRight(), ORDER_ARRAY.IN_ORDER, array);
				}
				case POS_ORDER -> {
					makeArray(root.getLeft(), ORDER_ARRAY.POS_ORDER, array);
					makeArray(root.getRight(), ORDER_ARRAY.POS_ORDER, array);
					array.add(root.getItem().getElem());
				}
			}
		}
	}
	
	public char[] makePreOrderArray() {
		ArrayList array = new ArrayList(this.count);
		makeArray(this.root, ORDER_ARRAY.PRE_ORDER, array);
		return array.getArray();
	}
	
	public char[] makeInOrderArray() {
		ArrayList array = new ArrayList(this.count);
		makeArray(this.root, ORDER_ARRAY.IN_ORDER, array);
		return array.getArray();
	}
	
	public char[] makePosOrderArray() {
		ArrayList array = new ArrayList(this.count);
		makeArray(this.root, ORDER_ARRAY.POS_ORDER, array);
		return array.getArray();
	}
	
	@Override
	public String toString() {
		String aux = structureToString(this.root);
		return aux;
	}
	
	public int getCount() {
		return count;
	}
}
