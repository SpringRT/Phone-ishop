package ru.phone.ishop.dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractSqlManager {
    protected long getGeneratedId(PreparedStatement statement) throws SQLException{
        ResultSet generatedKeys = statement.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getLong(1);
    }
}
