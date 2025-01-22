package api.utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Organization {
    public static Response getOrganizations(int limit, int offset, String updatedAtFrom, String updatedAtTo) {
        return given()
                .spec(BaseTest.spec) // Используем спецификацию
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .queryParam("updated_at_from", updatedAtFrom)
                .queryParam("updated_at_to", updatedAtTo)
                .when()
                .get()
                .then()
                .extract()
                .response();
    }
}
