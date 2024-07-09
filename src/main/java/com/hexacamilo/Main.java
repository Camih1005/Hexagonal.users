package com.hexacamilo;

import java.util.Scanner;

import Application.CreateUserUseCase;
import Application.FoundUserUseCase;
import Domain.Service.UserService;
import Infrastructure.In.UserController;
import Infrastructure.out.UserRepository;

public class Main {
    
    public static void main(String[] args) throws Exception{
        UserService userService = new UserRepository();
        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userService);
        FoundUserUseCase foundUserUseCase = new FoundUserUseCase(userService);
        UserController userController = new UserController(createUserUseCase, foundUserUseCase);
       
        
            

            //while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("-*-*- PRUEBA CRUD DB -*-*-*");
                System.out.println("1. Ingresar usuario nuevo");
                System.out.println("2. Buscar usuario por id");
                System.out.println("3. Salir");

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
                        System.out.println("Saliendo...");
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
