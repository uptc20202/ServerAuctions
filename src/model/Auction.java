package model;

import java.util.Comparator;

import means.AVLTree;
import means.Stack;

public class Auction {
	private String title, description, author;
	private Long minimumBid;
	private int id;
	private Stack<Bid> bids;
	
	public Auction(String title, int id, String description, String author, Long minimumBid) {
		super();
		this.title = title;
		this.description = description;
		this.author = author;
		this.minimumBid = minimumBid;
		this.id = id;
		this.bids = new Stack<Bid>(); 
	}



	public Auction(int id) {
		super();
		this.id = id;
	}



	public Auction(String author) {
		super();
		this.author = author;
	}



	public String getTitle() {
		return title;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Long getMinimumBid() {
		return minimumBid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMinimumBid(Long minimumBid) {
		this.minimumBid = minimumBid;
	}

	
	public void addBid(Bid bid) {
		bids.push(bid);
	}



	public String getAuthor() {
		return author;
	}



	public Stack<Bid> getBids() {
		return bids;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	@Override
	public String toString() {
		return "Auction [title=" + title + ", description=" + description + ", author=" + author + ", minimumBid="
				+ minimumBid + ", id=" + id +" ]";
	}
	
	
}
