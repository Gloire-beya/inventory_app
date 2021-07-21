package com.glory.inventory.repository;

import com.glory.inventory.entity.Role;
import com.glory.inventory.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void saveUserWithOneRole() {
        User user = new User("tebo@gmail.com", "tebo12345");
        Optional<Role> optionalRole1 = roleRepository.findById(2);

        if (optionalRole1.isPresent()) user.addRole(optionalRole1.get());

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getEmail()).isEqualTo("tebo@gmail.com");
    }

    @Test
    public void saveUserWithTwoRoles() {
        User user = new User("gloirebeyait@gmail.com", "glo12345");

        Optional<Role> optionalRole1 = roleRepository.findById(2);
        Optional<Role> optionalRole2 = roleRepository.findById(3);

        if (optionalRole1.isPresent()) user.addRole(optionalRole1.get());
        if (optionalRole2.isPresent()) user.addRole(optionalRole2.get());

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getEmail()).isEqualTo("gloirebeyait@gmail.com");

    }

    @Test
    public void assigningRoleToExistingUser() {
        User user = new User();
        Optional<User> userOptional = userRepository.findById(2);
        if (userOptional.isPresent()) user = userOptional.get();

        Optional<Role> optionalRole = roleRepository.findById(4);
        if (optionalRole.isPresent()) user.addRole(optionalRole.get());
        User savedUser = userRepository.save(user);

        assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    public void removingRoleFromExistingUser() {
        User user = new User();
        Optional<User> userOptional = userRepository.findById(2);
        if (userOptional.isPresent()) user = userOptional.get();

        Role role = new Role(2);
        user.removeRole(role);

        assertThat(user.getRoles()).doesNotContain(role);
    }

    @Test
    public void createNewUserWithNewRole() {
        Role role = new Role("Drive");
        User user = new User("danlukusa@gmail.com", "dan1458");
        user.addRole(role);

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getPassword()).isEqualTo("dan1458");
    }

    @Test
    public void fetchUser() {
        User user = new User();
        Optional<User> optionalUser = userRepository.findById(1);
        if (optionalUser.isPresent()) user = optionalUser.get();

    }

    @Test
    public void deleteUser(){
        userRepository.deleteById(2);

        Optional<User> optionalUser = userRepository.findById(2);

        assertThat(optionalUser).isEmpty();
    }
}