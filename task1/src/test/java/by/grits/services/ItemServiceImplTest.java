package by.grits.services;

import by.grits.dao.exception.DaoException;
import by.grits.dao.ItemDao;
import by.grits.entities.items.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

  @Mock private ItemDao itemDao;
  private ItemServiceImpl itemService;

  @Mock private Item mockedItem;

  @BeforeEach
  void setup() {
    itemService = new ItemServiceImpl(itemDao);
  }

  @Test
  void addItem() throws DaoException {
    itemService.addItem(mockedItem);

    verify(itemDao).add(mockedItem);
    verifyNoMoreInteractions(itemDao);
  }

  @Test
  void removeItem() throws DaoException {
    itemService.removeItem(1);

    verify(itemDao).delete(1);
    verifyNoMoreInteractions(itemDao);
  }

  @Test
  void getAllItems() throws DaoException {
    List<Item> itemDaoRespond = new ArrayList<>();
    itemDaoRespond.add(mockedItem);

    when(itemDao.getUserItems("email")).thenReturn(itemDaoRespond);

    Collection<Item> result = itemService.getAllItems("email");
    Assertions.assertEquals(mockedItem, result.iterator().next());
    Assertions.assertEquals(1, result.size());

    verify(itemDao).getUserItems("email");
    verifyNoMoreInteractions(itemDao);
  }

  @Test
  void removeAll() throws DaoException {
    List<Item> items = new ArrayList<>();
    items.add(mockedItem);
    when(itemDao.getAll()).thenReturn(items);
    when(mockedItem.getId()).thenReturn(1);
    when(mockedItem.getOwnersEmail()).thenReturn("email");

    itemService.removeAll("email");

    verify(itemDao).getAll();
    verify(itemDao).delete(1);
    verifyNoMoreInteractions(itemDao);
  }

  @Test
  void shouldNotDeleteItemsIfUserEmailNotFound() throws DaoException {
    List<Item> items = new ArrayList<>();
    items.add(mockedItem);

    when(itemDao.getAll()).thenReturn(items);
    when(mockedItem.getOwnersEmail()).thenReturn("rightEmail");

    itemService.removeAll("wrongEmail");

    verify(itemDao).getAll();
    verifyNoMoreInteractions(itemDao);
  }

  @Test
  void shouldDeleteSeveralUserItems() throws DaoException {
    List<Item> items = new ArrayList<>();
    Item mockedItem2 = mock(Item.class);
    items.add(mockedItem);
    items.add(mockedItem2);

    when(itemDao.getAll()).thenReturn(items);
    when(mockedItem.getOwnersEmail()).thenReturn("email");
    when(mockedItem2.getOwnersEmail()).thenReturn("email");
    when(mockedItem.getId()).thenReturn(0);
    when(mockedItem2.getId()).thenReturn(1);

    itemService.removeAll("email");

    verify(itemDao).getAll();
    verify(itemDao).delete(0);
    verify(itemDao).delete(1);
    verifyNoMoreInteractions(itemDao);
  }

  @DisplayName("delete only user items")
  @Test
  void shouldDeleteItemsBelongToOneUser() throws DaoException {
    List<Item> items = new ArrayList<>();
    Item mockedItem2 = mock(Item.class);
    Item mockedItem3 = mock(Item.class);
    items.add(mockedItem);
    items.add(mockedItem2);
    items.add(mockedItem3);

    when(itemDao.getAll()).thenReturn(items);
    when(mockedItem.getOwnersEmail()).thenReturn("firstEmail");
    when(mockedItem2.getOwnersEmail()).thenReturn("secondEmail");
    when(mockedItem3.getOwnersEmail()).thenReturn("firstEmail");
    when(mockedItem.getId()).thenReturn(0);
    when(mockedItem3.getId()).thenReturn(2);

    itemService.removeAll("firstEmail");

    verify(itemDao).getAll();
    verify(itemDao).delete(0);
    verify(itemDao).delete(2);
    verifyNoMoreInteractions(itemDao);
  }
}
