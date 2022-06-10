package main.java.pentagon.utility;

import main.java.config.environment.Environment;
import main.java.core.Logger;

public class PentagonUtility {

    public static String getPentagonVar(String var) {
        String value = null;
        try {
            value = Environment.envVars.getAsJsonObject("pentagon").get(var).getAsString();
        } catch (NullPointerException e) {
            Logger.log("There is no var defined as \"" + var + "\"");
        }
        return value;
    }
}
