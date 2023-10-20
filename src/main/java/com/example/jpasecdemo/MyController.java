package com.example.jpasecdemo;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class MyController {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @RequestMapping("/")
    public String showMain(Model model, Principal principal) {
        List<Users> usersList = (List<Users>) userRepository.findAll();
        System.out.println(principal.getName());
        model.addAttribute("usersList", usersList);
        model.addAttribute("username", principal.getName());
        model.addAttribute("principal", principal);
        return "index";
    }

    @RequestMapping("/new")
    public String addUser(Users users, Model model) {
        model.addAttribute("users", users);
        List<Role> listRoles = (List<Role>) roleRepository.findAll();
        model.addAttribute("listRoles", listRoles);
        return "adduser";
    }

    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        Users users = userRepository.findById(id).orElseThrow();
        List<Role> listRoles = (List<Role>) roleRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("listRoles", listRoles);

        return "adduser";
    }

    @RequestMapping("/delete/{id}")
    public String delUser(@PathVariable("id") Long id, Model model) {
        userRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/save")
    public String saveUser(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userRepository.save(users);
        return "redirect:/";
    }

}
