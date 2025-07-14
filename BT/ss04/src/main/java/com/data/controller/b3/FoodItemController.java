package com.data.controller.b3;

import com.data.entity.b3.FoodItem;
import com.data.service.b3.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/food")
public class FoodItemController {
    @Autowired
    private FoodItemService foodItemService;

    @GetMapping
    public String list(@RequestParam(defaultValue = "") String keyword,
                       @RequestParam(required = false) Long categoryId,
                       @RequestParam(defaultValue = "0") int page,
                       Model model) {
        Page<FoodItem> items = foodItemService.listFoodItems(keyword, categoryId, page, 5);
        model.addAttribute("foodItems", items);
        model.addAttribute("categories", foodItemService.getAllCategories());
        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryId", categoryId);
        return "b3/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("foodItem", new FoodItem());
        model.addAttribute("categories", foodItemService.getAllCategories());
        return "b3/form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute FoodItem foodItem) {
        foodItemService.save(foodItem);
        return "redirect:/food";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        FoodItem item = foodItemService.getById(id);
        model.addAttribute("foodItem", item);
        model.addAttribute("categories", foodItemService.getAllCategories());
        return "b3/form";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute FoodItem foodItem) {
        foodItemService.save(foodItem);
        return "redirect:/food";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        foodItemService.delete(id);
        return "redirect:/food";
    }
}
