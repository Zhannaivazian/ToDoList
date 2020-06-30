package org.redischool.sd2.todo.api;


/**
 * Specifies the format of a TODO item between frontend and server.
 * <p>
 * Each field corresponds to a JSON property.
 */
public final class ItemDto {
    private static long nextId = 100;

    private String id;
    private String label;
    private String type;
    private Integer amount;
    private Integer frequency;
    private String period;
    private String deadline;

    public static long getNextId(){
        return ++nextId;
    }


    public static ItemDto oneTimeTaskWithLabel(long id, String label) {

        ItemDto itemDto = new ItemDto();
        itemDto.id = String.valueOf(id);
        itemDto.label = label;
        itemDto.type = "TASK";
        return itemDto;
    }

    public static ItemDto oneTimeTaskWithLabelAndDeadline(long id, String label, String deadline) {
        ItemDto itemDto = new ItemDto();
        itemDto.id = String.valueOf(id);
        itemDto.label = label;
        itemDto.deadline = deadline;
        itemDto.type = "TASK";
        return itemDto;
    }

    public static ItemDto recurringTaskWithLabel(long id, String label, int frequency, String period) {
        ItemDto itemDto = new ItemDto();
        itemDto.id = String.valueOf(id);
        itemDto.label = label;
        itemDto.frequency = frequency;
        itemDto.period = period;
        itemDto.type = "RECURRING";
        return itemDto;
    }

    public static ItemDto shoppingItemWithLabel(long id, String label, int amount) {
        ItemDto itemDto = new ItemDto();
        itemDto.id = String.valueOf(id);
        itemDto.label = label;
        itemDto.amount = amount;
        itemDto.type = "SHOPPING_ITEM";
        return itemDto;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public String getPeriod() {
        return period;
    }

    public String getDeadline() {
        return deadline;
    }
}
