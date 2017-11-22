package model;

import java.util.LinkedList;

public class PersonRegister {
	private LinkedList<Person> people = new LinkedList<Person>();
	
	public void addPerson(Person p){
		this.people.add(p);
	}
	public Person findPerson(String pNbr){
		for(Person person: people)
			if(person.getPNbr().equals(pNbr))
				return person;
			
		return null;
	}
	public Person removePerson(String pNbr){
		for(Person person: people){
			if(person.getPNbr().equals(pNbr)){
				people.remove(person);
				return person;
			}
		}
		return null;
	}
	public LinkedList<Person> getPeople() {
		return people;
	}
	public void setPeople(LinkedList<Person> people) {
		this.people = people;
	}
}
