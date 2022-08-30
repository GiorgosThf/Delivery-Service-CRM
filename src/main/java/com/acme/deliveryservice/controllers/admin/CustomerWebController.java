package com.acme.deliveryservice.controllers.admin;


import com.acme.deliveryservice.domain.Customer;
import com.acme.deliveryservice.service.tservice.CustomerService;
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
@RequestMapping("/customer")
public class CustomerWebController {

    public static final Logger logger = LoggerFactory.getLogger(CustomerWebController.class);

    @Autowired
    private final CustomerService customerService;
    @ManyToOne
    private Customer customerDAO;

    public CustomerWebController(CustomerService customerService) {
        this.customerService = customerService;
    }


    public Customer getCustomerDAO() {
        return customerDAO;
    }

    public void setCustomerDAO(Customer customerDAO) {
        this.customerDAO = customerDAO;
    }


    @GetMapping(value = "/display")
    public String getUsers(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("Customer", new Customer());

        return "admin/customer/users";
    }


    @GetMapping(value = "/active")
    public String getActiveUsers(Model model) {
        List<Customer> customers = customerService.getActiveCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("Customer", new Customer());

        return "admin/customer/users";
    }

    @GetMapping(value = "/inactive")
    public String getInactiveUsers(Model model) {
        List<Customer> customers = customerService.getInactiveCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("Customer", new Customer());

        return "admin/customer/users";
    }


    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("customer", new Customer());
        return "admin/customer/create";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable String id, Model msg) {

        Customer customer = customerService.get(Long.valueOf(id));

        //  .orElseThrow(() -> new IllegalArgumentException("Customer Not Found:" + id));


        msg.addAttribute("customer", customer);

        customerDAO = customer;

        logger.info("Customer: {} {}", customerDAO.getId(), customerDAO.getFirstName());
        return "admin/customer/edit";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("customer") Customer customer,
                             BindingResult bindingResult,
                             Model msg,
                             RedirectAttributes atts) {
        customer.setId(customerDAO.getId());
        logger.info("Customer: {}", customer);
        logger.info("Type: {}", customer.getActive());

        customerService.update(customer);
        if (bindingResult.hasErrors()) {
            atts.addFlashAttribute("message", "Customer update failed.");
            return "admin/customer/edit";
        } else {
                atts.addFlashAttribute("message", "Customer updated successfully");

            return "redirect:/customer/display";
        }
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam Long id, Model msg) {
        customerService.deleteById(id);
        logger.info("Customer: {}", id);
        msg.addAttribute("message", "Customer deleted successfully");
        return "redirect:/customer/display";
    }


    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("customer") Customer customer,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes atts) {
        if (bindingResult.hasErrors()) {
            return "admin/customer/create";
        } else {
            if (customerService.create(customer) != null)
                atts.addFlashAttribute("message", "Customer created successfully");
            else
                atts.addFlashAttribute("message", "Customer creation failed.");

            return "redirect:/customer/display";
        }
    }
}

