package com.acme.deliveryservice.controllers.admin;


import com.acme.deliveryservice.domain.Store;
import com.acme.deliveryservice.service.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.ManyToOne;
import java.util.List;

@Controller
@RequestMapping("/store")
public class StoreWebController {

    public static final Logger logger = LoggerFactory.getLogger(StoreWebController.class);
    ///Store Service Constructor

    @Autowired
    private final StoreService storeService;

    @ManyToOne
    private Store storeDAO;

    public StoreWebController(StoreService storeService) {
        this.storeService = storeService;
    }


    @GetMapping("/display")
    public String listStores(Model model) {
        List<Store> stores = storeService.findAll();
        model.addAttribute("stores", stores);
        model.addAttribute("Store", new Store());

        return "/admin/store/stores";
    }

    @GetMapping("/details/{id}")
    public String getProductsOfStore(@PathVariable String id, Model msg) {
        Store store = storeService.get(Long.valueOf(id));
        msg.addAttribute("store", store);

        return "admin/store/products";
    }


}
