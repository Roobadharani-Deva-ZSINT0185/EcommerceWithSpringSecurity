package zucisystems.EcommerceApplication.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zucisystems.EcommerceApplication.configuration.JwtTokenUtil;
import zucisystems.EcommerceApplication.dao.JwtRequest;
import zucisystems.EcommerceApplication.dao.JwtResponse;
import zucisystems.EcommerceApplication.entity.User;
import zucisystems.EcommerceApplication.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;



    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName,userPassword);

        final UserDetails userDetails = loadUserByUsername(userName);
        System.out.println(userDetails);

        String generatedToken = jwtTokenUtil.generateToken(userDetails);
        System.out.println(generatedToken);

        User user = userRepository.findByUserName(userName);

        return new JwtResponse(user.getUserName(),generatedToken);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);

        if(user!=null){


            Set<GrantedAuthority> authorities = user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                    .collect(Collectors.toSet());
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(), user.getPassword(), authorities);
        }else{
            throw new UsernameNotFoundException("username is not valid");
        }
    }


    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (DisabledException e){
            throw new Exception("user is disabled");
        }catch (BadCredentialsException e){
            throw new Exception("bad credential form user");}
    }
}
