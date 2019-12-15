import java.awt.FlowLayout;
import java.awt.Color;

import javax.swing.JPanel;

/**
 * SlotPanel class creates JPanels for the slots
 */
public class SlotPanel extends JPanel {

    /**
     * The constructor gets a color sets it to the JPanel
     * And a Flow Layout afterwards
     * @param color
     */
    public SlotPanel(Color color) {
    	this.setBackground(color);
        this.setLayout(new FlowLayout());
    }

}
