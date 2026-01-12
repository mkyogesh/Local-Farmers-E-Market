package com.example.localfarmersemarket.controller;

import com.example.localfarmersemarket.model.AttaFloursAndSooji;
import com.example.localfarmersemarket.model.Homepage;
import com.example.localfarmersemarket.model.User;
import com.example.localfarmersemarket.repository.HomeRepo;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private HomeRepo homeRepo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping({"/home"})
    public String homePage(Model model, Principal principal) {
        model.addAttribute("smartBasket", homeRepo.findAll());
        User loggedInUser = userRepo.findByUsername(principal.getName()).get();
        model.addAttribute("userRole", loggedInUser.getRole());
        return "home";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/smartBasket/add")
    public String smartBasketForm(Model model) {
        model.addAttribute("homePage", new Homepage());
        return "home_form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/smartBasket/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Homepage homepage = homeRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("Homepage", homepage);
        return "home_form";
    }

    @PostMapping("/smartBasket/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveAtta(
            @ModelAttribute("homePage") Homepage homepage,
            @RequestParam("image") MultipartFile imageFile) throws IOException {

        if (!imageFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            String uploadDir = System.getProperty("user.dir") +  "/smart-basket/";
            new File(uploadDir).mkdirs();
            Files.write(Paths.get(uploadDir + fileName), imageFile.getBytes());
            homepage.setSmart_basket_image(fileName);
        } else {
            Homepage existing = homeRepo.findById(homepage.getId()).orElse(null);
            if (existing != null) {
                homepage.setSmart_basket_image(existing.getSmart_basket_image());
            }
        }

        homeRepo.save(homepage);
        return "redirect:/home";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/home/delete/{id}")
    public String deleteSmart(@PathVariable Long id) {
        homeRepo.deleteById(id);
        return "redirect:/home";
    }
}