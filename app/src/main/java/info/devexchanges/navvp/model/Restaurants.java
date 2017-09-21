package info.devexchanges.navvp.model;

import java.util.ArrayList;

public class Restaurants {
    private String message;
    private ArrayList<Restaurant> result;
    private String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Restaurant> getResult() {
        return this.result;
    }

    public void setResult(ArrayList<Restaurant> result) {
        this.result = result;
    }
}
