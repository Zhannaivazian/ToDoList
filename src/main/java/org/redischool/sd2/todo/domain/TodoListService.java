package org.redischool.sd2.todo.domain;

import java.util.List;

/**
 * Interface for managing the TODO list.
 */
public interface TodoListService {
    void markCompleted(long itemId);

    void updateRecurringTasks();

    List<Item> currentItems();

    void addItem(Item item);
}
