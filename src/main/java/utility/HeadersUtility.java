package main.java.utility;

import java.util.HashMap;
import java.util.Map;

public class HeadersUtility {

    public static Map<String, String> getSampleHeader() {

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("content-type", "application/json");

        return requestHeaders;
    }
}
