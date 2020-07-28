/** Construcors for the Computer and Notebook classes with parameters 
		processor, RAM size, and disk size.
	*/
public class Exercise03_01 {
	
	// Computer constructor
	public Computer(String processor, double ram, int disk) {
		this(null, processor, ram, disk, 0.0);
	}

	// Notebook constructor
	public Notebook(String processor, double ram, int disk) {
		this(null, processor, ram, disk, 0.0, 0.0);
	}
}
