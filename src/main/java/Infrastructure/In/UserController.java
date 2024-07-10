package Infrastructure.In;

import java.util.List;
import java.util.Scanner;

import Application.CreateUserUseCase;
import Application.DeleteUserUseCase;
import Application.FoundUserUseCase;
import Application.ListUserByName;
import Application.UpdateUserUseCase;
import Domain.Entity.User;

public class UserController {
    private CreateUserUseCase createUserUseCase;
    private FoundUserUseCase foundUserUseCase;
    private UpdateUserUseCase updateUserUseCase;
    private DeleteUserUseCase deleteUserUseCase;
    private ListUserByName listUserByName;

    public UserController(CreateUserUseCase createUserUseCase, FoundUserUseCase foundUserUseCase,
            UpdateUserUseCase updateUserUseCase, DeleteUserUseCase deleteUserUseCase, ListUserByName listUserByName) {
        this.createUserUseCase = createUserUseCase;
        this.foundUserUseCase = foundUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.listUserByName = listUserByName;
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Ingrese el nombre del usuario:");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese el correo electrónico del usuario:");
            String email = scanner.nextLine();

            User user = new User();
            user.setName(nombre);
            user.setEmail(email);

            createUserUseCase.execute(user);
            System.out.println("Usuario creado exitosamente");
        } catch (Exception e) {
            System.err.println("Error al crear el usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void findUserById() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Ingrese el ID del usuario a buscar:");
            Long id = scanner.nextLong();
            scanner.nextLine(); 

            User user = foundUserUseCase.execute(id);

            if (user != null) {
                System.out.println("Usuario encontrado:");
                System.out.println("ID: " + user.getId());
                System.out.println("Nombre: " + user.getName());
                System.out.println("Correo electrónico: " + user.getEmail());
            } else {
                System.out.println("No se encontró ningún usuario con el ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Error al buscar el usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateById() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Ingrese el ID del usuario a actualizar:");
            Long id = scanner.nextLong();
            scanner.nextLine(); 

            User user = foundUserUseCase.execute(id);
            if (user != null) {
                System.out.println("Usuario encontrado:");
                System.out.println("ID: " + user.getId());
                System.out.println("Nombre: " + user.getName());
                System.out.println("Correo electrónico: " + user.getEmail());

                System.out.println("¿Qué deseas modificar del usuario?");
                System.out.println("1. Nombre");
                System.out.println("2. Email");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        System.out.println("Ingresa el nuevo nombre:");
                        String newName = scanner.nextLine();
                        User updatedUser = updateUserUseCase.execute(id, newName, user.getEmail());
                        if (updatedUser != null) {
                            System.out.println("Nombre actualizado a: " + updatedUser.getName());
                        } else {
                            System.out.println("Error al actualizar el nombre.");
                        }
                        break;
                    case 2:
                        System.out.println("Ingresa el nuevo email:");
                        String newEmail = scanner.nextLine();
                        updatedUser = updateUserUseCase.execute(id, user.getName(), newEmail);
                        if (updatedUser != null) {
                            System.out.println("Email actualizado a: " + updatedUser.getEmail());
                        } else {
                            System.out.println("Error al actualizar el email.");
                        }
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } else {
                System.out.println("No se encontró ningún usuario con el ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteById() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Ingrese el ID del usuario a eliminar:");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Consumir la nueva línea después de nextLong()

            User userFound = foundUserUseCase.execute(id);
            if (userFound != null) {
                System.out.println("Usuario a eliminar:");
                System.out.println("ID: " + userFound.getId());
                System.out.println("Nombre: " + userFound.getName());
                System.out.println("Correo electrónico: " + userFound.getEmail());

                System.out.println("¿Estás seguro que deseas eliminar el usuario?");
                System.out.println("1. Sí");
                System.out.println("2. No");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea después de nextInt()

                switch (choice) {
                    case 1:
                        User deletedUser = deleteUserUseCase.execute(id);
                        if (deletedUser != null) {
                            System.out.println("Usuario eliminado con éxito.");
                        } else {
                            System.out.println("Error al eliminar el usuario.");
                        }
                        break;
                    case 2:
                        System.out.println("Operación de eliminación cancelada.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } else {
                System.out.println("No se encontró ningún usuario con el ID: " + id);
            }

        } catch (Exception e) {
            System.err.println("Error al eliminar el usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listUserbyName() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Ingrese el nombre del usuario a buscar:");
            String nombre = scanner.nextLine();

          
            User userList = listUserByName.execute(nombre);

            if (!userList.isEmpty()) {
                System.out.println("Usuarios encontrados con el nombre '" + nombre + "':");
                for (User user : userList) {
                    System.out.println("ID: " + user.getId());
                    System.out.println("Nombre: " + user.getName());
                    System.out.println("Correo electrónico: " + user.getEmail());
                    System.out.println("---------------------------");
                }
            } else {
                System.out.println("No se encontraron usuarios con el nombre '" + nombre + "'.");
            }

        } catch (Exception e) {
            System.err.println("Error al buscar usuarios por nombre: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
