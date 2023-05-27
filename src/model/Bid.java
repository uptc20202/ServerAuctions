package model;

public class Bid{
	private Long value;
	private User user;
	
	
	
	public Bid(Long value, User user) {
		super();
		this.value = value;
		this.user = user;
	}
	
	public Long getValue() {
		return value;
	}
	public User getUser() {
		return user;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	

	
	
	
}
