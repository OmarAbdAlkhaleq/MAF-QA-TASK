package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {

    public static RequestSpecification req;

    public RequestSpecification requestSpecification() throws IOException
    {
            RequestSpecification req = new RequestSpecBuilder()
                    .setBaseUri(getGlobalValue("baseUrl")).build();

            return req;
    }


    public static String getGlobalValue(String key) throws IOException
    {
        Properties prop =new Properties();
        FileInputStream file =new FileInputStream("src/test/java/resources/global.properties");
        prop.load(file);
        return prop.getProperty(key);
    }
}
