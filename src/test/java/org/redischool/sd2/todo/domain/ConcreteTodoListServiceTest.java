package org.redischool.sd2.todo.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ConcreteTodoListServiceTest {
    ConcreteTodoListService service = new ConcreteTodoListService();

    @Test
    public void testUpdateOneTimeTaskWithDeadline() {
        service.currentItems().clear();
        service.addItem(new OneTimeTask(100, "Call to mam", "2020.10.10"));
        service.addItem(new OneTimeTask(100, "Call to mam", "2021.11.11"));
        service.addItem(new OneTimeTask(100, "Call to mam", "2022.12.22"));
        for (Item item : service.currentItems()) {
            if (item instanceof OneTimeTask) {
                assertEquals(((OneTimeTask) item).getDeadline(), "2022.12.22");
            }
        }
        assertEquals(service.currentItems().size(), 1);
    }

    @Test
    public void testShoppingList() {
        ShoppingList shoppingList1 = new ShoppingList(1, "Apple", 4);
        ShoppingList shoppingList2 = new ShoppingList(1, "Apple", 1);
        service.addItem(shoppingList1);
        service.addItem(shoppingList2);
        for (Item item : service.currentItems()) {
            if (item instanceof ShoppingList)
                assertEquals(((ShoppingList) item).getAmount(), 5);
        }

    }

}
