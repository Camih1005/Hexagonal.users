package Infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Domain.Entity.User;
import Domain.Service.UserService;
import Infrastructure.Config.DataBaseConfig;

public class UserRepository implements UserService {

    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO users(name, email) VALUES (?, ?)";
        try (Connection connection = DataBaseConfig.getConecction();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail()); // Utiliza el índice 2 para el email

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserById(Long id) {
       String sql = "select id, name, email from users where id = ?";
       User user = null;
       try(Connection connection = DataBaseConfig.getConecction();PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setLong(1, id);
        try(ResultSet resultSet = statement.executeQuery()){
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
            }
        }
        
       } catch (SQLException e) {
        
        e.printStackTrace();
    }
    return user;
       
    }

    @Override
    public User updateById(Long id, String newName, String newEmail) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        try (Connection connection = DataBaseConfig.getConecction();PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newName);
            statement.setString(2, newEmail);
            statement.setLong(3, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return findUserById(id); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User deleteById(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = DataBaseConfig.getConecction();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("Usuario con ID " + id + " eliminado correctamente.");
          
            } else {
                System.out.println("No se encontró ningún usuario con ID " + id + " para eliminar.");
            }
    
        } catch (SQLException e) {
            System.out.println("error try : " + e);
    
        }
        
        return null;
    }

    @Override
    public User listUserbyName(String name) {
        String sql = "SELECT id, name, email FROM users WHERE name LIKE ?";
        try (Connection connection = DataBaseConfig.getConecction();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, "%" + name + "%"); 
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    return user; 
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar usuario por nombre: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null; 
    }
    
}