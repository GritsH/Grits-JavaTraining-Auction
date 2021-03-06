package by.grits.entities.people;

import by.grits.entities.enums.RoleType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/** This class represent the entity of user. Consists of fields that describe a user. */
public class User implements Comparable<User>, Serializable {
  private static final long serialVersionUID = 456L;

  private Integer id;
  private String emailAddress;
  private String password;
  private RoleType role;
  private LocalDate addedAt;

  public User(String emailAddress, String password, RoleType role) {
    this.emailAddress = emailAddress;
    this.password = password;
    this.role = role;
  }

  public void setAddedAt(LocalDate addedAt) {
    this.addedAt = addedAt;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public String getPassword() {
    return password;
  }

  public RoleType getRole() {
    return role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(emailAddress, user.emailAddress)
        && Objects.equals(password, user.password)
        && role == user.role;
  }

  @Override
  public int hashCode() {
    return Objects.hash(emailAddress, password, role);
  }

  @Override
  public String toString() {
    return "User{"
        + "emailAddress='"
        + emailAddress
        + '\''
        + ", password='"
        + password
        + '\''
        + ", role="
        + role
        + '}';
  }

  @Override
  public int compareTo(User user) {
    int result = this.addedAt.compareTo(user.addedAt);
    if (result == 0) {
      result = this.id.compareTo(user.id);
    }
    return result;
  }
}
