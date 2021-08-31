package models;

import java.io.File;
import java.util.ArrayList;

public class Restaurant {

	private File image;
	private String name;
	private ArrayList<Product> products;// K: nameProduct, V: PrizeProduct

	public Restaurant(File image, String name) {
		this.products = new ArrayList<Product>();
		this.image = image;
		
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public File getImage() {
		return image;
	}

	public void addProduct(Product product) {
		this.products.add(product);
	}

	public ArrayList<Product> getProducts() {
		return products;
	}
	
	@Override
	public String toString() {
		String text = "";
		text += name + "\n";
		for (Product product : products) {
			text += product.getName() + ":\t" + product.getPrize() + "\n";
		}
		return text;
	}

}
