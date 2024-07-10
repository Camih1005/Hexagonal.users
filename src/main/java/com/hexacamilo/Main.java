package com.hexacamilo;

import java.util.Scanner;

import Application.CreateUserUseCase;
import Application.DeleteUserUseCase;
import Application.FoundUserUseCase;
import Application.ListUserByName;
import Application.UpdateUserUseCase;
import Domain.Service.UserService;
import Infrastructure.In.UserController;
import Infrastructure.out.UserRepository;

public class Main {
    
    public static void main(String[] args) throws Exception{
        UserService userService = new UserRepository();
        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userService);
        FoundUserUseCase foundUserUseCase = new FoundUserUseCase(userService);
        UpdateUserUseCase updateUserUseCase = new UpdateUserUseCase(userService);
        DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(userService);
        ListUserByName listUserByName = new ListUserByName(userService);
        UserController userController = new UserController(createUserUseCase, foundUserUseCase,updateUserUseCase,deleteUserUseCase,listUserByName);
       
        
            

            //while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("-*-*- PRUEBA CRUD DB -*-*-*");
                System.out.println("1. Ingresar usuario nuevo");
                System.out.println("2. Buscar usuario por id");
                System.out.println("3. Actualizar usuario");
                System.out.println("4. Eliminar Usuario");
                System.out.println("5. Listar usuarios por nombre o coincidencia");
                System.out.println("6. Salir del programa");

                try{

                //int choice = scanner.nextInt();
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        userController.start();
                        break;
                    case 2:
                        userController.findUserById();
                        break;
                    case 3:
                        userController.updateById();
                        break;
                        case 4:
                        userController.deleteById();
                        break; 
                        case 5:
                        userController.listUserbyName();
                        break;
                        case 6:
                        System.out.println("SALIENDO...");
                        return;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                        break;
                }
                
            } catch (Exception e) {
                System.out.println("Error: " + e);
                scanner.next();
            }
                
            }
        
    
}
