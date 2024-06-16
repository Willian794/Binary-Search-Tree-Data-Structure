package entities;

public class Item {
	private char elem;

	public Item(char elem) {
		this.elem = elem;
	}

	public char getElem() {
		return elem;
	}

	public void setElem(char elem) {
		this.elem = elem;
	}

	@Override
	public String toString() {
		return ""+elem;
	}
}
