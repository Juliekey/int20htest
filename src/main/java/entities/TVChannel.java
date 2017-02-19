package entities;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Yuliya Pedash on 18.02.2017.
 */
public class TVChannel {
    String name;
    String date;
    List<TVProgram> programs;

    public TVChannel() {

    }

    public TVChannel(String name, String date, List<TVProgram> programs) {
        this.name = name;
        this.date = date;
        this.programs = programs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TVProgram> getPrograms() {
        return programs;
    }

    public void setPrograms(List<TVProgram> programs) {
        this.programs = programs;
    }

    @Override
    public String toString() {
        String newLine = "\n";
        String channel = "";
        channel = "Name: " + name + newLine + date + newLine;
        for (TVProgram tvProgram :
                programs) {
            channel += tvProgram.toString();
        }
        return channel;
    }
}
