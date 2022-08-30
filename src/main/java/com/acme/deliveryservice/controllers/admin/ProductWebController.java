package com.acme.deliveryservice.controllers.admin;


import com.acme.deliveryservice.domain.Product;
import com.acme.deliveryservice.service.tservice.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductWebController {

    public static final Logger logger = LoggerFactory.getLogger(ProductWebController.class);


    @ManyToOne
    private Product productDAO;
    @Autowired
    private final ProductService productService;


    public ProductWebController(ProductService productService) {
        this.productService = productService;
    }

    public Product getProductDAO() {
        return productDAO;
    }

    public void setProductDAO(Product productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping(value = "/display")
    public String getProducts(Model model) {
        List<Product> products = productService.findAll();

        model.addAttribute("products", products);
        model.addAttribute("Product", new Product());

        return "admin/product/products";
    }


    @GetMapping("/inactive")
    public String getInActiveProducts(Model model) {
        List<Product> products = productService.getProductsInactive();

        model.addAttribute("products", products);
        model.addAttribute("Product", new Product());

        return "admin/product/products";
    }

    @GetMapping("/active")
    public String getActiveProducts(Model model) {
        List<Product> products = productService.getProductsActive();

        model.addAttribute("products", products);
        model.addAttribute("Product", new Product());

        return "admin/product/products";
    }


    @GetMapping("/create")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product/create";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable String id, Model msg) {

        Product product = productService.get(Long.valueOf(id));

        msg.addAttribute("product", product);

        productDAO = product;

        logger.info("Product: {} ", productDAO);
        return "admin/product/edit";
    }

    @PostMapping("/update")
    public String updateProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult,
                                Model msg,
                                RedirectAttributes atts) {
        product.setId(productDAO.getId());
        productService.update(product);
        logger.info("Product: {}", product);

        if (bindingResult.hasErrors()) {
            atts.addFlashAttribute("message", "Customer update failed.");
            return "admin/product/edit";
        } else {

                atts.addFlashAttribute("message", "Customer updated successfully");

            return "redirect:/product/display";
        }
    }

    @PostMapping(value = "/delete")
    public String deleteProduct(@RequestParam Long id, Model msg) {
        productService.deleteById(id);
        msg.addAttribute("message", "Product deleted successfully");
        return "redirect:/product/display";
    }


    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("product") Product product,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes atts) {
        productService.create(product);
        logger.info("Product: {} {} {}", product.getProductName(), product.getPrice(), product.getIsInStock());
        if (bindingResult.hasErrors()) {
            atts.addFlashAttribute("message", "Customer creation failed.");
            return "admin/product/create";
        } else {
                atts.addFlashAttribute("message", "Customer created successfully");

            return "redirect:/product/display";
        }
    }
}
