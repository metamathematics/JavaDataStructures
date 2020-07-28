/** Exercise 1.1.2
    Write javadoc comments for the given method.
  */
public class Exercise01_02 {
  /** Checks if given Person has the same name is this Person.
      @param per The Person whose name will be checked against this Person
      @return Zero if the names are the same, not zero if the names are not the same
    */
  public int compareTo(Person per) {
    if (familyName.compareTo(per.familyName) == 0)
      return givenName.compareTo(per.givenName);
    else
      return familyName.compareTo(per.familyName);
  }
}
