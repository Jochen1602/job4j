package ru.job4j.magnit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreSQL implements AutoCloseable {
    private Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
    }

    public void generate(int size, Config conf) throws Exception {
        config = conf;
        connect = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/db/magnit.db");
        connect.setAutoCommit(false);
        PreparedStatement statement = connect.prepareStatement("CREATE TABLE IF NOT EXISTS entry " +
                "(field INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL);");
        statement.executeUpdate();
        try (PreparedStatement prepStmt = connect.prepareStatement("INSERT INTO entry (field) VALUES (?)")) {
            for (int i = 0; i <= size; i++) {
                prepStmt.setInt(1, i);
                prepStmt.addBatch();
                if (i / 1000 == 0) {
                    prepStmt.executeBatch();
                }
            }
            prepStmt.executeBatch();
            connect.commit();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        connect.setAutoCommit(true);
    }

    public List<XmlUsage.Field> load() throws SQLException {
        connect = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/db/magnit.db");
        List<XmlUsage.Field> list = new ArrayList<>();
        connect.setAutoCommit(false);
        try (PreparedStatement prepStmt = connect.prepareStatement("SELECT * FROM entry"); ResultSet rsc = prepStmt.executeQuery()) {
            while (rsc.next()) {
                list.add(new XmlUsage.Field(Integer.parseInt(rsc.getString("field"))));
            }
            connect.commit();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        connect.setAutoCommit(true);
        return list;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}