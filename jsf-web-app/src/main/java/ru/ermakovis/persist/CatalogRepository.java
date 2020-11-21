package ru.ermakovis.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class CatalogRepository {

    @Inject
    private ServletContext context;

    private Connection connection;

    @PostConstruct
    public void init() throws SQLException {
        connection = (Connection) context.getAttribute("jdbcConnection");
    }

    public void insert(CatalogItem catalogItem) throws SQLException {
        String sql = "INSERT INTO items(name, image, amount) values (?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, catalogItem.getName());
            statement.setString(2, catalogItem.getImage());
            statement.setInt(3, catalogItem.getAmount());
            statement.execute();
        }
    }

    public void update(CatalogItem storeItem) throws SQLException {
        String sql = "UPDATE items SET name = ?, image = ?, amount = ? where id = ?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, storeItem.getName());
            statement.setString(2, storeItem.getImage());
            statement.setInt(3, storeItem.getAmount());
            statement.setInt(4, storeItem.getId());
            statement.execute();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM items where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        }
    }

    public CatalogItem find(int id) throws SQLException {
        String sql = "SELECT id, name, image, amount FROM items WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new CatalogItem(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4));
            }
        }
        return new CatalogItem(-1, "", null, 0);
    }

    public List<CatalogItem> findAll() throws SQLException {
        String sql = "SELECT id, name, image, amount from items";
        List<CatalogItem> ret = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                ret.add(new CatalogItem(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4)));
            }
        }
        return ret;
    }
}
