package com.glory.inventory.repository;

import com.glory.inventory.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void addNewRole(){
        Role administrator = roleRepository.save(new Role("Administrator"));
        Role editor = roleRepository.save(new Role("Editor"));
        Role visitor = roleRepository.save(new Role("Visitor"));
        Role salesperson = roleRepository.save(new Role("Salesperson"));


        assertThat(administrator.getId()).isGreaterThan(0);
        assertThat(editor.getName()).isEqualTo("Editor");
        assertThat(visitor.getId()).isGreaterThan(0);
        assertThat(salesperson.getName()).isEqualTo("Salesperson");

    }


}