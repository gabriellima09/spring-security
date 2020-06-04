package com.example.security.seguranca.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExemploController {
    
    @GetMapping("/exemplo")
    public @ResponseBody String exemplo(){
        return "Teste";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/admin/principal")
    public @ResponseBody String principal(){
        return "Página ADMIN";
    }
}