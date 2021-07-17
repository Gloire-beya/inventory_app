package com.glory.inventory.controller;

import com.glory.inventory.entity.Category;
import com.glory.inventory.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CategoryController {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("categories")
    public String listCategories(Model model){
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);
        return "categories";
    }
    @GetMapping("categories/new")
    public String showCategoryNewForm(Model model){
        model.addAttribute("category", new Category());
        return"category_form";
    }
    @PostMapping("/categories/save")
    public String saveCategory(Category category){
        categoryRepository.save(category);
        return "redirect:/categories";
    }
}
