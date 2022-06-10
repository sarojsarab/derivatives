package main.java.core;

import org.apache.http.Header;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ApiExecutor {

    //    Making a post request by providing headers, JSON body and Host url
    public static Response sendPOST(String url, String requestBody, Map<String, String> headers) throws IOException {

        String curlRequest = "curl -X POST "+" " + url + " ";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        httpPost.setEntity(new StringEntity(requestBody));

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpPost.addHeader(entry.getKey(), entry.getValue());

            curlRequest += " -H " + "'" + entry.getKey() + ": "+ entry.getValue() + "'" + " ";
        }

        if (Objects.nonNull(requestBody)) {
            curlRequest += "-d " + "'" + requestBody + "'";
        }

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        StringBuffer body = new StringBuffer();

        if (httpResponse.getEntity() != null) {
            String inputLine;
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            while ((inputLine = reader.readLine()) != null) {
                body.append(inputLine);
            }
            reader.close();
        }

        httpClient.close();

        HashMap<String, String> tempResponseHeaders = new HashMap<>();

        Header[] responseHeaders = httpResponse.getAllHeaders();
        for (Header header : responseHeaders)
            tempResponseHeaders.put(header.getName(), header.getValue());

        Response response = new Response();
        response.setBody(body.toString());
        response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        response.setStatusLine(httpResponse.getStatusLine().toString());
        response.setHeaders(tempResponseHeaders);

        Logger.log("Time: " + System.currentTimeMillis());
        Logger.log(curlRequest);
        Logger.log("POST status code: " + response.getStatusCode());
        Logger.log("url: " + url);
        Logger.log("Request body: " + requestBody);
        Logger.log("Response body: " + response.getBody());


        return response;
    }

    //    Making a patch request by providing headers, JSON body and Host url
    public static Response sendPATCH(String url, String requestBody, Map<String, String> headers) throws IOException {

        String curlRequest = "curl -X PATCH "+" " + url + " ";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPatch httpPatch = new HttpPatch(url);

        httpPatch.setEntity(new StringEntity(requestBody));

        for (Map.Entry<String, String> entry : headers.entrySet()){
            httpPatch.addHeader(entry.getKey(), entry.getValue());
            curlRequest += " -H " + "'" + entry.getKey() + ": "+ entry.getValue() + "'" + " ";
        }

        if (Objects.nonNull(requestBody)) {
            curlRequest += "-d " + "'" + requestBody + "'";
        }

        CloseableHttpResponse httpResponse = httpClient.execute(httpPatch);
        StringBuffer body = new StringBuffer();

        if (httpResponse.getEntity() != null) {
            String inputLine;
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            while ((inputLine = reader.readLine()) != null) {
                body.append(inputLine);
            }
            reader.close();
        }

        httpClient.close();

        HashMap<String, String> tempResponseHeaders = new HashMap<>();

        Header[] responseHeaders = httpResponse.getAllHeaders();
        for (Header header : responseHeaders)
            tempResponseHeaders.put(header.getName(), header.getValue());

        Response response = new Response();
        response.setBody(body.toString());
        response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        response.setStatusLine(httpResponse.getStatusLine().toString());
        response.setHeaders(tempResponseHeaders);

        Logger.log("Time: " + System.currentTimeMillis());
        Logger.log(curlRequest);
        Logger.log("PATCH status code: " + response.getStatusCode());
        Logger.log("url: " + url);
        Logger.log("Request body: " + requestBody);
        Logger.log("Response body: " + response.getBody());

        return response;
    }

    //    Making a PUT request by providing headers, JSON body and Host url
    public static Response sendPUT(String url, String requestBody, Map<String, String> headers) throws IOException {

        String curlRequest = "curl -X PUT "+" " + url + " ";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);

        for (Map.Entry<String, String> entry : headers.entrySet()){
            httpPut.addHeader(entry.getKey(), entry.getValue());
            curlRequest += " -H " + "'" + entry.getKey() + ": "+ entry.getValue() + "'" + " ";
        }

        httpPut.setEntity(new StringEntity(requestBody));

        if (Objects.nonNull(requestBody)) {
            curlRequest += "-d " + "'" + requestBody + "'";
        }

        CloseableHttpResponse httpResponse = httpClient.execute(httpPut);
        StringBuffer body = new StringBuffer();

        if (httpResponse.getEntity() != null) {
            String inputLine;
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            while ((inputLine = reader.readLine()) != null) {
                body.append(inputLine);
            }
            reader.close();
        }

        httpClient.close();

        HashMap<String, String> tempResponseHeaders = new HashMap<>();

        Header[] responseHeaders = httpResponse.getAllHeaders();
        for (Header header : responseHeaders)
            tempResponseHeaders.put(header.getName(), header.getValue());

        Response response = new Response();
        response.setBody(body.toString());
        response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        response.setStatusLine(httpResponse.getStatusLine().toString());
        response.setHeaders(tempResponseHeaders);

        Logger.log("Time: " + System.currentTimeMillis());
        Logger.log(curlRequest);
        Logger.log("PUT status code: " + response.getStatusCode());
        Logger.log("url: " + url);
        Logger.log("Request body: " + requestBody);
        Logger.log("Response body: " + response.getBody());

        return response;
    }

    //    Making a GET request by providing headers and Host url
    public static Response sendGET(String url, Map<String, String> headers) throws IOException {

        String curlRequest = "curl -X GET "+" " + url + " ";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        for (Map.Entry<String, String> entry : headers.entrySet()){
            httpGet.addHeader(entry.getKey(), entry.getValue());
            curlRequest += " -H " + "'" + entry.getKey() + ": "+ entry.getValue() + "'" + " ";
        }

        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        StringBuffer body = new StringBuffer();

        if (httpResponse.getEntity() != null) {
            String inputLine;
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            while ((inputLine = reader.readLine()) != null) {
                body.append(inputLine);
            }
            reader.close();
        }

        httpClient.close();

        HashMap<String, String> tempResponseHeaders = new HashMap<>();

        Header[] responseHeaders = httpResponse.getAllHeaders();
        for (Header header : responseHeaders)
            tempResponseHeaders.put(header.getName(), header.getValue());

        Response response = new Response();
        response.setBody(body.toString());
        response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        response.setStatusLine(httpResponse.getStatusLine().toString());
        response.setHeaders(tempResponseHeaders);

        Logger.log("Time: " + System.currentTimeMillis());
        Logger.log(curlRequest);
        Logger.log("GET status code: " + response.getStatusCode());
        Logger.log("url: " + url);
        Logger.log("Response body: " + response.getBody());

        return response;
    }

    //    Making a GET request by providing url
    public static Response sendGET(String url) throws IOException {

        String curlRequest = "curl -X GET "+" " + url + " ";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        StringBuffer body = new StringBuffer();

        if (httpResponse.getEntity() != null) {
            String inputLine;
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            while ((inputLine = reader.readLine()) != null) {
                body.append(inputLine);
            }
            reader.close();
        }

        httpClient.close();

        HashMap<String, String> tempResponseHeaders = new HashMap<>();

        Header[] responseHeaders = httpResponse.getAllHeaders();
        for (Header header : responseHeaders)
            tempResponseHeaders.put(header.getName(), header.getValue());

        Response response = new Response();
        response.setBody(body.toString());
        response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        response.setStatusLine(httpResponse.getStatusLine().toString());
        response.setHeaders(tempResponseHeaders);

        Logger.log("Time: " + System.currentTimeMillis());
        Logger.log(curlRequest);
        Logger.log("GET status code: " + response.getStatusCode());
        Logger.log("url: " + url);
        Logger.log("Response body: " + response.getBody());

        return response;
    }


    //    Make post call with form data
    public static Response sendPOST(String url, StringEntity formData) throws IOException {

        String curlRequest = "curl -X POST "+" " + url + " ";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("content-type", "application/x-www-form-urlencoded");
        curlRequest += " -H " + "'" + "content-type" + ": "+ "application/x-www-form-urlencoded" + "'" + " ";

        httpPost.setEntity(formData);

        if (Objects.nonNull(formData)) {
            curlRequest += "-F " + "'"+formData+"'";
        }

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        StringBuffer body = new StringBuffer();

        if (httpResponse.getEntity() != null) {
            String inputLine;
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            while ((inputLine = reader.readLine()) != null) {
                body.append(inputLine);
            }
            reader.close();
        }

        httpClient.close();

        HashMap<String, String> tempResponseHeaders = new HashMap<>();

        Header[] responseHeaders = httpResponse.getAllHeaders();
        for (Header header : responseHeaders)
            tempResponseHeaders.put(header.getName(), header.getValue());

        Response response = new Response();
        response.setBody(body.toString());
        response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        response.setStatusLine(httpResponse.getStatusLine().toString());
        response.setHeaders(tempResponseHeaders);

        Logger.log("Time: " + System.currentTimeMillis());
        Logger.log(curlRequest);
        Logger.log("POST status code: " + response.getStatusCode());
        Logger.log("url: " + url);
        Logger.log("Response body: " + response.getBody());

        return response;
    }

    //    Making a delete request by providing headers and Host url
    public static Response sendDELETE(String url, Map<String, String> headers) throws IOException {

        String curlRequest = "curl -X DELETE "+" " + url + " ";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);

        for (Map.Entry<String, String> entry : headers.entrySet()){
            httpDelete.addHeader(entry.getKey(), entry.getValue());
            curlRequest += " -H " + "'" + entry.getKey() + ": "+ entry.getValue() + "'" + " ";
        }

        CloseableHttpResponse httpResponse = httpClient.execute(httpDelete);
        StringBuffer body = new StringBuffer();

        if (httpResponse.getEntity() != null) {
            String inputLine;
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            while ((inputLine = reader.readLine()) != null) {
                body.append(inputLine);
            }
            reader.close();
        }

        httpClient.close();

        HashMap<String, String> tempResponseHeaders = new HashMap<>();

        Header[] responseHeaders = httpResponse.getAllHeaders();
        for (Header header : responseHeaders)
            tempResponseHeaders.put(header.getName(), header.getValue());

        Response response = new Response();
        response.setBody(body.toString());
        response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        response.setStatusLine(httpResponse.getStatusLine().toString());
        response.setHeaders(tempResponseHeaders);

        Logger.log("Time: " + System.currentTimeMillis());
        Logger.log(curlRequest);
        Logger.log("DELETE status code: " + response.getStatusCode());
        Logger.log("url: " + url);
        Logger.log("Response body: " + response.getBody());

        return response;
    }

    public static Response sendPOST(String url) throws IOException {

        String curlRequest = "curl -X POST "+" " + url + " ";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        StringBuffer body = new StringBuffer();

        if (httpResponse.getEntity() != null) {
            String inputLine;
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            while ((inputLine = reader.readLine()) != null) {
                body.append(inputLine);
            }
            reader.close();
        }

        httpClient.close();

        HashMap<String, String> tempResponseHeaders = new HashMap<>();

        Header[] responseHeaders = httpResponse.getAllHeaders();
        for (Header header : responseHeaders)
            tempResponseHeaders.put(header.getName(), header.getValue());

        Response response = new Response();
        response.setBody(body.toString());
        response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        response.setStatusLine(httpResponse.getStatusLine().toString());
        response.setHeaders(tempResponseHeaders);

        Logger.log("Time: " + System.currentTimeMillis());
        Logger.log(curlRequest);
        Logger.log("POST status code: " + response.getStatusCode());
        Logger.log("url: " + url);
        Logger.log("Response body: " + response.getBody());

        return response;
    }

}
