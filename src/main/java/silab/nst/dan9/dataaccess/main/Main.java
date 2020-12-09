/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package silab.nst.dan9.dataaccess.main;

import silab.nst.dan9.dataaccess.domain.User;
import silab.nst.dan9.dataaccess.repository.UserRepository;

import java.util.List;

/**
 * @author laptop-02
 */
public class Main {

    static UserRepository userRepository = new UserRepository();

    public static void main(String[] args) {

        User u1 = new User(1L, "Pera", "Peric", "user_pera", "pass");
        User u2 = new User(2L, "Zika", "Zikic", "user_zika", "password");
        try {
            saveUser(u1);
            saveUser(u2);

            u1.setFirstname("PeraUpdate");
            updateUser(u1);

            getAllUsers().forEach(u -> {
                try {
                    deleteUser(u);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void saveUser(User user) throws Exception {
        userRepository.add(user);
    }

    private static void deleteUser(User user) throws Exception {
        userRepository.delete(user);
    }

    private static void updateUser(User user) throws Exception {
        userRepository.update(user);
    }

    private static List<User> getAllUsers() throws Exception {
        return userRepository.getAll();
    }
}
