package com.example.localfarmersemarket.controller;

import com.example.localfarmersemarket.model.FreshVegetables;
import com.example.localfarmersemarket.model.User;
import com.example.localfarmersemarket.repository.UserRepo;
import com.example.localfarmersemarket.repository.VeggiesRepo;
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
public class FreshVegetablesController {

    @Autowired
    private VeggiesRepo veggiesRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/shop/veggies")
    public String listVeggiesProducts(Model model, Principal principal) {
        model.addAttribute("veggiesProducts", veggiesRepo.findAll());
        User loggedInUser = userRepo.findByUsername(principal.getName()).get();
        model.addAttribute("userRole", loggedInUser.getRole());
        return "veggies";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/veggies/add")
    public String showAddForm(Model model) {
        model.addAttribute("veggiesProduct", new FreshVegetables());
        return "veggies_form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/veggies/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        FreshVegetables veggies = veggiesRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vegetable Id: " + id));
        model.addAttribute("veggiesProduct", veggies);
        return "veggies_form";
    }

    @PostMapping("/veggies/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveVeggies(
            @ModelAttribute("veggiesProduct") FreshVegetables veggies,
            @RequestParam("image") MultipartFile imageFile) throws IOException {

        if (!imageFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            String uploadDir = System.getProperty("user.dir") +  "/veggies-images/";
            new File(uploadDir).mkdirs();
            Files.write(Paths.get(uploadDir + fileName), imageFile.getBytes());
            veggies.setVeggies_image(fileName);
        } else {
            FreshVegetables existing = veggiesRepo.findById(veggies.getId()).orElse(null);
            if (existing != null) {
                veggies.setVeggies_image(existing.getVeggies_image());
            }
        }

        veggiesRepo.save(veggies);
        return "redirect:/shop/veggies";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/veggies/delete/{id}")
    public String deleteVeggies(@PathVariable Long id) {
        veggiesRepo.deleteById(id);
        return "redirect:/shop/veggies";
    }
}
