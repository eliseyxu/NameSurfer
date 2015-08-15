/*Elise Xu | CS 106a Section: Thurs 5:15pm
 * HW6
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import acm.graphics.*;
import acm.gui.*;
import acm.program.*;
import stanford.cs106.*;

/*This is the program class for an application that graphs the popularity ranks of various baby
 * names by pulling data from a text file that contains rank data for the years 1880 through 2013.
 * The user inputs the baby name and gender via a GUI interface, and the graph plots the rank data
 * for every name that has been inputted. The user may clear inputted names to start with a new,
 * blank plot. Specifications of the application that can be modified include the starting year,
 * ending year, maximum rank to graph, and maximum number of names to graph.
 */
public class NameSurfer extends Program implements NameSurferConstants {
	
	private NameDatabase database; //creates NameDatabase object
	private NameGraph graph; //creates NameGraph object
	private JTextField nameField; //text field for user to type in name to plot
	private JRadioButton female, male; //radio button for user to input gender of name
	private JStringList namesGraphed; //stores and displays names currently plotted
	
	//Sets up interactors on screen
	public void init() {
		try {
		    UIManager.setLookAndFeel(
		        UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    // empty
		}
		
		//create GUI components
		nameField = new JTextField(16);
		nameField.setEnabled(false);
		nameField.setText("Loading data...");
		female = new JRadioButton("Female");
		female.setSelected(true);
		male = new JRadioButton("Male");
		ButtonGroup genderButtons = new ButtonGroup();
		genderButtons.add(female);
		genderButtons.add(male);
		JButton graphButton = new JButton("Graph");
		graphButton.setIcon(new ImageIcon("res/icons/graph.gif"));
		JButton clearButton = new JButton("Clear");
		clearButton.setIcon(new ImageIcon("res/icons/clear.gif"));
		namesGraphed = new JStringList();
		database = new NameDatabase();
		graph = new NameGraph(database);
		
		//do window layout
		add(new JLabel("Name:"), NORTH);
		add(nameField, NORTH);
		add(female, NORTH);
		add(male, NORTH);
		add(graphButton, NORTH);
		add(clearButton, NORTH);
		add(new JLabel("Names shown:"), WEST);
		add(namesGraphed, WEST);
		add(graph, CENTER);
		
		addActionListeners();
				
	}
	
	/*reads data from text file that contains popularity rank info into database
	 * enables text field for typing in names to be plotted
	 * calls graph object to draw coordinate grid on which names will be plotted
	 */
	public void run() {
		try {
			database.readRankData(new Scanner(new File(RANKS_FILENAME)));
		} catch (IOException ioe) {
			println("File I/O error:\n" + ioe);
			return;
		}
		
		nameField.setText("");
		nameField.setEnabled(true);
		
		graph.drawGrid();
	}
	
	/*After user inputs a name and gender and clicks Graph, calls other methods to plot the 
	 * popularity rank data for that person and display the name in graph "legend" that shows
	 * all names being plotted.
	 * If user clicks "Clear," removes all plots, clears ArrayList of names that have been
	 * selected to be plotted, and clears graph "legend."
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("Graph")) {
			graphPerson();
			updateNamesGraphedList();
		}
		
		if (event.getActionCommand().equals("Clear")) {
			graph.update();
			database.clearSelected();
			updateNamesGraphedList();
		}
	}
	
	//Plots the popularity rank data for a specific name
	private void graphPerson() {
		String rawNameInput = nameField.getText();
		String formattedNameInput = "";
		formattedNameInput += Character.toUpperCase(rawNameInput.charAt(0));
		for (int i = 1; i < rawNameInput.length(); i++) {
			formattedNameInput += Character.toLowerCase(rawNameInput.charAt(i));
		}
		
		String genderInput = "";
		if (female.isSelected()) {
			genderInput = "F";
		} else if (male.isSelected()) {
			genderInput = "M";
		}
		
		Person inputtedPerson = database.getPerson(formattedNameInput, genderInput);
		if (inputtedPerson != null) {
			if (database.isSelected(inputtedPerson) != true && database.getSelectedCount() <= MAX_NAMES_TO_DISPLAY) {
				database.select(inputtedPerson);
			} else if (database.getSelectedCount() > MAX_NAMES_TO_DISPLAY) {
				JOptionPane.showMessageDialog(this, "You have exceeded the maximum number of names that can be graphed.");
			} else if (database.isSelected(inputtedPerson) == true && database.getSelectedCount() <= MAX_NAMES_TO_DISPLAY) {
				JOptionPane.showMessageDialog(this, formattedNameInput + " has already been entered.");
			}
		} else {
			JOptionPane.showMessageDialog(this,formattedNameInput + " was not found.");
		}
		
		graph.plotName(inputtedPerson);
		
	}
	
	//Updates graph "legend" of names currently being plotted
	private void updateNamesGraphedList() {
		namesGraphed.clear();
		for (int k = 0; k < database.getSelectedCount(); k++) {
			Person personGraphed = database.getSelectedPerson(k);
			String JStringListInput = personGraphed.getName();
			JStringListInput += " (" + personGraphed.getGender() + ")";
			namesGraphed.addItem(JStringListInput, NAME_COLORS[k]);
		}
	}

}
