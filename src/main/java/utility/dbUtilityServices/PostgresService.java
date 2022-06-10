package main.java.utility.dbUtilityServices;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.*;

public class PostgresService {
    private final String host;
    private final String username;
    private final String password;

    private Connection con;
    private ResultSet result;

    public PostgresService(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public PostgresService connectDb(String db) {
        String connectionUrl = String.format("jdbc:postgresql://%s:5432/%s", host, db);

        try {
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection(connectionUrl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public PostgresService executeQuery(String query) {
        try {
            Statement stmt = this.con.createStatement();
            this.result = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public JsonArray getAsJson() {
        JsonArray array = new JsonArray();
        try {
            ResultSetMetaData metaData = this.result.getMetaData();
            while (this.result.next()) {
                JsonObject object = new JsonObject();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    object.addProperty(metaData.getColumnName(i), result.getString(i));
                }
                array.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return array;
    }

    public JsonObject getFirst() {
        return getAsJson().get(0).getAsJsonObject();
    }

    public void closeConnection() {
        try {
            this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
