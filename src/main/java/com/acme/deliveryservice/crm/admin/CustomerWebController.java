package com.acme.deliveryservice.crm.admin;


import com.acme.deliveryservice.domain.Customer;
import com.acme.deliveryservice.repository.CustomerRepository;
import com.acme.deliveryservice.service.CustomerService;
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
    private final CustomerRepository customerRepository;

    @Autowired
    private final CustomerService customerService;
    @ManyToOne
    private Customer customerDAO;

    public CustomerWebController(CustomerRepository customerRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    public Customer getCustomerDAO() {
        return customerDAO;
    }

    public void setCustomerDAO(Customer customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping(value = "/display")
    public String getUsers(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("Customer", new Customer());

        return "crm-user/users";
    }

    @GetMapping(value = "/active")
    public String getActiveUsers(Model model) {
        List<Customer> customers = customerService.getActiveCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("Customer", new Customer());

        return "crm-user/users";
    }

    @GetMapping(value = "/inactive")
    public String getInactiveUsers(Model model) {
        List<Customer> customers = customerService.getInactiveCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("Customer", new Customer());

        return "crm-user/users";
    }


    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("customer", new Customer());
        return "crm-user/create";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable String id, Model msg) {

        Customer customer = customerRepository.findById(Long.valueOf(id))

                .orElseThrow(() -> new IllegalArgumentException("Customer Not Found:" + id));


        msg.addAttribute("customer", customer);

        customerDAO = customer;

        logger.info("Customer: {} {}", customerDAO.getId(), customerDAO.getFirstName());
        return "crm-user/edit";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("customer") Customer customer,
                             BindingResult bindingResult,
                             Model msg,
                             RedirectAttributes atts) {
        customer.setId(customerDAO.getId());
        logger.info("Customer: {}", customer);
        logger.info("Type: {}", customer.getActive());


        if (bindingResult.hasErrors()) {
            return "crm-user/edit";
        } else {
            if (customerRepository.save(customer) != null)

                atts.addFlashAttribute("message", "Customer updated successfully");
            else
                atts.addFlashAttribute("message", "Customer update failed.");

            return "redirect:/customer/display";
        }
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam Long id, Model msg) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer Not Found:" + id));
        logger.info("Customer: {}", id);
        customerRepository.delete(customer);

        msg.addAttribute("message", "Customer deleted successfully");
        return "redirect:/customer/display";
    }


    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("customer") Customer customer,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes atts) {
        if (bindingResult.hasErrors()) {
            return "crm-user/create";
        } else {
            if (customerService.save(customer) != null)
                atts.addFlashAttribute("message", "Customer created successfully");
            else
                atts.addFlashAttribute("message", "Customer creation failed.");

            return "redirect:/customer/display";
        }
    }
}

