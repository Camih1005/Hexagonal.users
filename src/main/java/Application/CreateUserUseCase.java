package Application;

import Domain.Entity.User;
import Domain.Service.UserService;

public class CreateUserUseCase {

private final UserService userService;

public CreateUserUseCase(UserService userService){
    this.userService = userService;
}

public void execute(User user){
    userService.createUser(user);
}
}
