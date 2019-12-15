/* 
 * Efraín Manuel Villanueva Castilla
 * Matric: 2488514V 
 */

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * FruitMachineEstateLabel class is used to create status JLabels
 * like the balance, welcome legend and combination of cards and points
 * and the label that shows if the player win or lose 
 *
 */
public class FruitMachineEstateLabel extends JLabel {

    /**
     * The constructor set the label to a specified format
     * 
     * @param text
     * @param width
     * @param height
     */
    public FruitMachineEstateLabel(String text, int width, int height) {
    	// set the text passed in the constructor
        this.setText(text);
        // set the width and height passed in the constructor
        this.setPreferredSize(new Dimension(width, height));
        // set the alignment, font size and font family
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setFont(new Font("Arial", Font.PLAIN, 18));
    }

}