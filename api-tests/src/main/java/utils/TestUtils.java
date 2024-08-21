package utils;

import com.google.gson.JsonObject;

import static org.testng.AssertJUnit.assertEquals;

public class TestUtils {
    public static JsonObject createTokenRequestBody(String userName, String password) {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("userName", userName);
        requestBody.addProperty("password", password);
        return requestBody;
    }

    public static JsonObject createUserRequestBody(String userName, String password) {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("userName", userName);
        requestBody.addProperty("password", password);
        return requestBody;
    }
}