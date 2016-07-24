package anh.excam.com.testapp.Model;

import java.io.Serializable;

/**
 * Created by MyPC on 09/07/2016.
 */
public class DataWorkOut implements Serializable {
    private String position;
    private String nameExercises;

    public DataWorkOut() {
    }

    public DataWorkOut(String position, String name) {
        this.position = position;
        nameExercises = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return nameExercises;
    }

    public void setName(String name) {
        nameExercises = name;
    }
}
