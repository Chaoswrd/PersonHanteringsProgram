package controller;

import java.util.ArrayList;

import model.Account;
import model.Person;
import model.PersonRegister;

public class Controller {
	private PersonRegister pr = new PersonRegister(); //Ett personregister objekt som håller koll på alla person och dess funktioner
	private Person currentPerson = new Person(); //Nuvarande person som displayas i viewPerson panen
	private ArrayList<String[]> accounts = new ArrayList<String[]>(); //Håller koll på alla konton som nuvarande person har för tillfället
	
	
	public void getPersonalData(String pNbr){ //Hämtar personlig data om en person om den existerar.
		currentPerson = new Person(); //Lägger nuvarande person till default
		accounts.clear();
		Person person = pr.findPerson(pNbr);
		if(person != null){ //Om person register hittar en person alltså inte är null
			currentPerson = person; //Sätter nuvarande person till den hittade personen
			for(Account a: person.getOwnAccounts()){ //Sätter nuvarande persons konton till hittad persons konton
				accounts.add(new String[]{a.getNbr(), ""+a.getBalance()}); //Lägger konton som string arrays så de kan lättare läggas i labels i vyn
			}
		}
		//Om personen inte hittas så returnas en default person utan accounts i get funktionerna.
	}
	public String getName(){ //Hämtar nuvarande persons namn
		return currentPerson.getName();
	}
	public String getPNbr(){ //Hämtar nuvarande persons personnummer 
		return currentPerson.getPNbr();
	}
	public ArrayList<String[]> getAccounts(){ //Returnerar alla konton med string arrays som innehåller kontonr och saldo
		return accounts;
	}
	
	public void addPerson(String pNbr, String name){ //Lägger till en ny person i registeret baserat på inputs från addPerson panen
		pr.addPerson(new Person(pNbr, name));
	}
	public void addAccount(String nbr, String balance, String pNbr){ //Lägger till nytt konto baserat på inputs från viewPerson panen
		Person person = pr.findPerson(pNbr); //Letar först upp om någon med detta personnr existerar, om ej så händer inget
		person.addAccount(new Account(nbr, Double.parseDouble(balance), person)); //Lägger till ett nytt konto baserat på inputs och läggs till på hittade personens konton
	}
	public void removeCurrentPerson(){ //Tar bort den nuvarande personen från personregister.
		pr.removePerson(currentPerson.getPNbr());
		currentPerson = new Person(); //Visar en default person i viewPerson
		accounts.clear();
	}
}
