/* 
 * Efraín Manuel Villanueva Castilla
 * Matric: 2488514V 
 */

import java.awt.Color;
import java.util.Random;

/* 
 * FruitMachineModel class if the model of the program
 * It will control of the information of the fruit machine
 * like balance, slot options or current slots shown
 */
public class FruitMachineModel {
    
	// balance of current game
    private int balance;
    // All slot options
    private SlotFigure[] options;
    // Slots displayed in the view
    private SlotFigure[] slots = new SlotFigure[3];
    
    /**
     * The constructor will set all basic info like balance, slots info and initial slots of the game
     */
    public FruitMachineModel() {
    	// Set balance to 100
        this.setDefaultBalance();
        // Read and set all slots options
        this.setDefaultCardOptions();
        // Get initial values of slots according to the number of available slots
        this.getInitialSlots();
    }

    /**
     * Getter of balance
     * @return balance
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * Setter of balance
     * @param balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Getter of slot options
     * @return all slot options
     */
    public SlotFigure[] getOptions() {
        return this.options;
    }

    /**
     * Getter of Slot figure single option according to position
     * @param position
     * @return slot option
     */
    public SlotFigure getOption(int position) {
        return this.options[position];
    }
    
    /**
     * Getter of current displayed slots
     * @return current slots
     */
    public SlotFigure[] getSlots() {
        return this.slots;
    }
    
    /**
     * Getter of current displayed single option according to position
     * @param position
     * @return single current slot
     */
    public SlotFigure getSlot(int position) {
        return this.slots[position];
    }

    /**
     * Set balance to default value (100)
     */
    public void setDefaultBalance() {
        this.balance = 100;
    }

    /**
     * setDefaultCardOptions method set all slot options
     * It set the name, if the slot adds or takes points and the color of the slot
     */
    public void setDefaultCardOptions() {
    	// Initiate the array of the options
        this.options = new SlotFigure[5];
        // Set all 5 options with their name, if they increase or decrease and the color
        this.options[0] = new SlotFigure("King", true, new Color(10, 132, 255));
        this.options[1] = new SlotFigure("Queen", true, new Color(28, 209, 88));
        this.options[2] = new SlotFigure("Jack", true, new Color(255, 159, 10));
        this.options[3] = new SlotFigure("Ace", true, new Color(191, 90, 242));
        this.options[4] = new SlotFigure("Joker", false, new Color(255, 69, 58));
    }

    /**
     * getInitialSlots method will fill all slots
     * the method fills all the number of slots even if they are more than the possible options 
     * @param start
     * @param numberOfSlots
     */
    public void getInitialSlots() {
    	// j variable will control options position
    	int j = 0;
    	// set a for loop to populate all slots
    	for(int i = 0; i < this.slots.length; i++) {
    		// start populating each slot
    		this.slots[i] = this.options[j];
    		// check if the slot number is higher than the options
    		// if so, check when i (variable that controls slot position) gets to the maximum of possible options
    		// when i get to the maximum of possible option set j variable to zero, to start again
    		if(this.slots.length > this.options.length) {
    			if(i == this.options.length - 1) {
    				j = 0;
    			} else {
    				j++;
    			}
    		// if the slot number is fewer than the possible options,
    		// then just add one more to j
    		} else {
    			j++;
    		}
    	}
    	
    }

    /**
     * getRandomSlots method will store three random values to each slot
     */
    public void getRandomSlots() {
    	// set the random object
        Random random = new Random();
        // set the for loop to fill all slots
        for(int i = 0; i < this.slots.length; i++) {
        	// get a slot randomly using Random object
            this.slots[i] = this.getOption(random.nextInt(this.options.length));
        }
    }

    /**
     * numberOfPositive method count the number of equal slots that are not a Joker
     * Or a negative slot (if its anything else but a Joker that is negative)
     * @return number of equal cards
     */
    public int numberOfPositive() {
    	// set the variable to count equal slots
        int numberOfEquals = 0;
        // start iterating all slots
        for (int i = 0; i < this.slots.length; i++) {
        	// check current slot with the others shown
            for (int j = i + 1; j < this.slots.length; j++) {
            	// if both are equals and are not a negative slot (Joker) then add one to the count
                if(this.slots[i].equals(this.slots[j]) && this.slots[i].getType() == true) {
                    numberOfEquals++;
                }
                // If more than two are found (maximum count to add points) then return the number 
                if(numberOfEquals >= 2) {
                    return numberOfEquals;
                }  
            }
        }
        return numberOfEquals;
    }

    /**
     * calculatePositivePoints method calculate positive points
     * @param equalCards
     * @return points
     */
    public int calculatePositivePoints(int equalCards) {
    	// set variable that will get the points
        int points = 0;
        // if the points are equal to one or two set the corresponding points
        if(equalCards == 1) {
            points = 20;
        } else if(equalCards == 2) {
            points = 50;
        }
        return points;
    }

    /**
     * numberOfNegative method count the number of negative (Joker) slots
     * @return conut of jokers
     */
    public int numberOfNegative() {
    	// set the counter to zero
        int numberOfJokers = 0;
        // iterate all slots to check if there is any negative (Joker) slot
        for (int i = 0; i < this.slots.length; i++) {
        	// if a negative (joker) slot is found then add one to the count
            if(this.slots[i].getType() == false) {
                numberOfJokers++;
            }
        }
        return numberOfJokers;
    }

    /**
     * negativePoints method will multiply the number of joker by 25 and convert the number to negative
     * @param points
     * @return negative points
     */
    public int negativePoints(int points) {
        return (points * 25) * -1;
    }

}