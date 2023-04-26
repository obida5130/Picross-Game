package game;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.Color;

/**
 * this class for grid buttom when set it to false or true
 */
public class GridButton extends JButton {
	/**
	 * AbstractButton utilizes Serializable.
	 * This line prevents warnings regarding serialVersionUID
	 */
	private static final long serialVersionUID = 1;
	/**
	 * x represent the RED X and O gray space
	 */
	ImageIcon X, O;
	/**
	 * index
	 */
	int index = 0;
	Color rightSquare, wrongSquare, emptySquare;
	/**
	 * hold value of clicks
	 */
	int value = 0;
	/**
	 * correct value on grid
	 */
	private int correctValue = 0;
	/**
	 * Used to determine if button has been initialized with usable data
	 */
	boolean init = false;
	/**
	 * x-inter
	 */
	int xCoord;
	/**
	 * y-inter
	 */
	int yCoord;
	/**
	 * Constructor of the grid button class
	 */
	GridButton(String imageDir) {
		X = new ImageIcon((imageDir + "x.png"));
		O = new ImageIcon((imageDir + "o.png"));
		// this.addActionListener(new GameController(mainPANEL, this));
	}

	/**
	 * returns the state/value of the button (default/empty/full square)
	 * 
	 * @return current value of the button
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Used to increment the value (state) of the button,
	 * updates the icon of the button to reflect state
	 */
	public void incrementValue() {
		if (value + 1 == 3) {
			value = 0;
			updateIcon();
			return;
		}
		value++;
		updateIcon();
	}

	/**
	 * Sets icon to default (null), full square, or marked
	 */
	public void updateIcon() {
		switch (value) {
			case 0:
				this.setBackground(rightSquare);
				break;
			case 1:
				this.setBackground(wrongSquare);
				break;
			case 2:
				this.setBackground(emptySquare);
				break;
		}
	}
	/**
	 * updateIconColor
	 * 
	 * @param state current state of the color
	 * @param col current set color
	 */
	public void updateIconColor(int state, Color col) {

		setIcon(null);
		switch (state) {
			case 0:
				rightSquare = col;
				break;
			case 1:
				wrongSquare = col;
				break;
			case 2:
				emptySquare = col;
				break;
		}

	}

	/**
	 * Returns x coordinate of the button
	 * 
	 * @return x coordinate of the buton
	 */
	public int getCoordX() {
		return xCoord;
	}

	/**
	 * Returns y coordinate of the button
	 * 
	 * @return y coordinate of the button
	 */
	public int getCoordY() {
		return yCoord;
	}

	/**
	 * Sets the expected value the button should have
	 * 
	 * @param value the value (state) the button is supposed to have in order to
	 *              solve the game
	 */
	public void setCorrectValue(int value) {
		this.correctValue = value;
	}
	/**
	 * deeting index
	 * @param index index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Get the value (state) that the number is supposed to have
	 * 
	 * @return correct value for this button
	 */
	public int getCorrectValue() {
		return correctValue;
	}

	/**
	 * Returns index of the button
	 * 
	 * @return index of the button
	 */
	public int getIndex() {
		return this.index;
	}
}
