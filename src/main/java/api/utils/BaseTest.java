package api.utils;

import helper.CommonHelpers;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.config.SSLConfig;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseTest {
    protected static RequestSpecification spec;

    @BeforeAll
    public static void setup() {
        RestAssured.config = RestAssured.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());

        // Читаем базовый URL и токен из конфигурации
        String baseUrl = CommonHelpers.getConfig("apiUrl");
        String authToken = CommonHelpers.getConfig("token");

        // Устанавливаем базовый URL
        RestAssured.baseURI = baseUrl;

        // Создаем спецификацию
        spec = new RequestSpecBuilder().setBaseUri(baseUrl).addHeader("Authorization", authToken).build();
    }

    public static <T> T getRequest(String path, Map<String, Object> queryParams, Class<T> responseClass){
        return given()
                .spec(BaseTest.spec)
                .queryParams(queryParams)
                .when()
                .get(path)
                .then()
                .statusCode(200)
                .extract()
                .as(responseClass);
    }
}
