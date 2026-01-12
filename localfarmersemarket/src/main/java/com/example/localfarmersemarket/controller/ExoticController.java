package com.example.localfarmersemarket.controller;

import com.example.localfarmersemarket.model.ExoticFruitsAndVeggies;
import com.example.localfarmersemarket.model.User;
import com.example.localfarmersemarket.repository.ExoticRepo;
import com.example.localfarmersemarket.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
public class ExoticController {

    @Autowired
    private ExoticRepo exoticRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/shop/exotic")
    public String listExoticProducts(Model model, Principal principal) {
        model.addAttribute("exoticProducts", exoticRepo.findAll());
        User loggedInUser = userRepo.findByUsername(principal.getName()).get();
        model.addAttribute("userRole", loggedInUser.getRole());
        return "exotic";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/exotic/add")
    public String showAddForm(Model model) {
        model.addAttribute("exoticProduct", new ExoticFruitsAndVeggies());
        return "exotic_form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/exotic/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ExoticFruitsAndVeggies exotic = exoticRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id: " + id));
        model.addAttribute("exoticProduct", exotic);
        return "exotic_form";
    }

    @PostMapping("/exotic/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveExotic(
            @ModelAttribute("exoticProduct") ExoticFruitsAndVeggies exotic,
            @RequestParam("image") MultipartFile imageFile) throws IOException {

        if (!imageFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            String uploadDir = System.getProperty("user.dir") +  "/exotic-images/";
            new File(uploadDir).mkdirs();
            Files.write(Paths.get(uploadDir + fileName), imageFile.getBytes());
            exotic.setExotic_image(fileName);
        } else {
            ExoticFruitsAndVeggies existing = exoticRepo.findById(exotic.getId()).orElse(null);
            if (existing != null) {
                exotic.setExotic_image(existing.getExotic_image());
            }
        }

        exoticRepo.save(exotic);
        return "redirect:/shop/exotic";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/exotic/delete/{id}")
    public String deleteExotic(@PathVariable Long id) {
        exoticRepo.deleteById(id);
        return "redirect:/shop/exotic";
    }
}
