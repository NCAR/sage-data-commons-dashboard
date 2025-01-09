package edu.sage.datacommonsdashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SocketStatusController {

    @GetMapping("/socket-status")
    public String index() {
        return "socket-status.html";
    }

}