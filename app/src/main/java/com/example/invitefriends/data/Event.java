package com.example.invitefriends.data;

import java.util.Date;
import java.util.List;

public class Event {
    private String id;
    private String owner;
    private String title;
    private Date date;
    private double latitude;
    private double longitude;
    private String description;
    private List<Long> accepted;
    private List<Long> declined;
    private List<Long> requested;
    private List<Long> invited;

    public Event() {
    }

    public Event(String owner, String title, Date date, double latitude, double longitude, String description, List<Long> accepted, List<Long> declined, List<Long> requested, List<Long> invited) {
        this.owner = owner;
        this.title = title;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.accepted = accepted;
        this.declined = declined;
        this.requested = requested;
        this.invited = invited;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getAccepted() {
        return accepted;
    }

    public void setAccepted(List<Long> accepted) {
        this.accepted = accepted;
    }

    public List<Long> getDeclined() {
        return declined;
    }

    public void setDeclined(List<Long> declined) {
        this.declined = declined;
    }

    public List<Long> getRequested() {
        return requested;
    }

    public void setRequested(List<Long> requested) {
        this.requested = requested;
    }

    public List<Long> getInvited() {
        return invited;
    }

    public void setInvited(List<Long> invited) {
        this.invited = invited;
    }
}
