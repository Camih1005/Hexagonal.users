package Domain.Service;
import Domain.Entity.User;


public interface UserService {

    void createUser(User user);
    User findUserById(Long id);
    User updateById(Long id, String newName, String newEmail);
    User deleteById(Long id);
    User listUserbyName(String name);
    // User DeteletByid(Long id);

}
