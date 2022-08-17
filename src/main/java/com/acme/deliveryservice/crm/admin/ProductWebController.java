package com.acme.deliveryservice.crm.admin;


import com.acme.deliveryservice.domain.Product;
import com.acme.deliveryservice.repository.ProductRepository;
import com.acme.deliveryservice.service.ProductService;
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

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ProductService productService;
    @ManyToOne
    private Product productDAO;

    public ProductWebController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
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
        List<Product> products = productRepository.findAll();

        model.addAttribute("products", products);
        model.addAttribute("Product", new Product());

        return "crm-product/products";
    }

    @GetMapping("/inactive")
    public String getInActiveProducts(Model model) {
        List<Product> products = productService.getProductsInactive();

        model.addAttribute("products", products);
        model.addAttribute("Product", new Product());

        return "crm-product/products";
    }

    @GetMapping("/active")
    public String getActiveProducts(Model model) {
        List<Product> products = productService.getProductsActive();

        model.addAttribute("products", products);
        model.addAttribute("Product", new Product());

        return "crm-product/products";
    }


    @GetMapping("/create")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "crm-product/create";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable String id, Model msg) {

        Product product = productRepository.findById(Long.valueOf(id))

                .orElseThrow(() -> new IllegalArgumentException("Customer Not Found:" + id));


        msg.addAttribute("product", product);

        productDAO = product;

        logger.info("Product: {} ", productDAO);
        return "crm-product/edit";
    }

    @PostMapping("/update")
    public String updateProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult,
                                Model msg,
                                RedirectAttributes atts) {
        product.setId(productDAO.getId());
        logger.info("Product: {}", product);

        if (bindingResult.hasErrors()) {
            return "crm-product/edit";
        } else {
            if (productRepository.save(product) != null)

                atts.addFlashAttribute("message", "Customer updated successfully");
            else
                atts.addFlashAttribute("message", "Customer update failed.");

            return "redirect:/product/display";
        }
    }

    @PostMapping(value = "/delete")
    public String deleteProduct(@RequestParam Long id, Model msg) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product Not Found:" + id));
        logger.info("Customer: {}", id);
        productRepository.delete(product);

        msg.addAttribute("message", "Product deleted successfully");
        return "redirect:/product/display";
    }


    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("product") Product product,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes atts) {

        logger.info("Product: {} {} {}", product.getProductName(), product.getPrice(), product.getIsInStock());
        if (bindingResult.hasErrors()) {
            return "crm-product/create";
        } else {
            if (productService.save(product) != null)
                atts.addFlashAttribute("message", "Customer created successfully");
            else
                atts.addFlashAttribute("message", "Customer creation failed.");

            return "redirect:/product/display";
        }
    }
}
