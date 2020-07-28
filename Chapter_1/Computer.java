/** Exercise 1.4.2 - The abstract class Computer.  */
public abstract class Computer {
  // Data Fields
  private String manufacturer;
  private String processor;
  private double ramSize;
  private int diskSize;
  private double processorSpeed;

  /** Creates a Computer with the given parameters.
      @param manufacturer The manufacturer
      @param processor The type of processor
      @param ramSize The RAM size
      @param diskSize The disk size
      @param processorSpeed The processor speed
    */
  public Computer(String manufacturer, String processor, double ramSize,
                  int diskSize, double processorSpeed) {
    this.manufacturer = manufacturer;
    this.procressor = processor;
    this.ramSize = ramSize;
    this.diskSize = diskSize;
    this.processorSpeed = processorSpeed;
  }
  
  // Abstract method
  /** Computes the cost-benifit of the computer system.
      @param cost The cost of the system
      @return The cost-benifit of this computer system
    */
  public abstract double costBenifit(double cost);

  // Regular methods
  /** Copmputes the computer's power level
      @return The computer's power level (RAM * processor speed)
    */
  public double computerPower() {
    return ramSize * processorSpeed;
  }

  // accessor methods
  public String getManufacturer() {
    return manufacturer;
  }

  public String getProcessor() {
    return processor;
  }

  public double getRamSize() {
    return ramSize;
  }

  public int getDiskSize() {
    return diskSize;
  }

  private double getProcessorSpeed() {
    return processorSpeed;
  }


  // modifier methods
  public String setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String setProcessor(String processor) {
    this.processor = processor;
  }

  public double setRamSize(double ramSize) {
    this.ramSize = ramSize;
  }

  public int setDiskSize(int diskSize) {
    this.diskSize = diskSize;
  }

  public double setProcessorSpeed(double processorSpeed) {
    this.processorSpeed = processorSpeed;
  }

  public String toString() {
    String result = "Manufacturer: " + manufacturer +
                    "\nCPU: " + processor +
                    "\nRAM: " + ramSize + " gigabytes" +
                    "\nDisk: " + diskSize + " gigabytes" + 
                    "\nProcessor speed: " + processorSpeed + " gigahertz";
    return result;
  }
}
