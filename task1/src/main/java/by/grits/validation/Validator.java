package by.grits.validation;

import java.util.regex.Pattern;

public class Validator {

  public static boolean validateEmailInput(String email) {
    String emailPattern =
        "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    return Pattern.matches(emailPattern, email);
  }

  public static boolean validatePhoneInput(String phoneNumber) {
    return Pattern.matches("^\\d{12}$", phoneNumber);
  }
}
