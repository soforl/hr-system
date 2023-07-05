package ru.hackathon.sovcombankchallenge.user.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hackathon.sovcombankchallenge.user.exception.UserAlreadyExistsException;
import ru.hackathon.sovcombankchallenge.user.models.Role;
import ru.hackathon.sovcombankchallenge.user.models.CustomUser;
import ru.hackathon.sovcombankchallenge.user.repository.RoleRepository;
import ru.hackathon.sovcombankchallenge.user.repository.UserRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser customUser = userRepository.findByEmail(username);

        if (customUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.builder()
                .username(customUser.getUsername())
                .password(customUser.getPassword())
                .roles(customUser.getRole().getName())
                .build();
    }

    public CustomUser findUserByUsername(String username){
        CustomUser customUser = userRepository.findByEmail(username);
        if (customUser == null){
            throw new UsernameNotFoundException("User not found");
        }
        return customUser;
    }

    public boolean checkingUser(String username){
        CustomUser customUser = userRepository.findByEmail(username);
        if (customUser == null){
            return false;
        }
        return true;
    }

    public CustomUser createUser(String username, String password, String name, String phoneNumber, String roleName)
            throws Exception {
        var role = roleRepository.getRoleByName(roleName);
        if (role == null){
            role = new Role(roleName);
            roleRepository.save(role);
        }
        if (checkingUser(username)) {
            throw new UserAlreadyExistsException("User with this name already exists");
        }
        else {
            var newUser = new CustomUser(username, password, name, phoneNumber, role);
            return this.saveUser(newUser);
        }
    }

    public CustomUser getById(UUID userId) {
        Optional<CustomUser> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new CustomUser());
    }

    public List<CustomUser> getAll() {
        return userRepository.findAll();
    }

    public CustomUser saveUser(CustomUser customUser) {
        CustomUser customUserFromDB = userRepository.findByEmail(customUser.getUsername());

        if (customUserFromDB != null) {
            // TODO: throw exception
            return null;
        }
        customUser.setPassword(bCryptPasswordEncoder.encode(customUser.getPassword()));
        return userRepository.save(customUser);
    }

    public boolean deleteUser(UUID userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public void updateUserPhoneNumber(UUID userId, String phoneNumber){
        CustomUser customUser = this.getById(userId);
        customUser.setPhoneNumber(phoneNumber);
        userRepository.save(customUser);
    }

    public void updateUserEmailNumber(UUID userId, String email){
        CustomUser customUser = this.getById(userId);
        customUser.setUsername(email);
        userRepository.save(customUser);
    }
}
