package com.demo.mvc.controller;

import com.demo.mvc.entity.Product;
import com.demo.mvc.entity.ProductCategory;
import com.demo.mvc.service.ProductService;
import com.demo.mvc.service.ProductCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductCategoryService categoryService;

    public ProductController(ProductService productService, ProductCategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "product_form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("product") Product product) {

        // Set Category ID giữ nguyên theo form nhập
        // Không cần gọi categoryService nữa

        productService.save(product);
        return "redirect:/products";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        productService.delete(id);
        return "redirect:/products";
    }
}
