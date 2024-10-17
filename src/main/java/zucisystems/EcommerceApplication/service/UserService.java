package zucisystems.EcommerceApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zucisystems.EcommerceApplication.entity.Role;
import zucisystems.EcommerceApplication.entity.User;
import zucisystems.EcommerceApplication.repository.RoleRepository;
import zucisystems.EcommerceApplication.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user){
        return userRepository.save(user);
    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }

}
