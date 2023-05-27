package model;

import java.util.Comparator;

import means.AVLTree;
import means.Stack;

public class User {
	private String name;
	private String nickname;
	private String password;
	private AVLTree<Auction> sales;
	private Stack<Auction> buys;
	
	
	
	public User(String name, String nickname, String password) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.password = password;
		this.sales = new AVLTree<Auction>(new Comparator<Auction>() {
            @Override
            public int compare(Auction t1, Auction	 t2) {
                return t1.getTitle().compareToIgnoreCase(t2.getTitle());
            }
        });
		this.buys = new Stack<Auction>();
	}
	
	
	
	public User(String nickname) {
		super();
		this.nickname = nickname;
	}



	public String getName() {
		return name;
	}
	public String getNickname() {
		return nickname;
	}
	public String getPassword() {
		return password;
	}
	public AVLTree<Auction> getSales() {
		return sales;
	}
	public Stack<Auction> getBuys() {
		return buys;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setSales(AVLTree<Auction> sales) {
		this.sales = sales;
	}
	public void addBuys(Auction buy) {
		this.buys.push(buy);
	}



	@Override
	public String toString() {
		return "User [name=" + name + ", nickname=" + nickname + ", password=" + password + "] \n";
	}


	
	
}
