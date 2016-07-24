package anh.excam.com.testapp.Model;

import java.io.Serializable;

/**
 * Created by MyPC on 09/07/2016.
 */
public class ExecisesPlay implements Serializable {
    private  String title;
    private  String image;
    private String description;

    public ExecisesPlay() {
    }

    public ExecisesPlay(String title, String image, String description) {
        this.title = title;
        this.image = image;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
