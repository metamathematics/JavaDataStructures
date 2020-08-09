/** Represents an Employee. */
public class Employee extends Person {
  // Data Fields
  private String department;
  private String title;
  private String yearHired;
  private String hourlyOrSalary;
  private float hourlyRate;
  private float hoursWorked;
  private float unionDues;
  private String annualSalary;

  // Constructors
  /** Default Contructor. */
  public Employee() {
    super();
    department = null;
    title = null;
    yearHired = null;
    hourlyRate = 0f;
    hoursWorked = 0f;
    unionDues = 0f;
    annualSalary = "0.0";
  }

  /** Creates a Salary Employee.
      @param name The name
      @param ssn The social
      @param age The age
      @param gender The gender
      @param address The address
      @param phone The phone number
      @param department The department
      @param title The job title
      @param yearHired The year hired
      @param annualSalary The annual salary
    */
  public Employee(String name, String ssn, int age, String gender, String address, 
                  String phone, String department, String title, String yearHired,
                  String annualSalary) {
  
    this(name, ssn, age, gender, address, phone, department, 
          title, yearHired, "S", 0f, 0f, 0f, annualSalary);
  }

  /** Creates an Hourly Employee.
      @param name The name
      @param ssn The social
      @param age The age
      @param gender The gender
      @param address The address
      @param phone The phone number
      @param department The department
      @param title The job title
      @param yearHired The year hired
      @param hourlyRate The pay per hour
      @param hoursWorked The total hours worked
      @param unionDues The total union dues owed
    */  
  public Employee(String name, String ssn, int age, String gender,String address, 
                  String phone, String department, String title, String yearHired,
                  float hourlyRate, float hoursWorked, float unionDues) {
  
    this(name, ssn, age, gender, address, phone, department, 
          title, yearHired, "H", hourlyRate, hoursWorked, unionDues, "0.0");
  }

  /** Creates an Employee with all attributes.
      @param name The name
      @param ssn The social
      @param age The age
      @param gender The gender
      @param address The address
      @param phone The phone number
      @param department The department
      @param title The job title
      @param yearHired The year hired
      @param hourlyOrSalary H if hourly, S if salary?
      @param hourlyRate The pay per hour
      @param hoursWorked The total hours worked
      @param unionDues The total union dues owed
      @param annualSalary The annual salary
    */
  public Employee(String name, String ssn, int age, String gender,String address, 
                  String phone, String department, String title, String yearHired,
                  String hourlyOrSalary, float hourlyRate, float hoursWorked, 
                  float unionDues, String annualSalary) {
  
    super(name, ssn, age, gender, address, phone);
    this.department = department;
    this.title = title;
    this.yearHired = yearHired;
    this.hourlyOrSalary = hourlyOrSalary;
    this.hourlyRate = hourlyRate;
    this.hoursWorked = hoursWorked;
    this.unionDues = unionDues;
    this.annualSalary = annualSalary;
  }


  // Getter methods
  /** Returns the department of this Employee.
      @return The department
    */
  public String getDepartment() {
    return department;
  }


  /** Returns the job title of this Employee.
      @return The job title
    */
  public String getTitle() {
    return title;
  }


  /** Returns the year this Employee was hired.
      @return The year hired
    */
  public String getYearHired() {
    return yearHired;
  }


  /** Returns H if hourly, S if salary.
      @return "H" or "S" if hourly or salary
    */
  public String getHourlyOrSalary() {
    return hourlyOrSalary;
  }


  /** Returns this Employee's hourly pay rate
      @return The hourly pay rate
    */
  public float getHourlyRate() {
    return hourlyRate;
  }


  /** Returns this Employees's total hours worked.
      @return The hours worked
    */
  public float getHoursWorked() {
    return hoursWorked;
  }


  /** Returns this Employee's total union dues.
      @return The union dues
    */
  public float getUnionDues() {
    return unionDues;
  }


  /** Return this Employee's annual salary.
      @return The annual salary
    */
  public String getAnnualSalary() {
    return annualSalary;
  }

  // Setter methods
  /** Sets the department of this Employee.
      @param department The department
    */
  public void setDepartment(String department) {
    this.department = department;
  }


  /** Sets the job title of this Employee.
      @param title The job title
    */
  public void setTitle(String title) {
    this.title = title;
  }


  /** Sets the year this Employee was hired.
      @param yearHired The year hired
    */
  public void setYearHired(String yearHired) {
    this.yearHired = yearHired;
  }


  /** Sets "H" if hourly, "S" if salary.
      @param hourlyOrSalary "H" or "S" if hourly or salary
    */
  public void setSalaried(String hourlyOrSalary) {
    this.hourlyOrSalary = hourlyOrSalary;
  }


  /** Sets this Employee's hourly pay rate
      @param hourlyRate The hourly pay rate
    */
  public void setHourlyRate(float hourlyRate) {
    this.hourlyRate = hourlyRate;
  }


  /** Sets this Employees's total hours worked.
      @param hoursWorked The hours worked
    */
  public void setHoursWorked(float hoursWorked) {
    this.hoursWorked = hoursWorked;
  }


  /** Sets this Employee's total union dues.
      @param unionDues The union dues
    */
  public void setUnionDues(float unionDues) {
    this.unionDues = unionDues;
  }


  /** Sets this Employee's annual salary.
      @param annualSalary The annual salary
    */
  public void setAnnualSalary(String annualSalary) {
    this.annualSalary = annualSalary;
  }


  /** String representation of this Employee. */
  @Override
  public String toString() {
    return String.format("Employee\n_________\n") + 
           super.toString() + 
           String.format("department: %s \ntitle: %s \nhire year: %s\n",
                         department, title, yearHired) +
           String.format("hourly rate: %.2f \nhours worked: %.2f\n",
                         hourlyRate, hoursWorked) + 
           String.format("union dues: %.2f \nannual salary: %s",
                         unionDues, annualSalary);
  }
}
