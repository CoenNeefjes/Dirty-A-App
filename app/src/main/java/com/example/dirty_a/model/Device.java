package com.example.dirty_a.model;

public class Device {

    protected long id;
    protected String name;
    protected int[] channels;

    public Device(long id, String name, int[] channels) {
        this.id = id;
        this.name = name;
        this.channels = channels;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
