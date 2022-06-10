package main.java.pentagon.dtos;

import com.google.gson.Gson;

public class OpenOrder {

    public String getAsJson() {
        return new Gson().toJson(this);
    }
}
