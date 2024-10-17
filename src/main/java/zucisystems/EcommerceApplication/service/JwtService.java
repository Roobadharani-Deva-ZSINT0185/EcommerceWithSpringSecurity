package zucisystems.EcommerceApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zucisystems.EcommerceApplication.configuration.JwtTokenUtil;
import zucisystems.EcommerceApplication.dao.JwtRequest;
import zucisystems.EcommerceApplication.dao.JwtResponse;
import zucisystems.EcommerceApplication.entity.User;
import zucisystems.EcommerceApplication.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName,userPassword);

        final UserDetails userDetails = loadUserByUsername(userName);

        String generatedToken = jwtTokenUtil.generateToken(userDetails);

        User user = userRepository.findById(userName).get();

        return new JwtResponse(user,generatedToken);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).get();

        if(user!=null){
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    getAuthoritize(user)
            );
        }else{
            throw new UsernameNotFoundException("username is not valid");
        }
    }

    private Set getAuthoritize(User user){
        Set authoritize = new HashSet();
        user.getRole().forEach(role->{
            authoritize.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });
        return authoritize;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        }catch (DisabledException e){
            throw new Exception("user is disabled");
        }catch (BadCredentialsException e){
            throw new Exception("bad credential form user");}
    }
}
