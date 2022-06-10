package main.java.core;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class Response {

    private String statusLine;
    private int statusCode;
    private String body;
    private HashMap<String, String> headers;

    public String getHeader(String headerKey) {
        return headers.get(headerKey);
    }

    public JsonObject getBodyAsJson() {
        return new JsonParser().parse(body).getAsJsonObject();
    }

}
