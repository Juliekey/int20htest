package entities;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Yuliya Pedash on 18.02.2017.
 */
public class TVProgram {
    String imageLink;
    String title;
    String subtitle;
    Date realtimeBegin;
    Date realtimEnd;

    public TVProgram(String imageLink, String title, String subtitle, Date realtimeBegin, Date realtim_end) {
        this.imageLink = imageLink;
        this.title = title;
        this.subtitle = subtitle;
        this.realtimeBegin = realtimeBegin;
        this.realtimEnd = realtim_end;
    }

    public TVProgram() {

    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Date getRealtimeBegin() {
        return realtimeBegin;
    }

    public void setRealtimeBegin(Date realtimeBegin) {
        this.realtimeBegin = realtimeBegin;
    }

    public Date getRealtimEnd() {
        return realtimEnd;
    }

    public void setRealtimEnd(Date realtimEnd) {
        this.realtimEnd = realtimEnd;
    }

    @Override
    public String toString() {
        String program = "";
        String newLine = "\n";
        program += "imageLink: " + imageLink + newLine + "title: " + title + newLine + "subtitle: " + subtitle + newLine + "RealtimeBegin: " + realtimeBegin.toString() + newLine + "realtimeEnd: " + realtimEnd.toString() + newLine;
        return program;
    }
}
