package Infrastructure.In;

import java.util.Scanner;

import com.hexacamilo.Main;

import Application.CreateUserUseCase;
import Application.FoundUserUseCase;
import Domain.Entity.User;

public class UserController {
    private CreateUserUseCase createUserUseCase;
    private FoundUserUseCase foundUserUseCase;

    // Inyectar ambos casos de uso mediante el constructor
    public UserController(CreateUserUseCase createUserUseCase, FoundUserUseCase foundUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.foundUserUseCase = foundUserUseCase;
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Ingrese el nombre del usuario: ");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese el correo electrónico del usuario: ");
            String email = scanner.nextLine();

            User user = new User();
            user.setName(nombre);
            user.setEmail(email);

            createUserUseCase.execute(user);
            System.out.println("Usuario creado con éxito");
            Main.main(null);
        } catch (Exception e) {
            System.err.println("Error al crear el usuario: " + e.getMessage());
       
        }
    }

    public void findUserById() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Ingrese el ID del usuario a buscar: ");
            Long id = scanner.nextLong(); 

            User user = foundUserUseCase.execute(id); 

            if (user != null) {
                System.out.println("Usuario encontrado:");
                System.out.println("ID: " + user.getId());
                System.out.println("Nombre: " + user.getName());
                System.out.println("Correo electrónico: " + user.getEmail());
                 Main.main(null);
            } else {
                System.out.println("No se encontró ningún usuario con el ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Error al buscar el usuario: " + e.getMessage());
          
        }
    }

    public void updateById() {
        try(Scanner scanner = new Scanner(System.in)) {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
