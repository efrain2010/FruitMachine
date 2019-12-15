/* 
 * Efraín Manuel Villanueva Castilla
 * Matric: 2488514V 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* 
 * FruitMachineController class will control the actions performed in the view
 * Basic information is passed to the constructor from an instansiation of the model
 * 
 */
public class FruitMachineController implements ActionListener {

    // model attribute to use the information across the controller
    private FruitMachineModel modelObject;
    // view attribute to control and send information to the view
    private FruitMachineView viewObject;

    /**
     * FruitMachineController is the constructor of the class
     * Here we will set the model attribute, instantiate the view and set it to the view attribute
     * And enable the view
     * 
     * @param model
     */
    public FruitMachineController(FruitMachineModel model) {
        // Store the model object and pass it to the view, along with the controller object
        this.modelObject = model;
        this.viewObject = new FruitMachineView(model, this);
        // Enable the view to visible after everything is set
        this.viewObject.setVisible(true);
    }

    /**
     * actionPerformed method will control the actions of the components of the view
     * It will control the actions of Spin Button and Reset Game button 
     * 
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewObject.spinButton) {
            spinSlots();
        } else if (e.getSource() == this.viewObject.newGameButton) {
            resetGame();
        }
    }

    /**
     * resetGame method will restart the game with its default values
     */
    public void resetGame() {
    	// set the balance to 100    	
        this.modelObject.setDefaultBalance();
        // Fill the slots with initial values (King, Queen, Ace)        
        this.modelObject.getInitialSlots();
        // Once the information is reset this method is called to reflect it to the view        
        this.viewObject.resetScreen();
    }

    /**
     * spinSlots method will "spin" the slots. It calls three methods to
     * Get new random cards, check the combination of those new cards
     * and reflect the changes of cards and balance to the view
     */
    public void spinSlots() {
    	// Generate new random slots	
        this.modelObject.getRandomSlots();
        // Check the new combination of slots       
        this.checkCombination();
        // Reflects the changes of the info to the view      
        this.viewObject.changeSlots();
    }

    /**
     * checkCombination method checks the combination of slots
     * get the points according to the combination,
     * after checking send the combination and the points to the view so it can be printed,
     * update the balance in the model and in the view,
     * and at last calls a method to check if the player won or lose.
     */
    public void checkCombination() {
    	// Get the number of negative slots (jokers)  	
        // get the points the balance will decrease for each joker
        // Start a boolean that tell if there has any joker in the combination of slots
        int numberOfFigures = this.modelObject.numberOfNegative();
        int points = this.modelObject.negativePoints(numberOfFigures);
        boolean anyJoker = true;

        // if the current number of figures (jokers) is zero or less
        // then no joker is in the combination, so we check for positive values
        if (numberOfFigures <= 0) {
        	// Change the boolean to say there are no jokers in the combination
            // overwrite object with the the number of equal figures
            // overwrite the number of points with the points of the equal figures
            anyJoker = false;
            numberOfFigures = this.modelObject.numberOfPositive();
            points = this.modelObject.calculatePositivePoints(numberOfFigures);
        }
        
        // Set the new balance in the model
        // update the new balance in the view
        // Update the state label to print how many equal or joker slots appeared and the number of point increased or decreased
        this.modelObject.setBalance(this.modelObject.getBalance() + points);
        this.viewObject.updateBalance();
        this.viewObject.updateSlotsResult(numberOfFigures, points, anyJoker);

        // After updating the balance check if the player won or lose
        this.checkWinOrLose();
    }

    /**
     * checkWinOrLose method checks if the balance is zero or equals or over to 150
     * then calls a method in the view to show if the player won or lose 
     */
    public void checkWinOrLose() {
    	// Check if balance is zero or below
        if (this.modelObject.getBalance() <= 0) {
        	// Show in the view that player lost
            this.viewObject.playerLose();
        // Check if balance is 150 or more
        } else if (this.modelObject.getBalance() >= 150) {
        	// Show in the view that player won
            this.viewObject.playerWon();
        }
    }

}