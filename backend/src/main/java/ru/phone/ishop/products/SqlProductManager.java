package ru.phone.ishop.products;

import ru.phone.ishop.dataBase.AbstractSqlManager;
import ru.phone.ishop.dataBase.SqlConnectionFactory;

import javax.sql.rowset.serial.SerialArray;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlProductManager extends AbstractSqlManager implements ProductManager {

    @Override
    public long addProduct(Product product) {
        try (Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "INSERT INTO products (name, short_description, full_description, price, creation_timestamp, brand  ) " +
                    "VALUES(?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,product.getName());
            statement.setString(2,product.getShortDescription());
            statement.setString(3,product.getFullDescription());
            statement.setInt(4,product.getPrice());
            long creationTimestamp = System.currentTimeMillis();
            product.setCreationTimestamp(creationTimestamp);
            statement.setLong(5,creationTimestamp);
            statement.setString(6, product.getBrand());
            statement.executeUpdate();
            long id =  getGeneratedId(statement);
            product.setId(id);
            return id;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getProduct(long productId) {
        try(Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "SELECT id, name, short_description, full_description, price, brand, creation_timestamp FROM products WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,productId);
            ResultSet rs =  statement.executeQuery();
            if (rs.next()){
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setShortDescription(rs.getString("short_description"));
                product.setFullDescription(rs.getString("full_description"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getInt("price"));
                product.setCreationTimestamp(rs.getLong("creation_timestamp"));
                return product;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void updateProduct(Product product) {
        try (Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "UPDATE products SET name=?, short_description=?, full_description=?, price=?, brand=? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,product.getName());
            statement.setString(2,product.getShortDescription());
            statement.setString(3,product.getFullDescription());
            statement.setInt(4,product.getPrice());
            statement.setString(5, product.getBrand());
            statement.setLong(6, product.getId());
            statement.executeUpdate();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduct(long productId) {
        try (Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "DELETE FROM products WHERE id = ?" ;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,productId);
            statement.executeUpdate();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getProductCount() {
        try(Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "SELECT count(*) as products_count FROM products";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs =  statement.executeQuery();
            rs.next();
            return rs.getInt("products_count");

        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> listOrderedProductsByCreationTimestamp(int count, int offset) {
        try(Connection connection = SqlConnectionFactory.createNewConnection()){
            String sql = "SELECT id, name, short_description, full_description, price, brand, creation_timestamp FROM products ORDER BY creation_timestamp desc limit ?,?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,offset);
            statement.setInt(2,count);
            ResultSet rs =  statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setShortDescription(rs.getString("short_description"));
                product.setFullDescription(rs.getString("full_description"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getInt("price"));
                product.setCreationTimestamp(rs.getLong("creation_timestamp"));
                products.add(product);
            }
            return  products;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }



    @Override
    public List<Product> listOrderedProductsByCreationTimestamp(List<Long> productIdList) {
        if (productIdList.isEmpty()){
            return new ArrayList<>();
        }
        try(Connection connection = SqlConnectionFactory.createNewConnection()){
            String[] params = new String[productIdList.size()];
            Arrays.fill(params, "?");
            String str = String.join(",", params);
            String sql = "SELECT id, name, short_description, full_description, price, brand, creation_timestamp FROM products WHERE id IN (" + str + ") ORDER BY creation_timestamp desc";
            PreparedStatement statement = connection.prepareStatement(sql);
            for(int i = 0; i < productIdList.size(); i++){
                statement.setLong(i+1, productIdList.get(i));
            }
            ResultSet rs =  statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setShortDescription(rs.getString("short_description"));
                product.setFullDescription(rs.getString("full_description"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getInt("price"));
                product.setCreationTimestamp(rs.getLong("creation_timestamp"));
                products.add(product);
            }
            return  products;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}