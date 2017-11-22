package model;

public class Account {
	private String nbr;
	private double balance;
	private Person owner; //Association
	
	public Account(){
		nbr = "";
		balance = 0;
	}
	public Account(String nbr, double balance, Person owner){
		this.nbr = nbr;
		this.balance = balance;
		this.owner = owner;
	}
	
	public String getNbr() {
		return nbr;
	}
	public void setNbr(String nbr) {
		this.nbr = nbr;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	//Association
	public Person getOwner() {
		return owner;
	}
	public void setOwner(Person newOwner) {
		this.owner = newOwner;
	}
	
	public void credit(double amount){
		balance += amount;
	}
	public void withdraw(double amount){
		balance -= amount;
	}
	
}
