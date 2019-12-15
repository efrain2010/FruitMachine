/* 
 * Efraín Manuel Villanueva Castilla
 * Matric: 2488514V 
 */

/* 
 * Main Class that initiates the programme. 
 * Calls the model to get the basic info and then sends it to the controller
 */
public class FruitMachine {
	
	public static void main(String[] args) {
		// Model instatiate to get basic info
		FruitMachineModel model = new FruitMachineModel();
		// Controller instatiation
		FruitMachineController controller = new FruitMachineController(model);
	}

}
