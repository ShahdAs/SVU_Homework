package com.example.homework.database.model;

public class DateModel {
    int dateId, agentId,  companyId;
    String title, dateWork, dateReminder, dateDelayed;
    Boolean dateIsDone, dateIsDelayed;

    public DateModel(int dateId, int agentId, int companyId, String title, String dateWork, String dateReminder, String dateDelayed, Boolean dateIsDelayed, Boolean dateIsDone) {
        this.dateId = dateId;
        this.agentId = agentId;
        this.companyId = companyId;
        this.title = title;
        this.dateWork = dateWork;
        this.dateReminder = dateReminder;
        this.dateDelayed = dateDelayed;
        this.dateIsDone = dateIsDone;
        this.dateIsDelayed = dateIsDelayed;
    }

    public int getDateId() {
        return dateId;
    }

    public void setDateId(int dateId) {
        this.dateId = dateId;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateWork() {
        return dateWork;
    }

    public void setDateWork(String dateWork) {
        this.dateWork = dateWork;
    }

    public String getDateReminder() {
        return dateReminder;
    }

    public void setDateReminder(String dateReminder) {
        this.dateReminder = dateReminder;
    }

    public String getDateDelayed() {
        return dateDelayed;
    }

    public void setDateDelayed(String dateDelayed) {
        this.dateDelayed = dateDelayed;
    }

    public Boolean getDateIsDone() {
        return dateIsDone;
    }

    public void setDateIsDone(Boolean dateIsDone) {
        this.dateIsDone = dateIsDone;
    }

    public Boolean getDateIsDelayed() {
        return dateIsDelayed;
    }

    public void setDateIsDelayed(Boolean dateIsDelayed) {
        this.dateIsDelayed = dateIsDelayed;
    }
}
