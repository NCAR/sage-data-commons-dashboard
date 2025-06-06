package edu.sage.datacommonsdashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("pageTitle", "Data Commons Dashboard");
        model.addAttribute("currentPage", "home");  // for navigation highlighting

        return "index";
    }
}
