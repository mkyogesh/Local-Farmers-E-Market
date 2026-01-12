package com.example.localfarmersemarket.controller;

import com.example.localfarmersemarket.model.AttaFloursAndSooji;
import com.example.localfarmersemarket.model.User;
import com.example.localfarmersemarket.repository.AttaRepo;
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
public class AttaController {

    @Autowired
    private AttaRepo attaRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/shop/atta")
    public String listAttaProducts(Model model, Principal principal) {
        model.addAttribute("attaProducts", attaRepo.findAll());
        User loggedInUser = userRepo.findByUsername(principal.getName()).get();
        model.addAttribute("userRole", loggedInUser.getRole());
        return "atta";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/atta/add")
    public String showAddForm(Model model) {
        model.addAttribute("attaProduct", new AttaFloursAndSooji());
        return "atta_form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/atta/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        AttaFloursAndSooji atta = attaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("attaProduct", atta);
        return "atta_form";
    }

    @PostMapping("/atta/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveAtta(
            @ModelAttribute("attaProduct") AttaFloursAndSooji atta,
            @RequestParam("image") MultipartFile imageFile) throws IOException {

        if (!imageFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            String uploadDir = System.getProperty("user.dir") +  "/atta-images/";
            new File(uploadDir).mkdirs();
            Files.write(Paths.get(uploadDir + fileName), imageFile.getBytes());
            atta.setAtta_image(fileName);
        } else {
            AttaFloursAndSooji existing = attaRepo.findById(atta.getId()).orElse(null);
            if (existing != null) {
                atta.setAtta_image(existing.getAtta_image());
            }
        }

        attaRepo.save(atta);
        return "redirect:/shop/atta";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/atta/delete/{id}")
    public String deleteattaProducts(@PathVariable Long id) {
        attaRepo.deleteById(id);
        return "redirect:/shop/atta";
    }
}


