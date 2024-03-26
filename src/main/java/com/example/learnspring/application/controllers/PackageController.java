package com.example.learnspring.application.controllers;

import com.example.learnspring.application.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PackageController {
    private final PackageService packageService;

    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping("/package/example-jpa")
    public void getByUserId() throws Exception {
        this.packageService.exampleJpa();
    }
}
