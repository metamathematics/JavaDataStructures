/** Compares two functions. */
public class Exercise01_01 {
  /** The main method. */
  public static void main(String[] args) {
   
    int y1 = 0;
    int y2 = 0;

    System.out.println();
    System.out.println(" n  |   y1      y2");
    System.out.println("-------------------");
    for (int n = 0; n <= 100; n += 10) {
      y1 = (100 * n) + 10;
      y2 = (5 * (n * n)) + 2;
      System.out.printf("%3d |%6d %6d\n",n, y1, y2);
      System.out.println("-------------------");
    }
    System.out.println();
  }
}
