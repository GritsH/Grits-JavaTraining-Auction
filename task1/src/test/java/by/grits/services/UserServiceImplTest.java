package by.grits.services;

import by.grits.dao.exception.DaoException;
import by.grits.dao.UserDao;
import by.grits.entities.people.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @Mock private UserDao userDao;
  private UserServiceImpl userService;

  @Mock private User mockedUser;

  @BeforeEach
  void setup() {
    userService = new UserServiceImpl(userDao);
  }

  @Test
  void logIn() throws DaoException {
    when(userDao.signIn("email", "password")).thenReturn(mockedUser);

    User result = userService.logIn("email", "password");
    Assertions.assertEquals(mockedUser, result);

    verify(userDao).signIn("email", "password");
    verifyNoMoreInteractions(userDao);
  }

  @Test
  void addNewUser() throws DaoException {
    userService.addNewUser(mockedUser);

    verify(userDao).add(mockedUser);
    verifyNoMoreInteractions(userDao);
  }

  @Test
  void userExists() throws DaoException {
    userService.userExists("email");

    verify(userDao).getByEmail("email");
    verifyNoMoreInteractions(userDao);
  }
}
