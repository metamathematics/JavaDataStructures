/** Represents a person. */
public class Person {
  // Data Fields
  private String name;
  private String ssn;
  private int age;
  private String gender;
  private String address;
  private String phone;

  // Constructors
  /** Default Constructor. */
  public Person() {
    this("J. Doe", "000-00-0000", 30, "O", "132 Boogie Ave", "(555) 555-5555");
  }

  /** Creates a Pearson with the given attributes.
      @param name The name
      @param ssn The social security number
      @param age The age
      @param gender The gender
      @param address The address
      @param phone The phone number
    */
  public Person(String name, String ssn, int age, String gender, String address, String phone) {
    this.name = name;
    this.ssn = ssn;
    this.age = age;
    this.gender = gender;
    this.address = address;
    this.phone = phone;
  }


  // Getter methods
  /** Returns the name of this Person.
      @return The name
    */
  public String getName() {
    return name;
  }


  /** Returns the social security number of this Person.
      @return The social
    */
  public String getSocial() {
    return ssn;
  }


  /** Returns the age of this Person.
      @return The age
    */
  public int getAge() {
    return age;
  }


  /** Returns the gender of this Person.
      @param The gender
    */
  public String getGender() {
    return gender;
  }


  /** Returns the address of this Person.
      @param The address
    */
  public String getAddress() {
    return address;
  }


  /** Return the phone number of this Person.
      @param The phone number
    */
  public String getPhone() {
    return phone;
  }

  // Setter methods
  /** Sets the name of this Person.
      @param name The name
    */
  public void setName(String name) {
    this.name = name;
  }


  /** Sets the social security number of this Person.
      @param ssn The social
    */
  public void setSocial(String ssn) {
    this.ssn = ssn;
  }


  /** Sets the age of this Person.
      @param age The age
    */
  public void setAge(int age) {
    this.age = age;
  }


  /** Sets the gender of this Person.
      @param gender The gender
    */
  public void setGender(String gender) {
    this.gender = gender;
  }


  /** Sets the address of this Person.
      @param address The address
    */
  public void setAddress(String address) {
    this.address = address;
  }


  /** Sets the phone number of this Person.
      @param phone The phone number
    */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  
  /** The String representation of this Person. */
  @Override
  public String toString() {
    return String.format("name: %s \nssn: %s \nage: %d \nsex: %s \naddress: %s \nphone: %s\n",
                          name, ssn, age, gender, address, phone);
  }
}
