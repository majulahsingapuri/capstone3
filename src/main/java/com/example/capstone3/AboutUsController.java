package com.example.capstone3;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUsController {
  @GetMapping("/aboutus")
  public String aboutUs(Model model) {
    List<TeamMember> teamMembers = new ArrayList<>();

    teamMembers.add(
        new TeamMember(
            "Jordan",
            "Web Security / About Us Specialist",
            "green.png",
            "https://www.linkedin.com/in/jordanvckj/"));
    teamMembers.add(
        new TeamMember(
            "Amirul",
            "CSS Master / Bug Fixer",
            "blue.png",
            "https://www.linkedin.com/in/helmyrsln/"));
    teamMembers.add(
        new TeamMember(
            "Bhargav",
            "Mastermind / Daddy",
            "orange.png",
            "https://www.linkedin.com/in/bhargav-singapuri/"));
    teamMembers.add(
        new TeamMember(
            "Justin",
            "Spring Master / Teller Stuff",
            "pink.png",
            "https://www.linkedin.com/in/justinleejj/"));
    teamMembers.add(
        new TeamMember(
            "Beiji", "CSS Master / MLBB", "red.png", "https://www.linkedin.com/in/beiji-li/"));
    teamMembers.add(
        new TeamMember(
            "Wei Feng",
            "Transaction Master / Logic Analyst",
            "white.png",
            "https://www.linkedin.com/in/weifeng-huang-025369142/"));
    teamMembers.add(
        new TeamMember(
            "Roy",
            "Database Specialist / Luckin Hater",
            "yellow.png",
            "https://www.linkedin.com/in/roy-teoh/"));
    model.addAttribute("teamMembers", teamMembers);

    return "aboutus";
  }
}
