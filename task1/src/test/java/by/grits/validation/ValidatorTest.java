package by.grits.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidatorTest {

  private Validator validator;

  @BeforeEach
  void setup() {
    validator = new Validator();
  }

  @Test
  void validateEmailInput() {
    boolean result = validator.validateEmailInput("email@gmail.com");
    Assertions.assertTrue(result);
  }

  @Test
  void shouldNotValidateIfWrongEmailInput() {
    boolean result = validator.validateEmailInput("asdsad4548adw");
    Assertions.assertFalse(result);
  }

  @Test
  void shouldNotValidateIfInputIsNull() {
    boolean result = validator.validateEmailInput("");
    Assertions.assertFalse(result);
  }

  @Test
  void validatePhoneInput() {
    boolean result = validator.validatePhoneInput("375000000000");
    Assertions.assertTrue(result);
  }

  @Test
  void shouldNotValidateIfBadPhoneInput() {
    boolean result = validator.validatePhoneInput("258963");
    Assertions.assertFalse(result);
  }

  @Test
  void shouldNotValidateIfWrongPhoneInput() {
    boolean result = validator.validatePhoneInput("help");
    Assertions.assertFalse(result);
  }
}
