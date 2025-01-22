package tests.api;

import api.utils.Organization;
import helper.DatabaseUtils;
import models.OrganizationResponse;
import api.utils.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ТК_А11Test extends BaseTest {

    private int limit;
    private int offset;
    private String updatedAtFrom;
    private String updatedAtTo;

    @BeforeEach
    public void generateTestData() {
        // Генерация данных
        limit = new Random().nextInt(10) + 1;
        offset = new Random().nextInt(10);
        LocalDate now = LocalDate.now();
        updatedAtFrom = now.minusYears(2).plusDays(new Random().nextInt(365)).toString(); // Рандомная дата за последние 2 года
        updatedAtTo = now.toString(); // Сегодняшняя дата
    }

    @Test
    public void testGetOrganizations() {

        Response response = Organization.getOrganizations(limit, offset, updatedAtFrom, updatedAtTo);
        assertThat(response.getStatusCode()).isEqualTo(200);
        OrganizationResponse organizationResponse = response.as(OrganizationResponse.class);

        // Проверка, что вернулся список организаций
        assertThat(organizationResponse.getResults()).isNotEmpty();

        // Проверка фильтрации: лимит и офсет
        assertThat(organizationResponse.getResults()).hasSize(limit);
        assertThat(organizationResponse.getResults().get(0).getId()).isEqualTo(offset + 1);


        // Проверка дат обновления
        organizationResponse.getResults().forEach(organization -> {
            LocalDate updatedAt = OffsetDateTime.parse(organization.getUpdatedAt()).toLocalDate();
            assertThat(updatedAt).isBetween(LocalDate.parse(updatedAtFrom), LocalDate.parse(updatedAtTo));
        });
    }

    @Test
    public void testGetOrganizationsWithCheckDb() {

        Response response = Organization.getOrganizations(1,0, updatedAtFrom, updatedAtTo);

        assertThat(response.getStatusCode()).isEqualTo(200);

        OrganizationResponse organizationResponse = response.as(OrganizationResponse.class);

        assertThat(organizationResponse.getResults()).hasSizeLessThanOrEqualTo(limit);
        String inn = organizationResponse.getResults().get(0).getTin();

        // Получаем данные из базы
        List<OrganizationResponse.Organization> dbOrganizations = DatabaseUtils.getOrganizationsFromDb(inn);

        // Проверяем, что данные из API совпадают с данными из базы
        assertThat(organizationResponse.getResults())
                .usingRecursiveComparison()
                .ignoringFields("uuid", "updatedAt", "siteUrl", "formatId", "form", "isBodyEmpty", "id")
                .withComparatorForFields(
                (o1, o2) -> {
                    if (o1 == null || o2 == null) {
                        return o1 == null && o2 == null || "".equals(o1) || "".equals(o2) ? 0 : 1;
                    }
                    return ((String) o1).compareTo((String) o2);
                },
                "description" // Указываем конкретное поле
        )
                .isEqualTo(dbOrganizations);
    }
}
