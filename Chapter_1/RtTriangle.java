import java.util.Scanner;

/** Represents a Right Triangle.
    Exercise 1.8.2
  */
public class RtTriangle extends Shape {
  double base;
  double height;
  
  /** Constructs a right triangle with base=1 and height=1. */
  public RtTriangle() {
    this(1, 1);
  }
  
  /** Constructs a right triangle with the given parameters.
      @param base The triangle's base
      @param height The triangle's height
    */
  public RtTriangle(double base, double height) {
    super("Right Triangle");
    this.base = base;
    this.height = height;
  }
  

  /** Returns the base of this right triangle.
      @return The base
    */
  public double getBase() {
    return base;
  }


  /** Returns the height of this right triangle.
      @return The height
    */
  public double getHeight() {
    return height;
  }


  /** Computes the area of this triangle.
      @return The area
    */
  public double computeArea() {
    return 0.5 * (base * height);
  }
  

  /** Computes the perimeter of this triangle.
      @return The perimeter
    */
  public double computePerimeter() {
    double hypotenuse = Math.hypot(base, height);
    return base + height + hypotenuse;
  }


  /** Reads the attributes of this triangle.
    */
  public void readShapeData() {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter the base.");
    base = input.nextDouble();
    System.out.println("Enter the height.");
    height = input.nextDouble();
  }


  /** Creates a string representation of this triangle.
      @return The string representation
    */
  public String toString() {
    return super.toString() + String.format(": base is %f, height is %f", base, height);
  }
}
