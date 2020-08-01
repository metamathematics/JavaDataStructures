import java.util.Scanner;

/** Represents a Circle.
    Exercise 1.8.1
  */
public class Circle extends Shape {
  double radius;
  
  /** Constructs a circle with radius 1. */
  public Circle() {
    this(1);
  }

  /** Constructs a Circle with the given parameters.
      @param radius The radius
    */
  public Circle(double radius) {
    super("Circle");
    this.radius = radius;
  }

  
  /** Returns the radius of this Circle
      @return The radius of this Circle
    */
  public double getRadius() {
    return radius;
  }


  /** Computes the area of this circle.
      @return The area (pi * r^2)
    */
  @Override
  public double computeArea() {
    return Math.PI * Math.pow(radius, 2);
  }


  /** Computes the perimeter of this circle.
      @return The perimeter (2 * pi * r)
    */
  @Override
  public double computePerimeter() {
    return 2 * (Math.PI * radius);
  }


  /** Reads and updates the radius.
    */
  @Override
  public void readShapeData() {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter the radius.");
    radius = input.nextDouble();
  }

  
  /** Creates a strings representation of this Circle.
      @return The string representation
    */
  @Override
  public String toString() {
    return super.toString() + String.format(": radius is %f", radius);
  }
}
