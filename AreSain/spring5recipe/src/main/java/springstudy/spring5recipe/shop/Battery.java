package springstudy.spring5recipe.shop;

public class Battery extends Product {
	private boolean rechargeable;

	public Battery() {
		super();
	}

	public Battery(String name, double price) {
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

	public boolean isRechargeable() {
		return rechargeable;
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public void setPrice(double price) {
		super.setPrice(price);
	}

	public void setRechargeable(boolean rechargeable) {
		this.rechargeable = rechargeable;
	}
}
