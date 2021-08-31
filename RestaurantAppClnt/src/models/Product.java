package models;

public class Product {

	private String name;
	private String prize;

	public Product(String name, String prize) {
		super();
		this.name = name;
		this.prize = prize;
	}

	public String getName() {
		return name;
	}

	public String getPrize() {
		return prize;
	}

}
