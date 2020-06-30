package org.redischool.sd2.todo.domain;

import org.redischool.sd2.todo.api.ItemDto;

public class ShoppingList extends Item {

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private int amount;

    public ShoppingList(long id, String label, int amount) {
        super(id);
        this.amount = amount;
        this.setLabel(label);
        this.setType(ItemType.ShoppingList);

    }

    @Override
    public ItemDto toDto() {
        return
                ItemDto.shoppingItemWithLabel(id, this.getLabel(), amount);
    }

}
