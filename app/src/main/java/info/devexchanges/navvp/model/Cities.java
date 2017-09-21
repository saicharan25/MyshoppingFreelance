package info.devexchanges.navvp.model;

/**
 * Created by acer on 9/9/2017.
 */

public class Cities {

   /* "id": "1",
            "city": "Nandyal",
            "status": "1"*/


    public String id;
    public String city;
    public String status;
    public String delivery_amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelivery_amount() {
        return delivery_amount;
    }

    public void setDelivery_amount(String delivery_amount) {
        this.delivery_amount = delivery_amount;
    }
}
