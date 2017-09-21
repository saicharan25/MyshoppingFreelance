package info.devexchanges.navvp.datasource;

/**
 * Created by rajeshrayala on 06/04/17.
 */

public class OfferModel {

    public OfferModel() {
    }

    public OfferModel(String title, String genre, String year) {
        this.title = title;
        this.message = genre;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    private String title, message, year;


}
