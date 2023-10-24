package com.example.capstone3;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class MyController {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @RequestMapping("/")
    public String showMain(Model model, Principal principal) {
        List<User> usersList = (List<User>) userRepository.findAll();
        model.addAttribute("usersList", usersList);
        model.addAttribute("username", principal.getName());
        model.addAttribute("principal", principal);
        return "index";
    }

    @RequestMapping("/new")
    public String addUser(User user, Model model) {
        model.addAttribute("user", user);
        List<Role> listRoles = (List<Role>) roleRepository.findAll();
        model.addAttribute("listRoles", listRoles);
        return "adduser";
    }

    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow();
        List<Role> listRoles = (List<Role>) roleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("id", id);

        return "edituser";
    }
    
    @PostMapping("/edit/{id}")
    public String save_editUser(@ModelAttribute("user") User user,@RequestParam("userRole") Long role_id) {
        user.setUserRoles(roleRepository.findById(role_id).get());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String delUser(@PathVariable("id") Long id, Model model) {
        userRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/save")
    public String saveUser(User user, Model model,@RequestParam("userRole") Long id) {
        Optional<User> db_user = userRepository.findByUsername(user.getUsername());
        if (db_user.isPresent()) {
            model.addAttribute("user", user);
            List<Role> listRoles = (List<Role>) roleRepository.findAll();
            model.addAttribute("listRoles", listRoles);
            model.addAttribute("error", "User already exists");
            return "adduser";
        }
        user.setUserRoles(roleRepository.findById(id).get());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/";
    }

}
