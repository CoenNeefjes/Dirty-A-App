package com.example.dirty_a.model;

import java.util.List;

public class Device {

    protected long id;
    protected String name;
    protected List<Integer> channels;

    public Device(long id, String name, List<Integer> channels) {
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

    public List<Integer> getChannels() {
        return channels;
    }
}
