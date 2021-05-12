package com.example.listtravel;

public class Travel {
    int id;
    String name;

    public Travel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Travel(String name) {
        this.name = name;
    }

    public Travel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id +". " +name;
    }
}
