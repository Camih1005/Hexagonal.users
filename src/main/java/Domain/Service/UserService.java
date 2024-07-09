package Domain.Service;
import Domain.Entity.User;


public interface UserService {

    void createUser(User user);
    User findUserById(Long id);
    User updateById(Long id);
    // User DeteletByid(Long id);

}
