package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UserService userService, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init(){
        ((UserServiceImpl) userService).setPasswordEncoder(passwordEncoder);
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        roleService.saveRole(adminRole);
        roleService.saveRole(userRole);

        List<Role> adminRoles = new ArrayList<>();
        adminRoles.add(adminRole);
        adminRoles.add(userRole);

        List<Role> userRoles = new ArrayList<>();
        userRoles.add(userRole);

        User adminUser = new User("admin", "100", "admin@mail.ru", adminRoles);
        User user = new User("user", "100", "user@mail.ru", userRoles);
        userService.saveUser(adminUser);
        userService.saveUser(user);
    }
}
