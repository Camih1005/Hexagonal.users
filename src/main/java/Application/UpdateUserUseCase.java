package Application;

import Domain.Entity.User;
import Domain.Service.UserService;

public class UpdateUserUseCase {

    private final UserService userService;

    public UpdateUserUseCase(UserService userService) {
        this.userService = userService;
    }

    public User execute(Long id, String newName, String newEmail) {
        return userService.updateById(id, newName, newEmail);
    }
}
