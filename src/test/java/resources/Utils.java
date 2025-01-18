package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {

    public static RequestSpecification requestSpec;

    public RequestSpecification requestSpecification() throws IOException {

        if (requestSpec == null) {

            PrintStream printStream = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(printStream))
                    .addFilter(ResponseLoggingFilter.logResponseTo(printStream))
                    .setContentType(ContentType.JSON).build();

            return requestSpec;
        }

        return requestSpec;
    }

    public static String getGlobalValue(String property) throws IOException {
        Properties properties = new Properties();
        FileInputStream fInputStream = new FileInputStream("src\\test\\java\\resources\\global.properties");
        properties.load(fInputStream);
        return properties.getProperty(property);
    }

}
