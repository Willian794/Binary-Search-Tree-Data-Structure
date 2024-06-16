package entities;

public class Node {
	private Item item;
	private Node left, right;
	
	public Node(Item item) {
		this.item = item;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node esq) {
		this.left = esq;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node dir) {
		this.right = dir;
	}
}
