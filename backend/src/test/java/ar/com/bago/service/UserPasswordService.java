package ar.com.bago.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserPasswordService {

    private PasswordEncoder passwordEncoder;

    public UserPasswordService() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public static void main(String[] args) {
        UserPasswordService userPasswordGenerator = new UserPasswordService();
        System.out.println(userPasswordGenerator.encodePassword("admin"));
    }
}
