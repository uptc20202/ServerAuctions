package model;

import java.util.ArrayList;

import presenter.Observer;

public abstract class Subject {

	private ArrayList<Observer> observerList;
	
	public Subject() {
		observerList = new ArrayList<>();
	}
	
	public void attach(Observer observer) {
		observerList.add(observer);
	}
	
	public void detach(Observer observer) {
		observerList.remove(observer);
	}
	
	public void notifyObservers() {
		for (Observer observer : observerList) {
			observer.update();
		}
	}
	
	public void notifyObserversSell() {
		for (Observer observer : observerList) {
			observer.notification();
		}
	}
}
