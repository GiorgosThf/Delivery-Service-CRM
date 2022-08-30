package com.acme.deliveryservice.controllers.admin;

import com.acme.deliveryservice.domain.Order;
import com.acme.deliveryservice.domain.OrderItem;
import com.acme.deliveryservice.service.tservice.OrderService;
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
@RequestMapping("/order")
public class OrderWebController {


    public static final Logger logger = LoggerFactory.getLogger(OrderWebController.class);

    @Autowired
    private final OrderService orderService;

    @ManyToOne
    private Order orderDAO;

    @ManyToOne
    private List<OrderItem> orderItemDAO;

    public OrderWebController(OrderService orderService) {

        this.orderService = orderService;

    }

    public Order getOrderDAO() {
        return orderDAO;
    }

    public void setOrderDAO(Order orderDAO) {
        this.orderDAO = orderDAO;
    }

    @GetMapping(value = "/display")
    public String getOrders(Model model) {
        List<Order> orders = orderService.findAll();

        model.addAttribute("orders", orders);
        model.addAttribute("Order", new Order());

        return "admin/order/orders";
    }

    @GetMapping("/complete")
    public String getCompletedOrders(Model model) {
        List<Order> orders = orderService.getCompletedOrders();

        model.addAttribute("orders", orders);
        model.addAttribute("Order", new Order());

        return "admin/order/orders";
    }

    @GetMapping("/incomplete")
    public String getInCompletedOrders(Model model) {
        List<Order> orders = orderService.getInCompletedOrders();

        model.addAttribute("orders", orders);
        model.addAttribute("Order", new Order());

        return "admin/order/orders";
    }


    @GetMapping("/details/{id}")
    public String getOrderDetails(@PathVariable String id, Model msg) {

        Order order = orderService.get(Long.valueOf(id));
        msg.addAttribute("order", order);

        return "admin/order/details";
    }

    @GetMapping("/create")
    public String createOrder(Model model) {
        model.addAttribute("order", new Order());
        return "admin/order/create";
    }

    @GetMapping("/edit/{id}")
    public String editOrder(@PathVariable String id, Model msg) {

        Order order = orderService.get(Long.valueOf(id));
        msg.addAttribute("order", order);

        orderDAO = order;

        logger.info("Order: {} ", orderDAO);
        return "admin/order/edit";
    }

    @PostMapping("/update")
    public String updateOrder(@Valid @ModelAttribute("order") Order order,
                              BindingResult bindingResult,
                              Model msg,
                              RedirectAttributes atts) {
        order.setId(orderDAO.getId());
        logger.info("Order: {}", order);
        orderService.update(order);

        if (bindingResult.hasErrors()) {
            atts.addFlashAttribute("message", "Order update failed.");
            return "admin/order/edit";
        } else {
            atts.addFlashAttribute("message", "Order updated successfully");
            return "redirect:/order/display";
        }
    }

    @PostMapping(value = "/delete")
    public String deleteProduct(@RequestParam Long id, Model msg) {
        orderService.deleteById(id);
        logger.info("Order: {}", id);
        msg.addAttribute("message", "Order deleted successfully");
        return "redirect:/order/display";
    }


    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("order") Order order,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes atts) {

        logger.info("Order: {} ", order);
        if (bindingResult.hasErrors()) {
            return "admin/order/create";
        } else {
            if (orderService.create(order) != null)
                atts.addFlashAttribute("message", "Order created successfully");
            else
                atts.addFlashAttribute("message", "Order creation failed.");

            return "redirect:/order/display";
        }
    }
}
