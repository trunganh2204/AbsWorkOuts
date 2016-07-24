package anh.excam.com.testapp.Model;

import java.io.Serializable;

/**
 * Created by MyPC on 05/07/2016.
 */
public class DataWeek implements Serializable {
    private int background;
    private String title;
    private String week;
    private String content;

    public DataWeek(int background, String title, String week, String content) {
        this.background = background;
        this.title = title;
        this.week = week;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DataWeek() {
    }


    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
