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
//    @Autowired
//    private RoleRepository roleRepository;
//
//    public void addInitialValues(){
//        Role userRole=new Role();
//        userRole.setRoleName("User");
//        userRole.setRoleDescription("this is user");
//        roleRepository.save(userRole);
//
//        Role adminRole=new Role();
//        adminRole.setRoleName("Admin");
//        adminRole.setRoleDescription("this is admin");
//        roleRepository.save(adminRole);
//
//        User user = new User();
//        user.setUserName("rooba");
//        user.setEmailId("rooba@gmail.com");
//        user.setPassword("rooba@123");
//        Set<Role> role=new HashSet<>();
//        role.add(userRole);
//        user.setRole(role);
//        userRepository.save(user);
//
//        User aUser = new User();
//        aUser.setUserName("oviya");
//        aUser.setEmailId("oviya@gmail.com");
//        aUser.setPassword("oviya@123");
//        Set<Role> arole = new HashSet<>();
//        arole.add(adminRole);
//        aUser.setRole(arole);
//        userRepository.save(aUser);
//
//    }
}
