package com.acme.deliveryservice.crm.admin;


import com.acme.deliveryservice.domain.Store;
import com.acme.deliveryservice.service.tservice.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.ManyToOne;
import java.util.List;
import java.util.stream.IntStream;

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

    @GetMapping(value = "/display2")
    public String getOrders(Model model) {
        List<Store> stores = storeService.findAll();

        model.addAttribute("stores", stores);
        model.addAttribute("Store", new Store());

        return "crm-store/stores";
    }

    @GetMapping(value = "/display")
    public String getAll(@RequestParam(defaultValue = "1") Integer pageNumber,
                         @RequestParam(defaultValue = "10") Integer pageSize, Model model) {


        List<Store> stores = storeService.getStoreList(pageNumber - 1, pageSize);
        model.addAttribute("stores", stores);

        int totalPages = stores.size();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "crm-store/stores";
    }

}
