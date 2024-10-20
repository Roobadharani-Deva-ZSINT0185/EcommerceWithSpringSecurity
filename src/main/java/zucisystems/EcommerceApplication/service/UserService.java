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
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user){
        user.setPassword(getEncodedPassword(user.getPassword()));

        // Assign roles (assume roles exist in the database)
        Set<Role> roles = user.getRoles().stream()
                .map(roleDto -> roleRepository.findByRoleName(roleDto.getRoleName()))
                .collect(Collectors.toSet());
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }

}
