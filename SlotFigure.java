import java.awt.Color;

/**
 * SlotFigure class creates the possible slots (king, queen, jack, etc)
 */
public class SlotFigure {

	// stores the name of the "figure"
    private String name;
    // stores the type of slot, if it increase or decrease points
    private boolean type;
    // stores the color of the slot
    private Color color;

    /**
     * The constructor will set a name, type and color to the figrue
     * @param name
     * @param type
     * @param color
     */
    public SlotFigure(String name, boolean type, Color color) {
        this.name = name;
        this.type = type;
        this.color = color;
    }

    /**
     * Sets a name to the figure
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the figure
     * @return name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Sets a type to the figure
     * @param type
     */
    public void setType(boolean type) {
        this.type = type;
    }
    
    /**
     * Gets the type of the figure
     * @return type
     */
    public boolean getType() {
        return this.type;
    }

    /**
     * Sets a color to the figure
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the color of the figure
     * @return color
     */
    public Color getColor() {
        return this.color;
    }

}
