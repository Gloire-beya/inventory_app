package com.glory.inventory.controller;

import com.glory.inventory.entity.Role;
import com.glory.inventory.entity.User;
import com.glory.inventory.repository.RoleRepository;
import com.glory.inventory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class UserController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/users")
    public String showUsersList(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        return "users";
    }

    @GetMapping("/users/new")
    public String showCreateUserForm(Model model) {
        List<Role> roleList = roleRepository.findAll();
        model.addAttribute("user", new User());
        model.addAttribute("roleList", roleList);
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("users/edit/{id}")
    public String showEditUserForm(@PathVariable Integer id, Model model) {
        User user = new User();

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) user = optionalUser.get();
        model.addAttribute("user", user);

        List<Role> roleList = roleRepository.findAll();
        if (roleList != null) model.addAttribute("roleList", roleList);

        return "user_form";
    }

    @GetMapping("users/delete/{id}")
    public String deleteUser(@PathVariable Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) userRepository.deleteById(id);

        return "redirect:/users";
    }
}

