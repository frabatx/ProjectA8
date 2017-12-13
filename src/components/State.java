package components;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import movements.Action;
import movements.Partition;

/**
 * The State represents the union between Field and Tractor.
 * @author Francesco Battista
 * @author Daniele Acquaviva
 * @author Ezgi Iscioglu 
 */
public class State {

    private int[][] field;
    private int k;
    private int max;
    private int sizeCol;
    private int sizeRow;
    private Tractor tractor;

    // COSTRUCTOR
    /**
     * The costructor of State.
     * 
     * @param field
     * matrix that compose the field
     * @param k
     * the quantity of sand that must be contained in each box
     * @param max
     * max quantity of sand you can find in the field
     * @param sizeCol
     * max number of column in field
     * @param sizeRow
     * max number of row in field
     * @param tractor
     * the object can modify field
     */
    public State(int[][] field, int k, int max, int sizeCol, int sizeRow, Tractor tractor) {
	this.field = field;
	this.k = k;
	this.max = max;
	this.sizeCol = sizeCol;
	this.sizeRow = sizeRow;
	this.tractor = tractor;
    }

    /**
     * Costructor. We can build a State inserting path of file name in parameter
     * 
     * @param path
     * string-name of file that contain the state
     */
    public State(String path) {
	this.tractor = new Tractor();
	try {
	    readField(path);
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	}
    }

    /**
     * Costructor.
     * We can create a copy of another state
     * 
     * @param state
     * the state to be copied
     */
    public State(State state) {
	this.field = state.field;
	this.k = state.k;
	this.max = state.max;
	this.sizeCol = state.sizeCol;
	this.sizeRow = state.sizeRow;
	this.tractor = state.tractor;
    }

    // GETTER
    /**
     * This method use a position to get a value in matrix
     * @param p 
     * position of a box in matrix
     * @return
     * a value of matrix 
     */
    public int getValue(Position p) {
	return field[p.getY()][p.getX()];
    }

    /**
     * This method obtains the value of the sand to be moved by the tractor.
     * It is a different between the value in position of tractor and the value K (desired value in each boxes)
     * @return
     * sand to be moved by tractor
     */
    public int getSandMoving() {
	return getValue(this.tractor.getPosition()) - this.k;
    }
    
    /**
     * This method return matrix of the state
     * @return
     * matrix field 
     */
    public int[][] getMatrix() {
	int[][] matrix = new int[sizeCol][sizeRow];
	for (int i = 0; i < matrix.length; i++) {
	    for (int j = 0; j < matrix.length; j++) {
		matrix[j][i] = this.field[j][i];
	    }
	}
	return matrix;
    }

    /**
     * This method return a clone of tractor of state
     * @return
     * the tractor
     * @throws CloneNotSupportedException
     */
    public Tractor getTractor() throws CloneNotSupportedException {
	return tractor.clone();
    }
    
    /**
     * This method return the size of columns in matrix
     * @return
     * the size of column in matrix
     */
    public int getSizeCol() {
	return sizeCol;
    }

    /**
     * This method return the size of rows in matrix
     * @return
     * the size of rows in matrix
     */
    public int getSizeRow() {
	return sizeRow;
    }
    
    /**
     * This method retur max sand of field
     * @return
     * max sand
     */
    public int getMax() {
	return max;
    }
    
    /**
     * This method return desired sand value in each box of matrix field
     * @return
     * desired sand
     */
    public int getK() {
	return k;
    }

    // SETTER
    /**
     * This method set value in matrix
     * @param p
     * position of matrix
     * @param num
     * value 
     */
    public void setValue(Position p, int num) {
	field[p.getY()][p.getX()] = num;
    }

    /**
     * This method set matrix in State
     * @param matrix
     */
    public void setMatrix(int[][] matrix) {
	this.field = matrix;
    }

    /**
     * This method set tractor in state
     * @param tractor
     */
    public void setTractor(Tractor tractor) {
	this.tractor = tractor;
    }

    /**
     * This method set number of column in matrix
     * @param sizeCol
     */
    public void setSizeCol(int sizeCol) {
	this.sizeCol = sizeCol;
    }

    /**
     * This method set number of rows in matrix
     * @param sizeRow
     */
    public void setSizeRow(int sizeRow) {
	this.sizeRow = sizeRow;
    }

    /**
     * This method set max value of sand in matrix
     * @param max
     */
    public void setMax(int max) {
	this.max = max;
    }

    /**
     * This method set desired value of sand in matrix
     * @param k
     */
    public void setK(int k) {
	this.k = k;
    }

    // OTHER METHOD
    /**
     * Create a new state from previus state and an action
     * 
     * @param previousState
     * rootState or motherState
     * @param action
     * action state modifing
     * @return State a new state modified
     * @throws CloneNotSupportedException
     */
    public State newState(State previousState, Action action) throws CloneNotSupportedException {
	State state = previousState.clone();
	this.upgradeState(state, action);
	return state;
    }

    /**
     * Modifing a state by an action. Is used in newState method
     * 
     * @param state
     * @param action
     * @throws CloneNotSupportedException
     */
    private void upgradeState(State state, Action action) throws CloneNotSupportedException {

	for (Position p : action.getSandMovement().keySet()) {
	    int sum = state.getValue(p) + action.getSandMovement().get(p);
	    state.setValue(p, sum);
	}

	if (state.getValue(state.getTractor().getPosition()) > state.getK()) {
	    state.setValue(state.getTractor().getPosition(), state.getK());
	}

	Tractor tractor = state.getTractor();
	tractor.setPosition(action.getNextPosition());
	state.setTractor(tractor);
    }

    /**
     * Read by file and updates every element of the State is used by costractor
     * 
     * @param path
     * is the name of file
     * @throws IOException
     */
    @SuppressWarnings("resource")
    public void readField(String path) throws IOException {
	FileReader file = new FileReader(path);
	Scanner inputStream = new Scanner(file);

	String items = inputStream.nextLine(); // first line contens x,y,nextsand,maxSand,row,columns
	String[] array = items.split(" ");
	this.tractor.setPosition(new Position(Integer.parseInt(array[0]), Integer.parseInt(array[1])));
	this.k = Integer.parseInt(array[2]);
	max = Integer.parseInt(array[3]);
	this.sizeCol = Integer.parseInt(array[4]);
	this.sizeRow = Integer.parseInt(array[5]);
	this.field = new int[sizeCol][sizeRow];

	// matrix filling
	int row = 0;
	int column = 0;
	int sum = 0;
	while (inputStream.hasNextLine()) {
	    items = inputStream.nextLine();
	    items = items.trim();
	    array = items.split(" ");

	    for (column = 0; column < sizeCol; column++) {
		Position parser = new Position(column, row);
		this.setValue(parser, Integer.parseInt(array[column]));
		sum += this.getValue(parser);
	    }
	    row++;
	}

	if ((sum) != (this.k * Math.pow(row, 2))) {
	    throw new FileWrongExcepion("The file is wrong");
	}

	this.tractor.setLimit(sizeCol);

	inputStream.close();
	file.close();

    }

    /**
     * Print state
     * 
     * @param path
     * is the name of file
     * @throws FileNotFoundException
     */
    public void printFild(String path) throws FileNotFoundException {
	File file = new File(path);
	PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)));

	out.println(tractor.getPosition().getY() + "\b" + tractor.getPosition().getX() + "\b" + this.k + "\b" + max
		+ "\b" + sizeCol + "\b" + sizeRow);

	for (int row = 0; row < sizeRow; row++) {
	    out.print("\b");
	    for (int col = 0; col < sizeCol; col++) {
		Position p = new Position(col, row);
		out.print(this.getValue(p) + "\b");
	    }
	    out.println();
	}
	out.close();
    }

    /**
     * Generate an action by a tractor. Started in a position P tractor can move
     * sand and go in a specific area of field. Method generate all possible action
     * by a tractor in the field.
     * 
     * @return ArrayList of Action that tractor cad do
     * @throws CloneNotSupportedException
     */
    public ArrayList<Action> generateActions() throws CloneNotSupportedException {
	ArrayList<Position> nextPosition;
	ArrayList<Action> actions = new ArrayList<Action>();
	nextPosition = this.getTractor().getMovements();
	int sandMoving = this.getSandMoving();
	if (sandMoving <= 0) {
	    sandMoving = 0;
	}
	ArrayList<String> possibleSand = Partition.generationSand(sandMoving, nextPosition.size());
	int[] sandInPoint = new int[nextPosition.size()];

	for (Position indexP : nextPosition) {// by all possible positions we can have possible action 
	    				      //(sand moving + next position)
	    int count = 0;
	    while (count != possibleSand.size()) {
		for (String sandDistribution : possibleSand) {

		    Action action = new Action(indexP); // create a new action

		    sandInPoint = trasformStringArray(sandDistribution.length(), sandDistribution);

		    boolean isIn = true; // if action pass control can be added to all possible action
		    int i = 0;
		    for (Position indexI : nextPosition) {

			action.addElement(indexI, sandInPoint[i]); // add action to the possible actions

			if (sandInPoint[i] > (this.getMax() - this.getValue(indexI))) {
			    isIn = false;
			}

			i++;
		    }
		    if (isIn == true) { // control
			actions.add(action);
			// System.out.println(action.toString()); // test to can see all action
		    }
		    count++;
		}
	    }
	}
	return actions;
    }

    /**
     * Trasform a string in array. Used in generateAction to trasform a string by
     * partition in array of integer.
     * 
     * @param size
     * array size
     * @param trasformed
     * the string that we have to trasform in array
     * @return array of integer
     */
    private int[] trasformStringArray(int size, String trasformed) {
	int[] array = new int[size];

	String[] stringhe = trasformed.split("");

	for (int i = 0; i < stringhe.length; i++) {
	    array[i] = Integer.parseInt(stringhe[i]);
	}
	return array;
    }
    
    /**
     * This method create a value h in State. this value represents the number of boxes that do not have K as sand value yet.
     * @return
     * a value h
     */
    public int getHeuristic() {
	int h = 0;
	for (int i = 0; i < this.getSizeCol(); i++)
	    for (int j = 0; j < this.getSizeRow(); j++)
		if (field[i][j] != k)
		    h++;
	return h;
    }
    
   @Override
    public State clone() throws CloneNotSupportedException {
	return new State(getMatrix(), k, max, sizeCol, sizeRow, getTractor());
    }

    @Override
    public String toString() {
	return "(" + this.tractor + " " + this.k + " " + this.k + " " + this.sizeCol + " " + this.sizeRow + ")";
    }

    

    
}
