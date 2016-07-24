package anh.excam.com.testapp.Model;

import java.io.Serializable;

/**
 * Created by Anh on 7/17/2016.
 */
public class ExecisesOfDay implements Serializable {
    private String nameEx;
    private String image;


    public ExecisesOfDay() {
    }

    public ExecisesOfDay(String nameEx, String image) {
        this.nameEx = nameEx;
        this.image = image;
    }

    public String getNameEx() {
        return nameEx;
    }

    public void setNameEx(String nameEx) {
        this.nameEx = nameEx;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

