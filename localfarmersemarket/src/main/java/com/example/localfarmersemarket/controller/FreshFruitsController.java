package com.example.localfarmersemarket.controller;

import com.example.localfarmersemarket.model.FreshFruits;
import com.example.localfarmersemarket.model.User;
import com.example.localfarmersemarket.repository.FruitsRepo;
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
public class FreshFruitsController {

    @Autowired
    private FruitsRepo fruitsRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/shop/fruits")
    public String listFruitsProducts(Model model, Principal principal) {
        model.addAttribute("fruitsProducts", fruitsRepo.findAll());
        User loggedInUser = userRepo.findByUsername(principal.getName()).get();
        model.addAttribute("userRole", loggedInUser.getRole());
        return "fruits";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/fruits/add")
    public String showAddForm(Model model) {
        model.addAttribute("fruitsProduct", new FreshFruits());
        return "fruits_form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/fruits/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        FreshFruits fruit = fruitsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid fruit Id: " + id));
        model.addAttribute("fruitsProduct", fruit);
        return "fruits_form";
    }

    @PostMapping("/fruits/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveFruits(
            @ModelAttribute("fruitsProduct") FreshFruits fruits,
            @RequestParam("image") MultipartFile imageFile) throws IOException {

        if (!imageFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            String uploadDir = System.getProperty("user.dir") +  "/fruits-images/";
            new File(uploadDir).mkdirs();
            Files.write(Paths.get(uploadDir + fileName), imageFile.getBytes());
            fruits.setFruits_image(fileName);
        } else {
            FreshFruits existing = fruitsRepo.findById(fruits.getId()).orElse(null);
            if (existing != null) {
                fruits.setFruits_image(existing.getFruits_image());
            }
        }

        fruitsRepo.save(fruits);
        return "redirect:/shop/fruits";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/fruits/delete/{id}")
    public String deleteFruit(@PathVariable Long id) {
        fruitsRepo.deleteById(id);
        return "redirect:/shop/fruits";
    }
}
