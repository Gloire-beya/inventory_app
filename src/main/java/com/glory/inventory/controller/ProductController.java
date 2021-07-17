package com.glory.inventory.controller;

import com.glory.inventory.entity.Category;
import com.glory.inventory.entity.Product;
import com.glory.inventory.repository.CategoryRepository;
import com.glory.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/products")
    public String showProductList(Model model) {
        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList", productList);
        return "products";
    }

    @GetMapping("/products/new")
    public String showNewProductFrom(Model model) {
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("product", new Product());
        model.addAttribute("categoryList", categoryList);
        return "product_form";
    }

    @PostMapping("/products/save")
    public String saveProduct(Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("products/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Integer id, Model model) {
        Product product = productRepository.findById(id).get();
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("product", product);
        return "product_form";
    }

    @GetMapping("products/delete/{id}")
    public String deleteProduct(@PathVariable Integer id, Model model) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
}