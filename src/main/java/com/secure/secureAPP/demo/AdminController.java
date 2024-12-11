package com.secure.secureAPP.demo;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@Secured("ROLE_ADMIN")
public class AdminController {

    @GetMapping
    public String get() {
        return "GET::admin controller";
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN_CREATE') or hasRole('ROLE_ADMIN')")
    public String post() {
        return "POST::admin controller";
    }



    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN_UPDATE') or hasRole('ROLE_ADMIN')")
    public String put() {
        return "PUT::admin controller";
    }


    @DeleteMapping
    @PreAuthorize("hasAuthority('ADMIN_DELETE') or hasRole('ROLE_ADMIN')")
    public String delete() {
        return "DELETE::admin controller";
    }


}
