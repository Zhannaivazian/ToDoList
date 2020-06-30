package org.redischool.sd2.todo.domain;

import org.redischool.sd2.todo.api.ItemDto;

import java.util.Objects;

public abstract class Item {

    public enum ItemType {
        OneTimeTask,
        RecurringTask,
        ShoppingList
    }
    private String label;
    private ItemType type;
    protected long id;

    public Item(long id){
        setId(id);
    }
    public abstract ItemDto toDto();

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType itemType) {
        this.type = itemType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public long id() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
