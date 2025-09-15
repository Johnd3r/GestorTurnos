package com.queueapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @GetMapping("/kiosk")
    public String kiosk() {
        return "kiosk";
    }

    @GetMapping("/operator")
    public String operator(@RequestParam(required = false) String role, Model model) {
        if (role == null || (!role.equals("A") && !role.equals("C"))) {
            role = "A";
        }
        model.addAttribute("role", role);
        model.addAttribute("roleName", role.equals("A") ? "ASESORIA" : "CAJA");
        return "operator";
    }

    @GetMapping("/hall")
    public String hall() {
        return "hall";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/kiosk";
    }
}