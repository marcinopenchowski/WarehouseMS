package com.wms.controller;

import com.wms.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/totalPrice")
    Double getTotalPrice() {
        return itemService.getTotalPrice();
    }

    @GetMapping("/totalCount")
    Integer getTotalCount() {
        return itemService.getTotalCount();
    }

    @GetMapping("/totalAverage")
    Double getTotalAverage() {
        return itemService.getTotalAverage();
    }
}
