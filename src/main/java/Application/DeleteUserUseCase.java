package Application;

import Domain.Entity.User;
import Domain.Service.UserService;

public class DeleteUserUseCase {
private UserService userService;

public DeleteUserUseCase(UserService userService) {
    this.userService = userService;
}
public User execute(Long id) {
        return userService.deleteById(id);
    }

}
