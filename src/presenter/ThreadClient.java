package presenter;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import model.Store;
import model.User;
import net.Connection;
import means.Stack;

public class ThreadClient extends Thread implements Observer {

	
	private Connection connection;
	private Store store;
	private User user;
	
	public ThreadClient(Socket socket, Store store) throws UnknownHostException, IOException {
		this.store = store;
		connection = new Connection(socket);

	}

	@Override
	public void run() {
		System.out.println("cliente conectado: "+getName());
		System.out.println("datos recibidos"+getName());
		try {
			menu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n fin de la transmision: "+getName());
		
	}

	@Override
	public void update() {
		try {
			connection.writeInt(99);
			connection.writeUTF(store.getSalesToString());
			connection.writeUTF(store.searchBidInAuctionToString(user.getNickname()));
			connection.writeUTF(user.getName());
			connection.writeUTF(store.getMySales(user.getNickname()));
			connection.writeUTF(store.getMyBuysToString(user.getNickname()));
		} catch (IOException e) {
			System.out.println("Actualización perdida "+getName());
		}
		
	}
	
	@Override
	public void notification() {
		try {
			ArrayList<User> users1 =store.getUsersForNotification();
			
			if(users1.get(0).equals(user)) {
				connection.writeInt(97);
				connection.writeUTF(users1.get(0).getBuys().peek().getTitle());
			}else {
				for(int i =1;i < users1.size(); i++ ) {
					if(user.equals(users1.get(i))) {
						connection.writeInt(98);
						connection.writeUTF(users1.get(0).getNickname());
						connection.writeUTF(users1.get(0).getBuys().peek().getTitle());
					}
				}
			}		
		} catch (IOException e) {
			System.out.println("Actualización perdida "+getName());
		}
		
	}
	
	public void menu() throws IOException {
		
		int option = 6;
		do {
			try {
				option = connection.readInt();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				return;
				//e.printStackTrace();
			}
			
			switch(option) {
			case 1:
				connection.writeInt(1);
				login();
				break;
			case 2:
				register();
				break;
			case 3:
				postAuction();
				store.notifyObservers();
				break;
			case 4:
				bidUp();
				store.notifyObservers();
				break;	
			case 5:
				toSell();
				store.notifyObservers();
				store.notifyObserversSell();
				break;
			case 6:
				toDeteleAuction();
				store.notifyObservers();
				break;	
			default:
				break;
			}
		}while(option != 8);
	}
	
	private void toDeteleAuction() {
		// TODO Auto-generated method stub
		int id =0;
		try {
			id= connection.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		store.deleteAuction(id);
	}

	private void toSell() {
		int id =0;
		try {
			id= connection.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			store.stopAuction(id);
		}catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.print("Error: Memoria en uso "+getName());
		}	
	}

	private void bidUp() {
		// TODO Auto-generated method stub
		int id =0;
		long value=0;
		try {
			id= connection.readInt();
			try {
				value = Long.parseLong(connection.readUTF());
			}catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				value = 0;
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			store.bidUp(user, id, value);
		}catch (NullPointerException e) {
			// TODO Auto-generated catch block
			System.out.print("Error: Memoria en uso "+getName());
		}	
		
	}

	private void postAuction() {
		String tittle ="";
		String description="";
		long minimumBid=0;
		User author=null;
		try {
			tittle = connection.readUTF();
			description=connection.readUTF();
			try {
				minimumBid= Long.parseLong(connection.readUTF());
			}catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				minimumBid = 0;
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		store.postAuction(tittle, description, minimumBid, user);
	}

	private void register() throws IOException {
		String userName = connection.readUTF();
		String nickname = connection.readUTF();
		String password = connection.readUTF();
		try {
			store.addUser(userName, nickname, password);
		}catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			System.out.print("\n Intento de Registro con nickname en uso "+getName());
		}	
	}

	public void login() throws IOException {
		
		String nickname = connection.readUTF();
		String password = connection.readUTF();
		
		if(store.login(nickname, password)) {
			user = store.searchUsers(nickname);
			connection.writeBoolean(true);
			connection.writeUTF(store.getSalesToString());
			connection.writeUTF(store.searchBidInAuctionToString(nickname));
			connection.writeUTF(user.getName());
			connection.writeUTF(store.getMySales(nickname));
			connection.writeUTF(store.getMyBuysToString(nickname));
		}else {
			connection.writeBoolean(false);
		}
		
	}
}
