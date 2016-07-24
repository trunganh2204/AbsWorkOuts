package anh.excam.com.testapp.Model;

import java.io.Serializable;

/**
 * Created by Admin on 7/19/2016.
 */
public class MoreApps implements Serializable {




    private int imageIcon;
    private String title;
    private String detail;
    private int TYPE_ITEM;



    public int getTYPE_ITEM() {
        return TYPE_ITEM;
    }

    public void setTYPE_ITEM(int TYPE_ITEM) {
        this.TYPE_ITEM = TYPE_ITEM;
    }

    public MoreApps(){

    }
    public MoreApps(int TYPE_ITEM,int imageIcon, String title, String detail) {
        this.imageIcon = imageIcon;
        this.title = title;
        this.detail = detail;
        this.TYPE_ITEM = TYPE_ITEM;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(int imageIcon) {
        this.imageIcon = imageIcon;
    }
}
