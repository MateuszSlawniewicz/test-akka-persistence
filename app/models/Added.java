package models;



public class Added implements Event, MySerializable {

    public String data;

    public Added(){}

    public Added(String data) {
        this.data = data;
    }
}

