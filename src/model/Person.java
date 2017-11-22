package model;

import java.util.LinkedList;

public class Person {
	private String pNbr;
	private String name;
	private LinkedList<Account> ownAccounts = new LinkedList<Account>();
	
	//Generic Person
	public Person(){
		pNbr="xxxxxx-xxxx";
		name="John Doe";
	}
	//Specific Person
	public Person(String pNbr, String name){
		this.pNbr = pNbr;
		this.name = name;
	}
	
	public String getPNbr() {
		return pNbr;
	}
	public void setPNbr(String pNbr) {
		this.pNbr = pNbr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public void addAccount(Account a){
		this.ownAccounts.add(a);
	}
	
	//Association med Account
	public LinkedList<Account> getOwnAccounts(){
		return ownAccounts;
	}
	public void setOwnAccounts(LinkedList<Account> newOwnAccounts) {
		ownAccounts = newOwnAccounts;
	}
}
