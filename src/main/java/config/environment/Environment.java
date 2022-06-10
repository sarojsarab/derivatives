package main.java.config.environment;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;

import java.io.File;
import java.io.FileReader;

@Getter
public class Environment {

    public static EnvironmentType environment;

    public static JsonObject envVars;

    public static void prepareEnvironment(EnvironmentType environmentType) throws Exception {

        switch (environmentType) {

            case DEV:
                //Extracting the path of configurations package
                envVars = readEnvironmentJson("DEV");
                break;

            case QA:
                //Extracting the path of configurations package
                envVars = readEnvironmentJson("QA");
                break;

            default:
                throw new Exception("Undefined environment");

        }
    }

    private static JsonObject readEnvironmentJson(String environment) throws Exception {
        String path = Environment.class.getClassLoader().getResource("configurations").getPath();
        JsonElement element = new JsonParser().parse(new FileReader(path + File.separator + "Derivative.json"));
        return element.getAsJsonObject().getAsJsonObject(environment);
    }

}
