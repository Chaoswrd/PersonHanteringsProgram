package controller;

import java.util.ArrayList;

import model.Account;
import model.Person;
import model.PersonRegister;

public class Controller {
	private PersonRegister pr = new PersonRegister();
	private Person currentPerson = new Person();
	private ArrayList<String[]> accounts = new ArrayList<String[]>();
	
	
	public void getPersonalData(String pNbr){
		currentPerson = new Person();
		accounts.clear();
		Person person = pr.findPerson(pNbr);
		if(person != null){
			currentPerson = person;
			for(Account a: person.getOwnAccounts()){
				accounts.add(new String[]{a.getNbr(), ""+a.getBalance()});
			}
		}
		else{
			currentPerson = new Person();
			accounts.clear();
		}
	}
	public String getName(){
		return currentPerson.getName();
	}
	public String getPNbr(){
		return currentPerson.getPNbr();
	}
	public ArrayList<String[]> getAccounts(){
		return accounts;
	}
	
	public void addPerson(String pNbr, String name){
		pr.addPerson(new Person(pNbr, name));
	}
	public void addAccount(String nbr, String balance, String pNbr){
		Person person = pr.findPerson(pNbr);
		person.addAccount(new Account(nbr, Double.parseDouble(balance), person));
	}
	public void removeCurrentPerson(){
		pr.removePerson(currentPerson.getPNbr());
		currentPerson = new Person();
		accounts.clear();
	}
	
	private void regexCheck(String answer, String regex){
		
	}
}
