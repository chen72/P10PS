package sg.edu.rp.webservices.p10ps;

import java.io.Serializable;

public class Todo implements Serializable {
    private String title;
    private String date;
    private int numOfDays;
    private boolean completed;

    public Todo(String title, String date, int numOfDays, boolean completed) {
        this.title = title;
        this.date = date;
        this.numOfDays = numOfDays;
        this.completed = completed;
    }
    public Todo() {
        // Default constructor required for calls to DataSnapshot.getValue(Message.class)

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Message{" +
                ", Title='" + title + '\'' +
                ", Date='" + date + '\'' +
                ", NumOfDays='" + numOfDays + '\'' +
                ", Complete?='" + completed + '\'' +
                '}';
    }
}
