package tests.api;

import helper.CommonHelpers;
import helper.DatabaseUtils;
import models.OrganizationResponse;
import api.utils.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ТК_А11Test extends BaseTest {

    private int limit;
    private int offset;
    private String updatedAtFrom;
    private String updatedAtTo;
    private String path = CommonHelpers.getConfig("path_organization");

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

        Map<String, Object> params = new HashMap<>();
        params.put("limit", limit);
        params.put("offset", offset);
        params.put("updated_at_from", updatedAtFrom);
        params.put("updated_at_to", updatedAtTo);

        OrganizationResponse response = BaseTest.getRequest(path, params, OrganizationResponse.class);

        // Проверка, что вернулся список организаций
        assertThat(response.getResults()).isNotEmpty();

        // Проверка фильтрации: лимит и офсет
        assertThat(response.getResults()).hasSize(limit);
        response.getResults().forEach(organization -> {
            if (organization.getId() >= offset) {
                System.err.println("Ошибка: ID " + organization.getId() + " больше или равен offset " + offset);
            }
            assertThat(organization.getId()).isLessThan(offset);
        });


        // Проверка дат обновления
        response.getResults().forEach(organization -> {
            LocalDate updatedAt = OffsetDateTime.parse(organization.getUpdatedAt()).toLocalDate();
            assertThat(updatedAt).isBetween(LocalDate.parse(updatedAtFrom), LocalDate.parse(updatedAtTo));
        });
    }

    @Test
    public void testGetOrganizationsWithCheckDb() {

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 1);
        params.put("offset", 0);
        params.put("updated_at_from", updatedAtFrom);
        params.put("updated_at_to", updatedAtTo);

        OrganizationResponse response = BaseTest.getRequest(path, params, OrganizationResponse.class);

        assertThat(response.getResults()).hasSizeLessThanOrEqualTo(limit);
        String inn = response.getResults().get(0).getTin();

        // Получаем данные из базы
        List<OrganizationResponse.Organization> dbOrganizations = DatabaseUtils.getOrganizationsFromDb(inn);

        // Проверяем, что данные из API совпадают с данными из базы
        assertThat(response.getResults())
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
