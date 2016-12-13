package ru.phone.ishop.bucket;

import ru.phone.ishop.dataBase.AbstractSqlManager;
import ru.phone.ishop.dataBase.SqlConnectionFactory;
import ru.phone.ishop.products.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlBucketManager extends AbstractSqlManager implements BucketManager {


    @Override
    public boolean addProductToBucket(long userId, long productId) {
        try (Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "INSERT INTO buckets (user_id, product_id) VALUES(?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,userId);
            statement.setLong(2,productId);
            statement.executeUpdate();
            return true;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteProductFromBucket(long userId, long productId) {
        try (Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "DELETE FROM buckets WHERE user_id = ? and product_id=?" ;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,userId);
            statement.setLong(2,productId);
            statement.executeUpdate();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public int getProductsCountInBucket(long userId) {
        try(Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "SELECT count(*) as products_count FROM buckets WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
            ResultSet rs =  statement.executeQuery();
            rs.next();
            return rs.getInt("products_count");

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Long> listProductsFromBucket(long userId) {
        try(Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "SELECT product_id FROM buckets WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,userId);
            ResultSet rs =  statement.executeQuery();
            List<Long> productIdList = new ArrayList<>();
            while (rs.next()){
                productIdList.add(rs.getLong("product_id"));
            }
            return  productIdList;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean payOrder(long userId) {
        try (Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "DELETE FROM buckets WHERE user_id = ?" ;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,userId);
            statement.executeUpdate();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return true;
    }
}
