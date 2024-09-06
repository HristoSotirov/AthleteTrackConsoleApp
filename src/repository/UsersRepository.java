package repository;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersRepository {

    private Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

}
