package ru.phone.ishop.users;

import ru.phone.ishop.dataBase.AbstractSqlManager;
import ru.phone.ishop.dataBase.SqlConnectionFactory;

import java.sql.*;

public class SqlUserManager extends AbstractSqlManager implements UserManager {


    public long addUser(User user) {
        try (Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "INSERT INTO users (login,password,email,admin) VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,user.getLogin());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getEmail());
            statement.setBoolean(4,user.isAdmin());
            statement.executeUpdate();
            long id =  getGeneratedId(statement);
            user.setId(id);
            return id;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean existsLogin(String login) {
        try(Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "SELECT * FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet rs =  statement.executeQuery();
            if (rs.next()){
                return true;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean existsEmail(String email) {
        try(Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs =  statement.executeQuery();
            if (rs.next()){
                return true;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return false;
    }

    public User getUser(String login, String password) {
        try(Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "SELECT id, login, password, email, admin FROM users WHERE login = ? and password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs =  statement.executeQuery();
            if (rs.next()){                User user = new User();
                user.setId(rs.getLong("id"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAdmin(rs.getBoolean("admin"));
                return user;
            }

        }catch (Exception e){
            throw new RuntimeException(e);

        }
        return null;
    }

    public User getUser(long userId) {
        try(Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "SELECT id, login, password, email, admin FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,userId);
            ResultSet rs =  statement.executeQuery();
            if (rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAdmin(rs.getBoolean("admin"));
                return user;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }


}
