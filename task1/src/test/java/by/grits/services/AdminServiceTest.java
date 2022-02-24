package by.grits.services;

import by.grits.dao.DaoException;
import by.grits.dao.ItemDao;
import by.grits.dao.UserDao;
import by.grits.entities.items.Item;
import by.grits.entities.people.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {
  @Mock private ItemDao itemDao;
  @Mock private UserDao userDao;

  private AdminService adminService;
  private User mockedUser;
  private Item mockedItem;

  @BeforeEach
  void setUp() {
    adminService = new AdminService(itemDao, userDao);
    mockedUser = mock(User.class);
    mockedItem = mock(Item.class);
  }

  @Test
  void getAllUsers() throws DaoException {
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
  void getAllItems() throws DaoException {
    Collection<Item> itemDaoRespond = new HashSet<>();
    itemDaoRespond.add(mockedItem);

    when(itemDao.getAll()).thenReturn(itemDaoRespond);

    Collection<Item> result = adminService.getAllItems();
    Assertions.assertEquals(mockedItem, result.iterator().next());
    Assertions.assertEquals(1, result.size());

    verify(itemDao).getAll();
    verifyNoMoreInteractions(itemDao);
  }

  @Test
  void getUsersItems() throws DaoException {
    Collection<Item> itemDaoRespond = new HashSet<>();
    itemDaoRespond.add(mockedItem);

    when(itemDao.getUserItems("email")).thenReturn(itemDaoRespond);

    Collection<Item> result = adminService.getUsersItems("email");
    Assertions.assertEquals(mockedItem, result.iterator().next());
    Assertions.assertEquals(1, result.size());

    verify(itemDao).getUserItems("email");
    verifyNoMoreInteractions(itemDao);
  }

  @Test
  void getSpecificItem() {
    when(itemDao.getEntityById(1)).thenReturn(mockedItem);
    Item resultItem = adminService.getSpecificItem(1);
    Assertions.assertEquals(mockedItem, resultItem);

    verify(itemDao).getEntityById(1);
    verifyNoMoreInteractions(itemDao);
  }

  @Test
  void getSpecificUser() throws DaoException {
    when(userDao.get(1)).thenReturn(mockedUser);
    User result = adminService.getSpecificUser(1);
    Assertions.assertEquals(mockedUser, result);

    verify(userDao).get(1);
    verifyNoMoreInteractions(userDao);
  }
}
