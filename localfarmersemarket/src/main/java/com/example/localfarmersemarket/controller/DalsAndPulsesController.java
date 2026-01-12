package com.example.localfarmersemarket.controller;

import com.example.localfarmersemarket.model.DalsAndPulses;
import com.example.localfarmersemarket.repository.DalsRepo;
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
public class DalsAndPulsesController {

    @Autowired
    private DalsRepo dalsRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/shop/dals")
    public String listDalsProducts(Model model, Principal principal) {
        model.addAttribute("dalsProducts", dalsRepo.findAll());

        if (principal != null) {
            userRepo.findByUsername(principal.getName())
                    .ifPresent(user -> model.addAttribute("userRole", user.getRole()));
        }
        return "dals";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dals/add")
    public String showAddDalsForm(Model model) {
        model.addAttribute("dalsProduct", new DalsAndPulses());
        return "dals_form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dals/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        DalsAndPulses dals = dalsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID:" + id));
        model.addAttribute("dalsProduct", dals);
        return "dals_form";
    }

    @PostMapping("/dals/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveDals(
            @ModelAttribute("dalsProduct") DalsAndPulses dals,
            @RequestParam("image") MultipartFile imageFile) throws IOException {

        if (!imageFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            String uploadDir = System.getProperty("user.dir") +  "/dals-images/";
            new File(uploadDir).mkdirs();
            Files.write(Paths.get(uploadDir + fileName), imageFile.getBytes());
            dals.setDals_image(fileName);
        } else {
            DalsAndPulses existing = dalsRepo.findById(dals.getId()).orElse(null);
            if (existing != null) {
                dals.setDals_image(existing.getDals_image());
            }
        }

        dalsRepo.save(dals);
        return "redirect:/shop/dals";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dals/delete/{id}")
    public String deleteDalsProducts(@PathVariable Long id) {
        dalsRepo.deleteById(id);
        return "redirect:/shop/dals";
    }
}
