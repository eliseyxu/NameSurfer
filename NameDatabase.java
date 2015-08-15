/*Elise Xu | CS 106a Section: Thurs 5:15pm
 * HW6
 */

import java.awt.*;
import java.io.*;
import java.util.*;

/*Creates NameDatabase class to store baby name popularity rank data in a HashMap and names that have
 * been inputted by the user to be graphed in an ArrayList. 
 */
public class NameDatabase implements NameSurferConstants {
	private HashMap<String, Person> persons;
	private ArrayList<Person> selected;
	
	//Constructor to initialize state of an empty database
	public NameDatabase() {
		persons = new HashMap<String, Person>();
		selected = new ArrayList<Person>();
	}
	
	//Reads name popularity rank data from text file into HashMap
	public void readRankData(Scanner input) {
		while (input.hasNextLine()) {
			String dataLine = input.nextLine();
			Person entry = new Person(dataLine);
			Scanner tokens = new Scanner(dataLine);
			String nameGender = tokens.next();
			nameGender += tokens.next();
			persons.put(nameGender, entry);
		}
	}
	
	//Returns the correct person for a given name and gender from the HashMap storing all popularity
	//rank data 
	public Person getPerson(String name, String gender) {
		String nameGender = "";
		nameGender += name;
		nameGender += gender;
		if (persons.containsKey(nameGender)) {
			return persons.get(nameGender);
		} else {
			return null;
		}
	}
	
	//Adds a person to the ArrayList that tracks all persons to be plotted
	public void select(Person person) {
		selected.add(person);
	}
	
	//Checks whether a person is in the ArrayList that tracks all persons to be plotted
	public boolean isSelected(Person person) {
		if (selected.indexOf(person) != -1) {
			return true;
		} else {
			return false;
		}
	}
	
	//Returns the index of a person in the Arraylist that tracks all persons to be plotted
	public int getSelectedIndex(Person person) {
		return selected.indexOf(person);
	}
	
	//Clears the ArrayList that tracks all persons to be plotted
	public void clearSelected() {
		selected.clear();
	}

	//Returns number of persons in the ArrayList that tracks all persons to be plotted
	public int getSelectedCount() {
		return selected.size();
	}
	
	//Returns the person at index i in the ArrayList that tracks all persons to be plotted
	public Person getSelectedPerson(int i) {
		return selected.get(i);
	}
}
