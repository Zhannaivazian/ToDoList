package org.redischool.sd2.todo.api;

import org.redischool.sd2.todo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;


import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides the endpoints allowing the frontend to communicate with the server.
 */
@RestController
final class TodoServiceController {
  private final TodoListService todoListService;

  TodoServiceController(@Autowired TodoListService todoListService) {
    this.todoListService = todoListService;
  }

  @GetMapping("/api/items")
  FetchItemsResponseDto fetchItems() {
    return new FetchItemsResponseDto(currentItems());
  }

  @PostMapping("/api/items")
  AddItemResponseDto addItem(@RequestBody AddItemRequestDto addItemDto) {
    todoListService.addItem(addItemDto.toItem(ItemDto.getNextId()));
   return new AddItemResponseDto(currentItems());
  }
  private Period toPeriod(int frequency, String unit) {
    switch (unit) {
      case "DAY":
        return Period.ofDays(frequency);
      case "WEEK":
        return Period.ofWeeks(frequency);
      case "MONTH":
        return Period.ofMonths(frequency);
      case "YEAR":
        return Period.ofYears(frequency);
      default:
        throw new HttpClientErrorException(
            HttpStatus.BAD_REQUEST, String.format("Unknown period unit %s", unit));
    }
  }

  @DeleteMapping("/api/items/{id}")
  DeleteItemResponseDto deleteItem(@PathVariable("id") String id) {
    todoListService.markCompleted(Long.parseLong(id));
    return new DeleteItemResponseDto(currentItems());
  }

  @PutMapping("/api/items:updateRecurring")
  void updateRecurringTasks() {
    todoListService.updateRecurringTasks();
  }

  private List<ItemDto> currentItems() {
    List<ItemDto> result = new ArrayList<>();
    List<Item> items = todoListService.currentItems();
    for ( Item i : items ) {
      result.add(i.toDto());
    }
   return result;
  }

  private static final class FetchItemsResponseDto {
    private final List<ItemDto> items;

    FetchItemsResponseDto(List<ItemDto> items) {
      this.items = List.copyOf(items);
    }

    public List<ItemDto> getItems() {
      return items;
    }
  }

  private static final class AddItemResponseDto {
    private final List<ItemDto> items;

    AddItemResponseDto(List<ItemDto> items) {
      this.items = List.copyOf(items);
    }

    public List<ItemDto> getItems() {
      return items;
    }
  }

  private static final class DeleteItemResponseDto {
    private final List<ItemDto> items;

    DeleteItemResponseDto(List<ItemDto> items) {
      this.items = List.copyOf(items);
    }

    public List<ItemDto> getItems() {
      return items;
    }
  }

  private static final class AddItemRequestDto {
    String label;
    String type;
    Integer amount;
    Integer frequency;
    String period;
    String deadline;

    public Item toItem(long id) {
      switch (this.type) {
        case "TASK":
          return new OneTimeTask(id, this.label, this.deadline);
        case "RECURRING":
          return new RecurringTask(id, this.label, this.period, this.frequency);
        case "SHOPPING_ITEM":
          return new ShoppingList(id, this.label, this.amount);
        default:
          return null;
      }
    }

    public void setLabel(String label) {
      this.label = label;
    }

    public void setType(String type) {
      this.type = type;
    }

    public void setAmount(Integer amount) {
      this.amount = amount;
    }

    public void setFrequency(Integer frequency) {
      this.frequency = frequency;
    }

    public void setPeriod(String period) {
      this.period = period;
    }

    public void setDeadline(String deadline) {
      this.deadline = deadline;
    }
  }
}
