package entities;

public class ArrayList {
	private char[] array;
	private int count;
	
	public ArrayList(int quantity) {
		this.array = new char[quantity];
	}
	
	public void add(char elem) {
		if (count < array.length) {
			this.array[count] = elem;
			this.count += 1;
		}
	}
	
	public char[] getArray() {
		return array;
	}
}
