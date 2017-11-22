package model;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PersonRegister pr = new PersonRegister();
		Person a = new Person("980204-8851", "Mårten Ward");
		Person b = new Person("920412-5561", "Lukas Persson");
		
		Account acc = new Account("1", 3000, a);
		Account acc2 = new Account("2", 4000, a);
		Account acc3 = new Account("3", 1000, b);
		Account acc4 = new Account("4", 100, b);
		
		pr.addPerson(a);
		pr.addPerson(b);
	}

}
