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
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return user;
       
    }

    @Override
    public User updateById(Long id) {
        String sql = "UPDATE users" + 
                        "SET  = ?" + 
                        "where id =  ? ";


        return null;
    }
    
}
