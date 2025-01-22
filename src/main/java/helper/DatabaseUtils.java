package helper;

import models.OrganizationResponse.Organization;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {

    private static final String DB_URL = CommonHelpers.getConfig("dbUrl");
    private static final String DB_USER = CommonHelpers.getConfig("dbUser");
    private static final String DB_PASSWORD = CommonHelpers.getConfig("dbPass");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static List<Organization> getOrganizationsFromDb(String inn) {
        String query = "SELECT name, inn, description, region, base_year " +
                "FROM accounts WHERE inn=?";

        List<Organization> organizations = new ArrayList<>();

        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, inn);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Organization organization = new Organization();
                organization.setTitle(resultSet.getString("name"));
                organization.setTin(resultSet.getString("inn"));
                organization.setDescription(resultSet.getString("description"));
                organization.setRegionId(resultSet.getInt("region"));
                organization.setBaseYear(resultSet.getString("base_year"));
                organizations.add(organization);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Organizations from DB: " + organizations.size());

        return organizations;
    }
}
