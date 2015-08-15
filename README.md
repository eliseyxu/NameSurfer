# NameSurfer
This repository contains my code for a Stanford CS 106A class assignment to program an application with a GUI that graphs popularity trends of the 1000 most popular boy and girl names in the US.

DESCRIPTION OF THE PROGRAM
When the user enters a name, the program creates a plot line charting the popularity rank of that name for each year from 1880 to 2013. Clicking "Clear" in the GUI removes all plot lines from the graph so that the user can enter more names. A color-coded list of all names currently plotted in the graph is shown within a legend on the left side of the GUI.

Popularity rankings are displayed on a scale of 1 to 2000 (1 = most popular, 2000 = least popular). Any ranking >2000 appears at the bottom of the graph. If a name did not have a ranking in a given year, it is given a rank value of 0, which is treated the same as a rank > 2000 and shown at the bottom of the graph.

LIST OF CODE FILES
NameSurfer: Main program class that ties together the application. It creates the other objects and responds to the buttons at the bottom of the GUI window, but only to the point of redirecting those events to the objects represented by the other classes.

Person: Each Person object contains the information for a given name (name, gender, popularity rank).

NameDatabase: This class tracks all the information (e.g., popularity rank data) stored in the data files but is separate from the user interface. It is responsible for reading in the data and locating the data associated with a particular name and gender.

NameGraph: A graphical canvas (subclass of GCanvas) that displays the graph of the various names.

NOTE: I did not include two code files that were provided by the class instructor for the assignment:
1) A ConsoleTester.java file that implements a console-based testing program allowing you to test behavior of the Person and NameDatabase classes.
2) A NameSurferConstants.java file that simply declares several constants that students were supposed to use in their code files.
