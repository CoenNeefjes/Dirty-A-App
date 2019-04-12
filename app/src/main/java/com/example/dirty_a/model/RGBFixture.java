package com.example.dirty_a.model;

public class RGBFixture extends Device {

    public RGBFixture(long id, String name, int[] channels) {
        super(id, name, channels);
    }

    public void changeColor(int r, int g, int b) {

    }

    public void changeColor(int[] rgb) {
        if (rgb.length != channels.length) {
            //TODO: throw exception
        }
    }
}
