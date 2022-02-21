package by.grits.services;

import by.grits.dao.*;
import by.grits.entities.item.Item;
import by.grits.entities.people.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {
  @Mock private ItemDao itemDao;
  @Mock private UserDao userDao;

  private AdminService adminService;

  @BeforeEach
  void setUp() {
    adminService = new AdminService(itemDao, userDao);
  }

  @Test
  void getAllUsers() throws DaoException {
    User mockedUser = mock(User.class);
    Set<User> daoResponse = new HashSet<>();
    daoResponse.add(mockedUser);
    when(userDao.getAll()).thenReturn(daoResponse);

    Collection<User> result = adminService.getAllUsers();
    Assertions.assertEquals(mockedUser, result.stream().iterator().next());
    Assertions.assertEquals(1, result.size());

    verify(userDao).getAll();
    verifyNoMoreInteractions(userDao);
  }

  @Test
  void getAllItems() {

  }

  @Test
  void getUsersItems() {}

  @Test
  void getSpecificItem() {}

  @Test
  void getSpecificUser() {}
}
