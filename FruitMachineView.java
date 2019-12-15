/* 
 * Efraín Manuel Villanueva Castilla
 * Matric: 2488514V 
 */

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * FruitMachineView class creates the JFrame for the programme and set the main layout
 *
 */
public class FruitMachineView extends JFrame {

	// store the object of the model and the controller
    private FruitMachineModel modelObject;
    private FruitMachineController controllerObject;

    // set buttons, JLabels and JPanels the view is going to have
    public JButton spinButton, newGameButton;
    private JLabel balanceLabel, statusLabel, winLoseLabel;
    private SlotPanel[] slotPanel;
    private JLabel[] slotLabel;
    
    /**
     * The constructor set the model and controller objects to the attributes
     * also set the basic design for the JFrame
     * @param model
     * @param controller
     */
    public FruitMachineView(FruitMachineModel model, FruitMachineController controller) {
    	// set model and controller objects
        this.modelObject = model;
        this.controllerObject = controller;

        // set the name, size, location and behaviour of the JFrame
        this.setTitle("Fruit Machine");
        this.setSize(525, 377);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        // Call to the method that will create all the layout
        setMainLayout();
    }

    /**
     * setMainLayout method creates the main layout for te view
     */
    public void setMainLayout() {

    	// create the JPanel that will contain all others
        JPanel masterContainer = new JPanel();
        masterContainer.setLayout(null);
        
        // Create the JPanel that will have the labels
    	JPanel labelsContainer = new JPanel();
        labelsContainer.setSize(525, 125);
        labelsContainer.setLocation(0, 0);
        labelsContainer.setBorder(new EmptyBorder(12,12,12,12));
        labelsContainer.setLayout(new BoxLayout(labelsContainer, BoxLayout.Y_AXIS));
        
        // Create the panel that will contain all slots
        JPanel slotsContainer = new JPanel();
        slotsContainer.setSize(400, 252);
        slotsContainer.setLocation(0, 125);
        slotsContainer.setBorder(new EmptyBorder(12,12,12,12));
        
        // Create the panel that will contain the buttons
        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setLayout(null);
        buttonsContainer.setSize(125, 252);
        buttonsContainer.setLocation(400, 125);
        buttonsContainer.setBorder(new EmptyBorder(12,12,12,12));
        
        // Create JLabels for the balance, status and the win/lose message label
        this.balanceLabel = new FruitMachineEstateLabel("Balance is " + this.modelObject.getBalance(), 120, 35);
        this.statusLabel = new FruitMachineEstateLabel("Welcome!", 320, 35);
        this.winLoseLabel = new FruitMachineEstateLabel("", 120, 35);
        
        // Get from the model the slots to create the panels for them
        SlotFigure[] slots = this.modelObject.getSlots();

        // set the array of JPanel and JLabel with the number of slots
        this.slotPanel = new SlotPanel[slots.length];
        this.slotLabel = new FruitMachineEstateLabel[slots.length];

        // iterate the slots and set the JPanels and JLabels
        for(int i = 0; i < slots.length; i++) {
            // create the JPanel with each slot color
            this.slotPanel[i] = new SlotPanel(this.modelObject.getSlot(i).getColor());
            // create the JLabel with each slot name
            this.slotLabel[i] = new FruitMachineEstateLabel(this.modelObject.getSlot(i).getName(), 110, 190);
            // Add the JLabel to the JPanel and the container
            this.slotPanel[i].add(this.slotLabel[i]);
            slotsContainer.add(slotPanel[i]);
        }
        
        // Create Spin and New game buttons with its actions listeners pointing to the controller
        this.spinButton = new JButton("Spin");
        this.spinButton.setBounds(0, 13, 110, 35);
        this.spinButton.addActionListener(this.controllerObject);
        
        this.newGameButton = new JButton("New Game");
        this.newGameButton.setEnabled(false);
        this.newGameButton.setBounds(0, 48, 110, 35);
        this.newGameButton.addActionListener(this.controllerObject);
        
        // Add buttons, panels and labels tom its containers, and after to the master container
        labelsContainer.add(balanceLabel);
        labelsContainer.add(statusLabel);
        labelsContainer.add(winLoseLabel);
        buttonsContainer.add(spinButton);
        buttonsContainer.add(newGameButton);
        masterContainer.add(labelsContainer);
        masterContainer.add(buttonsContainer);
        masterContainer.add(slotsContainer);
        
        // add everything to tye JFrame
        this.add(masterContainer);

    }

    /** 
     * resetScreen method set the screen to its original values
     */
    public void resetScreen() {
        // set balance to 100, welcom text and erase the win/lose message
        this.balanceLabel.setText("Balance is " + this.modelObject.getBalance());
        this.statusLabel.setText("Welcome!");
        this.winLoseLabel.setText("");

        // set bottoms to its original state
        this.spinButton.setEnabled(true);
        this.newGameButton.setEnabled(false);

        // change view slots to the current slots order
        changeSlots();
    }
    
    /**
     * changeSlots method changes the color and name of view slots
     */
    public void changeSlots() {
        // obtain current slots from the model
        SlotFigure[] slots = this.modelObject.getSlots();
        // set the color and name for each view slot
        for(int i = 0; i < slots.length; i++) {
            this.slotPanel[i].setBackground(this.modelObject.getSlot(i).getColor());
            this.slotLabel[i].setText(this.modelObject.getSlot(i).getName());
        }
    }

    /**
     * updateBalance method update the balance label
     */
    public void updateBalance() {
        // get from the model the current balance and set it to the label
        this.balanceLabel.setText("Balance is " + this.modelObject.getBalance());
    }

    /**
     * updateSlotsResult method updates the "estate" label, the one that shows the result of a spin
     * @param numberOfFigures
     * @param points
     * @param anyJoker
     */
    public void updateSlotsResult(int numberOfFigures, int points, boolean anyJoker) {
        // variable where the message will be stored
        String statusText = "";
        
        // check if there is a joker or not for the type of message and set the message according to what happened
        if(anyJoker == false) {
            statusText = Integer.toString(numberOfFigures + 1) + " of a Kind: you win " + points + " points";
        } else {
            if(numberOfFigures < 2) {
                statusText = Integer.toString(numberOfFigures) + " Joker: you lose " + (points * -1) + " points";
            } else {
                statusText = Integer.toString(numberOfFigures) + " Jokers: you lose " + (points * -1) + " points";
            }
        }

        // If no joker or equal slots then set the message to this
        if(numberOfFigures == 0) {
            statusText = "Balance Unchanged";
        }

        // change the text of the label
        this.statusLabel.setText(statusText);
    }
    
    /**
     * playerLose method will show a message if the player loses
     */
    public void playerLose() {
        // the game stops, so the button spin is disabled and the button new game is enabled
        this.spinButton.setEnabled(false);
        this.newGameButton.setEnabled(true);

        // show lose message
        this.winLoseLabel.setText("You Lose :(");
    }
    
    /**
     * playerWon method will show a message if the player wins
     */
    public void playerWon() {
        // the game stops, so the button spin is disabled and the button new game is enabled
        this.spinButton.setEnabled(false);
        this.newGameButton.setEnabled(true);

        // show win message
        this.winLoseLabel.setText("YOU WON :D");
    }

};