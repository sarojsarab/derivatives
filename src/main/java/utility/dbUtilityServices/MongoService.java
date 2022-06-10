package main.java.utility.dbUtilityServices;

import com.google.gson.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class MongoService {

    private MongoClient client = null;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    private String uri;
    private String dbName;
    private List<Bson> filter = new ArrayList<>();
    private List<Document> result = new ArrayList<>();

    public MongoService(String uri, String db) {
        this.uri = uri;
        this.dbName = db;
    }

    public MongoService(String uri) {
        this.uri = uri;
    }

    public MongoService() {
    }

    public MongoService connect() {
        if (client == null) {
            client = new MongoClient(new MongoClientURI(uri));
        }
        this.database = client.getDatabase(dbName);
        return this;
    }

    public MongoService connect(String db) {
        this.dbName = db;
        if (client == null) {
            client = new MongoClient(new MongoClientURI(uri));
        }
        this.database = client.getDatabase(dbName);
        return this;
    }

    public MongoService getTable(String tableName) {
        try {
            this.collection = this.database.getCollection(tableName);
            this.result = this.collection.find().into(new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public MongoService getByUserId(String userId) {
        ObjectId user = new ObjectId(userId);
        this.result = this.collection.find(and(eq("user", user)))
                .into(new ArrayList<>());
        return this;
    }

    public MongoService getById(String id) {
        ObjectId docId = new ObjectId(id);
        this.result = this.collection.find(eq("_id", docId))
                .into(new ArrayList<>());
        return this;
    }

    public MongoService query(String key, Object value) {
        Bson eq = eq(key, value);
        this.filter.add(eq);
        return this;
    }

    public MongoService queryIn(String key, String... values) {
        Bson in = in(key, values);
        this.filter.add(in);
        return this;
    }

    public MongoService executeQuery() {
        this.result = this.collection.find(and(this.filter)).into(new ArrayList<>());
        this.filter.clear();
        return this;
    }

    public MongoCollection<Document> getCollection() {
        return collection;
    }

    public JsonElement getAsJson() {
        JsonArray json = new JsonArray();
        this.result.forEach(doc -> json.add(new JsonParser().parse(doc.toJson())));
        this.result.clear();
        return json;
    }

    public List<String> getAsString() {
        List<String> docs = new ArrayList<>();
        this.result.forEach(doc -> docs.add(doc.toJson()));
        this.result.clear();
        return docs;
    }

    public JsonObject getFirst() {
        JsonObject single = new JsonParser().parse(this.result.get(0).toJson()).getAsJsonObject();
        this.result.clear();
        return single;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setDb(String db) {
        this.dbName = db;
    }

    public void closeConnection(){
        client.close();
        this.client = null;
    }
}
