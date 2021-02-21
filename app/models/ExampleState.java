package models;

import java.util.ArrayList;
import java.util.List;

class ExampleState implements MySerializable {
    private List<String> items;
    private  String last;

    public void setItems(List<String> items) {
        this.items = items;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public List<String> getItems() {
        return items;
    }

    public String getLast() {
        return last;
    }

    private ExampleState(List<String> items, String last) {
        this.items = items;
        this.last = last;
    }

    public ExampleState() {
        this.items = new ArrayList<>();
    }

    public ExampleState addItem(String data) {
        List<String> newItems = new ArrayList<>(items);
        newItems.add(0, data);
        // keep 5 items
        List<String> latest = newItems.subList(0, Math.min(4, newItems.size() - 1));
        return new ExampleState(latest, data);
    }

    public ExampleState copy() {
        return new ExampleState(new ArrayList<>(items), last);
    }


}

