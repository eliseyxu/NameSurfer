/*Elise Xu | CS 106a Section: Thurs 5:15pm
 * HW6
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import acm.graphics.*;

//Creates NameGraph class that is central graphical canvas where line graphs of baby name popularity
//are drawn
public class NameGraph extends GCanvas implements NameSurferConstants {
	
	private NameDatabase nameDB;
	
	//Constructor that initializes state of an empty NameGraph
	public NameGraph(NameDatabase database) {
		nameDB = database;
	}
	
	//Draws coordinate grid of NameGraph
	public void drawGrid() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		setBackground(Color.WHITE);
		add(new GLine(0, 20, getWidth(), 20)); // top edge of central area
		add(new GLine(0, getHeight()-20, getWidth(), getHeight() - 20)); // bottom edge of central area
		
		double width = getWidth();
		double offset = width / YEARS_OF_DATA; // spacing between vertical lines in central area
		
		for (int i = 0; i < YEARS_OF_DATA; i++) {
			GLine line = new GLine(i * offset, 20, i * offset, getHeight() - 20);
			int yearOfLine = i + MIN_YEAR; // the year each vertical line corresponds to
			if (yearOfLine % 10 == 0) {
				line.setColor(Color.BLACK);
				add(new GLabel(Integer.toString(yearOfLine), i * offset + 3, getHeight() - 3));
			} else {
				line.setColor(Color.LIGHT_GRAY);
			}
			add(line);
		}
	}
	
	//Draws plot of popularity rank data for a specific person
	public void plotName(Person inputtedPerson) {
		double gridHeight = getHeight() - 20 - 20; // store height of central area
		double width = getWidth();
		double offset = width / YEARS_OF_DATA; // spacing between vertical lines in central area
		
			Person plotPerson = inputtedPerson;

			for (int k = MIN_YEAR + 1; k <= MAX_YEAR; k++) {				
				double y0 = 0;
				double y1 = 0;
				double multiple0 = (double)(plotPerson.getRank(k-1)) / (MAX_RANK_TO_DISPLAY);
				double multiple1 = (double)(plotPerson.getRank(k)) / (MAX_RANK_TO_DISPLAY);
				
				if ((plotPerson.getRank(k-1) > 0) && (plotPerson.getRank(k-1) < MAX_RANK_TO_DISPLAY)) {
					y0 = 20 + (multiple0 * gridHeight);
				} else {
					y0 = 20 + gridHeight;
				}
				
				if ((plotPerson.getRank(k) > 0) && (plotPerson.getRank(k) < MAX_RANK_TO_DISPLAY)) {
					y1 = 20 + (multiple1 * gridHeight);
				} else {
					y1 = 20 + gridHeight;
				}
				
				double x0 = (k - MIN_YEAR- 1) * offset;
				double x1 = (k - MIN_YEAR) * offset;
				
				GLine plotLine = new GLine(x0, y0, x1, y1);
				plotLine.setLineWidth(2);
				plotLine.setColor(NAME_COLORS[nameDB.getSelectedIndex(plotPerson)]);
				add(plotLine);
				
				if ((k-1) % 10 == 0) {
					GLabel rankLabel = new GLabel(Integer.toString(plotPerson.getRank(k-1)), x0, y0);
					add(rankLabel);
				}
				
				if (k == MAX_YEAR) {
					add(new GLabel(Integer.toString(plotPerson.getRank(k))), x1, y1);
				}
			}
	}
	
	//Clears coordinate grid of plots 
	public void update() {
		double gridHeight = getHeight() - 20 - 20; // store height of central area
		double width = getWidth();
		double offset = width / YEARS_OF_DATA; // spacing between vertical lines in central area
		
		for (int j = 0; j < nameDB.getSelectedCount(); j++) {
			Person plotPerson = nameDB.getSelectedPerson(j);

			for (int k = MIN_YEAR + 1; k <= MAX_YEAR; k++) {				
				double y0 = 0;
				double y1 = 0;
				double multiple0 = (double)(plotPerson.getRank(k-1)) / (MAX_RANK_TO_DISPLAY);
				double multiple1 = (double)(plotPerson.getRank(k)) / (MAX_RANK_TO_DISPLAY);
				
				if ((plotPerson.getRank(k-1) > 0) && (plotPerson.getRank(k-1) < MAX_RANK_TO_DISPLAY)) {
					y0 = 20 + (multiple0 * gridHeight);
				} else {
					y0 = 20 + gridHeight;
				}
				
				if ((plotPerson.getRank(k) > 0) && (plotPerson.getRank(k) < MAX_RANK_TO_DISPLAY)) {
					y1 = 20 + (multiple1 * gridHeight);
				} else {
					y1 = 20 + gridHeight;
				}
				
				double x0 = (k - MIN_YEAR - 1) * offset;
				double x1 = (k - MIN_YEAR) * offset;
				
				GObject plotLine = getElementAt(x0, y0);
				if (plotLine != null) {
					remove(plotLine);
				}
				
				if ((k-1) % 10 == 0) {
					GObject rankLabel = getElementAt(x0, y0);
					remove(rankLabel);
				}
				
				if (k == MAX_YEAR) {
					GObject lastRankLabel = getElementAt(x1, y1);
					remove(lastRankLabel);
				}
				
			}
		}
	}

}
