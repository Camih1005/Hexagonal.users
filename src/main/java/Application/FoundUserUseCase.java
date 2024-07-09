package Application;

import Domain.Entity.User;
import Domain.Service.UserService;

public class FoundUserUseCase {

    private final UserService userService;

    public FoundUserUseCase(UserService userService) {
        this.userService = userService;
    }

    public User execute(Long id) {
        return userService.findUserById(id);
    }
}
