package org.redischool.sd2.todo.domain;


import org.redischool.sd2.todo.api.ItemDto;

public class RecurringTask extends Item {

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    private String period;

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    private int frequency;

    public RecurringTask(long id, String label, String period, int frequency) {
        super(id);
        this.period = period;
        this.frequency = frequency;
        this.setLabel(label);
        this.setType(ItemType.RecurringTask);


    }

    @Override
    public ItemDto toDto() {

        return
               ItemDto.recurringTaskWithLabel(id, getLabel(),frequency, period);
    }

}
