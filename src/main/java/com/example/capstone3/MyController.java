package com.example.capstone3;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        List<User> usersList = (List<User>) userRepository.findAll();
        System.out.println(principal.getName());
        model.addAttribute("usersList", usersList);
        model.addAttribute("username", principal.getName());
        model.addAttribute("principal", principal);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasAdminRole = authentication.getAuthorities().stream()
        .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        boolean hasTellerRole = authentication.getAuthorities().stream()
        .anyMatch(r -> r.getAuthority().equals("ROLE_TELLER"));
        System.out.println("has admin role is: " + hasAdminRole);
        System.out.println("has teller role is: " + hasTellerRole);
        if (hasAdminRole) {
            return "adminindex";
        }
        else if (hasTellerRole) {
        
            return "tellerindex";
        }
        return "errorindex";
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

        return "adduser";
    }

    @RequestMapping("/delete/{id}")
    public String delUser(@PathVariable("id") Long id, Model model) {
        userRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/save")
    public String saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/";
    }

}
