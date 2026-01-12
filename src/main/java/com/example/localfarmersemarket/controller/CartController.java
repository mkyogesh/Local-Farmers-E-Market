//package com.example.localfarmersemarket.controller;
//
//import com.example.localfarmersemarket.model.*;
//import com.example.localfarmersemarket.repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//public class CartController {
//
//    @Autowired private CartRepo cartRepo;
//    @Autowired private HomeRepo homeRepo;
//    @Autowired private AttaRepo attaRepo;
//    @Autowired private DalsRepo dalsRepo;
//    @Autowired private ExoticRepo exoticRepo;
//    @Autowired private FruitsRepo fruitsRepo;
//    @Autowired private VeggiesRepo veggiesRepo;
//    @Autowired private RiceRepo riceRepo;
//
//    // ================= ADD TO CART =================
//    @GetMapping("/add-to-cart/{category}/{id}")
//    public String addToCart(@PathVariable String category,
//                            @PathVariable Long id) {
//
//        Cart existing = cartRepo.findByCartIdAndCartCategory(id, category);
//        if (existing != null) {
//            existing.setCart_quantity(existing.getCart_quantity() + 1);
//            existing.setCart_amount(existing.getCart_quantity() * existing.getCart_price());
//            cartRepo.save(existing);
//            return "redirect:/cart";
//        }
//
//        Cart cart = new Cart();
//        cart.setCartId(id);
//        cart.setCartCategory(category);
//        cart.setCart_quantity(1);
//
//        switch (category) {
//
//            case "smart_basket" -> {
//                Homepage p = homeRepo.findById(id).orElseThrow();
//                cart.setCart_name(p.getSmart_basket_name());
//                cart.setCart_price(p.getSmart_basket_price());
//                cart.setCart_quantity(p.getSmart_basket_quantity());
//                cart.setCart_image("/smart-basket/" + p.getSmart_basket_image());
//            }
//
//            case "atta" -> {
//                AttaFloursAndSooji p = attaRepo.findById(id).orElseThrow();
//                cart.setCart_name(p.getAtta_name());
//                cart.setCart_price(p.getAtta_price());
//                cart.setCart_quantity_label(p.getAtta_quantity());
//                cart.setCart_image("/atta-images/" + p.getAtta_image());
//            }
//
//            case "dals" -> {
//                DalsAndPulses p = dalsRepo.findById(id).orElseThrow();
//                cart.setCart_name(p.getDals_name());
//                cart.setCart_price(p.getDals_price());
//                cart.setCart_quantity_label(p.getDals_quantity());
//                cart.setCart_image("/dals-images/" + p.getDals_image());
//            }
//
//            case "exotic" -> {
//                ExoticFruitsAndVeggies p = exoticRepo.findById(id).orElseThrow();
//                cart.setCart_name(p.getExotic_name());
//                cart.setCart_price(p.getExotic_price());
//                cart.setCart_quantity_label(p.getExotic_quantity());
//                cart.setCart_image("/exotic-images/" + p.getExotic_image());
//            }
//
//            case "fruits" -> {
//                FreshFruits p = fruitsRepo.findById(id).orElseThrow();
//                cart.setCart_name(p.getFruits_name());
//                cart.setCart_price(p.getFruits_price());
//                cart.setCart_quantity_label(p.getFruits_quantity());
//                cart.setCart_image("/fruits-images/" + p.getFruits_image());
//            }
//
//            case "veggies" -> {
//                FreshVegetables p = veggiesRepo.findById(id).orElseThrow();
//                cart.setCart_name(p.getVeggies_name());
//                cart.setCart_price(p.getVeggies_price());
//                cart.setCart_quantity_label(p.getVeggies_quantity());
//                cart.setCart_image("/veggies-images/" + p.getVeggies_image());
//            }
//
//            case "rice" -> {
//                RiceAndRiceProducts p = riceRepo.findById(id).orElseThrow();
//                cart.setCart_name(p.getRice_name());
//                cart.setCart_price(p.getRice_price());
//                cart.setCart_quantity_label(p.getRice_quantity());
//                cart.setCart_image("/rice-images/" + p.getRice_image());
//            }
//        }
//
//        cart.setCart_amount(cart.getCart_price() * cart.getCart_quantity());
//        cartRepo.save(cart);
//
//        return "redirect:/cart";
//    }
//
//    // ================= VIEW CART =================
//    @GetMapping("/cart")
//    public String viewCart(Model model) {
//        List<Cart> cartItems = cartRepo.findAll();
//        double total = cartItems.stream().mapToDouble(Cart::getCart_amount).sum();
//        model.addAttribute("cartItems", cartItems);
//        model.addAttribute("totalAmount", total);
//        return "cart";
//    }
//
//    // ================= QUANTITY =================
//    @GetMapping("/cart/increase/{id}")
//    public String increase(@PathVariable Long id) {
//        Cart cart = cartRepo.findById(id).orElseThrow();
//        cart.setCart_quantity(cart.getCart_quantity() + 1);
//        cart.setCart_amount(cart.getCart_quantity() * cart.getCart_price());
//        cartRepo.save(cart);
//        return "redirect:/cart";
//    }
//
//    @GetMapping("/cart/decrease/{id}")
//    public String decrease(@PathVariable Long id) {
//        Cart cart = cartRepo.findById(id).orElseThrow();
//        if (cart.getCart_quantity() > 1) {
//            cart.setCart_quantity(cart.getCart_quantity() - 1);
//            cart.setCart_amount(cart.getCart_quantity() * cart.getCart_price());
//            cartRepo.save(cart);
//        }
//        return "redirect:/cart";
//    }
//
//    // ================= DELETE =================
//    @GetMapping("/cart/delete/{id}")
//    public String delete(@PathVariable Long id) {
//        cartRepo.deleteById(id);
//        return "redirect:/cart";
//    }
//}
package com.example.localfarmersemarket.controller;

import com.example.localfarmersemarket.model.*;
import com.example.localfarmersemarket.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private HomeRepo homeRepo;

    @Autowired
    private AttaRepo attaRepo;

    @Autowired
    private DalsRepo dalsRepo;

    @Autowired
    private ExoticRepo exoticRepo;

    @Autowired
    private FruitsRepo fruitsRepo;

    @Autowired
    private RiceRepo riceRepo;

    @Autowired
    private VeggiesRepo veggiesRepo;

    @GetMapping("/add-to-cart-smart_basket/{id}")
    public String addToCartSmartBasket(@PathVariable Long id) {
        Homepage homepage = homeRepo.findById(id).orElse(null);
        if (homepage != null) {
            Cart existing = cartRepo.findByCartIdAndCartCategory(id, "smart_basket");
            if (existing != null) {
                existing.setCart_quantity(existing.getCart_quantity() + 1);
                existing.setCart_amount(existing.getCart_quantity() * homepage.getSmart_basket_price());
                cartRepo.save(existing);
            } else {
                Cart cart = new Cart();
                cart.setCartId(id);
                cart.setCartCategory("smart_basket");
                cart.setCart_name(homepage.getSmart_basket_name());
                cart.setCart_image(homepage.getSmart_basket_image());
                cart.setCart_quantity(1);
                cart.setCart_amount(homepage.getSmart_basket_price());
                cartRepo.save(cart);
            }
        }
        return "redirect:/home";
    }

    @GetMapping("/add-to-cart-atta/{id}")
    public String addToCartAtta(@PathVariable Long id) {
        AttaFloursAndSooji attaFloursAndSooji = attaRepo.findById(id).orElse(null);
        if (attaFloursAndSooji != null) {
            Cart existing = cartRepo.findByCartIdAndCartCategory(id, "atta");
            if (existing != null) {
                existing.setCart_quantity(existing.getCart_quantity() + 1);
                existing.setCart_amount(existing.getCart_quantity() * attaFloursAndSooji.getAtta_price());
                cartRepo.save(existing);
            } else {
                Cart cart = new Cart();
                cart.setCartId(id);
                cart.setCartCategory("atta");
                cart.setCart_name(attaFloursAndSooji.getAtta_name());
                cart.setCart_image(attaFloursAndSooji.getAtta_image());
                cart.setCart_quantity(1);
                cart.setCart_amount(attaFloursAndSooji.getAtta_price());
                cartRepo.save(cart);
            }
        }
        return "redirect:/shop/atta";
    }

    @GetMapping("/add-to-cart-dals/{id}")
    public String addToCartDals(@PathVariable Long id) {
        DalsAndPulses dalsAndPulses = dalsRepo.findById(id).orElse(null);
        if (dalsAndPulses != null) {
            Cart existing = cartRepo.findByCartIdAndCartCategory(id, "dals");
            if (existing != null) {
                existing.setCart_quantity(existing.getCart_quantity() + 1);
                existing.setCart_amount(existing.getCart_quantity() * dalsAndPulses.getDals_price());
                cartRepo.save(existing);
            } else {
                Cart cart = new Cart();
                cart.setCartId(id);
                cart.setCartCategory("dals");
                cart.setCart_name(dalsAndPulses.getDals_name());
                cart.setCart_image(dalsAndPulses.getDals_image());
                cart.setCart_quantity(1);
                cart.setCart_amount(dalsAndPulses.getDals_price());
                cartRepo.save(cart);

            }
        }
        return "redirect:/shop/dals";
    }

    @GetMapping("/add-to-cart-exotic/{id}")
    public String addToCartExotic(@PathVariable Long id) {
        ExoticFruitsAndVeggies exoticFruitsAndVeggies = exoticRepo.findById(id).orElse(null);
        if (exoticFruitsAndVeggies != null) {
            Cart existing = cartRepo.findByCartIdAndCartCategory(id, "exotic");
            if (existing != null) {
                existing.setCart_quantity(existing.getCart_quantity() + 1);
                existing.setCart_amount(existing.getCart_quantity() * exoticFruitsAndVeggies.getExotic_price());
                cartRepo.save(existing);
            } else {
                Cart cart = new Cart();
                cart.setCartId(id);
                cart.setCartCategory("exotic");
                cart.setCart_name(exoticFruitsAndVeggies.getExotic_name());
                cart.setCart_image(exoticFruitsAndVeggies.getExotic_image());
                cart.setCart_quantity(1);
                cart.setCart_amount(exoticFruitsAndVeggies.getExotic_price());
                cartRepo.save(cart);

            }
        }
        return "redirect:/shop/exotic";
    }

    @GetMapping("/add-to-cart-fruits/{id}")
    public String addToCartFruits(@PathVariable Long id) {
        FreshFruits freshFruits = fruitsRepo.findById(id).orElse(null);
        if (freshFruits != null) {
            Cart existing = cartRepo.findByCartIdAndCartCategory(id, "fruits");
            if (existing != null) {
                existing.setCart_quantity(existing.getCart_quantity() + 1);
                existing.setCart_amount(existing.getCart_quantity() * freshFruits.getFruits_price());
                cartRepo.save(existing);
            } else {
                Cart cart = new Cart();
                cart.setCartId(id);
                cart.setCartCategory("fruits");
                cart.setCart_name(freshFruits.getFruits_name());
                cart.setCart_image(freshFruits.getFruits_image());
                cart.setCart_quantity(1);
                cart.setCart_amount(freshFruits.getFruits_price());
                cartRepo.save(cart);

            }
        }
        return "redirect:/shop/fruits";
    }

    @GetMapping("/add-to-cart-veggies/{id}")
    public String addToCartVeggies(@PathVariable Long id) {
        FreshVegetables freshVegetables = veggiesRepo.findById(id).orElse(null);
        if (freshVegetables != null) {
            Cart existing = cartRepo.findByCartIdAndCartCategory(id, "veggies");
            if (existing != null) {
                existing.setCart_quantity(existing.getCart_quantity() + 1);
                existing.setCart_amount(existing.getCart_quantity() * freshVegetables.getVeggies_price());
                cartRepo.save(existing);
            } else {
                Cart cart = new Cart();
                cart.setCartId(id);
                cart.setCartCategory("veggies");
                cart.setCart_name(freshVegetables.getVeggies_name());
                cart.setCart_image(freshVegetables.getVeggies_image());
                cart.setCart_quantity(1);
                cart.setCart_amount(freshVegetables.getVeggies_price());
                cartRepo.save(cart);

            }
        }
        return "redirect:/shop/veggies";
    }

    @GetMapping("/add-to-cart-rice/{id}")
    public String addToCartRice(@PathVariable Long id) {
        RiceAndRiceProducts riceAndRiceProducts = riceRepo.findById(id).orElse(null);
        if (riceAndRiceProducts != null) {
            Cart existing = cartRepo.findByCartIdAndCartCategory(id, "rice");
            if (existing != null) {
                existing.setCart_quantity(existing.getCart_quantity() + 1);
                existing.setCart_amount(existing.getCart_quantity() * riceAndRiceProducts.getRice_price());
                cartRepo.save(existing);
            } else {
                Cart cart = new Cart();
                cart.setCartId(id);
                cart.setCartCategory("rice");
                cart.setCart_name(riceAndRiceProducts.getRice_name());
                cart.setCart_image(riceAndRiceProducts.getRice_image());
                cart.setCart_quantity(1);
                cart.setCart_amount(riceAndRiceProducts.getRice_price());
                cartRepo.save(cart);

            }
        }
        return "redirect:/shop/rice";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {

        List<Cart> cartItems = cartRepo.findAll();

        double totalAmount = cartItems.stream()
                .mapToDouble(Cart::getCart_amount)
                .sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);

        return "cart";
    }


    @GetMapping("/increase-quantity_smart_basket/{id}")
    public String increaseQuantitySmartBasket(@PathVariable Long id) {
        Cart item = cartRepo.findById(id).orElse(null);
        if (item != null) {
            item.setCart_quantity(item.getCart_quantity() + 1);
            Homepage homepage = homeRepo.findById(item.getCartId()).orElse(null);
            if (homepage != null) {
                item.setCart_amount(item.getCart_quantity() * homepage.getSmart_basket_price());
                cartRepo.save(item);
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/increase-quantity_Atta/{id}")
    public String increaseQuantityAtta(@PathVariable Long id) {
        Cart item = cartRepo.findById(id).orElse(null);
        if (item != null) {
            item.setCart_quantity(item.getCart_quantity() + 1);
            AttaFloursAndSooji attaFloursAndSooji = attaRepo.findById(item.getCartId()).orElse(null);
            if (attaFloursAndSooji != null) {
                item.setCart_amount(item.getCart_quantity() * attaFloursAndSooji.getAtta_price());
                cartRepo.save(item);
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/increase-quantity_dals/{id}")
    public String increaseQuantityDals(@PathVariable Long id) {
        Cart item = cartRepo.findById(id).orElse(null);
        if (item != null) {
            item.setCart_quantity(item.getCart_quantity() + 1);
            DalsAndPulses dalsAndPulses = dalsRepo.findById(item.getCartId()).orElse(null);
            if (dalsAndPulses != null) {
                item.setCart_amount(item.getCart_quantity() * dalsAndPulses.getDals_price());
                cartRepo.save(item);
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/increase-quantity_exotic/{id}")
    public String increaseQuantityExotic(@PathVariable Long id) {
        Cart item = cartRepo.findById(id).orElse(null);
        if (item != null) {
            item.setCart_quantity(item.getCart_quantity() + 1);
            ExoticFruitsAndVeggies exoticFruitsAndVeggies = exoticRepo.findById(item.getCartId()).orElse(null);
            if (exoticFruitsAndVeggies != null) {
                item.setCart_amount(item.getCart_quantity() * exoticFruitsAndVeggies.getExotic_price());
                cartRepo.save(item);
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/increase-quantity_fruits/{id}")
    public String increaseQuantityFruits(@PathVariable Long id) {
        Cart item = cartRepo.findById(id).orElse(null);
        if (item != null) {
            item.setCart_quantity(item.getCart_quantity() + 1);
            FreshFruits freshFruits = fruitsRepo.findById(item.getCartId()).orElse(null);
            if (freshFruits != null) {
                item.setCart_amount(item.getCart_quantity() * freshFruits.getFruits_price());
                cartRepo.save(item);
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/increase-quantity_veggies/{id}")
    public String increaseQuantityVeggies(@PathVariable Long id) {
        Cart item = cartRepo.findById(id).orElse(null);
        if (item != null) {
            item.setCart_quantity(item.getCart_quantity() + 1);
            FreshVegetables freshVegetables = veggiesRepo.findById(item.getCartId()).orElse(null);
            if (freshVegetables != null) {
                item.setCart_amount(item.getCart_quantity() * freshVegetables.getVeggies_price());
                cartRepo.save(item);
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/increase-quantity_rice/{id}")
    public String increaseQuantityRice(@PathVariable Long id) {
        Cart item = cartRepo.findById(id).orElse(null);
        if (item != null) {
            item.setCart_quantity(item.getCart_quantity() + 1);
            RiceAndRiceProducts riceAndRiceProducts = riceRepo.findById(item.getCartId()).orElse(null);
            if (riceAndRiceProducts != null) {
                item.setCart_amount(item.getCart_quantity() * riceAndRiceProducts.getRice_price());
                cartRepo.save(item);
            }
        }
        return "redirect:/cart";
    }


    @GetMapping("/ajax-increase-quantity_{category}/{id}")
    @ResponseBody
    public Map<String, Object> ajaxIncreaseQuantity(@PathVariable String category, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        Cart item = cartRepo.findById(id).orElse(null);
        if (item != null) {
            item.setCart_quantity(item.getCart_quantity() + 1);

            // Update amount based on category
            switch (category) {
                case "smart_basket":
                    Homepage homepage = homeRepo.findById(item.getCartId()).orElse(null);
                    if (homepage != null) {
                        item.setCart_amount(item.getCart_quantity() * homepage.getSmart_basket_price());
                    }
                    break;
                case "atta":
                    AttaFloursAndSooji atta = attaRepo.findById(item.getCartId()).orElse(null);
                    if (atta != null) {
                        item.setCart_amount(item.getCart_quantity() * atta.getAtta_price());
                    }
                    break;
                case "dals":
                    DalsAndPulses dals = dalsRepo.findById(item.getCartId()).orElse(null);
                    if (dals != null) {
                        item.setCart_amount(item.getCart_quantity() * dals.getDals_price());
                    }
                    break;
                case "exotic":
                    ExoticFruitsAndVeggies exotic = exoticRepo.findById(item.getCartId()).orElse(null);
                    if (exotic != null) {
                        item.setCart_amount(item.getCart_quantity() * exotic.getExotic_price());
                    }
                    break;
                case "fruits":
                    FreshFruits fruits = fruitsRepo.findById(item.getCartId()).orElse(null);
                    if (fruits != null) {
                        item.setCart_amount(item.getCart_quantity() * fruits.getFruits_price());
                    }
                    break;
                case "veggies":
                    FreshVegetables veggies = veggiesRepo.findById(item.getCartId()).orElse(null);
                    if (veggies != null) {
                        item.setCart_amount(item.getCart_quantity() * veggies.getVeggies_price());
                    }
                    break;
                case "rice":
                    RiceAndRiceProducts rice = riceRepo.findById(item.getCartId()).orElse(null);
                    if (rice != null) {
                        item.setCart_amount(item.getCart_quantity() * rice.getRice_price());
                    }
                    break;
            }

            cartRepo.save(item);

            List<Cart> allItems = cartRepo.findAll();
            double totalAmount = allItems.stream().mapToDouble(Cart::getCart_amount).sum();

            response.put("quantity", item.getCart_quantity());
            response.put("amount", item.getCart_amount());
            response.put("total", totalAmount);
        }

        return response;
    }

    @GetMapping("/ajax-decrease-quantity_{category}/{id}")
    @ResponseBody
    public Map<String, Object> ajaxDecreaseQuantity(@PathVariable String category, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        Cart item = cartRepo.findById(id).orElse(null);
        if (item != null && item.getCart_quantity() > 1) { // Prevent going below 1
            item.setCart_quantity(item.getCart_quantity() - 1);

            // Update amount based on category
            switch (category) {
                case "smart_basket":
                    Homepage homepage = homeRepo.findById(item.getCartId()).orElse(null);
                    if (homepage != null) {
                        item.setCart_amount(item.getCart_quantity() * homepage.getSmart_basket_price());
                    }
                    break;
                case "atta":
                    AttaFloursAndSooji atta = attaRepo.findById(item.getCartId()).orElse(null);
                    if (atta != null) {
                        item.setCart_amount(item.getCart_quantity() * atta.getAtta_price());
                    }
                    break;
                case "dals":
                    DalsAndPulses dals = dalsRepo.findById(item.getCartId()).orElse(null);
                    if (dals != null) {
                        item.setCart_amount(item.getCart_quantity() * dals.getDals_price());
                    }
                    break;
                case "exotic":
                    ExoticFruitsAndVeggies exotic = exoticRepo.findById(item.getCartId()).orElse(null);
                    if (exotic != null) {
                        item.setCart_amount(item.getCart_quantity() * exotic.getExotic_price());
                    }
                    break;
                case "fruits":
                    FreshFruits fruits = fruitsRepo.findById(item.getCartId()).orElse(null);
                    if (fruits != null) {
                        item.setCart_amount(item.getCart_quantity() * fruits.getFruits_price());
                    }
                    break;
                case "veggies":
                    FreshVegetables veggies = veggiesRepo.findById(item.getCartId()).orElse(null);
                    if (veggies != null) {
                        item.setCart_amount(item.getCart_quantity() * veggies.getVeggies_price());
                    }
                    break;
                case "rice":
                    RiceAndRiceProducts rice = riceRepo.findById(item.getCartId()).orElse(null);
                    if (rice != null) {
                        item.setCart_amount(item.getCart_quantity() * rice.getRice_price());
                    }
                    break;
            }

            cartRepo.save(item);

            // Calculate total cart amount
            List<Cart> allItems = cartRepo.findAll();
            double totalAmount = allItems.stream().mapToDouble(Cart::getCart_amount).sum();

            // Response for AJAX
            response.put("quantity", item.getCart_quantity());
            response.put("amount", item.getCart_amount());
            response.put("total", totalAmount);
        }

        return response;
    }



    @GetMapping("/cart/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        cartRepo.deleteById(id);
        return "redirect:/cart";
    }

    @GetMapping("/cart/confirmation")
    public String orderConfirmationPage(Model model) {
        model.addAttribute("message", "Order placed successfully!");
        return "order_success";
    }

}

