package ru.ermakovis.webapp.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreItemRepository {
    private static final Logger logger = LoggerFactory.getLogger(StoreItemRepository.class);
    private final Connection connection;

    public StoreItemRepository(Connection connection) {
        logger.info("StoreItemRepository created");
        this.connection = connection;
    }

    //Why not just use String.format(...items(%s, %s, %d)) ?
    public void insert(StoreItem storeItem) throws SQLException {
        logger.info("insert call");
        String sql = "INSERT INTO items(name, image, amount) values (?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, storeItem.getName());
            statement.setString(2, storeItem.getImage());
            statement.setInt(3, storeItem.getAmount());
            statement.execute();
        }
    }

    public void update(StoreItem storeItem) throws SQLException {
        logger.info("update call");
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
        logger.info("delete call");
        String sql = "DELETE FROM items where id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        }
    }

    public StoreItem find(int id) throws SQLException {
        logger.info("find call");
        String sql = "SELECT id, name, image, amount FROM items WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new StoreItem(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4));
            }
        }
        return new StoreItem(-1, "", null, 0);
    }

    public List<StoreItem> findAll() throws SQLException {
        logger.info("find all call");
        String sql = "SELECT id, name, image, amount from items";
        List<StoreItem> ret = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                ret.add(new StoreItem(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4)));
            }
        }
        return ret;
    }
}
