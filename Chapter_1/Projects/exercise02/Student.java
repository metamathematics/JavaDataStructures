/** Represents a Student. */
public class Student extends Person {
  // Data Fields
  private float gpa;
  private String major;
  private String graduationYear;

  // Contructors
  /** Default Constructor. */
  public Student() {
    super();
    gpa = 0f;
    major = null;
    graduationYear = null;
  }

  /** Contructs a Student with the given attributes.
    */

  public Student(String name, String ssn, int age, String gender, String address,
                 String phone, String major) {

    this(name, ssn, age, gender, address, phone, 0f, major, null);
  }

  /** Contructs a Student with the given attributes.
    */
  public Student(String name, String ssn, int age, String gender, String address,
                 String phone, float gpa, String major, String gradulationYear) {

    super(name, ssn, age, gender, address, phone);
    this.gpa = gpa;
    this.major = major;
    this.graduationYear = graduationYear;
  }


  // Getter methods
  /** Returns the gpa for this Student.
      @return The gpa
    */
  public float getGpa() {
    return gpa;
  }


  /** Returns the major for this Student.
      @return The major
    */
  public String getMajor() {
    return major;
  }


  /** Returns the graduation year for this Student.
      @return The graduation year
    */
  public String getGradYear() {
    return graduationYear;
  }


  // Setter methods
  /** Sets the gpa for this Student.
      @param gpa The gpa
    */
  public void setGpa(float gpa) {
    this.gpa = gpa;
  }


  /** Sets the major for this Student.
      @param major The major
    */
  public void setMajor(String major) {
    this.major = major;
  }


  /** Sets the graduation year for this Student.
      @param graduationYear The graduation year
    */
  public void setGradYear(String graduationYear) {
    this.graduationYear = graduationYear;
  }


  /** The string representation of this Student. */
  @Override
  public String toString() {
    return String.format("Student\n_________\n") + 
           super.toString() + 
           String.format("gpa: %.1f \nmajor: %s \nyear of graduation: %s", 
                         gpa, major, graduationYear);
  }
}
