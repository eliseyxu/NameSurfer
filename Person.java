/*Elise Xu | CS 106a Section: Thurs 5:15pm
 * HW6
 */

import java.util.*;

/*Defines Person object to manage baby name data. Each Person object contains the name, gender,
 * and an array that stores its popularity rank in each year.
 */
public class Person implements NameSurferConstants {
	private int[] rank;
	String name = "";
	String gender = "";
	
	//Constructor to initialize a new Person object
	public Person(String dataLine) {
		Scanner input = new Scanner(dataLine);
		name = input.next();
		gender = input.next();
		rank = new int[134];
		for (int i = 0; i < 134; i++) {
			rank[i] = input.nextInt();
		}
	}
	
	//Returns name of a person
	public String getName() {
		return name;
	}
	
	//Returns gender of a person
	public String getGender() {
		return gender;
	}
	
	//Returns popularity rank of the person's name in a given year
	public int getRank(int year) {
		if (year >= 1880 && year <= 2013) {
		return rank[year - 1880];
		} else {
			return -1;
		}
	}
	
	//Outputs a Person object as a string
	public String toString() {
		String personString = "";
		personString += name + " , ";
		personString += gender + " , ";
		personString += Arrays.toString(rank);
		return personString;
	}
}
