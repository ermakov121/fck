package api.utils;

import helper.CommonHelpers;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.config.SSLConfig;

public class BaseTest {
    protected static RequestSpecification spec;

    @BeforeAll
    public static void setup() {
        RestAssured.config = RestAssured.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());

        // Читаем базовый URL и токен из конфигурации
        String baseUrl = CommonHelpers.getConfig("apiUrl");
        String apiPath = CommonHelpers.getConfig("path");
        String authToken = CommonHelpers.getConfig("token");

        // Устанавливаем базовый URL
        RestAssured.baseURI = baseUrl;

        // Создаем спецификацию
        spec = new RequestSpecBuilder().setBaseUri(baseUrl).setBasePath(apiPath).addHeader("Authorization", authToken).build();
    }
}
