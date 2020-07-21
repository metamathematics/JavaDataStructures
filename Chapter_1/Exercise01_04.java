/**	Exercise 1.1.4
		The method verifyPIN from the ATMbankAmerica class.
  */
public class Exercise01_04 {
	/**	Verifies a user's PIN.
			@param pin The user's PIN
			@return Whether or not the user's PIN is verified
	  */
	public boolean verifyPIN(String pin) {
		return (this.pin.compareTo(pin) == 0);
	}
}
