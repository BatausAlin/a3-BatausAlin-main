package repository;

import domain.Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseRepository<T extends Entity> extends Repository<T> {
    private final String url;
    private final String username;
    private final String password;

    public DatabaseRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public void add(T item) {
        // Implementația pentru adăugarea în baza de date
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SQL pentru adăugare")) {
            // Setează parametrii și execută statement-ul
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(T item) {
        String sql = "DELETE FROM your_table_name WHERE id = ?"; // Înlocuiește 'your_table_name' cu numele tabelului tău

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, item.getId()); // Presupunând că 'getId()' returnează ID-ul entității
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Implementează și alte metode necesare (getAll, findById, etc.)

    // Restul codului...
}

