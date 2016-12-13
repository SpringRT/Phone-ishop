package ru.phone.ishop.message;


import ru.phone.ishop.dataBase.AbstractSqlManager;
import ru.phone.ishop.dataBase.SqlConnectionFactory;
import ru.phone.ishop.products.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlMessageManager extends AbstractSqlManager implements MessageManager{


    @Override
    public long addMessage(Message message) {
        try (Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "INSERT INTO messages (user_id, product_id, message,creation_timestamp) " +
                    "VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1,message.getUserId());
            statement.setLong(2,message.getProductId());
            statement.setString(3,message.getMessage());
            long creationTimestamp = System.currentTimeMillis();
            message.setCreationTimestamp(creationTimestamp);
            statement.setLong(4,creationTimestamp);
            statement.executeUpdate();
            long id =  getGeneratedId(statement);
            message.setId(id);
            return id;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Message> listMessages(long productId) {
        try(Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "SELECT m.id as id,m.user_id as user_id, m.product_id as product_id, m.message as message, m.creation_timestamp as creation_timestamp, u.login as user_name " +
                    "FROM messages m INNER JOIN users u ON m.user_id = u.id " +
                    "WHERE product_id = ? ORDER BY creation_timestamp DESC ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,productId);
            ResultSet rs =  statement.executeQuery();
            List<Message> messagesList = new ArrayList<>();
            while (rs.next()){
                Message message = new Message();
                message.setId(rs.getLong("id"));
                message.setUserId(rs.getLong("user_id"));
                message.setProductId(rs.getLong("product_id"));
                message.setMessage(rs.getString("message"));
                message.setCreationTimestamp(rs.getLong("creation_timestamp"));
                message.setUserName(rs.getString("user_name"));
                messagesList.add(message);
            }
            return  messagesList;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
