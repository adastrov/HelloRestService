package hello_rest_service.palagen.com.github.dto;

import java.io.Serializable;

public class ContactDTO implements Serializable {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return name;
    }

    public void setContactName(String name) {
        this.name = name;
    }

}
