package info.devexchanges.navvp.datasource;

/**
 * Created by rajeshrayala on 06/04/17.
 */

public class Data_Model {

    // Getter and Setter model for recycler view items
    private String title;
    private int image;

    public Data_Model(String title,  int image) {

        this.title = title;

        this.image = image;
    }

    public String getTitle() {
        return title;
    }



    public int getImage() {
        return image;
    }
}

