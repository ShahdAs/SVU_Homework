package com.example.homework.database.model;

public class AgentModel {
    String firstName, lastName,  work,  phoneNumber, email;
    int id;

    public AgentModel(int id, String firstName, String lastName, String work, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.work = work;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
