package Application;

import Domain.Entity.User;
import Domain.Service.UserService;

public class ListUserByName {

    private UserService userService;

    public ListUserByName(UserService userService) {
        this.userService = userService;
    }
    public User execute(String name){

         return userService.listUserbyName(name);
    }


}
