package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.IPresenter;
import models.Product;
import models.Restaurant;

public class Client {
	
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	public static final int PORT = 4000;
	private IPresenter iPresenter;

	public Client(IPresenter presenter) throws IOException {
		this.iPresenter = presenter;
		socket = new Socket("127.0.0.1", PORT);
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		readResponse();
		getRestaurants();
	}

	public void sendName() throws IOException {
		String name = JOptionPane.showInputDialog(null, "Envia tu nombre");
		outputStream.writeUTF("/new");
		outputStream.writeUTF(name);
	}

	public void readResponse() {
		new Thread(() -> {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while (!socket.isClosed()) {
				try {
					if (socket.getInputStream().available() > 0) {
						String response = inputStream.readUTF();
						switch (response) {
						case "/new":
							int code = inputStream.readInt();
							System.out.println(code);
							break;
						case "/advertisement":
							String advertisement = inputStream.readUTF();
							iPresenter.showAdvertisement(advertisement);
							break;
						case "/getRestaurants":
							System.out.println(response + ": " + inputStream.readInt());
							break;
						case "/registerRestaurant":
							System.out.println(response + ": " + inputStream.readInt());
							break;
						case "/registerProduct":
							System.out.println(response + ": " + inputStream.readInt());
							break;
						case "/sendOrder":
							String notification = inputStream.readUTF();
							iPresenter.notificatoToRestaurant(notification, notification);
							break;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println(this.socket.getLocalAddress().getHostAddress() + "Conexion Perdida");
		}).start();
	}
	
	public void registerProduct(String restaurantName, Product product) throws IOException {
		outputStream.writeUTF("/registerProduct");
		outputStream.writeUTF(restaurantName);
		outputStream.writeUTF(product.getName());
		outputStream.writeUTF(product.getPrize());
		iPresenter.addProductToRestaurant(restaurantName, product);
	}

	public void registerRestaurant(Restaurant restaurant) throws IOException {
		// imagen
		outputStream.writeUTF("/registerRestaurant");
		File image = restaurant.getImage();
		byte[] imageData = Files.readAllBytes(image.toPath());
		int imageSize = imageData.length;
		outputStream.writeInt(imageSize);
		outputStream.write(imageData);

		// info
		outputStream.writeUTF(restaurant.getName());
		iPresenter.addRestaurantToList(restaurant);
	}

	public void getRestaurants() throws IOException {
		outputStream.writeUTF("/getRestaurants");
		int size = inputStream.readInt();
		ArrayList<Restaurant> restaurants = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			int imageSize = inputStream.readInt();
			byte[] imageData = new byte[imageSize];
			inputStream.read(imageData);
			String name = inputStream.readUTF();
			Restaurant restaurant = iPresenter.createRestaurant(name, imageData);
			restaurants.add(restaurant);
		}
		iPresenter.getRestaurants(restaurants);
	}

	public void sendOrder(String restaurant) throws IOException {
		outputStream.writeUTF("/sendOrder");
		outputStream.writeUTF(restaurant);
	}

}
