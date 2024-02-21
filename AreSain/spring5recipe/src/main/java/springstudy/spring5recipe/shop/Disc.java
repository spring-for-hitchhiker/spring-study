package springstudy.spring5recipe.shop;

public class Disc extends Product {
	private int capacity;

	public Disc() {
		super();
	}

	public Disc(String name, double price) {
		super(name, price);
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public double getPrice() {
		return super.getPrice();
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public void setPrice(double price) {
		super.setPrice(price);
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
