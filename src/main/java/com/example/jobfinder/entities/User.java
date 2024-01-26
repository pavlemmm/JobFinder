package com.example.jobfinder.entities;

public abstract class User {
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "User{" + "ID=" + ID + '}';
    }
}
