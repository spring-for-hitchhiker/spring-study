package springstudy.spring5recipe.sequence.shop;

import springstudy.spring5recipe.sequence.shop.config.Product;

public class Disc extends Product {

	private int capacity;

	public Disc() {
		super();
	}

	public Disc(String name, double price) {
		super(name, price);
	}

	public int getCapacity() {
		return capacity;
	}

	// Getters and Setters
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
