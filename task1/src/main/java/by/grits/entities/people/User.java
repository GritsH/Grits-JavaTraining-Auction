package by.grits.entities.people;

import by.grits.entities.enums.RoleType;

import java.util.Objects;

public class User {
  private int id;
  private String name;
  private String phoneNumber;
  private String emailAddress;
  private String login;
  private String password;
  private RoleType role;

  public User(
      String name,
      String phoneNumber,
      String emailAddress,
      String login,
      String password,
      RoleType role) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
    this.login = login;
    this.password = password;
    this.role = role;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public RoleType getRole() {
    return role;
  }

  public void setRole(RoleType role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(name, user.name)
        && Objects.equals(phoneNumber, user.phoneNumber)
        && Objects.equals(emailAddress, user.emailAddress)
        && Objects.equals(login, user.login)
        && Objects.equals(password, user.password)
        && role == user.role;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, phoneNumber, emailAddress, login, password, role);
  }

  @Override
  public String toString() {
    return "User{"
        + "name='"
        + name
        + '\''
        + ", phoneNumber='"
        + phoneNumber
        + '\''
        + ", emailAddress='"
        + emailAddress
        + '\''
        + ", login='"
        + login
        + '\''
        + ", password='"
        + password
        + '\''
        + ", role="
        + role
        + '}';
  }
}
