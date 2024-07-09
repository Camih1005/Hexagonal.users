package Application;

import Domain.Service.UserService;

public class UpdateUserUseCase {

   private final UserService userService;

public UpdateUserUseCase(UserService userService) {
    this.userService = userService;
}

public void execute(Long id){
    userService.findUserById(id);
}
   
}
