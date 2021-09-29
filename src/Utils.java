/** Class of utility methods.
 *  All methods should be static
 */

import java.util.Scanner;

public class Utils {

    /** Takes yes/no answers */
    public static boolean takeYes(Scanner in) {
        String continueStr = in.nextLine().trim();
        try {
            if (continueStr.equalsIgnoreCase("yes")) {
                return true;
            }
            else if (continueStr.equalsIgnoreCase("no")) {
                return false;
            }
            else {
                throw new RuntimeException();
            }
        }
        catch (RuntimeException e) {
            System.out.print("Invalid input! Try again: ");
            return takeYes(in);
        }
    }

    /** Reads input as array of Strings from command line */
    public static String[] getInputStrParameters(Scanner in) {
        String input = in.nextLine();
        if (input.equalsIgnoreCase("exit")) {
            return new String[0];
        }
        return input.split("( *, *)");
    }

    /** Checks if the input has enough number */
    public static void parameterNumberCheck(int expectedNum, String[] parameters) throws ArrayIndexOutOfBoundsException {
        if (expectedNum != parameters.length) {
            throw new ArrayIndexOutOfBoundsException("Incorrect number of parameters! Expect: " + expectedNum + ", get: " + parameters.length + ", try again: ");
        }
    }
}
