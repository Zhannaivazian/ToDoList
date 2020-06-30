package org.redischool.sd2.todo.domain;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
final class ConcreteTodoListService implements TodoListService {

    @Override
    public void markCompleted(long itemId) {
        Item obj = null;
        for (Item i : allItems) {
            if (i.id == itemId) {
                obj = i;
                break;
            }
        }
        if (obj != null) {
            allItems.remove(obj);
        }
    }

    @Override
    public void updateRecurringTasks() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
//    @Override
//    public void addTaskWithDeadline(long id, String label,String deadline) {
//        Optional<OneTimeTask> existingOneTimeTaskWithDeadline = currentItems().stream().
//                filter(item -> item instanceof OneTimeTask && item.getLabel().equalsIgnoreCase(label)).
//                map(item -> (OneTimeTask)item).findFirst();
//        if(existingOneTimeTaskWithDeadline.isPresent()){
//            existingOneTimeTaskWithDeadline.get().setDeadline(deadline);
//
//        }
//        else  {
//            currentItems().add(new OneTimeTask(id,label,deadline));
//        }
//    }

    @Override
    public List<Item> currentItems() {
        return allItems;
    }

    @Override
    public void addItem(Item item) {
        boolean isNew = true;
        for (Item i : allItems) {
            if (item.getType() == i.getType()) {
                switch (item.getType()) {
                    case OneTimeTask:
                        if (item.getLabel().equalsIgnoreCase(i.getLabel())) {
                            ((OneTimeTask) i).setDeadline(((OneTimeTask) item).getDeadline());
                            isNew = false;
                        }
                        break;
                    case RecurringTask:
                        if (item.getLabel().equalsIgnoreCase(i.getLabel())) {
                            ((RecurringTask) i).setPeriod(((RecurringTask) item).getPeriod());
                            ((RecurringTask) i).setFrequency(((RecurringTask) item).getFrequency());
                            isNew = false;
                        }
                        break;
                    case ShoppingList:
                        if (item.getLabel().equalsIgnoreCase(i.getLabel())) {
                            ((ShoppingList) i).setAmount(((ShoppingList) i).getAmount() + ((ShoppingList) item).getAmount());
                            isNew = false;
                        }
                        break;

                }
            }
        }
        if (isNew) {
            allItems.add(item);
        }

    }

    private List<Item> allItems = new ArrayList<Item>();


//    public void Item (long id, String label, int amount){
//        ShoppingList existingList = null;
//        for ( Item item: allItems){
//            if (item.getLabel().equalsIgnoreCase(label) && (item instanceof ShoppingList)){
//                existingList = (ShoppingList) item;
//            }
//        }
//        if (existingList == null){
//            allItems.add(new ShoppingList(id, label, amount));
//        }
//        else {
//            existingList.setAmount(amount+existingList.getAmount());
//        }
//    }
}














