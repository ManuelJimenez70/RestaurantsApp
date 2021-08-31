package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Logger;

import models.FileManager;
import models.Product;
import models.Restaurant;

public class Server {

	private ServerSocket serverSocket;
	public static final int PORT = 4000;
	private ArrayList<Socket> connections;
	private FileManager fileManager;

	public Server() throws IOException {
		createFileManager();
		serverSocket = new ServerSocket(PORT);
		connections = new ArrayList<>();
		Logger.getGlobal().info("Se inicia server en el puerto:" + PORT);
		acceptConnections();
		manageConnections();
	}

	private void createFileManager() {
		try {
			fileManager = new FileManager();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void acceptConnections() {
		new Thread(() -> {
			while (!serverSocket.isClosed()) {
				try {
					Socket connection = serverSocket.accept();
					Logger.getGlobal().info("Nueva conexion");
					connections.add(connection);
				} catch (IOException e) {
					Logger.getGlobal().warning(e.getMessage());
				}
			}
		}).start();
	}

	private void manageConnections() {
		new Thread(() -> {
			while (!serverSocket.isClosed()) {
				for (Socket connection : connections) {
					try {
						if (connection.getInputStream().available() > 0) {
							analyzeRequest(connection);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void analyzeRequest(Socket connection) throws IOException {// cambiar tipo de escucha
		DataInputStream inputStream = new DataInputStream(connection.getInputStream());
		DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
		String request = inputStream.readUTF();
		switch (request) {
		case "/new":
			String name = inputStream.readUTF();
			System.out.println(name);
			outputStream.writeUTF(request);
			outputStream.writeInt(200);
			break;
		case "/getRestaurants":
			sendRestaurants(outputStream, request);
			break;
		case "/registerRestaurant":
			registerRestaurant(inputStream, outputStream, request);
			break;
		case "/registerProduct":
			registerProductToRestaurant(inputStream, outputStream, request);
			break;
		case "/sendOrder":
			sendOrder(inputStream, outputStream, request);
			break;
		default:

			break;
		}
	}

	private void sendOrder(DataInputStream inputStream, DataOutputStream outputStream, String request) throws IOException {
		String restaurantName = inputStream.readUTF();
		for (Socket connection : connections) {
			outputStream = new DataOutputStream(connection.getOutputStream());
			outputStream.writeUTF("/sendOrder");
			outputStream.writeUTF(restaurantName);
		}
	}

	private void registerProductToRestaurant(DataInputStream inputStream, DataOutputStream outputStream, String request)
			throws IOException {
		String restaurantName = inputStream.readUTF();
		Product product = new Product(inputStream.readUTF(), inputStream.readUTF());
		fileManager.addProductToRestaurant(restaurantName, product);
		outputStream.writeUTF(request);
		outputStream.writeInt(200);
	}

	private void registerRestaurant(DataInputStream inputStream, DataOutputStream outputStream, String request)
			throws IOException {
		int imageSize = inputStream.readInt();
		byte[] imageData = new byte[imageSize];
		inputStream.read(imageData);
		String nameRestaurant = inputStream.readUTF();
		fileManager.addRestaurant(nameRestaurant, imageData);
		outputStream.writeUTF(request);
		outputStream.writeInt(200);
	}

	private void sendRestaurants(DataOutputStream outputStream, String request) throws IOException {
		ArrayList<Restaurant> restaurants = fileManager.getRestaurants();
		int size = restaurants.size();
		System.out.println(fileManager.getRestaurants().size());
		outputStream.writeInt(size);
		for (int i = 0; i < size; i++) {
			// imagen
			File image = restaurants.get(i).getImage();
			byte[] imageData = Files.readAllBytes(image.toPath());
			int imageSize = imageData.length;
			outputStream.writeInt(imageSize);
			outputStream.write(imageData);

			// info
			outputStream.writeUTF(restaurants.get(i).getName());
		}
		outputStream.writeUTF(request);
		outputStream.writeInt(200);
	}

	public void sendAdvertisement() throws IOException {
		for (Socket connection : connections) {
			DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
			outputStream.writeUTF("/advertisement");
			outputStream.writeUTF("Compra pls :3");
		}
	}

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
