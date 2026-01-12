package com.example.localfarmersemarket.controller;

import com.example.localfarmersemarket.model.AttaFloursAndSooji;
import com.example.localfarmersemarket.model.RiceAndRiceProducts;
import com.example.localfarmersemarket.model.User;
import com.example.localfarmersemarket.repository.RiceRepo;
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
public class RiceController {

    @Autowired
    private RiceRepo riceRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/shop/rice")
    public String listRiceProducts(Model model, Principal principal) {
        model.addAttribute("riceProducts", riceRepo.findAll());
        User loggedInUser = userRepo.findByUsername(principal.getName()).get();
        model.addAttribute("userRole", loggedInUser.getRole());
        return "rice";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/rice/add")
    public String showAddForm(Model model) {
        model.addAttribute("riceProduct", new RiceAndRiceProducts());
        return "rice_form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/rice/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        RiceAndRiceProducts rice = riceRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rice Id: " + id));
        model.addAttribute("riceProduct", rice);
        return "rice_form";
    }

    @PostMapping("/rice/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveRice(
            @ModelAttribute("riceProduct") RiceAndRiceProducts rice,
            @RequestParam("image") MultipartFile imageFile) throws IOException {

        if (!imageFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            String uploadDir = System.getProperty("user.dir") +  "/rice-images/";
            new File(uploadDir).mkdirs();
            Files.write(Paths.get(uploadDir + fileName), imageFile.getBytes());
            rice.setRice_image(fileName);
        } else {
            RiceAndRiceProducts existing = riceRepo.findById(rice.getId()).orElse(null);
            if (existing != null) {
                rice.setRice_image(existing.getRice_image());
            }
        }

        riceRepo.save(rice);
        return "redirect:/shop/rice";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/rice/delete/{id}")
    public String deleteRice(@PathVariable Long id) {
        riceRepo.deleteById(id);
        return "redirect:/shop/rice";
    }
}
