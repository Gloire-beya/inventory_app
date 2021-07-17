package com.glory.inventory.controller;

import com.glory.inventory.entity.Brand;
import com.glory.inventory.entity.Category;
import com.glory.inventory.repository.BrandRepository;
import com.glory.inventory.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BrandController {
    private BrandRepository brandRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public BrandController(BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/brands/new")
    public String showCreateNewBrandForm(Model model){
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("brand", new Brand());
        return "brand_form";
    }

    @PostMapping("/brands/save")
    public String saveBrand(Brand brand){
        brandRepository.save(brand);
        return "redirect:/brands";
    }

    @GetMapping("/brands")
    public String listBrand(Model model){
        List<Brand> brandList = brandRepository.findAll();
        model.addAttribute("brandList", brandList);
        return "brands";
    }

    @GetMapping("/brands/edit/{id}")
    public String showEditBrandForm(@PathVariable Integer id, Model model){
        List<Category> categoryList = categoryRepository.findAll();
        Brand brand = brandRepository.findById(id).get();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("brand", brand);
        return "brand_form";
    }

    @GetMapping("/brands/delete/{id}")
    public String deleteBrandById(@PathVariable Integer id){
        brandRepository.deleteById(id);
        return "redirect:/brands";
    }
}
