package com.acme.deliveryservice.crm.admin;

import com.acme.deliveryservice.domain.Order;
import com.acme.deliveryservice.domain.OrderItem;
import com.acme.deliveryservice.repository.OrderItemRepository;
import com.acme.deliveryservice.repository.OrderRepository;
import com.acme.deliveryservice.service.OrderItemService;
import com.acme.deliveryservice.service.OrderService;
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
    private final OrderRepository orderRepository;

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final OrderItemRepository orderItemRepository;

    @Autowired
    private final OrderItemService orderItemService;

    @ManyToOne
    private Order orderDAO;

    public OrderWebController(OrderRepository orderRepository, OrderService orderService, OrderItemRepository orderItemRepository, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.orderItemRepository = orderItemRepository;
        this.orderItemService = orderItemService;
    }

    public Order getOrderDAO() {
        return orderDAO;
    }

    public void setOrderDAO(Order orderDAO) {
        this.orderDAO = orderDAO;
    }

    @GetMapping(value = "/display")
    public String getOrders(Model model) {
        List<Order> orders = orderRepository.findAll();

        model.addAttribute("orders", orders);
        model.addAttribute("Order", new Order());

        return "crm-order/orders";
    }

    @GetMapping("/complete")
    public String getCompletedOrders(Model model) {
        List<Order> orders = orderService.getCompletedOrders();

        model.addAttribute("orders", orders);
        model.addAttribute("Order", new Order());

        return "crm-order/orders";
    }

    @GetMapping("/incomplete")
    public String getInCompletedOrders(Model model) {
        List<Order> orders = orderService.getInCompletedOrders();

        model.addAttribute("orders", orders);
        model.addAttribute("Order", new Order());

        return "crm-order/orders";
    }


    @GetMapping("/details/{id}")
    public String getOrderDetaiils(@PathVariable String id, Model msg) {

        Order order = orderRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Order Not Found:" + id));
        msg.addAttribute("order", order);

        List<OrderItem> orderitems = orderItemService.findOrderByOrderId(Long.valueOf(id));
        msg.addAttribute("orderitems", orderitems);
        msg.addAttribute("orderitem", new OrderItem());
        return "crm-order/details";
    }

    @GetMapping("/create")
    public String createOrder(Model model) {
        model.addAttribute("order", new Order());
        return "crm-order/create";
    }

    @GetMapping("/edit/{id}")
    public String editOrder(@PathVariable String id, Model msg) {

        Order order = orderRepository.findById(Long.valueOf(id))

                .orElseThrow(() -> new IllegalArgumentException("Order Not Found:" + id));


        msg.addAttribute("order", order);

        orderDAO = order;

        logger.info("Order: {} ", orderDAO);
        return "crm-order/edit";
    }

    @PostMapping("/update")
    public String updateOrder(@Valid @ModelAttribute("order") Order order,
                              BindingResult bindingResult,
                              Model msg,
                              RedirectAttributes atts) {
        order.setId(orderDAO.getId());
        logger.info("Order: {}", order);

        if (bindingResult.hasErrors()) {
            return "crm-order/edit";
        } else {
            if (orderRepository.save(order) != null)

                atts.addFlashAttribute("message", "Customer updated successfully");
            else
                atts.addFlashAttribute("message", "Customer update failed.");

            return "redirect:/order/display";
        }
    }

    @PostMapping(value = "/delete")
    public String deleteProduct(@RequestParam Long id, Model msg) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order Not Found:" + id));
        logger.info("Order: {}", id);
        orderRepository.delete(order);

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
            return "crm-order/create";
        } else {
            if (orderRepository.save(order) != null)
                atts.addFlashAttribute("message", "Order created successfully");
            else
                atts.addFlashAttribute("message", "Order creation failed.");

            return "redirect:/order/display";
        }
    }
}
