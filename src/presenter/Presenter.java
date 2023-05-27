package presenter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import model.Store;

public class Presenter {
	
	private final int PORT = 1234;
	private ServerSocket serverSocket;
	private Store store;


	public Presenter() throws IOException{
		serverSocket = new ServerSocket(PORT);
		store = new Store();
	}

	public void start() throws IOException {
		while(true) {
			Socket socket = serverSocket.accept();
			ThreadClient thread = new ThreadClient(socket, store);
			store.attach(thread);
			thread.start();
		}
	}

	public static void main(String[] args) throws IOException {
		new Presenter().start();
	}

}
