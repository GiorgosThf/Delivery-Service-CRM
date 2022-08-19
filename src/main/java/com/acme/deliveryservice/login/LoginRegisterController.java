package com.acme.deliveryservice.login;

import com.acme.deliveryservice.repository.AdminRepository;
import com.acme.deliveryservice.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class LoginRegisterController {
    private static final Logger logger = LoggerFactory.getLogger(LoginRegisterController.class);
    @Autowired
    private final AdminService adminService;
    @Autowired
    private final AdminRepository adminRepository;


    public LoginRegisterController(AdminService adminService, AdminRepository adminRepository) {
        this.adminService = adminService;
        this.adminRepository = adminRepository;

    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @PostMapping("/process-login")
    public String loginSuccessHandler() {
        logger.info("Login success handler");
        return "redirect:/home";
    }

}



