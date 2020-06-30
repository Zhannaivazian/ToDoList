package org.redischool.sd2.todo.domain;

import org.redischool.sd2.todo.api.ItemDto;


public class OneTimeTask extends Item {
    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    private String deadline;

    public OneTimeTask(long id, String label, String deadline) {
        super(id);
        this.deadline = deadline;
        this.setLabel(label);
        this.setType(ItemType.OneTimeTask);
    }

    // gri code for ete nuyn taskic createa exel nuyn orva hamar beri message
    // aselov vor "duq arden uneq sargac ayspisi task"


    @Override
    public ItemDto toDto() {
        return
        this.deadline == null ? ItemDto.oneTimeTaskWithLabel(id, getLabel())
               : ItemDto.oneTimeTaskWithLabelAndDeadline(id, getLabel(), this.deadline);
    }
}
