/** Accessor and modifier methods fo class Computer. 
    Exercise 1.2.1
  */
public class Exercise02_01 {

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
}
