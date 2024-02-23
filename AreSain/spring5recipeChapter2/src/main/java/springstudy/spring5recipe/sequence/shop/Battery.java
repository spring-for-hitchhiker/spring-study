package springstudy.spring5recipe.sequence.shop;

import springstudy.spring5recipe.sequence.shop.config.Product;

public class Battery extends Product {

	private boolean rechargeable;

	public Battery() {
		super();
	}

	public Battery(String name, double price) {
		super(name, price);
	}

	public boolean getRechargeable() {
		return rechargeable;
	}

	// Getters and Setters
	public void setRechargeable(boolean rechargeable) {
		this.rechargeable = rechargeable;
	}
}
